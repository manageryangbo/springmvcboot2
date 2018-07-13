/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: OnlineBanking
 * Author:   martin
 * Date:     2018/7/13 11:23
 * Description: 模板抽象类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.pattern.model;

public abstract class OnlineBanking {

    public void proccessCustomer(int id){
        Customer c = Customer.getEnumByIndex(id);
        makeCustomerHappy(c);
    }

    abstract  void makeCustomerHappy(Customer c);

}
