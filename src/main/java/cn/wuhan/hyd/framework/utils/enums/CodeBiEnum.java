package cn.wuhan.hyd.framework.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 功能说明：  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年07月12日 <br>
 */
@Getter
@AllArgsConstructor
public enum CodeBiEnum {

    /* 旧邮箱修改邮箱 */
    ONE(1, "旧邮箱修改邮箱"),

    /* 通过邮箱修改密码 */
    TWO(2, "通过邮箱修改密码");

    private final Integer code;
    private final String description;

    public static CodeBiEnum find(Integer code) {
        for (CodeBiEnum value : CodeBiEnum.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }

}
