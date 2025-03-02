package ru.moskalev.sandbox.spring_ripper.service;


import org.springframework.stereotype.Component;
import ru.moskalev.sandbox.spring_ripper.annotations.DeprecatedClass;
import ru.moskalev.sandbox.spring_ripper.annotations.InjectRandomInt;
import ru.moskalev.sandbox.spring_ripper.annotations.PostProxy;
import ru.moskalev.sandbox.spring_ripper.annotations.Profiling;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//Здесь показан жизненный цикл бина
@Profiling
@Component
@DeprecatedClass(newImpl = T1000.class)
public class TerminatorQuoter implements Quoter {

    //2 этап -пре-инициализацитя (отработка beanPostProcessor before)
    @InjectRandomInt(min = 2, max = 7)
    private int repeat;

    private String message = "I'll be back";

    //3 этап - инициализация
    @PostConstruct
    public void initMethod() {
        System.out.println("repeat value from post construct= " + repeat);
        System.out.println("Phase 2");
    }

    //4 этап пост-инициализацитя (отработка beanPostProcessor after)


    //6 этап - завершение работы (отработка PreDestroy, если xml-destroy метод
    @PreDestroy
    public void destroyMethod() {
        System.out.println("Bean TerminatorQuoter destroyed");
    }

    //1 этап -создание объекта (конструктор)
    public TerminatorQuoter() {
        System.out.println("repeat value from constructor= " + repeat); //на этом этапе repeat будет 0 так как beanpostprocessor который обрабатывает @InjectRandomInt отрабатывает после создания бина (конструктора), но до его использования
        System.out.println("Phase 1");
    }

    //5 этап - использование бина
    public void sayQuotes() {
        for (int i = 0; i < repeat; i++) {
            System.out.println("message= " + message);
        }
    }

    @PostProxy
    public void phase3(){
        System.out.println("Phase 3");
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
