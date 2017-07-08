package com.cdhaixun.shop.service.impl;

import com.cdhaixun.domain.Image;
import com.cdhaixun.persistence.ImageMapper;
import com.cdhaixun.shop.service.IImageService;
import com.cdhaixun.vo.ImageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author tanggm
 * @Date 2017/6/14 23:33
 */
@Service
public class ImageServiceImpl implements IImageService {
    @Autowired
    private ImageMapper imageMapper;
    @Override
    public Image findById(Integer id) {
        return imageMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(Image domain) throws Exception {
        imageMapper.insert(domain);
    }

    @Override
    public void update(Image domain) throws Exception {
        imageMapper.updateByPrimaryKey(domain);
    }

    @Override
    public void delete(Image domain) throws Exception {
        imageMapper.deleteByPrimaryKey(domain.getId());
    }

    @Override
    public List<ImageVo> getList(Map<String, Object> parMap) {
        return imageMapper.getList(parMap);
    }
}
