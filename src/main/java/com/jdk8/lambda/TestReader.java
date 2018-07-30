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

import com.jdk8.actionappleparameter.App;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

public class TestReader {
    public static void main(String[] args) throws Exception {

        BufferedReader brf = new BufferedReader(new FileReader("E://data.txt"));
        int printNumber = 1213 ;
        BufferedReaderProcessor brp = brf2 ->{
            System.out.println(printNumber);
            return brf2.readLine() +"\n"+ brf2.readLine();
        }; // 类型推断BufferedReader -> brf2
//        printNumber = 1254; // printNumber被lambda引用后，隐性为最终变量final，如果改变其值，lambda编译会报错。
        String data = processFile((BufferedReader br) -> br.readLine() +"\n"+ br.readLine(),brf);
        System.out.println( data );

    }

    public static String processFile(BufferedReaderProcessor brp,BufferedReader brf) throws IOException {
        return brp.process(brf);
    }
}
