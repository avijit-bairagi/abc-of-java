package com.ovi.abc;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MapTest {

    private static final Logger logger = LoggerFactory.getLogger(MapTest.class);

    @Test
    void simpleMap() {
        /**
         Map Interface is present in java.util package represents a mapping between a key and a value.
         Java Map interface is not a subtype of the Collection interface.
         Therefore, it behaves a bit differently from the rest of the collection types.
         A map contains unique keys.
         */

        /**
         * Characteristics of a Map Interface
         * 1. A Map cannot contain duplicate keys and each key can map to at most one value.
         * Some implementations allow null key and null values like the HashMap and LinkedHashMap,
         * but some do not like the TreeMap.
         * 2. The order of a map depends on the specific implementations.
         * For example, TreeMap and LinkedHashMap have predictable orders, while HashMap does not.
         * 3. There are two interfaces for implementing Map in Java.
         * They are Map and SortedMap, and three classes: HashMap, TreeMap, and LinkedHashMap.
         */

        Map<?, ?> map = new HashMap<>();
    }

    @Test
    void hashMapTest() {

        /**
         * 1. Key can have null value but for example, if we run map.put(null, 5)
         * and then map.put(null, 6), the value will be 6.
         * So null is a unique key and only one can exist in the HashMap.
         * 2. Value can be null
         * 3. Insertion order can not be guaranteed
         * 4. Time complexity of get(), put(), remove(), containsKey()
         * On Average: O(1)
         * Worst case : O(n)
         */

        Map<String, Double> map = new HashMap<>();

        map.put("Avijit", 100.00);
        map.put("Zoro", 200.00);
        map.put("Kaido", 80.00);
        map.put(null, null);

        map.forEach((k, v) -> System.out.println(k + " --> " + v));
    }

    @Test
    void concurrentHashMap() {
        /**
         * 1. Thread safe.
         * 2. Key and value must not be null
         * 3. Time complexity same as hashMap
         */

        Map<String, Double> map = new ConcurrentHashMap<>();

        map.put("Avijit", 100.00);
        map.put("Zoro", 200.00);
        map.put("Kaido", 80.00);

        map.forEach((k, v) -> System.out.println(k + " --> " + v));
    }

    @Test
    void linkedHashMapTest() {

        /**
         * 1. Same as the hasMap but keep the insertion order.
         * 2. It needs more space since it uses a doubly-linked list
         * 3. Time complexity same as hashMap
         */

        Map<String, Double> map = new LinkedHashMap<>();

        map.put("Avijit", 100.00);
        map.put("Zoro", 200.00);
        map.put("Kaido", 80.00);
        map.put(null, null);

        map.forEach((k, v) -> System.out.println(k + " --> " + v));
    }

    @Test
    void sortedHashMapTest() {

        /**
         * 1. Default order is natural order.
         * 2. Key must not be null.
         * 3. Time complexity of get(), put(), remove() and containsKey() is guaranteed to be around O(logn).
         * 4. It uses Red-Black Binary Search tree.
         */

        Map<String, Double> map = new TreeMap<>(Collections.reverseOrder());

        map.put("Avijit", 100.00);
        map.put("Zoro", 200.00);
        map.put("Kaido", 80.00);
        map.put("Nami", null);

        map.forEach((k, v) -> System.out.println(k + " --> " + v));
    }

    @Test
    void hashMapVsWeekHashMap() {
        /**
         * 1. Java.util.HashMap class is a Hashing based implementation.
         * In HashMap, we have a key and a value pair.
         * Even though the object is specified as key in hashmap,
         * it does not have any reference and it is not eligible for garbage collection
         * if it is associated with HashMap i.e. HashMap dominates over Garbage Collector.
         * 2. WeakHashMap is an implementation of the Map interface.
         * WeakHashMap is almost same as HashMap except in case of WeakHashMap,
         * if object is specified as key doesn't contain any references- it is eligible
         * for garbage collection even though it is associated with WeakHashMap.
         * i.e Garbage Collector dominates over WeakHashMap.
         */

        //hash map
        Demo demo = new Demo();
        Map<Demo, String> map = new HashMap<>();
        map.put(demo, "Hi");
        System.out.println(map);
        demo = null;
        System.gc();
        SleepUtils.sleep(4);
        System.out.println(map);

        //weak hash map
        Demo demo2 = new Demo();
        Map<Demo, String> map2 = new WeakHashMap<>();
        map2.put(demo2, "Hi");
        System.out.println(map2);
        demo2 = null;
        System.gc();
        SleepUtils.sleep(4);
        System.out.println(map2);
    }

    @Test
    void hashTableTest() {
        /**
         The java.util.Hashtable class is a class in Java that provides a key-value data structure,
         similar to the Map interface. It was part of the original Java Collections framework
         and was introduced in Java 1.0.
         However, the Hashtable class has since been considered obsolete and its use is generally discouraged.
         This is because it was designed prior to the introduction of the Collections framework
         and does not implement the Map interface, which makes it difficult to use in conjunction
         with other parts of the framework. In addition, the Hashtable class is synchronized,
         which can result in slower performance compared to other implementations of the Map interface.
         */

    }

    @Test
    void internalOfHashMapTest() {

        /**
         * @Info HashCode: returns an integer that represents the internal memory address of the object
         * @Info Hash function:
         * static final int hash(Object var0) {
         *      int var1;
         *      return var0 == null ? 0 : (var1 = var0.hashCode()) ^ var1 >>> 16;
         * }
         * @Info Get index no from hash value and map size
         * index = hash & (n -1)
         * @Info Map Node definition
         * final int hash;
         * final K key;
         * V value;
         * Node<K, V> next;
         **/


    }

    @Test
    void getAllInternalArchitecture() throws InterruptedException {

        int n = 16;

        Map<String, Integer> map = new HashMap<>();

        map.put("Avijit", 1);
        map.put("Bairagi", 2);
        map.put("Zoro", 3);
        map.put("Usap", 100000);
        map.put("Nami", 5);
        map.put("Kibria", 5);
        map.put("Mammi", 5);


        for (int i = 0; i < 100; i++) {
            map.forEach((k, v) -> {

                int hash = hash(k);
                int index = index(hash, n);

                logger.info("{} --> {}. hash: {}, index: {}", k, v, hash, index);
            });
            System.out.println("-------------------");
            Thread.sleep(2000);
        }
    }

    @Test
    void getAllInternalArchitecture2() throws InterruptedException {

        int n = 16;

        Map<SampleKey, Integer> map = new HashMap<>();

        map.put(new SampleKey(1L, "Avijit"), 1);
        map.put(new SampleKey(2L, "Bairagi"), 2);
        map.put(new SampleKey(3L, "Zoro"), 3);
        map.put(new SampleKey(3L, "Zoro"), 3);
        map.put(new SampleKey(4L, "Usap"), 100000);
        map.put(new SampleKey(5L, "Nami"), 5);
        map.put(new SampleKey(6L, "Kibria"), 5);
        map.put(new SampleKey(7L, "Mammi"), 5);


        for (int i = 0; i < 10; i++) {
            map.forEach((k, v) -> {

                int hash = hash(k);
                int index = index(hash, n);

                logger.info("{} --> {}. hash: {}, index: {}", k, v, hash, index);
            });

            System.out.println("-------------------");
            Thread.sleep(2000);
        }
    }

    static class Demo {

        public String toString() {
            return "Demo";
        }

        protected void finalize() {
            System.out.println("finalized called.");
        }
    }

    private static class SampleKey {

        private Long id;

        private String value;

        public SampleKey(Long id, String value) {
            this.id = id;
            this.value = value;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "SampleKey{" +
                    "id=" + id +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    private int hash(Object var0) {
        int var1;
        return var0 == null ? 0 : (var1 = var0.hashCode()) ^ var1 >>> 16;
    }

    private int index(int hash, int n) {
        return hash & (n - 1);
    }
}
