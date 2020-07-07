package com.cinder.mianshi;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Cinder
 * @Description:jdk1.8 chm死循环
 * @Date create in 15:17 2020/6/17/017
 * @Modified By:
 */
public class CHM {
    public static void main(String[] args) {
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.computeIfAbsent("cinder", s -> {
            return concurrentHashMap.computeIfAbsent(s, String::toUpperCase);
        });
        String cinder = concurrentHashMap.get("cinder");
        System.out.println(cinder);
    }
}
