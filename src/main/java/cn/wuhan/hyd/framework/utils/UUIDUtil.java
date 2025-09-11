package cn.wuhan.hyd.framework.utils;

import java.util.UUID;

/**
 * 功能说明： ID工具 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
public class UUIDUtil {

    public static String getBatchNo() {
        return "BATCH_" + DateUtil.getTodayStr();
    }

    public static String getUUID() {
        return "BATCH_" + UUID.randomUUID().toString().replace("-", "").substring(0, 16);
    }
}
