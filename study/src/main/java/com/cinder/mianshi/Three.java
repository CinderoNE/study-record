package com.cinder.mianshi;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Cinder
 * @Description:
 * @Date create in 19:53 2020/6/4/004
 * @Modified By:
 */
public class Three implements ThreeInterface{
    public static void main(String[] args) {
        Three three = new Three();
        ThreeInterface threeInterface = (ThreeInterface)
                Proxy.newProxyInstance(three.getClass().getClassLoader(),
                        three.getClass().getInterfaces(),new ProxyObject(three));
        threeInterface.testProxy();
        SingletonObject instance = SingletonObject.getInstance();
        SingletonObject instance2 = SingletonObject.getInstance();
        System.out.println(instance.equals(instance2));
    }

    @Override
    public void testProxy(){
        System.out.println("Three.testProxy");
    }


}


class SingletonObject {

    private SingletonObject(){

    }

    static class Inner{
        private static SingletonObject singletonObject = new SingletonObject();
    }

    public static SingletonObject getInstance(){
        return Inner.singletonObject;
    }
}


interface ThreeInterface{
    void testProxy();
}

class ProxyObject implements InvocationHandler{

    private Object target;

    public ProxyObject(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(proxy.getClass());
        System.out.println("进行代理");
        Object invoke = method.invoke(target, args);
        System.out.println("代理之后");
        return invoke;
    }
}
