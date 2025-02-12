package ru.moskalev.sandbox.spring_ripper.postprocessors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import ru.moskalev.sandbox.spring_ripper.annotations.Profiling;
import ru.moskalev.sandbox.spring_ripper.service.ProfilingController;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProfilingHandlerBeanPostProcessor implements BeanPostProcessor {
    private Map<String, Class> map = new HashMap<>();
    private ProfilingController controller = new ProfilingController();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClazz = bean.getClass();
        if (beanClazz.isAnnotationPresent(Profiling.class)) {
            map.put(beanName, beanClazz);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        var clazz = map.get(beanName);
        if (clazz != null) {
            //любой класс знает какой загрузчик классов его грузил
            return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if (controller.isEnabled()) {
                        System.out.println("I am profiling.....");
                        long before = System.nanoTime();
                        Object retVal = method.invoke(bean, args);
                        long after = System.nanoTime();
                        System.out.println( after - before);
                        System.out.println("End profiling");

                        return retVal;
                    }
                    else {
                       return method.invoke(bean, args);
                    }
                }
            });
        }
        return bean;
    }
}
