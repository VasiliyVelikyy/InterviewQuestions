package sandbox.spring_ripper;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class App {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        var terminatorBean = context.getBean(TerminatorQuoter.class);
        terminatorBean.sayQuotes();
    }
}
