/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Subject
 * Author:   martin
 * Date:     2018/7/13 15:05
 * Description: 主题
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.pattern.observer;

public interface Subject {
    void registerObserver(Observer o);
    void notifyObserver(String tweet);
}
