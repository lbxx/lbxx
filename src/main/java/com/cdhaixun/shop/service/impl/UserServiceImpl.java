package com.cdhaixun.shop.service.impl;

import com.cdhaixun.domain.Store;
import com.cdhaixun.domain.User;
import com.cdhaixun.domain.UserType;
import com.cdhaixun.persistence.UserMapper;
import com.cdhaixun.persistence.UserTypeMapper;
import com.cdhaixun.shop.service.IUserService;
import com.cdhaixun.util.Pager;
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
public class UserServiceImpl implements IUserService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserTypeMapper userTypeMapper;

    @Override
    public List<Store> selectStoreList() {
        return userMapper.selectStoreList();
    }

    @Override
    public List<UserType> selectTypeList() {
        return userTypeMapper.selectTypeList();
    }

    @Override
    public User findByMobile(String mobile) {
        User user =new User();
        user.setMobile(mobile);
        return userMapper.selectOneByUser(user);
    }

    @Override
    public List<User> getUserListBy(Map<String, Object> parMap) {
        return userMapper.findUserList(parMap);
    }

    @Override
    public User findById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(User domain) throws Exception {
        userMapper.insert(domain);
    }

    @Override
    public void update(User domain) throws Exception {
        userMapper.updateByPrimaryKey(domain);
    }

    @Override
    public void delete(User domain) throws Exception {
        userMapper.deleteByPrimaryKey(domain.getId());
    }

    @Override
    public Pager<User> getUserList(Pager pager, Map<String, Object> parMap) {
        Page dbpage = PageHelper.startPage(pager.getPageNum(), pager.getPageSize());
        List<User> userList = userMapper.findUserList(parMap);
        pager.setTotal(dbpage.getTotal());
        pager.setResult(userList);
        pager.setPages(dbpage.getPages());
        return pager;
    }
}
