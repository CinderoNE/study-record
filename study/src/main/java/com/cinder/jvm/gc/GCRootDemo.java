package com.cinder.jvm.gc;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * java中可以作为GC Roots的对象有
 *
 * 虚拟机栈（栈帧中的局部变量区，也叫做局部变量表）中引用的对象。
 * 方法区中的类静态属性引用的对象。
 * 方法区中常量引用的对象
 * 本地方法栈中JNI（Native方法）引用的对象。
 */
public class GCRootDemo {
    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(null, "com/cinder");
        Set<Map.Entry<String, String>> set = hashMap.entrySet();
        Iterator<Map.Entry<String, String>> iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next().getKey());
        }

    }
}
