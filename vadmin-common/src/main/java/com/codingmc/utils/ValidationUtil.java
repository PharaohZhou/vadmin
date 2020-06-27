package com.codingmc.utils;

import cn.hutool.core.util.ObjectUtil;
import com.codingmc.exception.BadRequestException;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;

/**
 * 验证工具
 * @ClassName ValidationUtil
 * @Description: TODO
 * @Author zhou
 * @Date 2020/4/29
 * @Version V1.0
 **/
public class ValidationUtil {

    /**
     * 验证空
     * @param obj
     * @param entity
     * @param paramter
     * @param value
     */
    public static void isNull(Object obj,
                              String entity,
                              String parameter,
                              Object value) {
        if (ObjectUtil.isNull(obj)) {
            String msg = entity + " 不存在: "+ parameter +" is "+ value;
            throw new BadRequestException(msg);
        }
    }

    /**
     * 验证是否为邮箱
     */
    public static boolean isEmail(String email) {
        return new EmailValidator().isValid(email, null);
    }
}
