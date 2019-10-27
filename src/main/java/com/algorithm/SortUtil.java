/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: SortUtil
 * Author:   martin
 * Date:     2019/7/15 14:53
 * Description:
 *          排序算法帮助类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.algorithm;


import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;

public class SortUtil {

    public static int totalCount = 0 ; // 计数器
    public static int changeCount = 0 ; // 交换计数


    /**
     * 冒泡排序算法[顺势]
     * @param array
     */
     public static void bubbleSortAsc(int[] array){
           for(int i=0 ; i< array.length ; i++){
               for (int j=i+1;j<array.length;j++){
                   if(array[i]>array[j]){
                       int temp = array[i];
                       array[i] = array[j];
                       array[j] = temp ;
                       changeCount++;
                   }
                   totalCount ++ ;
               }
               System.out.println(  Arrays.toString( array ) ); // 顺势打印效果
           }
     }

    /**
     * 冒泡排序算法[逆势]
     * @param array
     */
    public static void bubbleSortDesc(int[] array){
        int length = array.length;
        for(int i=0 ; i< length ; length -- ){
            int index = i;
            for (int j=index+1;j<length;j++,index++){
                if(array[index]>array[j]){
                    int temp = array[index];
                    array[index] = array[j];
                    array[j] = temp ;
                    changeCount++;
                }
                totalCount ++ ;
            }
            System.out.println(  Arrays.toString( array ) );  // 逆势打印效果
        }
    }

    /**
     * 选择排序算法[顺势]【将轮询范围内的查出最小值插入到最前端，】
     * @param array
     */
    public static void selectorSortAsc(int[] array){
        int length = array.length;
        for(int i=0 ; i< length ; i++){
            int minIndex = i;
            for (int j=i+1;j<length;j++){
                minIndex = array[j]<array[minIndex]?j:minIndex;
                totalCount ++ ;
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp ;
            changeCount ++ ;
            System.out.println(  Arrays.toString( array ) );  // 逆势打印效果
            System.out.println("===========");
        }
    }

    /**
     * 插入排序算法【将当前的值插入到合适的位置如:67985，8需插入到79之间】
     * @param array
     */
    public static void insertSortAsc(int[] array){
        int length = array.length;
        for(int i=1 ; i< length ; i++){
            int insertIndex = i;
            int insertValue = array[insertIndex];
            int j=i-1 ;
            for (;j>=0&&array[j]>insertValue;j--){
                array[j+1] =  array[j];
                changeCount ++ ;
            }
            array[j+1] = insertValue;
            System.out.println(  Arrays.toString( array ) );  // 逆势打印效果
        }
    }

    public static void main(String[] args ) throws Exception {
            FileOutputStream  fileOutputStream = new FileOutputStream("D://test.txt");
            fileOutputStream.write("holle".getBytes());
            fileOutputStream.close();
//            File file = new File("D://test.txt");
//            response.setHeader("Content-Disposition","attachment;filename=" + new String("ab".getBytes()));
//            response.addHeader("Content-Length", "" + file.length());
//            response.setContentType("application/octet-stream");
//            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
//            OutputStream os = response.getOutputStream();
//            toClient.write("sd".getBytes());
//            toClient.close();
    }

}
