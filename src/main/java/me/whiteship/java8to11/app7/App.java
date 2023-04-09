package me.whiteship.java8to11.app7;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;

public class App {
  
  public static void main(String[] args) {
    List<String> name = new ArrayList<>();
    name.add("young");
    name.add("whiteship");
    name.add("toby");
    name.add("foo");
  
    name.forEach(System.out::println);
  
    System.out.println("=========");
  
    for (String n : name) {
      System.out.println(n);
    }
  
    System.out.println("=========");
  
    Spliterator<String> spliterator = name.spliterator();
//    Spliterator<String> spliterator1 = spliterator.trySplit();
    while (spliterator.tryAdvance(System.out::println));
    System.out.println("=========");
  //  while (spliterator1.tryAdvance(System.out::println));
  
    System.out.println("=========");
  
    name.removeIf(s -> s.startsWith("y"));
    name.forEach(System.out::println);
  
    System.out.println("=========");
    
    name.sort(String::compareToIgnoreCase);
    name.forEach(System.out::println);
  }
}
