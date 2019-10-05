package com.algorithm;

/**
 *  参考:https://blog.csdn.net/zeb_perfect/article/details/53333606
 *  分治算法，用于处理前N排行榜，销量排行，算法速度较快O(n)级别
 *  有个问题: 当数据较大时，单机内存可能不够【较消耗内存】
 *  解决方案使用堆内存，分N个小顶堆
 */
public class TopNPartition {

    public static void main(String[] args) {
        int a[]={2,20,3,7,9,1,17,18,0,4};
        int topN = 8;
        new TopNPartition().partitionMinBySort(a ,0,a.length-1,topN);
        for (int i=0;i<topN;i++){
            System.out.print(a[i]+",");
        }
    }


    void partitionMinBySort(int[] a,int start,int end,int num){
        if (start < end) {
            // 先获取分治的下标
            int partitionIndex = getpartitionIndex(a,start,end);
            if(partitionIndex == num-1){
                return;
            }else if (partitionIndex>num-1){  // 使用前面的分区数据
                partitionMinBySort(a, start, partitionIndex-1,num);
            }else {// 使用后面的分区数据
                partitionMinBySort(a,partitionIndex+1,end,num);
            }
        }

    }

    int getpartitionIndex(int[] a,int start,int end){
        int partitionMarkIndex = start ;  //循环分治区域的临时分区下标，用于传给下次分区使用
        int partitionValue = a[end];
        for (int index=start;index<end;index++){
            if(a[index]<partitionValue){
                int temp = a[index];
                a[index] = a[partitionMarkIndex];
                a[partitionMarkIndex] = temp ;
                partitionMarkIndex ++ ;
            }
        }
        a[end] =  a[partitionMarkIndex];
        a[partitionMarkIndex] = partitionValue;
        return partitionMarkIndex ;
    }

}
