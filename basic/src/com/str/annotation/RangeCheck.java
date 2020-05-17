package com.str.annotation;

import java.lang.reflect.Field;

public class RangeCheck {
    public void check(Person person) throws IllegalAccessException {
        // 遍历所有Field
        for (Field field : person.getClass().getFields()) {
            // 获取Field定义的@Range
            Range range = field.getAnnotation(Range.class);
            // 如果@Range存在
            if (range != null) {
                // 获取Field的值
                Object value = field.get(person);
                // 如果值是String
                if (value instanceof String) {
                    String s = (String) value;
                    // 判断值是否满足@Range的min/max
                    if (s.length() < range.min() || s.length() > range.max()) {
                        throw new IllegalArgumentException("Invalid field: " +
                                field.getName());
                    }
                }
                // 如果值是int
                if (value instanceof Integer) {
                    int val = (int) value;
                    if (val < range.min() || val > range.max()) {
                        throw new IllegalArgumentException("Invalid field: " +
                                field.getName());
                    }
                }
            }
        }
    }


}
