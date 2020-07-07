package com.cinder.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Cinder
 * @Description
 * @Date create in 17:05 2020/5/16/016
 * @Modified By:
 */
public class LRUCacheByLinkedHashMap extends LinkedHashMap {

    private final int CACHE_SIZE;


    public LRUCacheByLinkedHashMap(int cacheSize){
        //true表示按照访问顺序进行排序
        super((int) (Math.ceil(cacheSize)/0.75)+1,0.75f,true);
        CACHE_SIZE = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        //当MAP中容量大于CACHE_SIZE时，删除最老的数据
        return size() > CACHE_SIZE;
    }
}
