package com.cinder.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGlib动态代理
 */
public class CGLibProxy implements MethodInterceptor {

    //目标对象
    Object targetObject = null;

    public Object newProxy(Object targetObject){
        this.targetObject = targetObject;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetObject.getClass());
        enhancer.setCallback(this);
        Object obj = enhancer.create();
        return obj;
    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if (method.getName().equals("addUser")){
            check();
        }
        Object obj = method.invoke(targetObject, objects);
        return obj;
    }

    public void check(){
        System.out.println("CGLibProxy.check权限检查");
    }
}
