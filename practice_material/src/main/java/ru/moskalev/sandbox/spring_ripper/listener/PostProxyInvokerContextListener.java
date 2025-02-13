package ru.moskalev.sandbox.spring_ripper.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ru.moskalev.sandbox.spring_ripper.annotations.PostProxy;

import java.lang.reflect.Method;

/**Работает на этапе когда контекст создан и сделан refresh*/
@Component
public class PostProxyInvokerContextListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    ConfigurableListableBeanFactory factory;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        var context = event.getApplicationContext();
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            //  var proxyBeanNameClass = context.getBean(name).getClass();//здесь будут имена прокси а не имена указанных нами бинов
            BeanDefinition beanDefinition = factory.getBeanDefinition(name);
            String originalClassName = beanDefinition.getBeanClassName();//получение оригинального имени класса
            try {
                if (originalClassName != null) {
                    Class<?> originalClass = Class.forName(originalClassName);//получение объекта класса
                    Method[] methods = originalClass.getMethods();//извлечение всех методов
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(PostProxy.class)) {
                            //    method.invoke()//будет работать если прокси через cglib, если dynamic то не будет. Потому что метод ищем в оригинальном класса. А у нас бин-прокси
                            Object bean = context.getBean(name);
                            Class<?> proxyClass = bean.getClass();//прокси класс
                            Method currentMethod = proxyClass.getMethod(method.getName(), method.getParameterTypes());
                            currentMethod.invoke(bean);
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
