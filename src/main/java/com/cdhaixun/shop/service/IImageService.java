package com.cdhaixun.shop.service;

import com.cdhaixun.common.service.BaseService;
import com.cdhaixun.domain.Image;
import com.cdhaixun.vo.ImageVo;

import java.util.List;
import java.util.Map;

/**
 * @Author tanggm
 * @Date 2017/6/14 23:13
 */
public interface IImageService extends BaseService<Image> {

    List<ImageVo> getList(Map<String, Object> parMap);
}
