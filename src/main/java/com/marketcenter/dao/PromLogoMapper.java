package com.marketcenter.dao;

import com.marketcenter.dbo.PromLogo;

public interface PromLogoMapper {
    int deleteByPrimaryKey(Integer promLogoId);

    int insert(PromLogo record);

    int insertSelective(PromLogo record);

    PromLogo selectByPrimaryKey(Integer promLogoId);

    int updateByPrimaryKeySelective(PromLogo record);

    int updateByPrimaryKey(PromLogo record);
}