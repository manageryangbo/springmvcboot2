/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: CallTimesLimit
 * Author:   martin
 * Date:     2019/8/14 9:39
 * Description: 自定义注解
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.springs.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface CallTimesLimit {

    // 唯一标识名
    String keys();

    // 限制次数，以秒为单位
    int limit() default  5;

}
