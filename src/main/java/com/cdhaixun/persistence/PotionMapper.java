package com.cdhaixun.persistence;

import com.cdhaixun.domain.Potion;
import com.cdhaixun.vo.PotionVo;

import java.util.List;
import java.util.Map;

public interface PotionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Potion record);

    Potion selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Potion record);

    List<PotionVo> findPotionList(Map<String, Object> parMap);

    List<Potion> selectByPotionCategory(Potion potion);
}