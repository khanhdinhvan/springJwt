package com.khanhdv.spring.jwt.common.utils;

import com.google.gson.annotations.SerializedName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class Label {
    private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);

    private Label() {
    }

    public static String getLabel(Class<?> clazz, String code) throws NoSuchFieldException {
        Field fields = clazz.getDeclaredField(code);
        return fields.getAnnotation(SerializedName.class).value();
    }

}
