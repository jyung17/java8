package me.whiteship.java8to11;

public class Foo {

  public static void main(String[] args) {

    // 익명 내부 클래스 anonymous inner class
    RunSomething runSomething = new RunSomething() {
      @Override
      public void doIt() {
        System.out.println("Hello");
      }
    };

    // lambda 표현식
    RunSomething runSomething2 = () -> System.out.println("Hello");
    runSomething2.doIt();

    RunSomething runSomething3 = () -> {
      System.out.println("Hello");
      System.out.println("lambda");
    };
    runSomething3.doIt();

    RunSomething2 runSomething21 = (number) -> {
      return number + 1;
    };

    System.out.println(runSomething21.doIt(1));

  }
}
