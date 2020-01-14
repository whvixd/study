package com.github.whvixd.message;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 非线程安全copy on write Map, 优化嵌套Map的拷贝性能
 * <p>
 * Created by liyiguang on 2020/1/6.
 */
public class CopyOnWriteMap extends AbstractMap implements Map {
    private Map originMap;

    private Map writableMap = Maps.newLinkedHashMap();
    /**
     * 拷贝状态
     */
    private boolean copied = false;

    private CopyOnWriteMap(Map originMap) {
        Objects.requireNonNull(originMap);
        if (originMap instanceof CopyOnWriteMap) {
            this.originMap = ((CopyOnWriteMap) originMap).originMap;
        } else {
            this.originMap = originMap;
        }
    }

    /**
     * 普通Map 递归拷贝一个生成一个CopyOnWriteMap
     *
     * @param map
     * @return
     */
    public static CopyOnWriteMap copy(Map map) {
        if (map == null) {
            return null;
        }
        return (CopyOnWriteMap) deepClone(map);
    }

    private static Object deepClone(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof List) {
            List<Object> list = Lists.newArrayList();
            ((List) value).forEach(v -> list.add(deepClone(v)));
            return list;
        } else if (value instanceof Set) {
            Set<Object> set = Sets.newHashSet();
            ((Set) value).forEach(v -> set.add(deepClone(v)));
            return set;
        } else if (value instanceof Map) {
            CopyOnWriteMap m = new CopyOnWriteMap((Map) value);
            ((Map) value).forEach((k, v) -> {
                Object copy = deepClone(v);
                //引用不等
                if (copy != v) {
                    m.updateWriteMap(k, copy);
                }
            });
            return m;
        } else if (value.getClass().isArray()) {
            int length = Array.getLength(value);
            Object[] arr = new Object[length];
            for (int i = 0; i < length; i++) {
                arr[i] = deepClone(Array.get(value, i));
            }
            return arr;
        } else {
            return value;
        }
    }

    @Override
    public int size() {
        if (copied) {
            return writableMap.size();
        } else {
            return originMap.size();
        }
    }

    @Override
    public boolean isEmpty() {
        if (copied) {
            return writableMap.isEmpty();
        } else {
            return originMap.isEmpty();
        }
    }

    @Override
    public boolean containsKey(Object key) {
        if (copied) {
            return writableMap.containsKey(key);
        } else {
            return originMap.containsKey(key);
        }
    }

    @Override
    public boolean containsValue(Object value) {
        if (copied) {
            return writableMap.containsValue(value);
        } else {
            //value为Map的时候是有问题的，不支持
            return originMap.containsValue(value);
        }
    }

    @Override
    public Object get(Object key) {
        if (copied) {
            return writableMap.get(key);
        } else {
            //没有拷贝，优先从copyMap中获取value
            Object value = null;
            if (writableMap != null) {
                value = writableMap.get(key);
            }
            if (value == null) {
                value = originMap.get(key);
            }
            return value;
        }
    }

    @Override
    public Object put(Object key, Object value) {
        doCopy();
        return writableMap.put(key, value);
    }

    @Override
    public Object remove(Object key) {
        doCopy();
        return writableMap.remove(key);
    }

    @Override
    public void putAll(Map m) {
        doCopy();
        writableMap.putAll(m);
    }

    @Override
    public void clear() {
        this.writableMap = Maps.newLinkedHashMap();
        copied = true;
    }

    transient Set<Map.Entry> entrySet;

    @Override
    public Set<Map.Entry> entrySet() {
        Set<Map.Entry> es;
        return (es = entrySet) == null ? (entrySet = new EntrySet()) : es;
    }


    private void doCopy() {
        if (copied) {
            return;
        } else {
            Map newMap = Maps.newLinkedHashMap(originMap);
            //覆盖原值
            if (writableMap != null) {
                writableMap.forEach((k, v) -> newMap.put(k, v));
            }
            writableMap = newMap;
            copied = true;
        }
    }

    final class EntrySet extends AbstractSet<Map.Entry> {
        @Override
        public Iterator<Map.Entry> iterator() {

            return new Iterator<Map.Entry>() {
                private Iterator<Map.Entry> origin = originMap.entrySet().iterator();
                private Iterator<Map.Entry> copy = writableMap.entrySet().iterator();

                @Override
                public boolean hasNext() {
                    if (copied) {
                        return copy.hasNext();
                    } else {
                        return origin.hasNext();
                    }
                }

                @Override
                public Map.Entry next() {
                    if (copied) {
                        return copy.next();
                    } else {
                        Map.Entry e = origin.next();
                        Object key = e.getKey();
                        if (writableMap.containsKey(key)) {
                            return new InnerEntry(key, writableMap.get(key));
                        }
                        return e;
                    }
                }
            };
        }

        @Override
        public int size() {
            return originMap.size();
        }
    }

    static class InnerEntry implements Map.Entry {
        final Object key;
        Object value;

        InnerEntry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public Object getKey() {
            return key;
        }

        @Override
        public Object getValue() {
            return value;
        }

        @Override
        public Object setValue(Object value) {
            Object oldValue = value;
            this.value = value;
            return oldValue;
        }
    }

    private void updateWriteMap(Object key, Object value) {
        writableMap.put(key, value);
    }

}