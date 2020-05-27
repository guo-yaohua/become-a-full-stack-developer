package com.gyh.part2.day40;

import java.util.LinkedHashSet;
import java.util.Set;

public class MyHashMap<K, V> {
    // 常量
    private static final int DEFAULT_ARRAY_SIZE = 16;
    private static final int MAX_ARRAY_SIZE = 1 << 30;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    // 属性

    private Entry[] table;
    private int size;
    private float loadFactor;
    private int threshold; //阈值, 当size达到threshold时候就需要扩容了

    private static class Entry {
        Object key;
        Object value;
        int hash;
        Entry next;

        public Entry(Object key, Object value, int hash, Entry next) {
            this.key = key;
            this.value = value;
            this.hash = hash;
            this.next = next;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    // 构造方法
    public MyHashMap() {
        this(DEFAULT_ARRAY_SIZE, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity <= 0 || initialCapacity > MAX_ARRAY_SIZE) {
            throw new IllegalArgumentException("initialCapacity=" + initialCapacity);
        }
        if (loadFactor <= 0) {
            throw new IllegalArgumentException("loadFactor=" + loadFactor);
        }
        // 求大于等于initialCapacity最小的2的幂
        int capacity = calculateCapacity(initialCapacity);
        table = new Entry[capacity];
        this.loadFactor = loadFactor;
        threshold = (int) (table.length * loadFactor);
    }

    private int calculateCapacity(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n + 1;
    }

    /**
     * 通过键获取值
     *
     * @param key 键
     * @return 键关联的值，如果键不存在返回null
     */
    @SuppressWarnings("unchecked")
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null.");
        int hash = hash(key);
        int index = indexFor(hash, table.length);
        // 遍历链表, 找到key关联值
        for (Entry e = table[index]; e != null; e = e.next) {
            if (hash == e.hash && (key == e.key || key.equals(e.key))) {
                // 找到该结点
                return (V) e.value;
            }
        }
        // 没有这样的key
        return null;
    }

    private int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    private int hash(K key) {
        int h = key.hashCode();
        return (h << 16) ^ (h >> 16);
    }

    /**
     * 判断key是否在哈希表中存在
     * @param key 键
     * @return 如果存在返回true, 否则返回false
     */
    public boolean contains(K key) {
        return get(key) != null;
    }

    /**
     * 删除 key 对应结点
     * @param key
     */
    public void delete(K key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null.");
        int hash = hash(key);
        int index = indexFor(hash, table.length);
        // 遍历链表, 找到key关联值
        Entry e = table[index];
        if (e == null) {
            return;
        } else {
            if (hash == e.hash && (key == e.key || key.equals(e.key))) {    // 如果头结点就是查找的结点
                table[index] = null;
                size--;
                return;
            }
        }
        Entry preNode = e;
        for (e = e.next; e != null; preNode = e, e = e.next) {
            if (hash == e.hash && (key == e.key || key.equals(e.key))) {
                // 找到该结点
                preNode.next = e.next;  // 删除该结点
                size--;
            }
        }
    }

    /**
     * 清空 Map
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            table[i] = null;
        }
        size = 0;
    }

    /**
     * 判断 Map 是否为空
     * @return true or false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 返回此映射中的键-值映射关系数。
     * @return 返回此映射中的键-值映射关系数。
     */
    public int size() {
        return size;
    }

    /**
     * 返回此映射中所包含的键的 Set 视图。
     * @return 返回此映射中所包含的键的 Set 视图。
     */
    public Set<K> keys() {
        Set<K> set = new LinkedHashSet<>();
        for (Entry e : table) {
            while (e != null) {
                set.add((K)e.key);
                e = e.next;
            }
        }
        return set;
    }
}

