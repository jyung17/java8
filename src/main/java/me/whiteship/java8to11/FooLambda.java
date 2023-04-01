package me.whiteship.java8to11;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class FooLambda {

  public static void main(String[] args) {
    Supplier<Integer> get10 = () -> 10;
    BinaryOperator<Integer> sum = (a, b) -> a + b;
    System.out.println(sum.apply(10, 20));

    UnaryOperator<Integer> plus10 = (i) -> i + 10;
    UnaryOperator<Integer> multiply2 = (i) -> i * 10;
    System.out.println(plus10.andThen(multiply2).apply(2));

    FooLambda FooLambda = new FooLambda();
    FooLambda.run();
  }

  private void run() {
    int baseNumber = 10;

    // 로컬 클래스
    class LocalClass {
      void printBaseNumber() {
        int baseNumber = 11;
        System.out.println("printBaseNumber(): " + baseNumber);
      }
    }

    // 익명 클래스
    Consumer<Integer> integerConsumer = new Consumer<Integer>() {
      @Override
      public void accept(Integer baseNumber) {
        System.out.println("integerConsumer: " + baseNumber);
      }
    };

    // 람다
    IntConsumer printInt = (i) -> {
      System.out.println("printInt: " + i + baseNumber);
    };

    printInt.accept(10);
    integerConsumer.accept(10);
    LocalClass localClass = new LocalClass();
    localClass.printBaseNumber();
  }
}
