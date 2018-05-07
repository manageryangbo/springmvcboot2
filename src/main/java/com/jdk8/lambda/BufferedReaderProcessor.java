/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: BufferedReaderProcessor
 * Author:   martin
 * Date:     2018/4/27 11:15
 * Description: 自定义的读取文件内容接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.jdk8.lambda;

import java.io.BufferedReader;
import java.io.IOException;

@FunctionalInterface
public interface BufferedReaderProcessor {

    String process(BufferedReader br) throws IOException;
}
