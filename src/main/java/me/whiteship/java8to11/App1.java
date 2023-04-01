package me.whiteship.java8to11;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class App1 {

  public static void main(String[] args) {
    UnaryOperator<String> hi = Greeting::hi;
    System.out.println("hi: " + hi.apply("young"));

    // 인스턴스의 매서드를 사용
    Greeting greeting = new Greeting();
    UnaryOperator<String> hello = greeting::hello;
    System.out.println("hello: " + hello.apply("young"));

    //입력 값은 없는데 결과 값은 있는것
    Supplier<Greeting> newGreeting = Greeting::new;
    System.out.println("newGreeting.get(): " + newGreeting.get());

    Function<String, Greeting> youngGreeting = Greeting::new;
    Greeting young = youngGreeting.apply("young");
    System.out.println("youngGreeting.apply(\"young\"): " + young);
    System.out.println("young.getName(): " + young.getName());

    // 임의 객체의 인스턴스 메소드 참조
    String[] names = {"young", "whiteship", "toby"};
    //Arrays.sort(names, (o1, o2) -> 0);
    Arrays.sort(names, String::compareToIgnoreCase);
    System.out.println(Arrays.toString(names));
  }
}
