package ru.moskalev.sandbox.spring_ripper.context;

import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import ru.moskalev.sandbox.spring_ripper.service.Quoter;

public class PropertyFileApplicationContext extends GenericApplicationContext {
    public PropertyFileApplicationContext(String filename) {
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(this);
        int countBeans = reader.loadBeanDefinitions(filename);
        System.out.println("Number of found beans : " + countBeans);
        refresh(); //по аналогии, после наполнения контекста нужно его рефрешнуть
    }

    //при этом коментить app класс не нужно. Все будет работать
    public static void main(String[] args) {
        //написанный нами контекст
        var newContext = new PropertyFileApplicationContext("configurationbean.properties");
        newContext.getBean(Quoter.class).sayQuotes();
    }

}
