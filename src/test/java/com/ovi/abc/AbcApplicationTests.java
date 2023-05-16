package com.ovi.abc;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;


class AbcApplicationTests {

    @Test
    void sortTest() {

        long l = 1684247267042L;

        List<User> users = Arrays.asList(
                new User(1, "Ovi", new Date(l)),
                new User(3, "Avi", new Date(l + l)),
                new User(2, "Ai", new Date(l - l / 2)),
                new User(4, "Ai", new Date(l + 2 * l)));

        System.out.println(users);

        List<User> sortedUsers = users.stream()
                .sorted(Comparator.comparing(User::getId, Comparator.naturalOrder()))
                .collect(Collectors.toList());

        System.out.println(sortedUsers);


        sortedUsers = users.stream()
                .sorted((o1, o2) -> o2.getId() - o1.getId())
                .collect(Collectors.toList());

        System.out.println(sortedUsers);

        sortedUsers = users.stream()
                .sorted((o1, o2) -> o2.getDateOfBirth().compareTo(o1.getDateOfBirth()))
                .collect(Collectors.toList());

        System.out.println(sortedUsers);

        sortedUsers = users.stream()
                .sorted((o1, o2) -> o2.getName().compareTo(o1.getName()))
                .collect(Collectors.toList());

        System.out.println(sortedUsers);

        sortedUsers = users.stream()
                .sorted(new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        return 0;
                    }
                })
                .collect(Collectors.toList());
    }

    @Test
    void array() {

        int[] arr = {1, 2, 5, 7, 9, 10, 12, 15, 17};

        for (int i = 0; i < arr.length - 1; i++) {
            if (Math.abs(arr[i] - arr[i + 1]) == 2)
                System.out.println(arr[i] + " " + arr[i + 1] + " = " + 2);
        }
    }

    @Test
    void map() {

        int[] arr = {5, 2, 2, 7, 9, 7, 12, 5, 7};

        Map<Integer, Integer> map = new HashMap<>();

        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        map.forEach((key, value) -> System.out.println(key + " -> " + value));

        System.out.println("-----------------------");

        Map<Integer, Integer> sortedMap = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        sortedMap.forEach((key, value) -> System.out.println(key + " -> " + value));
    }
}
