package com.ovi.abc;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class StreamInterviewQuestionTest {

    @Test
    void test() {

        //https://medium.com/@bhaskarsharan/practice-java-streams-questions-8a76cbfee1be

        EmployeeFactory employeeFactory = new EmployeeFactory();
        List<Employee> employees = employeeFactory.getAllEmployee();

        //### 1. List all distinct project in non-ascending order.

        System.out.println("### 1. List all distinct project in non-ascending order.");

        List<Project> distinctProjects = employees.stream()
                .flatMap(employee -> employee.getProjects().stream())
                .distinct()
                .sorted(Comparator.comparing(Project::getName, Comparator.reverseOrder()))
                .collect(Collectors.toList());

        distinctProjects.forEach(p -> System.out.println(p.getName()));

        //### 2. Print full name of any employee whose firstName starts with ‘A’.

        System.out.println("### 2. Print full name of any employee whose firstName starts with ‘A’.");

        employees.stream().filter(e -> e.getFirstName().startsWith("A"))
                .collect(Collectors.toList())
                .forEach(e -> System.out.println(e.getFirstName() + " " + e.getLastName()));

        //### 3. List of all employee who joined in year 2023 (year to be extracted from employee id i.e., 1st 4 characters)

        System.out.println("### 3. List of all employee who joined in year 2023 (year to be extracted from employee id i.e., 1st 4 characters)");

        employees.stream().filter(e -> e.getId().substring(0, 4).equalsIgnoreCase("2023"))
                .collect(Collectors.toList())
                .forEach(e -> System.out.println(e.getFirstName() + " " + e.getLastName()));

        //### 4. Sort employees based on firstName, for same firstName sort by salary.

        System.out.println("### 4. Sort employees based on firstName, for same firstName sort by salary.");

        employees.stream()
                .sorted(Comparator.comparing(Employee::getFirstName, Comparator.naturalOrder())
                        .thenComparing(Employee::getSalary, Comparator.reverseOrder()))
                .collect(Collectors.toList())
                .forEach(e -> System.out.println(e.getFirstName() + ", " + e.getSalary()));

        //### 5. Print names of all employee with 3rd highest salary. (generalise it for nth highest salary).

        System.out.println("### 5. Print names of all employee with 3rd highest salary. (generalise it for nth highest salary).");

        int n = 3;

        employees.stream()
                .map(Employee::getSalary)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .peek(System.out::println)
                .skip(n - 1)
                .limit(1)
                .findFirst()
                .ifPresent(s -> employees.stream().filter(e -> e.getSalary() == s)
                        .forEach(e -> System.out.println(e.getFirstName() + ", " + e.getSalary())));


        //### 6. Print min salary.

        System.out.println("### 6. Print min salary.");

        employees.stream().min(Comparator.comparingInt(Employee::getSalary))
                .ifPresent(employee -> System.out.println(employee.getSalary()));

        //### 7. Print list of all employee with min salary.

        System.out.println("### 7. Print list of all employee with min salary.");

        employees.stream().min(Comparator.comparingInt(Employee::getSalary))
                .ifPresent(employee -> employees.stream()
                        .filter(e -> e.getSalary() == employee.getSalary())
                        .forEach(e -> System.out.println(e.getFirstName() + ", " + e.getSalary())));

        //###8. List of people working on more than 2 projects.

        System.out.println("###8. List of people working on more than 2 projects.");

        employees.stream().filter(e -> e.getProjects().size() > 2)
                .forEach(e -> System.out.println(e.getFirstName() + ", " + e.getProjects().size()));

        //###8.1. List of people working on more than 2 projects and sort by project numbers.

        System.out.println("###8.1. List of people working on more than 2 projects and sort by project numbers.");

        employees.stream().filter(e -> e.getProjects().size() > 2)
                .sorted(Comparator.comparing(employee -> employee.getProjects().size(), Comparator.reverseOrder()))
                .forEach(e -> System.out.println(e.getFirstName() + ", " + e.getProjects().size()));

        //###9. Count of total laptops assigned to the employees.

        System.out.println("###9. Count of total laptops assigned to the employees.");

        int laptopCount = employees.stream().mapToInt(Employee::getTotalLaptopsAssigned).sum();

        System.out.println(laptopCount);

        //###10. Count of all projects with Robert Downey Jr as PM.

        System.out.println("###10. Count of all projects with Robert Downey Jr as PM.");

        long pmCount = employees.stream().flatMap(e -> e.getProjects().stream())
                .filter(p -> p.getProjectManager().equalsIgnoreCase("Robert Downey Jr"))
                .distinct().count();

        System.out.println(pmCount);

        //###11. List of all projects with Robert Downey Jr as PM.

        System.out.println("###11. List of all projects with Robert Downey Jr as PM.");

        employees.stream().flatMap(e -> e.getProjects().stream())
                .filter(p -> p.getProjectManager().equalsIgnoreCase("Robert Downey Jr"))
                .distinct()
                .forEach(p -> System.out.println(p.getName()));


        //###12. List of all people working with Robert Downey Jr.

        System.out.println("###12. List of all people working with Robert Downey Jr.");

        employees.stream()
                .filter(e -> e.getProjects().stream().anyMatch(p -> p.getProjectManager().equalsIgnoreCase("Robert Downey Jr")))
                .distinct()
                .forEach(e -> System.out.println(e.getFirstName() + " " + e.getLastName()));

        //###13. Create a map based on this data, they key should be the year of joining, and value should be list of all the employees who joined the particular year. (Hint : Collectors.toMap)

        System.out.println("###13. Create a map based on this data, they key should be the year of joining, and value should be list of all the employees who joined the particular year. (Hint : Collectors.toMap)");

        System.out.println("First way");

        //Way 1
        employees.stream().collect(Collectors.toMap(
                        e -> e.getId().substring(0, 4),
                        e -> new ArrayList<>(Collections.singletonList(e)),
                        (e1, e2) -> {
                            e1.addAll(e2);
                            return e1;
                        },
                        TreeMap::new))
                .forEach((k, v) -> System.out.println(k + "-> " + v.stream().map(e -> e.getFirstName() + " " + e.getLastName()).collect(Collectors.joining(", "))));

        System.out.println("Second way");

        //Way 2
        employees.stream().collect(Collectors.groupingBy(e -> e.getId().substring(0, 4)))
                .forEach((k, v) -> System.out.println(k + "-> " + v.stream().map(e -> e.getFirstName() + " " + e.getLastName()).collect(Collectors.joining(", "))));

        System.out.println("Third way");

        //Way 3
        employees.stream().collect(Collectors.groupingBy(
                        e -> e.getId().substring(0, 4),
                        TreeMap::new,
                        Collectors.mapping(e -> e.getFirstName() + e.getLastName(),
                                Collectors.joining(","))))
                .forEach((k, v) -> System.out.println(k + "-> " + v));

        //###14. Create a map based on this data, the key should be year of joining and value should be the count of people joined in that particular year. (Hint : Collectors.groupingBy())

        System.out.println("###14. Create a map based on this data, the key should be year of joining and value should be the count of people joined in that particular year. (Hint : Collectors.groupingBy())");

        System.out.println("First way");

        employees.stream().collect(Collectors.groupingBy(
                        e -> e.getId().substring(0, 4),
                        TreeMap::new,
                        Collectors.mapping(e -> e, Collectors.counting())))
                .forEach((k, v) -> System.out.println(k + " " + v));

        System.out.println("Second way");

        employees.stream().collect(Collectors.toMap(
                        e -> e.getId().substring(0, 4),
                        e -> 1,
                        Integer::sum,
                        TreeMap::new))
                .forEach((k, v) -> System.out.println(k + " " + v));
    }

}

class Project {

    private final String name;
    private final String team;
    private final String projectManager;

    public Project(String name, String team, String projectManager) {
        this.name = name;
        this.team = team;
        this.projectManager = projectManager;
    }

    public String getName() {
        return name;
    }

    public String getProjectManager() {
        return projectManager;
    }
}

class Employee {

    private final String id;
    private final String firstName;
    private final String lastName;
    private final int salary;
    private final int totalLaptopsAssigned;
    private final List<Project> projects;

    public Employee(String id, String firstName, String lastName, int salary, int totalLaptopsAssigned,
                    List<Project> projects) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.totalLaptopsAssigned = totalLaptopsAssigned;
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", salary=" + salary + ", projects="
                + projects + "]";
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getSalary() {
        return salary;
    }

    public int getTotalLaptopsAssigned() {
        return totalLaptopsAssigned;
    }

    public List<Project> getProjects() {
        return projects;
    }
}


class EmployeeFactory {

    ArrayList<Employee> employees = new ArrayList<>();

    public List<Employee> getAllEmployee() {

        Project Delta = new Project("Delta Model", "Login", "Robert Downey Jr");
        Project Beta = new Project("Beta Enhancement", "Authentication", "Chris");
        Project TwoFactorAuth = new Project("Two Factor Authentication", "Authentication", "MSD");
        Project CommonUI = new Project("Common UI", "UI", "Robert Downey Jr");
        Project Pegasus = new Project("Pegasus Model", "Data", "Vikram");
        Project CustomerOnboarding = new Project("Customer Onboarding", "Ads", "Vedha");
        Project Verification = new Project("Source Verification", "Data", "Pablo");
        Project RemoveUsers = new Project("Remove Invalid User", "Proxy", "Jeetu");
        Project SiteReliability = new Project("Site Reliability", "Admin", "Zaheer Khan");
        Project DataTransition = new Project("Data Transition", "Data", "Atif Aslam");
        Project TwoPhaseDeployment = new Project("Two Phase Deployment", "Deployment", "Shaktiman");

        employees.add(new Employee("2020Emp0234", "Bhaskar", "Sharan", 900000, 1, Arrays.asList(Delta, Beta)));
        employees.add(new Employee("2012Emp1923", "Dev", "Sharma", 3500000, 3, Arrays.asList(Pegasus, CustomerOnboarding, Beta, SiteReliability)));
        employees.add(new Employee("2017Emp0721", "Priti", "Kabir", 1800000, 3, Arrays.asList(TwoFactorAuth, Beta, CommonUI)));
        employees.add(new Employee("2017Emp00031", "Chris", "Martin", 2200000, 2, Arrays.asList(Delta, TwoFactorAuth)));
        employees.add(new Employee("2013Emp0872", "Sanjay", "Singhania", 2200000, 3, Arrays.asList(Pegasus, Delta, RemoveUsers, DataTransition)));
        employees.add(new Employee("2022Emp0087", "Babu", "Rao", 900000, 1, Collections.singletonList(TwoFactorAuth)));
        employees.add(new Employee("2019Emp0050", "Dev", "Anand", 1300000, 1, Arrays.asList(RemoveUsers, CommonUI)));
        employees.add(new Employee("2023Emp0934", "Shruti", "Sen", 1100000, 1, Collections.singletonList(Pegasus)));
        employees.add(new Employee("2023Emp1033", "Akshay", "Kumar", 1200000, 0, Collections.singletonList(Delta)));
        employees.add(new Employee("2015Emp0009", "Babu", "Dutt", 2600000, 2, Arrays.asList(Verification, RemoveUsers, TwoPhaseDeployment)));

        return employees;
    }
}