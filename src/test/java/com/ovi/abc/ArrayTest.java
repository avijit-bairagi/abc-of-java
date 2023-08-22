package com.ovi.abc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArrayTest {

    @Test
    void findMissingNumber_oneMissing() {

        int length = 6;
        int[] arr = {1, 2, 3, 4, 6};

        int output = 5;

        float sum = (length * (length + 1)) / 2f;

        int arrSum = 0;

        for (int i : arr) {
            arrSum += i;
        }
        Assertions.assertEquals(output, sum - arrSum);

        length = 7;
        arr = new int[]{2, 4, 6, 8, 12, 14}; //46
        output = 10;

        sum = (length / 2f) * (arr[arr.length - 1] + arr[0]);

        arrSum = 0;

        for (int i : arr) {
            arrSum += i;
        }

        Assertions.assertEquals(output, sum - arrSum);

    }

    @Test
    void findMissingNumber_multipleMissing() {

        int[] arr = {6, 7, 10, 11, 13};

        int length = arr.length;

        int diff = arr[0];

        for (int i = 0; i < length; i++) {
            if (arr[i] - i != diff) {
                while (diff + i < arr[i]) {
                    System.out.println(diff++ + i);
                }
            }
        }
    }

    @Test
    void findMissingNumber_multipleMissing_alt() {

        int[] arr = {6, 7, 10, 11, 13};

        int len = 8;

        List<Integer> integers = IntStream.range(arr[0], arr[0] + len).boxed().collect(Collectors.toList());

        integers.removeAll(
                Arrays.stream(arr).boxed().collect(Collectors.toList())
        );

        System.out.println(integers);
    }

    @Test
    void findDuplicate() {

        int[] arr = {1, 2, 4, 3, 2, 1};

        Arrays.sort(arr);

        int[] result = new int[arr.length];

        result[0] = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] == arr[i]) {
                result[i] = 0;
            } else {
                result[i] = arr[i];
            }
        }

        System.out.println(Arrays.toString(result));
    }

    @Test
    void findLargest() {

        int[] arr = {-2, 2, 10, 0, 101, 9, 100, -20, 101};

        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

        for (int j : arr) {
            if (max < j) {
                max = j;
            } else if (min > j) {
                min = j;
            }
        }

        System.out.println(max + " " + min);
    }

    @Test
    void nonRepeatedCharacter() {

        String data = "abcdefgabxyz";

        Map<Character, Integer> map = new LinkedHashMap<>();

        for (int i = 0; i < data.length(); i++) {
            Character c = data.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        map.entrySet().stream().filter(m -> m.getValue() == 1).findFirst()
                .ifPresent(res -> System.out.println(res.getKey()));

    }

    @Test
    void findPairSum() {

        int[] arr = {4, 7, 0, 14, 0, 8, 3, 5, 7};

        int sum = 11;

        Set<Integer> set = new HashSet<>();

        for (int j : arr) {

            int d = sum - j;

            if (set.contains(d)) {
                System.out.println(d + ", " + j);
            } else {
                set.add(j);
            }
        }
    }

    @Test
    void reverseArray() {

        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        for (int i = 0, j = arr.length - 1; i < arr.length / 2; i++, j--) {
            arr[i] = arr[i] + arr[j];
            arr[j] = arr[i] - arr[j];
            arr[i] = arr[i] - arr[j];
        }
        System.out.println(Arrays.toString(arr));
    }

    @Test
    void reverseString() {

        String data = "AVIJIT";

        System.out.println(reverse(data));

    }

    @Test
    void permutationString() {

        String data = "123";

        permutation("", data);
    }

    @Test
    void palindrome() {
        System.out.println(isPalindrome("data"));
        System.out.println(isPalindrome("abba"));
    }

    private boolean isPalindrome(String data) {
        for (int i = 0, j = data.length(); i < data.length() / 2; i++, j--) {

            if (data.charAt(i) != data.charAt(j - 1)) {
                return false;
            }
        }
        return true;
    }

    private void permutation(String per, String word) {

        if (word.isEmpty()) {
            System.out.println(per + word);
        } else {
            for (int i = 0; i < word.length(); i++) {
                permutation(per + word.charAt(i),
                        word.substring(0, i) + word.substring(i + 1));
            }
        }
    }

    private String reverse(String data) {

        if (data == null || data.length() <= 1) {
            return data;
        }
        return reverse(data.substring(1)) + data.charAt(0);
    }

}
