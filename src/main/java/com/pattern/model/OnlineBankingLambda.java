/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: OnlineBankingLambda
 * Author:   martin
 * Date:     2018/7/13 11:51
 * Description: Lambda接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.pattern.model;

import java.util.function.Consumer;

public class OnlineBankingLambda {
    public void proccessCustomer(int id , Consumer<Customer>  makeCustomerHappy ){
        Customer c = Customer.getEnumByIndex(id);
        makeCustomerHappy.accept(c);
    }
}
