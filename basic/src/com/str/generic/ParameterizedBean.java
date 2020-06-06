package com.str.generic;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class ParameterizedBean {
    List<String> list1;
    List list2;
    Map<String, Long> map1;
    Map map2;

    public static void main(String[] args) {
        Field[] fields = ParameterizedBean.class.getDeclaredFields();
        for (Field f : fields) {
            Boolean isPType = f.getGenericType() instanceof ParameterizedType;
            //System.out.println(f.getName() + ": " + isPType);
            if ( isPType ) {
                ParameterizedType pType = (ParameterizedType) f.getGenericType();
                System.out.print("变量：" + f.getName() + "\t");
                Type[] types = pType.getActualTypeArguments();
                for (Type t : types) {
                    System.out.print("参数类型：" + t.getTypeName() + " ");
                }
                System.out.println();
            }
        }
    }
}
