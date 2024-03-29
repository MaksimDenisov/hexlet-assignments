package exercise;

import exercise.calculator.Calculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// BEGIN
@Component
class CustomBeanPostProcessor implements BeanPostProcessor {
    Logger LOGGER = LoggerFactory.getLogger("log");

    private Map<String, Class> inspectingBeans = new HashMap<>();
    private Map<String, String> logLevels = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Inspect.class)) {
            inspectingBeans.put(beanName, bean.getClass());
            String level = bean.getClass().getAnnotation(Inspect.class).level();
            logLevels.put(beanName, level);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class beanClass = inspectingBeans.get(beanName);
        if (beanClass != null) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(),
                    (proxy, method, args) -> {
                        String message = String.format(
                                "Was called method: %s() with arguments: %s",
                                method.getName(),
                                Arrays.toString(args)
                        );
                        String logLevel = logLevels.get(beanName);
                        if (logLevel.equals("info")) {
                            LOGGER.info(message);
                        } else {
                            LOGGER.debug(message);
                        }
                        return method.invoke(bean, args);
                    }
            );
        }
        return bean;
    }
}
// END
