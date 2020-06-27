package com.codingmc.exception;

import org.springframework.util.StringUtils;

/**
 * @ClassName EntityNotFoundException
 * @Description: TODO
 * @Author zhou
 * @Date 2020/4/28
 * @Version V1.0
 **/
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class clazz, String field, String val) {
        super(EntityNotFoundException.generateMessage(clazz.getSimpleName(), field, val));
    }

    private static String generateMessage(String entity, String field, String val) {
        return StringUtils.capitalize(entity)
                + " with " + field + " "+ val + " does not exist";
    }
}
