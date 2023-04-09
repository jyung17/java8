package me.whiteship.java8to11.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
  
  public static void main(String[] args) {
    List<String> names = new ArrayList<>();
    names.add("young");
    names.add("whiteship");
    names.add("toby");
    names.add("foo");
  
    Stream<String> stringStream = names.stream().map(String::toUpperCase);
  
    System.out.println("===names.forEach(System.out::println);===");

    names.forEach(System.out::println);
    System.out.println("===stringStream.forEach(System.out::println);===");
    stringStream.forEach(System.out::println);
  
    System.out.println("===System.out.println(s)===");
  
    List<String> collect = names.stream().map((s) -> {
      System.out.println(s);
      return s.toUpperCase();
    }).collect(Collectors.toList());
  
    System.out.println("===collect.forEach(System.out::println);===");
    collect.forEach(System.out::println);
  }
}
