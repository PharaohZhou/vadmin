package com.codingmc.exception;

import org.springframework.util.StringUtils;

/**
 * @ClassName EntityExistException
 * @Description: TODO
 * @Author zhou
 * @Date 2020/4/28
 * @Version V1.0
 **/
public class EntityExistException extends RuntimeException {

    public EntityExistException(Class clazz, String field, String val) {
        super(EntityExistException.generateMessage(clazz.getSimpleName(), field, val));
    }

    private static String generateMessage(String entity, String field, String val) {
        return StringUtils.capitalize(entity)
                + " with " + field + " "+ val + " existed";
    }
}