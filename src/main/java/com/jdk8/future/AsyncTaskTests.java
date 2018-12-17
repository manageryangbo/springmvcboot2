/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: AsyncTaskTests
 * Author:   martin
 * Date:     2018/11/27 15:31
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.jdk8.future;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

public class AsyncTaskTests {

    @Autowired
    private AsyncThread asyncTask;

    @Test
    public void AsyncTaskTest() throws InterruptedException {
        List<Future<Map<String, List<String>>>> futureList = new ArrayList<Future<Map<String, List<String>>>>();
        asyncTask.generate(4, futureList);
        asyncTask.getResult(futureList);
    }
}
