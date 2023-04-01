package me.whiteship.java8to11;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FooPlus10 {

  public static void main(String[] args) {
    Function<Integer, Integer> plus10_1 = (i) ->  i + 10;
    Function<Integer, Integer> multiply2 = (i) -> i * 2;

    System.out.println("plus10_1.apply(1): " + plus10_1.apply(1));
    System.out.println("multiply2.apply(2): " + multiply2.apply(2));

    Function<Integer, Integer> multiply2AndPlus10 = plus10_1.compose(multiply2);
    System.out.println(multiply2AndPlus10.apply(2));

    System.out.println("plus10_1.compose(multiply2).apply(2): " + plus10_1.compose(multiply2).apply(2));
    System.out.println("plus10_1.andThen(multiply2).apply(2): " + plus10_1.andThen(multiply2).apply(2));



    Consumer<Integer> printT = (i) -> System.out.println(i);
    printT.accept(10);

    Supplier<Integer> get10 = () -> 10;
    System.out.println("get10: " + get10);
    System.out.println("get10.get(): " + get10.get());

    Predicate<String> startWithYoung = (s) -> s.startsWith("young");
    System.out.println(startWithYoung.negate());
  }
}
