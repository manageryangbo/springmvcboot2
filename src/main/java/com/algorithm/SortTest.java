/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: SortTest
 * Author:   martin
 * Date:     2019/7/15 14:53
 * Description: 排序测试
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.algorithm;

import java.util.Arrays;

public class SortTest {

    public static void main(String[] args) {

//        int[] ints1 = new int[]{2,9,6,1,5,8,4,3};
//        SortUtil.bubbleSortAsc(ints1);
//        System.out.println( "1-最终结果:" + Arrays.toString(ints1));
//        System.out.println( "循环次数:"+SortUtil.totalCount + "交换次数:"+SortUtil.changeCount );
//
//        System.out.println("===============================================");
//
//        int[] ints2 = new int[]{2,9,6,1,5,8,4,3};
//        SortUtil.bubbleSortDesc( ints2 );
//        System.out.println( "2-最终结果:" + Arrays.toString(ints2));
//        System.out.println( "循环次数:"+SortUtil.totalCount + "交换次数:"+SortUtil.changeCount );
//
//        System.out.println("===============================================");
//
//        int[] ints3 = new int[]{2,9,6,1,5,8,4,3};
//        SortUtil.selectorSortAsc( ints3 );
//        System.out.println( "3-最终结果:" + Arrays.toString(ints3));
//        System.out.println( "循环次数:"+SortUtil.totalCount + "交换次数:"+SortUtil.changeCount );
//
//        System.out.println("===============================================");

        int[] ints4 = new int[]{2,9,6,1,5,8,4,3};
        SortUtil.insertSortAsc( ints4 );
        System.out.println( "3-最终结果:" + Arrays.toString(ints4));
        System.out.println( "交换次数:"+SortUtil.changeCount );
        System.out.println("devlopemnt_marketcenter  revert之前main");

    }
}
