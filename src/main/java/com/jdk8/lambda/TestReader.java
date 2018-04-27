/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TestReader
 * Author:   martin
 * Date:     2018/4/27 11:17
 * Description: 测试自定义方式阅读内容
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.jdk8.lambda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.Callable;

public class TestReader {
    public static void main(String[] args) {
        String data = processFile((BufferedReader br) -> br.readLine() +"\n"+ br.readLine());
        System.out.println( data );
    }

    public static String processFile(BufferedReaderProcessor brp) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("E://data.txt"));
            return brp.process(br);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
