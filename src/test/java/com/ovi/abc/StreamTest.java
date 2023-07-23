package com.ovi.abc;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class StreamTest {

    @Test
    void simpleStreamTest() {
        /**
         Stream API is used to process collections of objects.A stream is a sequence of objects that supports various
         methods which can be pipelined to produce the desired result.The features of Java stream are –

         1. A stream is not a data structure instead it takes input from the Collections, Arrays or I/O channels.
         2. Streams don’t change the original data structure, they only provide the result as per the pipelined methods.
         3. Each intermediate operation is lazily executed and returns a stream as a result, hence
         various intermediate operations can be pipelined.Terminal operations mark the end of the stream and return the
         result.
         */
    }

    @Test
    void mapTest() {
        List<Integer> number = Arrays.asList(2, 3, 4, 5, 3);
        List<Integer> square = number.stream().map(x -> x * x).collect(Collectors.toList());
        System.out.println(square);
    }

    @Test
    void sortTest() {
        List<Integer> number = Arrays.asList(2, 3, 4, 5, 3);
        List<Integer> sorted = number.stream().sorted((o1, o2) -> o2 - o1)
                .collect(Collectors.toList());
        System.out.println(sorted);

        List<Integer> anotherSorted = number.stream().sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println(anotherSorted);

        List<User> users = Arrays.asList(
                new User(1, "Ovi"),
                new User(3, "Avi"),
                new User(2, "Ai"),
                new User(4, "Ai"));

        System.out.println(users);

        List<User> sortedUsers = users.stream()
                .sorted(Comparator.comparing(User::getId, Comparator.naturalOrder()))
                .collect(Collectors.toList());

        System.out.println(sortedUsers);

        sortedUsers = users.stream()
                .sorted((o1, o2) -> o2.getName().compareTo(o1.getName()))
                .collect(Collectors.toList());

        System.out.println(sortedUsers);
    }

    @Test
    void filterAndFindAnyTest() {
        List<Integer> number = Arrays.asList(2, 3, 4, 5, 3);
        List<Integer> filtered = number.stream().filter(i -> i % 2 == 0)
                .collect(Collectors.toList());
        System.out.println(filtered);

        Optional<Integer> optional = number.stream().filter(i -> i > 100).findAny();

        System.out.println(optional.isPresent());
    }

    @Test
    void collectSumMaxMinAvgTest() {

        List<Integer> number = Arrays.asList(2, 3, 4, 5, 3);
        int sum = number.stream().mapToInt(Integer::intValue).sum();
        System.out.println(sum);

        int anotherSum = number.stream().collect(Collectors.summingInt(integer -> integer));
        System.out.println(anotherSum);

        int max = number.stream().mapToInt(Integer::intValue).max().getAsInt();
        System.out.println(max);
        int min = number.stream().mapToInt(Integer::intValue).min().getAsInt();
        System.out.println(min);
        double avg = number.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        System.out.println(avg);
    }

    @Test
    void collectGroupingByPartitioningByTest() {
        List<User> users = Arrays.asList(
                new User("Ovi", 100.0),
                new User("Avi", 200.00),
                new User("Avi", 80.00),
                new User("Ai", 90.0));

        Map<String, List<User>> map = users.stream().collect(Collectors.groupingBy(User::getName));
        map.forEach((key, value) -> System.out.println(key + " --> " + value));

        Map<Boolean, List<User>> partitionMap = users.stream().collect(Collectors.partitioningBy(s -> s.getSalary() >= 100));
        partitionMap.forEach((key, value) -> System.out.println(key + " --> " + value));
    }

    @Test
    void collectToMapTest() {

        List<User> users = Arrays.asList(
                new User("Ovi", 100.0),
                new User("Avi", 200.00),
                new User("Ai", 90.0));

        Map<String, Double> salaryMap = users.stream().collect(Collectors.toMap(User::getName, User::getSalary));

        System.out.println(salaryMap);

        //compute duplicate key
        Map<String, Integer> ageMap = new HashMap<String, Integer>() {{
            put("Avijit", 23);
            put("Zoro", 22);
            put("Kaido", 23);
        }};

        Map<Integer, String> reveredMap = ageMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey,
                        (e1, e2) -> e1 + ", " + e2, HashMap::new));

        System.out.println(reveredMap);
    }
}
