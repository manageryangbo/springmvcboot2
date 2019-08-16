/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: CallTimesLimitImp
 * Author:   martin
 * Date:     2019/8/14 9:43
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.springs.annotations;

import com.utils.exception.RRException;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.stereotype.Component;

import org.aspectj.lang.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.logging.Logger;

@Aspect
@Component
public class CallTimesLimitImp {

    private Logger logger = (Logger) LoggerFactory.getLogger(CallTimesLimitImp.class);

    @Around(value = "@annotation(com.springs.annotations.CallTimesLimit)")
    public Object validateCallTimes(ProceedingJoinPoint joinPoint) throws Throwable {
        String key = "";
        int limitNum = 0;
        try {
            long start = System.currentTimeMillis();
            // 获取用户cookie
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            // 拦截的实体类
            Object target = joinPoint.getTarget();
            // 拦截的方法名称
            String methodName = joinPoint.getSignature().getName();
            // 拦截的方法参数
            Object[] argsa = joinPoint.getArgs();
            // 拦截的放参数类型
            Class[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
            Method method = target.getClass().getMethod(methodName, parameterTypes);
            CallTimesLimit limitItem = method.getAnnotation(CallTimesLimit.class);
            key = limitItem.keys();
            limitNum = limitItem.limit();
            if (!StringUtils.isEmpty(key)) {
                logger.info("CallTimesLimitImp key="+key+"limitNum="+limitNum);
                // TODO 这里可以做一堆事情。。。
            }
            logger.info("CallTimesLimitImp key="+key+"limitNum="+"limitNumspendtimes="+ (System.currentTimeMillis() - start) / 1000);
            return joinPoint.proceed();
        } catch (Exception e) {
            throw new RRException( "调用次数达到上限",10001);
        }
    }

}
