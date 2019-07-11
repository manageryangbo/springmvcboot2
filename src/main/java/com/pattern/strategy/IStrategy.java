/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: IStrategy
 * Author:   martin
 * Date:     2019/7/11 10:14
 * Description: 策略接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.pattern.strategy;

import java.math.BigDecimal;

public interface IStrategy {

    public BigDecimal calPriceRule(BigDecimal price);
}
