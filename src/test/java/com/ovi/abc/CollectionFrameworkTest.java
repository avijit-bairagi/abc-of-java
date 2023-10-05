package com.ovi.abc;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CollectionFrameworkTest {

    @Test
    void arrayListTest() {

        /**
         * https://www.scaler.com/topics/java/arraylist-in-java/
         * Important Features of ArrayList Java
         * Dynamic Resizing: ArrayList grows dynamically as we add elements to the list
         * or shrinks as we remove elements from the list.
         * Ordered: ArrayList preserves the order of the elements i.e.
         * the order in which the elements were added to the list.
         * Index based: ArrayList in Java supports random access from the list using index positions
         * starting with ‘0’.
         * Object based: ArrayList can store only Objects data types. They cannot be used for
         * primitive data types (int, float, etc). We require a wrapper class in that case.
         * Not Synchronized: ArrayList in Java is not synchronized, we can use the Vector class
         * for the synchronized version. It means ArrayList operations are not thread-safe
         * and multiple threads should not operate on the same ArrayList object at the same time.
         * Time Complexities of key ArrayList operations:
         * Random access takes O(1) time
         * Adding element takes amortized constant time O(1)
         * Inserting/Deleting takes O(n) time
         * Searching takes O(n) time for unsorted array and O(log n) for a sorted one
         * Threshold = (Load Factor) * (Current Capacity)
         * new_capacity = old_capacity + (old_capacity >> 1)
         */

        List<String> names = new ArrayList<>();

        names.add("Luffy");
        names.add("Zoro");
        names.add("Kaido");
        names.add("Kaido");
        names.add("Kaido");

        names.forEach(System.out::println);

        System.out.println("---------------------");

        names.remove("Kaido");
        names.forEach(System.out::println);

        System.out.println("---------------");

        names.removeAll(Collections.singletonList("Kaido"));
        names.forEach(System.out::println);
    }
}
