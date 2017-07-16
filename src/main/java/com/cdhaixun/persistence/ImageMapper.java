package com.cdhaixun.persistence;

import com.cdhaixun.common.persistence.BaseMapper;
import com.cdhaixun.domain.Image;
import com.cdhaixun.vo.ImageVo;

import java.util.List;
import java.util.Map;

public interface ImageMapper extends BaseMapper<Image> {

    List<ImageVo> getList(Map<String, Object> parMap);

    List<Image> findByImage(Image image);
}