/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ChinaOnlineBanking
 * Author:   martin
 * Date:     2018/7/13 11:37
 * Description: 中国银行
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.pattern.model;

public class ChinaOnlineBanking extends OnlineBanking{
    @Override
    void makeCustomerHappy(Customer c) {
        System.out.println("The customer of china bank  name is:"+c.getName());
    }
}
