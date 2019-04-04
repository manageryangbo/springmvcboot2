package com.marketcenter.dao;

import com.marketcenter.dbo.PromotionalLadder;

public interface PromotionalLadderDao {
    int deleteByPrimaryKey(Integer promLadderId);

    int insert(PromotionalLadder record);

    int insertSelective(PromotionalLadder record);

    PromotionalLadder selectByPrimaryKey(Integer promLadderId);

    int updateByPrimaryKeySelective(PromotionalLadder record);

    int updateByPrimaryKey(PromotionalLadder record);
}