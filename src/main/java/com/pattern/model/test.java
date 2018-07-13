/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: test
 * Author:   martin
 * Date:     2018/7/13 11:40
 * Description: 写一个通用的模式以外,每个实现类额外的加一些额外的功能
 *
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.pattern.model;

public class test {
    public static void main(String[] args) {
        OnlineBanking ob = new ChinaOnlineBanking();
        ob.proccessCustomer(1);

        new OnlineBankingLambda().proccessCustomer(2,(Customer c)-> System.out.println("The lambda bank of customer name is:" + c.getName() ));
    }
}
