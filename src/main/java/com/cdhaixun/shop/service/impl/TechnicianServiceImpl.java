package com.cdhaixun.shop.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdhaixun.domain.Technician;
import com.cdhaixun.domain.User;
import com.cdhaixun.domain.UserType;
import com.cdhaixun.persistence.TechnicianMapper;
import com.cdhaixun.shop.service.ITechnicianService;
import com.cdhaixun.util.Pager;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Service
public class TechnicianServiceImpl implements ITechnicianService {
    
    @Autowired
    TechnicianMapper technicianMapper;
    
    @Override
    public Technician findById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void save(Technician domain) throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update(Technician domain) throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(Technician domain) throws Exception {
        
    }

    @Override
    public Pager selectTechnicianList(Pager pager, Map<String, Object> parMap) {
        Page dbpage = PageHelper.startPage(pager.getPageNum(), pager.getPageSize());
        
        List<Technician> list = technicianMapper.selectTechnicianList();
        for(Technician technician : list){
            String[] workDays = technician.getWorkday().split(",");
            if(workDays.length > 0){
                for(int i = 0;i<workDays.length;i++){
                     switch(workDays[i]){
                     case   "1":
                         technician.setMon("1");
                         break;
                     case   "2":
                         technician.setTue("1");
                         break;
                     case   "3":
                         technician.setWed("1");
                         break;
                     case   "4":
                         technician.setThu("1");
                         break;
                     case   "5":
                         technician.setFri("1");
                         break;
                     case   "6":
                         technician.setSat("1");
                         break;
                     case   "7":
                         technician.setSun("1");
                         break;
                         default:
                         break;
                     }
                        
                }
            }
        }
        
        pager.setTotal(dbpage.getTotal());
        pager.setResult(list);
        pager.setPages(dbpage.getPages());
        return pager;
    }

    @Override
    public List<UserType> selectTypeList() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(User user) {
        // TODO Auto-generated method stub
        
    }

}
