package com.marketcenter.dao;

import com.marketcenter.dbo.Promotional;

public interface PromotionalDao {
    int deleteByPrimaryKey(Integer promId);

    int insert(Promotional record);

    int insertSelective(Promotional record);

    Promotional selectByPrimaryKey(Integer promId);

    int updateByPrimaryKeySelective(Promotional record);

    int updateByPrimaryKey(Promotional record);
}