package com.cinder.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * jdk动态代理
 */
public class JDKProxy implements InvocationHandler {

    //目标对象
    Object targetObject = null;

    public Object newProxy(Object targetObject){
        this.targetObject = targetObject;
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
                targetObject.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //检查权限
        check();
        Object obj = method.invoke(targetObject, args);
        return obj;
    }

    public void  check(){
        System.out.println("JDKProxy.check 检查权限");
    }
}
