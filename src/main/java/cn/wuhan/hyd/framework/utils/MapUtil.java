package cn.wuhan.hyd.framework.utils;

import cn.wuhan.hyd.framework.annotation.DateFormat;
import cn.wuhan.hyd.framework.annotation.ExcelField;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.*;
import java.util.Map.Entry;

/**
 * 功能说明: Map工具类<br>
 * 系统版本: v1.0<br>
 * 开发人员: @author huadi <br>
 * 开发时间: 2025-08-10<br>
 */
public class MapUtil {

    private final static Logger logger = LoggerFactory.getLogger(MapUtil.class);

    public static <T> T map2ObjectIgnoreCase(Class<T> clazz, Map<String, ?> map) {
        T object = null;
        try {
            object = clazz.newInstance();
            if (map == null) return object;
            Map<String, Object> tMap = new HashMap<String, Object>();
            tMap.putAll(map);
            toObject(clazz, object, tMap, true);
        } catch (InstantiationException e) {
            logger.error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(), e);
        }
        return object;
    }

    public static <T> T map2Object(Class<T> clazz, Map<String, ?> map) {
        T object = null;
        try {
            object = clazz.newInstance();
            Map<String, Object> tMap = new HashMap<String, Object>();
            tMap.putAll(map);
            toObject(clazz, object, tMap, false);
        } catch (InstantiationException e) {
            logger.error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(), e);
        }
        return object;
    }

    private static void toObject(Class<?> clazz, Object object, Map<String, ?> map, boolean ignoreCase) {
        Field[] fields = clazz.getDeclaredFields();
        if (fields != null && fields.length > 0) {
            if (ignoreCase) {
                // 不区分大小写
                map = new CaseInsensitiveMap(map);
            }
            for (int i = 0; i < fields.length; i++) {
                try {
                    ExcelField excelField = fields[i].getAnnotation(ExcelField.class);
                    String fieldName = fields[i].getName();
                    if ("serialVersionUID".equals(fieldName)) {
                        continue;
                    } else if ("class".equals(fieldName)) {
                        continue;
                    }
                    Object fieldValue = map.get(fieldName);
                    if (fieldValue == null && excelField != null) {
                        // 适配别名
                        fieldValue = map.get(excelField.name());
                    }
                    if (fieldValue != null) {
                        if (Date.class.isAssignableFrom(fields[i].getType()) && fieldValue instanceof String) {
                            fieldValue = DateUtil.parse((String) fieldValue);
                        }
                        BeanUtils.setProperty(object, fieldName, fieldValue);
                    }
                } catch (IllegalAccessException e) {
                    logger.error(e.getMessage(), e);
                } catch (InvocationTargetException e) {
                    logger.error(e.getMessage(), e);
                } catch (SecurityException e) {
                    logger.error(e.getMessage(), e);
                } catch (ParseException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        if (clazz.getSuperclass() != null) {
            toObject(clazz.getSuperclass(), object, map, ignoreCase);
        }

    }

    @SuppressWarnings("rawtypes")
    public static List<Map<String, Object>> toMapList(Collection collection) {
        List<Map<String, Object>> retList = new ArrayList<Map<String, Object>>();
        if (collection != null && !collection.isEmpty()) {
            for (Object object : collection) {
                Map<String, Object> map = new HashMap<String, Object>();
                toMap(object.getClass(), object, map);
                retList.add(map);
            }
        }
        return retList;
    }

    /**
     * 将对象转成&lt;String, Object&gt;，支持别名，支持日期格式化(DateFormat注解)
     *
     * @param object
     * @return
     */
    public static Map<String, Object> toMap(Object object) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (object == null) {
            return map;
        }

        if (object instanceof Map) {
            return (Map<String, Object>) object;
        }
        toMap(object.getClass(), object, map);
        return map;
    }

    /**
     * 将对象转成Map&lt;String, String&gt;，支持别名，支持日期格式化(DateFormat注解)
     *
     * @param object
     * @return
     */
    public static Map<String, String> toMapString(Object object) {
        Map<String, Object> map = toMap(object);
        Map<String, String> mapString = new HashMap<String, String>();
        for (Entry<String, Object> entry : map.entrySet()) {
            mapString.put(entry.getKey(), String.valueOf(entry.getValue()));
        }
        return mapString;
    }

    private static void toMap(Class<?> clazz, Object object, Map<String, Object> map) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            if ("serialVersionUID".equals(fieldName)) {
                continue;
            } else if ("class".equals(fieldName)) {
                continue;
            }
            try {
                Object value = PropertyUtils.getProperty(object, fieldName);
                // 日期格式化
                if (value instanceof Date) {
                    DateFormat dateFormat = field.getAnnotation(DateFormat.class);
                    value = DateUtil.format((Date) value, dateFormat == null ? DateUtil.DATE_FORMAT : dateFormat.value());
                }

                // 如果value为null，则设置成空字符串。否则返回到前台的是null字符串
                map.put(fieldName, value == null ? "" : value);
            } catch (IllegalAccessException e) {
                logger.error(e.getMessage(), e);
            } catch (InvocationTargetException e) {
                logger.error(e.getMessage(), e);
            } catch (NoSuchMethodException e) {
                // 忽略get方法不存在错误
                // logger.error(e.getMessage(), e);
            }
        }
        if (clazz.getSuperclass() != null) {
            toMap(clazz.getSuperclass(), object, map);
        }
    }

    /**
     * 将后面一个Map合并到前面一个Map中，合并过程中如果有重复的将忽略
     *
     * @return
     */
    public static Map<String, Object> mergeMap(Map<String, Object> mainMap, Map<String, Object> subMap) {
        for (Entry<String, Object> entry : subMap.entrySet()) {
            if (!mainMap.containsKey(entry.getKey())) {
                mainMap.put(entry.getKey(), entry.getValue());
            }
        }
        return mainMap;
    }

    /**
     * clone一个map，并按key进行过滤
     *
     * @param from
     * @param filter
     * @return
     */
    public static Map<String, Object> cloneByFilter(Map<String, ?> from, String[] filter) {
        Map<String, Object> target = new HashMap<String, Object>();
        for (String item : filter) {
            target.put(item, ObjectUtils.defaultIfNull(from.get(item), ""));
        }
        return target;
    }

    /**
     * clone一个List，并按key进行过滤
     *
     * @param from
     * @param filter
     * @return
     */
    public static List<Map<String, Object>> cloneByFilter(List<Map<String, ?>> from, String[] filter) {
        Map<String, Object> target = new HashMap<String, Object>();
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (Map<String, ?> map : from) {
            for (String item : filter) {
                target.put(item, ObjectUtils.defaultIfNull(map.get(item), ""));
                result.add(target);
            }
        }
        return result;
    }

    /**
     * 把对象转成Map&lt;String, Object&gt;，不支持别名
     *
     * @param obj
     * @return
     * @author yejg
     */
    public static Map<String, Object> toMapIgnoreAlias(Object obj) {
        Map<String, Object> objMap = new HashMap<String, Object>();
        if (obj == null) {
            return objMap;
        }

        if (obj instanceof Map) {
            return (Map<String, Object>) obj;
        }

        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            String key = fields[i].getName();

            Object value = null;
            try {
                value = PropertyUtils.getProperty(obj, key);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                logger.error(e.getMessage(), e);
            }

            if (value != null && StringUtils.isNotBlank(String.valueOf(value))) {
                objMap.put(key, value);
            }
        }
        return objMap;
    }
}
