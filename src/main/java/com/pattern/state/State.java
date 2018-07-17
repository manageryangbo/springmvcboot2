/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: State
 * Author:   martin
 * Date:     2018/7/17 10:05
 * Description: 状态接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.pattern.state;

public interface State {
    /**
     * 状态对应的处理
     */
    public void handle(String sampleParameter);
}
