package com.ovi.abc;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MapTest {

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
    void treeTableTest() {
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
        Map<?, ?> map = new HashMap<>();
        System.out.println(map.);
    }

    static class Demo {

        public String toString() {
            return "Demo";
        }

        protected void finalize() {
            System.out.println("finalized called.");
        }
    }
}
