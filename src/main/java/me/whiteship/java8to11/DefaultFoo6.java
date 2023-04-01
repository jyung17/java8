package me.whiteship.java8to11;

public class DefaultFoo6 implements Foo6, Bar6 {

  String name;

  public DefaultFoo6(String name) {
    this.name = name;
  }

  @Override
  public void printName() {
    System.out.println(name);
  }

  @Override
  public void printNameUpperCase() {
    Foo6.super.printNameUpperCase();
  }

  @Override
  public String getName() {
    return name;
  }
}
