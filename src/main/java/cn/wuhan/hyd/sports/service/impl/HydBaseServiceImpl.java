package cn.wuhan.hyd.sports.service.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 功能说明： 基础服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月23日 <br>
 */
public class HydBaseServiceImpl {

    /**
     * 将源实体列表转换为目标实体（历史表实体）列表
     *
     * @param sourceList  源实体列表
     * @param targetClass 目标实体类（如历史表实体类）
     * @param batchNo     批次号
     * @param <S>         源实体类型
     * @param <T>         目标实体类型（历史表实体类型）
     * @return 转换后的目标实体列表
     */
    public static <S, T> List<T> convert(
            Logger logger,
            List<S> sourceList,
            Class<T> targetClass,
            String batchNo) {

        try {
            return sourceList.stream()
                    .map(source -> {
                        try {
                            // 创建目标实体实例
                            T target = targetClass.getDeclaredConstructor().newInstance();
                            // 拷贝属性
                            BeanUtils.copyProperties(target, source);
                            // 设置版本号
                            setBatchNoToTarget(target, batchNo);
                            return target;
                        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                                 InstantiationException e) {
                            throw new RuntimeException(
                                    String.format("【批量保存】数据转换失败，异常信息：%s", e.getMessage()), e);
                        }
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("【批量保存】数据转换失败，批次号：{}，异常信息：", batchNo, e);
            throw new RuntimeException("【批量保存】数据转换失败，批次号：" + batchNo, e);
        }
    }

    /**
     * 通用保存并日志记录方法：复用时间统计逻辑，减少代码冗余
     *
     * @param dataList     待保存数据列表
     * @param saveFunction 保存操作的函数式接口（Repository的saveAll方法）
     * @param tableName    表名（用于日志）
     * @param batchNo      批次号
     * @param <T>          数据类型
     * @return 实际保存的数量
     */
    public <T> int saveAndLog(
            Logger logger,
            List<T> dataList,
            java.util.function.Function<List<T>, List<T>> saveFunction,
            String tableName,
            String batchNo) {
        long startTime = System.currentTimeMillis();
        List<T> savedList = saveFunction.apply(dataList);
        long costTime = System.currentTimeMillis() - startTime;

        // 日志包含批次号、表名、数据量、耗时，便于问题定位和性能分析
        logger.info("【批量保存】{}表保存完成，批次号：{}，保存数量：{}，耗时：{} ms",
                tableName, batchNo, savedList.size(), costTime);

        return savedList.size();
    }

    /**
     * 为目标实体设置批次号（通过反射调用setBatchNo方法）
     */
    private static <T> void setBatchNoToTarget(T target, String batchNo) {
        Method setBatchNo = null;
        try {
            // 调用目标实体的setBatchNo(String)方法
            setBatchNo = target.getClass().getMethod("setBatchNo", String.class);
        } catch (NoSuchMethodException ignored) {
        }
        if (setBatchNo != null) {
            try {
                setBatchNo.invoke(target, batchNo);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException("调用setBatchNo方法失败", e);
            }
        }

    }

    static BigDecimal addValue(BigDecimal total, String value) {
        if (value == null || value.trim().isEmpty()) {
            return total; // 空值视为0
        }
        try {
            return total.add(processPercentage(value.trim()));
        } catch (NumberFormatException e) {
            // 非数字格式视为0
            return total;
        }
    }

    /**
     * 计算单个字段的平均值
     *
     * @param total 总和
     * @param count 有效数据条数
     * @return 平均值（保留两位小数）
     */
    public static BigDecimal calculateSingleAverage(BigDecimal total, int count) {
        if (count <= 0) {
            return BigDecimal.ZERO;
        }
        return total.divide(new BigDecimal(count), 2, RoundingMode.HALF_UP);
    }

    /**
     * 处理单个百分比字符串（去除百分号、转换为数值）
     *
     * @param percentage 带百分号的字符串（如"12.50%"）
     * @return 处理结果（数值和有效性标识）
     */
    public static BigDecimal processPercentage(String percentage) {
        if (percentage == null || percentage.trim().isEmpty()) {
            return BigDecimal.ZERO;
        }

        try {
            // 去除百分号并转换为BigDecimal
            String numericStr = percentage.trim().replace("%", "");
            return new BigDecimal(numericStr);
        } catch (NumberFormatException e) {
            // 格式错误时返回0和无效标识
            return BigDecimal.ZERO;
        }
    }

    /**
     * 从Map中获取BigDecimal（支持自定义默认值）
     *
     * @param map          目标Map
     * @param key          键
     * @param defaultValue 默认值（键不存在/值异常时返回）
     * @return BigDecimal值
     */
    public static BigDecimal getBigDecimalFromMap(Map<String, Object> map, String key, BigDecimal defaultValue) {
        // 1. 校验Map和键
        if (map == null || !map.containsKey(key)) {
            return defaultValue;
        }

        Object value = map.get(key);
        if (value == null) {
            return defaultValue;
        }

        // 2. 类型转换（支持BigDecimal、Number、String）
        try {
            if (value instanceof BigDecimal) {
                return (BigDecimal) value;
            } else if (value instanceof Number) {
                // 处理Long、Double、Integer等Number子类
                return new BigDecimal(value.toString());
            } else if (value instanceof String) {
                // 处理字符串格式的数值（需确保格式合法，如"123.45"）
                return new BigDecimal((String) value);
            } else {
                // 不支持的类型，返回默认值
                return defaultValue;
            }
        } catch (Exception e) {
            // 转换失败（如String不是合法数值），返回默认值
            return defaultValue;
        }
    }

    /**
     * 从Map中获取BigDecimal（支持多种数值类型转换）
     * @param map 目标Map
     * @param key 键
     * @return BigDecimal值（若键不存在/值为null/类型不支持，返回BigDecimal.ZERO）
     */
    public static BigDecimal getBigDecimalFromMap(Map<String, Object> map, String key) {
        return getBigDecimalFromMap(map, key, BigDecimal.ZERO);
    }
}
