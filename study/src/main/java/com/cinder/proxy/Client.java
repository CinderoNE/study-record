package com.cinder.proxy;

import com.cinder.mybatis.bean.Department;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class Client {
    public static void main(String[] args) throws Exception {
        JDKProxy jdkProxy = new JDKProxy();
        UserManager userManager = (UserManager) jdkProxy.newProxy(new UserManagerImpl());
        userManager.addUser("com/cinder", "com/cinder");
        //
        // CGLibProxy cgLibProxy = new CGLibProxy();
        // UserManager userManagerCGLib = (UserManager) cgLibProxy.newProxy(new UserManagerImpl());
        // userManagerCGLib.addUser("bat","bat");

        Class<?> aClass = Class.forName("com.cinder.mybatis.bean.Department");
        System.out.println(aClass.getName());
        Field[] fields = aClass.getDeclaredFields();
        Constructor<?> constructor = aClass.getConstructor(int.class,String.class);
        Department dept = (Department) constructor.newInstance(1,"dept");
        System.out.println(dept);
        for (Field field : fields) {
            // field.setAccessible(true);
            System.out.println(field.getType());
        }


    }
}
