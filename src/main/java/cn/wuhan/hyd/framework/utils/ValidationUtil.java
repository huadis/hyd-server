package cn.wuhan.hyd.framework.utils;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ObjectUtil;
import cn.wuhan.hyd.framework.exception.BadRequestException;

/**
 * 功能说明： 验证工具 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年07月12日 <br>
 */
public class ValidationUtil {

    /**
     * 验证空
     */
    public static void isNull(Object obj, String entity, String parameter , Object value){
        if(ObjectUtil.isNull(obj)){
            String msg = entity + " 不存在: "+ parameter +" is "+ value;
            throw new BadRequestException(msg);
        }
    }

  /**
   * 验证是否为邮箱
   */
  public static boolean isEmail(String email) {
    return Validator.isEmail(email);
  }
}
