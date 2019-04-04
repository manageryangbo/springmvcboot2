package com.suneee.ep.promotion.dao;

import com.suneee.ep.promotion.model.MarketDetail;
import java.math.BigDecimal;

public interface MarketDetailMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(MarketDetail record);

    int insertSelective(MarketDetail record);

    MarketDetail selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(MarketDetail record);

    int updateByPrimaryKey(MarketDetail record);
}