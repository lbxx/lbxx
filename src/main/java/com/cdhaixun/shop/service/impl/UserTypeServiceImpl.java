package com.cdhaixun.shop.service.impl;

import com.cdhaixun.domain.ChainStore;
import com.cdhaixun.domain.UserType;
import com.cdhaixun.persistence.UserTypeMapper;
import com.cdhaixun.shop.service.IUserTypeService;
import com.cdhaixun.util.Pager;
import com.cdhaixun.vo.UserTypeListVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author tanggm
 * @Date 2017/6/14 23:33
 */
@Service
public class UserTypeServiceImpl implements IUserTypeService{
    @Autowired
    private UserTypeMapper userTypeMapper;
    @Override
    public UserType findById(Integer id) {
        return userTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(UserType domain) throws Exception {
        userTypeMapper.insert(domain);
    }

    @Override
    public void update(UserType domain) throws Exception {
        userTypeMapper.updateByPrimaryKey(domain);
    }

    @Override
    public void delete(UserType domain) throws Exception {
        userTypeMapper.deleteByPrimaryKey(domain.getId());
    }

    @Override
    public Pager getUserTypeList(Pager pager, Map<String, Object> parMap) {
        Page dbpage = PageHelper.startPage(pager.getPageNum(), pager.getPageSize());
        List<UserTypeListVo> userList = userTypeMapper.findUserTypeList(parMap);
        pager.setTotal(dbpage.getTotal());
        pager.setResult(userList);
        pager.setPages(dbpage.getPages());
        return pager;
    }

    @Override
    public List<ChainStore> getChainStoreList() {
        return userTypeMapper.getChainStoreList();
    }
}
