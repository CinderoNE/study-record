package com.cinder.spring.simple.ioc;

import lombok.Data;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Cinder
 * @Description: ioc解决循环依赖的思想
 * @Date create in 15:44 2020/7/7/007
 * @Modified By:
 */
public class SimpleIoc {
    private static Map<String,Object> singletonObjects = new ConcurrentHashMap<>(128);

    @SneakyThrows
    private static <T> T getBean(Class<T> type) {
        String typeName = type.getSimpleName().toLowerCase();
        if(singletonObjects.containsKey(typeName)){
            return (T) singletonObjects.get(typeName);
        }
        T instance = type.getDeclaredConstructor().newInstance();
        singletonObjects.put(typeName, instance);
        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Class<?> fieldType = field.getType();
            String fieldName = fieldType.getSimpleName().toLowerCase();
            field.set(instance, singletonObjects.containsKey(fieldName)?
                    singletonObjects.get(fieldName):getBean(fieldType));
        }
        return instance;
    }

    public static void main(String[] args){
        Class[] classes = {A.class,B.class};
        for (Class clazz : classes) {
            //初始化所有bean
            getBean(clazz);
        }

        System.out.println(getBean(A.class).getB().equals(getBean(B.class)));
        System.out.println(getBean(B.class).getA().equals(getBean(A.class)));
    }


}

@Data
class A{
    private B b;
}
@Data
class B{
    private A a;
}
