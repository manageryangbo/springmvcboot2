/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: RewardTest
 * Author:   martin
 * Date:     2018/4/8 11:06
 * Description: 抽奖测试
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.suneee.reward;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class RewardTest {
    public static void main(String[] args) {
        
        ruanTimeTest();

    }

    public static void ruanTimeTest(){

        long start = System.currentTimeMillis();
        List<RewardItem> rewardItemList=new ArrayList<RewardItem>();
        RewardItem rewardItem1=new RewardItem();
        rewardItem1.setRid(10);
        rewardItem1.setItemName("3个积分");
        rewardItem1.setWinRatio( new BigDecimal("50.000") );
        rewardItemList.add( rewardItem1 );
        RewardItem rewardItem2=new RewardItem();
        rewardItem2.setRid(12);
        rewardItem2.setItemName("5个积分");
        rewardItem2.setWinRatio( new BigDecimal("30.000") );
        rewardItemList.add( rewardItem2 );
        RewardItem rewardItem3=new RewardItem();
        rewardItem3.setRid(14);
        rewardItem3.setItemName("7个积分");
        rewardItem3.setWinRatio( new BigDecimal("10.000") );
        rewardItemList.add( rewardItem3 );
        RewardItem rewardItem4=new RewardItem();
        rewardItem4.setRid(16);
        rewardItem4.setItemName("10个积分");
        rewardItem4.setWinRatio( new BigDecimal("9.900") );
        rewardItemList.add( rewardItem4 );
        RewardItem rewardItem5=new RewardItem();
        rewardItem5.setRid(18);
        rewardItem5.setItemName("50个积分");
        rewardItem5.setWinRatio( new BigDecimal("0.080") );
        rewardItemList.add( rewardItem5 );
        RewardItem rewardItem6=new RewardItem();
        rewardItem6.setRid(21);
        rewardItem6.setItemName("1000个积分");
        rewardItem6.setWinRatio( new BigDecimal("0.019") );
        rewardItemList.add( rewardItem6 );
        RewardItem rewardItem7=new RewardItem();
        rewardItem7.setRid(25);
        rewardItem7.setItemName("100万个积分");
        rewardItem7.setWinRatio( new BigDecimal("0.001") );
        rewardItemList.add( rewardItem7 );

        int totalSize = 1000;	// 数据池容量
        int temptotal= 0 ; // 临时数据段    50000 80000 90000 99900 99980 99999 100000
        int finaltotal= 0 ; // 最终数据段    100000
        List<Integer> zonelist=new ArrayList<Integer>();
        for (RewardItem rewardItem : rewardItemList) {
            zonelist.add( temptotal+ (rewardItem.getWinRatio().multiply( new BigDecimal( totalSize) ).intValue() ));
            temptotal = temptotal+ (rewardItem.getWinRatio().multiply( new BigDecimal( totalSize) ).intValue() );
            finaltotal  = temptotal;
        }
        System.out.println( finaltotal );
        int testCount = 100000000; //测试次数
        int i10=0,i12=0,i14=0,i16=0,i18=0,i21=0,i25=0;
        for (int i = 0; i < testCount; i++) {
            //  测试抽奖核心算法
            Random random = new Random();
            int randNum = random.nextInt(finaltotal);
            //System.out.println(randNum);
            for (int j = 0; j < zonelist.size() ; j++) {
                if(randNum<zonelist.get(j)){
                    //System.out.println("中了奖品:"+rewardItemList.get(j).getItemName());
                    if(rewardItemList.get(j).getRid()==10){
                        i10 += 1;
                    }else if(rewardItemList.get(j).getRid()==12){
                        i12 += 1;
                    }else if(rewardItemList.get(j).getRid()==14){
                        i14 += 1;
                    }else if(rewardItemList.get(j).getRid()==16){
                        i16 += 1;
                    }else if(rewardItemList.get(j).getRid()==18){
                        i18 += 1;
                    }else if(rewardItemList.get(j).getRid()==21){
                        i21 += 1;
                    }else if(rewardItemList.get(j).getRid()==25){
                        i25 += 1;
                    }
                    break;
                }else{
                    continue;
                }
            }
        }
        System.out.println("测试次数累计:"+testCount);
        System.out.println("i10:=>(3个积分)比率:"+((double)i10)/100000000*100+"%");
        System.out.println("i12:(5个积分)比率:"+((double)i12)/100000000*100+"%");
        System.out.println("i14:(7个积分)比率:"+((double)i14)/100000000*100+"%");
        System.out.println("i16:(10个积分)比率:"+((double)i16)/100000000*100+"%");
        System.out.println("i18:(50个积分)比率:"+((double)i18)/100000000*100+"%");
        System.out.println("i21:(1000个积分)比率:"+((double)i21)/100000000*100+"%");
        System.out.println("i25:(100万个积分)比率:"+((double)i25)/100000000*100+"%");
        System.out.println("抽奖算法耗时：" + (System.currentTimeMillis() - start) + "毫秒");

    }


}
