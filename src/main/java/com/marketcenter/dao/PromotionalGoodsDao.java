package com.marketcenter.dao;

import com.marketcenter.dbo.PromotionalGoods;

public interface PromotionalGoodsDao {
    int deleteByPrimaryKey(Integer promGoodsId);

    int insert(PromotionalGoods record);

    int insertSelective(PromotionalGoods record);

    PromotionalGoods selectByPrimaryKey(Integer promGoodsId);

    int updateByPrimaryKeySelective(PromotionalGoods record);

    int updateByPrimaryKey(PromotionalGoods record);
}