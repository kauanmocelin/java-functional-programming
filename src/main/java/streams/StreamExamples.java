package streams;

import java.util.*;
import java.util.stream.Collectors;

import static streams.Person.*;

public class StreamExamples {

    public static void main(String[] args) {
        filter();
        sorted();
        allMatch();
        anyMatch();
        noneMatch();
        max();
        min();
        oldestFemaleAge();
        reduceAges();
        collect();
        collectJoining();
        collectAverage();
        collectSummarizing();
        maxMinBy();
        collectGroupingBy();
        collectToMap();
    }

    /**
     * Customize map with key and value
     */
    private static void collectToMap() {
        Map<String, Gender> nameKeyWithGenderValue = getPeople().stream()
                .collect(Collectors.toMap(person -> person.getName(), person -> person.getGender()));

        nameKeyWithGenderValue.forEach((name, gender) -> {
            System.out.println(name + ": " + gender);
        });
    }

    /**
     * groupingBy and partitionBy
     */
    private static void collectGroupingBy() {
        Map<Gender, List<Person>> groupingByGender = getPeople().stream()
                .collect(Collectors.groupingBy(Person::getGender));

        groupingByGender.forEach((gender, people) -> {
            System.out.println(gender);
            people.forEach(System.out::println);
        });
    }

    /**
     * maxBy and minBy
     */
    private static void maxMinBy() {
        Optional<String> maxByName = getPeople().stream()
                .map(person -> person.getName())
                .collect(Collectors.minBy(Comparator.naturalOrder()));

        maxByName.ifPresent(System.out::println);
    }

    private static void collectSummarizing() {
        IntSummaryStatistics statistics = getPeople().stream()
                .collect(Collectors.summarizingInt(person -> person.getAge()));

        System.out.println(statistics.getAverage());
        System.out.println(statistics.getCount());
        System.out.println(statistics.getMin());
        System.out.println(statistics.getMax());
        System.out.println(statistics.getSum());
    }


    /**
     * Averaging and summing
     */
    private static void collectAverage() {
        Double averagePeopleAge = getPeople().stream()
                .collect(Collectors.averagingInt(person -> person.getAge()));

        System.out.println(averagePeopleAge);
    }

    private static void collectJoining() {
        String peopleNameSeparateddByComma = getPeople().stream()
                .map(person -> person.getName())
                .collect(Collectors.joining(", "));

        System.out.println(peopleNameSeparateddByComma);
    }

    private static void collect() {
        HashSet<Person> setPeople = getPeople().stream()
                .collect(Collectors.toCollection(() -> new HashSet<>()));

        System.out.println(setPeople);
    }

    private static void reduceAges() {
        Optional<Integer> reduce = getPeople().stream()
                .map(person -> person.getAge())
                .reduce((age, age2) -> age + age2);

        System.out.println(reduce);
    }

    /**
     * Chaining streams example
     * Get the oldest female and return your name and age
     */
    private static void oldestFemaleAge() {
        Optional<String> oldestFemaleAge = getPeople().stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .max(Comparator.comparing(Person::getAge))
                .map(person -> person.getName() + ", " + person.getAge());

        oldestFemaleAge.ifPresent(person -> System.out.println(person));
    }

    private static void min() {
        getPeople().stream()
                .min(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);
    }

    private static void max() {
        getPeople().stream()
                .max(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);
    }

    private static void noneMatch() {
        boolean noneMatch = getPeople().stream()
                .noneMatch(person -> person.getName().equals("Antonio"));

        System.out.println(noneMatch);
    }

    private static void anyMatch() {
        boolean anyMatch = getPeople().stream()
                .anyMatch(person -> person.getAge() < 5);

        System.out.println(anyMatch);
    }

    private static void allMatch() {
        boolean allMatch = getPeople().stream()
                .allMatch(person -> person.getAge() > 5);

        System.out.println(allMatch);
    }

    private static void sorted() {
        List<Person> sortedPeople = getPeople().stream()
//                .sorted(Comparator.comparing(Person::getAge).reversed())
//                .sorted(Comparator.comparing(Person::getAge))
                .sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getGender))
                .collect(Collectors.toList());

        sortedPeople.forEach(System.out::println);
    }

    private static void filter() {
        List<Person> females = getPeople().stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .collect(Collectors.toList());

        females.forEach(System.out::println);
    }

    private static List<Person> getPeople() {
        return List.of(
                new Person("Antonio", 20, Gender.MALE),
                new Person("Alina Smith", 33, Gender.FEMALE),
                new Person("Helen White", 57, Gender.FEMALE),
                new Person("Alex Boz", 14, Gender.MALE),
                new Person("Jamie Goa", 99, Gender.MALE),
                new Person("Anna Cook", 7, Gender.FEMALE),
                new Person("Zelda Brown", 120, Gender.FEMALE)
        );
    }
}
