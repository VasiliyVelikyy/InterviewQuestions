package sandbox.spring_ripper;

import org.springframework.stereotype.Component;

@Component
public class TerminatorQuoter implements Quoter {
    @InjectRandomInt(min = 2, max = 7)
    private int repeat;

    private String message="I'll be back";

    public TerminatorQuoter() {
      //  System.out.println(repeat); //на этом этапе repeat будет 0 так как beanpostprocessor который обрабатывает @InjectRandomInt отрабатывает после создания бина (конструктора), но до его использования

        System.out.println("Phase 1");
    }

    public void sayQuotes() {
        for (int i = 0; i < repeat; i++) {
            System.out.println("message= " + message);
        }

    }
}
