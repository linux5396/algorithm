package com.linxu.algorithm.data_struct;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author linxu
 * @date 2020/3/1
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class LRU<K, V> extends LinkedHashMap<K, V> implements Map<K, V> {
    /**
     * lru缓存的最大阈值
     */
    private final int threshold;

    /**
     * LRU必须开启accessOrder 保证能够按照访问顺序排序
     *
     * @param initialCapacity
     * @param loadFactor
     * @param threshold       LRU max size
     */
    public LRU(int initialCapacity, float loadFactor, int threshold) {
        super(initialCapacity, loadFactor, true);
        this.threshold = threshold;
    }

    /**
     * override this for lru
     *
     * @param eldest
     * @return
     */
    @Override
    protected boolean removeEldestEntry(Entry<K, V> eldest) {
        return this.size() > threshold;
    }

    public static void main(String[] args) {
        LRU<String, String> lru = new LRU<>(4, (float) 0.75, 4);
        lru.put("A", "1");
        lru.put("B", "2");
        lru.get("A");
        lru.put("C", "3");
        lru.put("D", "4");
        lru.put("E", "5");
        System.out.println(lru);
    }
}
