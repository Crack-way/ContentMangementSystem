package com.example.contentmanagementsystem.service;

import java.io.IOException;
import java.util.stream.Stream;

public class test {


    public static Stream<String> stringNumbersStream() {


        return Stream.of("one", "two", "three", "four", "five", "six");
    }

    public static Stream<Integer> intNumbersStream() {

        return Stream.iterate(0, i -> i + 2).limit(10);
    }


    public static Stream<User> userStream() {

        return Stream.of(new User(1, "Rupesh", "Sunuwar"),
                new User(2, "Suman", "Paudel"),
                new User(3, "Gyalbu", "Sherpa"),
                new User(4, "Preet", "Bista"),
                new User(5, "Satya", "Awasthi"),
                new User(6, "Rujan", "Rajnitkar"));
    }


    public static void main(String[] args) throws IOException {

//        //printing the list of numbers
//        intNumbersStream().forEach(System.out::println);
//
//        //Print numbers from intNumbers that are less than 5
//        intNumbersStream().filter(number -> number < 5)
//                .forEach(number -> System.out.println(number));
//
//
//        //Print the second and third number intNumbersStream that's greaterh than 5
//        intNumbersStream().filter(number -> number > 5).skip(1).
//                limit(2).forEach(number -> System.out.println(number));
//
//        //Print the first number in intNumbersStream that's greater than5
//        //If nothing is found , print -1
//        Integer value = intNumbersStream().filter(number -> number > 5).
//                findFirst().orElse(-1);
//        System.out.println(value);
//
//
//        //Print first names of all users in userStream
//
//        userStream().map(user -> user.getName()).forEach(userName -> System.out.println(userName));
//
//
//        //Print first names in userStream for users that have Ids from number stream
//
//
//        intNumbersStream().flatMap(id -> userStream().filter(user -> user.getId() == id))
//                .map(user -> user.getName())
//                .forEach(userName -> System.out.println(userName));


        ReactiveSources.intNumbersFlux().subscribe(e ->
                System.out.println(e),
                err-> System.out.println(err.getMessage()),
                ()-> System.out.println("Complete"));

        ReactiveSources.userFlux().subscribe(user ->
                System.out.println(user));

        System.out.println("Press a key to end");
        System.in.read();





    }




}
