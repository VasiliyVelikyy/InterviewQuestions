package ru.moskalev.sandbox.spring_ripper;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.moskalev.sandbox.spring_ripper.service.Quoter;

@SpringBootApplication
public class App {
    private static ApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(App.class, args);
        var terminatorBean = context.getBean(Quoter.class);
        terminatorBean.sayQuotes();
    }
}
