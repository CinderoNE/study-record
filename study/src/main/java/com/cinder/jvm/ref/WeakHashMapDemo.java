package com.cinder.jvm.ref;

import java.util.HashMap;
import java.util.WeakHashMap;

public class WeakHashMapDemo {
    public static void main(String[] args) {
        // myHashMap();
        myWeakHashMap();
    }

    private static void myWeakHashMap() {
        WeakHashMap<Integer, String> weakHashMap = new WeakHashMap<>();
        Integer key = Integer.valueOf(128);
        String value = "weakHashMap";
        weakHashMap.put(key,value);

        System.out.println(weakHashMap);
        key = null;
        System.out.println(weakHashMap);

        System.gc();
        System.out.println(weakHashMap+"\t"+weakHashMap.size());
    }

    private static void myHashMap() {
        HashMap<Integer, String> map = new HashMap<>();
        Integer key = Integer.valueOf(128);
        String value = "hashmap";
        map.put(key,value);

        System.out.println(map);
        key = null;
        System.out.println(map);
        System.gc();
        System.out.println(map+"\t"+map.size());
    }
}
