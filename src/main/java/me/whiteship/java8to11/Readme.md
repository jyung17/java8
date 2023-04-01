## 2. 함수형 인터페이스와 람다 표현식 소개
### 함수형 인터페이스 (Functional Interface)
- 추상 메소드를 딱 하나만 가지고 있는 인터페이스
- SAM (Single Abstract Method) 인터페이스
- @FunctionalInterface 애노테이션을 가지고 있는 인터페이스

```java
@FunctionalInterface
public interface RunSomething {
  void doIt();
}
```

```java
public class Foo {
  public static void main(String[] args) {
    // 익명 내부 클래스 anonymous inner class
    RunSomething runSomething = new RunSomething() {
      @Override
      public void doIt() {
        System.out.println("Hello");
      }
    };
  }
}
```

### 람다 표현식 (Lambda Expressions)
- 함수형 인터페이스의 인스턴스를 만드는 방법으로 쓰일 수 있다.
- 코드를 줄일 수 있다.
- 메소드 매개변수, 리턴 타입, 변수로 만들어 사용할 수도 있다.

```java
public class Foo {
  public static void main(String[] args) {
    RunSomething runSomething2 = () -> System.out.println("Hello");
    runSomething2.doIt();
  }
}
```

### 자바에서 함수형 프로그래밍
- 함수를 First class object로 사용할 수 있다.
- 순수 함수 (Pure function)
  - 사이드 이팩트가 없다. (함수 밖에 있는 값을 변경하지 않는다.)
  - 상태가 없다. (함수 밖에 있는 값을 사용하지 않는다.)
- 고차 함수 (Higher-Order Function)
  - 함수가 함수를 매개변수로 받을 수 있고 함수를 리턴할 수도 있다.
- 불변성

```java
@FunctionalInterface
public interface RunSomething2 {
  int doIt(int number);
}
```

```java
public class Foo {
  public static void main(String[] args) {
    RunSomething2 runSomething21 = (number) -> {
      return number + 1;
    };

    System.out.println(runSomething21.doIt(1));
  }
}
```


## 3. 자바에서 제공하는 함수형 인터페이스
### Java가 기본으로 제공하는 함수형 인터페이스
- java.util.function 패키지
- 자바에서 미리 정의해둔 자주 사용할만한 함수 인터페이스
- Function<T, R>
- BiFunction<T, U, R>
- Consumer<T>
- Supplier<T>
- Predicate<T>
- UnaryOperator<T>
- BinaryOperator<T>

### Function<T, R>
- T 타입을 받아서 R 타입을 리턴하는 함수 인터페이스
  - R apply(T t)
- 함수 조합용 메소드
  - andThen
  - compose

```java
    Function<Integer, Integer> plus10_1 = (i) -> i + 10;
    Function<Integer, Integer> multiply2 = (i) -> i * 2;

    System.out.println("plus10_1.apply(1): " + plus10_1.apply(1));
    System.out.println("multiply2.apply(2): " + multiply2.apply(2));

    System.out.println("plus10_1.compose(multiply2).apply(2): " + plus10_1.compose(multiply2).apply(2));
    System.out.println("plus10_1.andThen(multiply2).apply(2): " + plus10_1.andThen(multiply2).apply(2));
```
```text
plus10_1.apply(1): 11
multiply2.apply(2): 4
plus10_1.compose(multiply2).apply(2): 14
plus10_1.andThen(multiply2).apply(2): 24
```

### BiFunction<T, U, R>
- 두 개의 값(T, U)를 받아서 R 타입을 리턴하는 함수 인터페이스
  - R apply(T t, U u)

### Consumer<T>
- T 타입을 받아서 아무값도 리턴하지 않는 함수 인터페이스
  - void Accept(T t)
- 함수 조합용 메소드
  - andThen

```java
    Consumer<Integer> printT = (i) -> System.out.println(i);
    printT.accept(10);
```
```text
10
```

### Supplier<T>
- T 타입의 값을 제공하는 함수 인터페이스
  - T get()

```java
Supplier<Integer> get10 = () -> 10;
    System.out.println("get10: " + get10);
    System.out.println("get10.get(): " + get10.get());
```
```text
get10: me.whiteship.java8to11.FooPlus10$$Lambda$21/0x0000000800c03480@7699a589
get10.get(): 10
```

### Predicate<T>
- T 타입을 받아서 boolean을 리턴하는 함수 인터페이스
  - boolean test(T t)
- 함수 조합용 메소드
  - And
  - Or
  - Negate

### UnaryOperator<T>
- Function<T, R>의 특수한 형태로, 입력값 하나를 받아서 동일한 타입을 리턴하는 함수 인터페이스

### BinaryOperator<T>
- BiFunction<T, U, R>의 특수한 형태로, 동일한 타입의 입렵값 두개를 받아 리턴하는 함수 인터페이스


## 4. 람다 표현식
### 람다
- (인자 리스트) -> {바디}

### 인자 리스트
- 인자가 없을 때: ()
- 인자가 한개일 때: (one) 또는 one
- 인자가 여러개 일 때: (one, two)
- 인자의 타입은 생략 가능, 컴파일러가 추론(infer)하지만 명시할 수도 있다. (Integer one, Integer two)

### 바디
- 화살표 오른쪽에 함수 본문을 정의한다.
- 여러 줄인 경우에 {}를 사용해서 묶는다.
- 한 줄인 경우에 생략 가능, reutrn도 생략 가능.

### 변수 캡처 (Variable Capture)
- 로컬 변수 캡처
  - final이거나 effective final인 경우에만 참조할 수 있다.
  - 그렇지 않을 경우 concurrency 문제가 생길 수 있어서 컴파일러가 방지한다.
- effective final
  - 이것도 역시 자바 8부터 지원하는 기능으로 "사실상" final인 변수.
  - final 키워드 사용하지 않은 변수를 익명 클래스 구현체 또는 람다에서 참조할 수 있다.
- 익명 클래스 구현체와 달리 "쉐도윙"하지 않는다.
  - 익명 클래스는 새로 스콥을 만들지만, 람다는 람다를 감싸고 있는 스콥 같다. 

```java
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
```
```text
30
120
printInt: 1010
integerConsumer: 10
printBaseNumber(): 11
```

### 참고
[https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html#shadowing](https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html#shadowing)
[https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html)

## 5. 메소드 레퍼런스
람다가 하는 일이 기존 메소드 또는 생성자를 호출하는 거라면, 메소드 레퍼런스를 사용해서 매우 간결하게 표현할 수 있다.

메소드 참조하는 방법

| 스태틱 메소드 참조         | 타입::스태틱 메소        |
|--------------------|-------------------|
| 특정 객체의 인스턴스 메소드 참조 | 객체 레퍼런스::인스턴스 메소드 |
| 임의 객체의 인스턴스 메소드 참조 | 타입::인스턴 메소드       |
| 생성자 참조             | 타입:new            |

- 메소드 또는 생서앚의 매개변수로 람다의 입력값을 받는다.
- 리턴값 또는 생성한 객체는 람다의 리턴값이다.

```java
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
```
```text
hi: hi young
hello: hello young
newGreeting.get(): me.whiteship.java8to11.Greeting@7cca494b
youngGreeting.apply("young"): me.whiteship.java8to11.Greeting@3b9a45b3
young.getName(): young
[toby, whiteship, young]
```

### 참고
[https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html](https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html
)

# 3부 인터페이스의 변화
## 6. 인터페이스 기본 메소드와 스태틱 메소드
### 기본 메소드 (Default Method)
- 인터페이스에 메소드 선언이 아니라 구현체를 제공하는 방법
- 해당 인터페이스를 구현한 클래스를 깨트리지 않고 새 기능을 추가할 수 있다.
- 기본 메소드는 구현체가 모르게 추가된 기능으로 그만큼 리스크가 있다.
  - 컴파일 에러는 아니지만 구현체에 따라 런타임 에러가 발생할 수 있다.
  - 반드시 문서화 할 것. (@implSpec 자바 태그 사용)
- Object가 제공하는 기능(equals, hasCode)는 기본 메소드로 제공할 수 없다.
  - 구현체가 재정의해야 한다.
- 본인이 수정할 수 있는 인터페이스에만 기본 메소드 제공할 수 있다.
- 인터페이스를 상속받는 인터페이스에서 다시 추상 메소드로 변경할 수 있다.
- 인터페이스 구현체가 재정의 할 수도 있다.

```java
public interface Foo6 {
  void printName();

  /**
   * @implSpec 이 구현체는 getName()으로 가져온 문자열을 대문자로 바꿔 출력한다.
   */
  default void printNameUpperCase() {
    System.out.println(getName().toUpperCase());
  }

  static void printAnything() {
    System.out.println("Foo6");
  }

  String getName();
}
```

```java
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
```

```java
public class App6 {

  public static void main(String[] args) {
    Foo6 foo6 = new DefaultFoo6("young");
    foo6.printName();
    foo6.printNameUpperCase();
    Foo6.printAnything();
  }
}
```
```text
young
YOUNG
Foo6
```

### 스태틱 메소드
- 해당 타입 관련 헬퍼 또는 유틸리티 메소드를 제공할 때 인터페이에 스태 메소드를 제공할 수 있다.

### 참고
[https://docs.oracle.com/javase/tutorial/java/IandI/nogrow.html](https://docs.oracle.com/javase/tutorial/java/IandI/nogrow.html)
[https://docs.oracle.com/javase/tutorial/java/IandI/defaultmethods.html](https://docs.oracle.com/javase/tutorial/java/IandI/defaultmethods.html)