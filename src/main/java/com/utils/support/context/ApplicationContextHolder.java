/**
 *
 */
package com.utils.support.context;

import com.utils.InstanceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 *
 * @author gingerjie
 * @version 2017年12月6日 上午11:53:31
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationContextHolder.class);
    static ApplicationContext applicationContext;
    private static final Map<String, Object> serviceFactory = InstanceUtil.newHashMap();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHolder.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> t) {
        return applicationContext.getBean(t);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> t) {
        return applicationContext.getBeansOfType(t);
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }


}
