package bytedance.dt;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author linxu
 * @date 2020/3/4
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 最间接的LRU缓存
 */
public class LRUCache {
    private final int capacity;
    private final LRU lru;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.lru = new LRU(0, 0.75f, true);
    }

    public int get(int key) {
        return lru.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        lru.put(key, value);
    }

    class LRU extends LinkedHashMap<Integer, Integer> {
        public LRU(int initialCapacity, float loadFactor, boolean accessOrder) {
            super(initialCapacity, loadFactor, accessOrder);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return super.size() > capacity;
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));  // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        System.out.println(cache.get(2));     // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        System.out.println(cache.get(1));       // 返回 -1 (未找到)
        System.out.println(cache.get(3));       // 返回  3
        System.out.println(cache.get(4));       // 返回  4

    }
}
