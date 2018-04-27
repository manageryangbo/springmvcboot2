/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ColorApplePredicate
 * Author:   martin
 * Date:     2018/4/25 14:26
 * Description: 苹果颜色断言实现类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.jdk8.actionappleparameter;

public class ColorApplePredicate implements ApplePredicate{


    @Override
    public boolean judge(App app,App toApp) {
        return app.getColor().equals(toApp.getColor());
    }
}
