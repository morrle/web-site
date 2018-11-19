package com.morrle.units;

import io.vertx.core.json.JsonObject;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author morrle
 * @date 2018/11/04 17:24
 * javaBean和jsonObject转换工具类
 **/
public class BeanUtils {

    private static final String BEAN_ID = "id";
    private static final String DB_ID = "_id";

    private BeanUtils() {
    }


    public static JsonObject beanToJson(Object instance) {
        JsonObject json = new JsonObject();
        Class clazz = instance.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                String fieldName = field.getName();
                PropertyDescriptor pd = new PropertyDescriptor(fieldName, clazz);
                Method readMethod = pd.getReadMethod();
                json.put(BEAN_ID.equals(fieldName) ? DB_ID : fieldName, readMethod.invoke(instance));
            }
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static <T> T jsonToBean(JsonObject json, Class<T> clazz) {
        try {
            T t = clazz.newInstance();
            for (String fieldName : json.fieldNames()) {
                PropertyDescriptor pd = new PropertyDescriptor(DB_ID.equals(fieldName) ? BEAN_ID : fieldName, clazz);
                Method writeMethod = pd.getWriteMethod();
                writeMethod.invoke(t, json.getValue(fieldName));
            }
            return t;
        } catch (InstantiationException | IllegalAccessException | IntrospectionException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

}
