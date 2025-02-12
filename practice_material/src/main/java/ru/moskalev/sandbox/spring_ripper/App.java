package ru.moskalev.sandbox.spring_ripper;


import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        System.out.println("ok");
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
//        var terminatorBean = context.getBean(TerminatorQuoter.class);
//        terminatorBean.sayQuotes();
    }
}
