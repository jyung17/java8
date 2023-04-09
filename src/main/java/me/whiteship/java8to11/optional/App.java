package me.whiteship.java8to11.optional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class App {
  public static void main(String[] args) {
    List<OnlineClass> springClasses = new ArrayList<>();
    springClasses.add(new OnlineClass(1, "spring boot", true));
    springClasses.add(new OnlineClass(5, "rest api development", false));
  
    Optional<OnlineClass> optional = springClasses.stream()
        .filter(oc -> oc.getTitle().startsWith("spring"))
        .findFirst();
  
    boolean present = optional.isPresent();
    boolean empty = optional.isEmpty();
    System.out.println("present = " + present);
    System.out.println("empty = " + empty);
/*    String title = optional.get().getTitle();
    System.out.println("title = " + title);*/
  
    // if 문으로 체크할 필요없이 optional.ifPresent() 메서드를 사용할 수 있다.
    optional.ifPresent(oc -> {
      System.out.println("oc.getTitle() = " + oc.getTitle());
    });
  
    OnlineClass onlineClass = optional.orElse(createNewJpaClass());
    System.out.println("onlineClass = " + onlineClass.getTitle());
   
    OnlineClass onlineClass2 = optional.orElseGet(App::createNewJpaClass);
    System.out.println("onlineClass2 = " + onlineClass2.getTitle());
    
    /*
    OnlineClass onlineClass3 = optional.orElseThrow();
    System.out.println("onlineClass3 = " + onlineClass3.getTitle());
  
    OnlineClass onlineClass3_1 = optional.orElseThrow(() -> {
      return new IllegalArgumentException();
    });
    System.out.println("onlineClass3 = " + onlineClass3_1.getTitle());
  
    OnlineClass onlineClass3_2 = optional.orElseThrow(IllegalArgumentException::new);
    System.out.println("onlineClass3 = " + onlineClass3_2.getTitle());
     */
  
    Optional<OnlineClass> onlineClass_filter = optional
        .filter(oc -> !oc.isClosed());
    System.out.println("onlineClass_filter.isEmpty() = " + onlineClass_filter.isEmpty());
    System.out.println("onlineClass_filter.isPresent() = " + onlineClass_filter.isPresent());
  
    Optional<Integer> integer = optional.map(OnlineClass::getId);
    System.out.println("integer = " + integer.isPresent());
  
    
    Optional<Progress> progress = optional.flatMap(OnlineClass::getProgress);
    
    Optional<Optional<Progress>> progress1 = optional.map(OnlineClass::getProgress);
    Optional<Progress> progress2 = progress1.orElse(Optional.empty());
  }
  
  private static OnlineClass createNewJpaClass() {
    System.out.println("creating new online class");
    return new OnlineClass(10, "New class", false);
  }
}
