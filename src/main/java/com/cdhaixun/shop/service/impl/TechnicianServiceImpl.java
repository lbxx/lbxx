package com.cdhaixun.shop.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdhaixun.common.vo.Result;
import com.cdhaixun.domain.Technician;
import com.cdhaixun.domain.TechnicianBusiness;
import com.cdhaixun.persistence.TechnicianBusinessMapper;
import com.cdhaixun.persistence.TechnicianMapper;
import com.cdhaixun.shop.service.ITechnicianService;
import com.cdhaixun.util.Pager;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Service
public class TechnicianServiceImpl implements ITechnicianService {
    
    @Autowired
    TechnicianMapper technicianMapper;
    @Autowired
    TechnicianBusinessMapper technicianBusinessMapper;
    
    @Override
    public Technician selectByPrimaryKey(Integer technicianId) {
        return technicianMapper.selectByPrimaryKey(technicianId);
    }

    @Override
    public void update(Technician domain) throws Exception {
        
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
    public List<Technician> findByStoreId(Integer storeid) {
        Technician technician=new Technician();
        technician.setStoreid(storeid);
     List<Technician> technicianList=   technicianMapper.selectByTechnician(technician);
        return technicianList;
    }

    @Override
    public Result save(Technician technician,Map<String, Object> parMap) {
        Map businessTimeMap = new HashMap<>();
        Result result = new Result();
        for(String key : parMap.keySet()){
            if(key.startsWith("businessTime_")){
                businessTimeMap.put(key.substring(key.lastIndexOf("_")+1), parMap.get(key));
            }
        }
        Date date = new Date();
        technician.setCreatetime(date);
        technician.setWorkday(parMap.get("workdays").toString());
        technician.setStoreid(Integer.parseInt(parMap.get("storeid").toString()));
//        technician.setDescription(parMap.get("description").toString());
        //如果id不为空,表示是更新技师
        if(parMap.get("id") != null && !("".equals(parMap.get("id")))){
            try {
                technicianMapper.updateByPrimaryKeySelective(technician);
                //删除technician_business中原有的记录
                technicianBusinessMapper.deleteByTechnicianId(technician.getId());
                insertTechnicianBusiness(technician,businessTimeMap, date);
            } catch (Exception e) {
                e.printStackTrace();
                result.setResult(false);
                result.setMsg("更新技师失败");
                return result;
            }
            result.setResult(true);
            result.setMsg("更新技师成功");
            return result;
        }else{//添加技师
            try {
                technicianMapper.insertSelective(technician);
                insertTechnicianBusiness(technician, businessTimeMap, date);
            } catch (Exception e) {
                e.printStackTrace();
                result.setResult(false);
                result.setMsg("添加技师失败");
                return result;
            }
            result.setResult(true);
            result.setMsg("添加技师成功");
            return result;
        }
        
    }

    /**
     * @param technician
     * @param technicianBusiness
     * @param businessTimeMap
     * @param date
     */
    private void insertTechnicianBusiness(Technician technician,Map businessTimeMap, Date date) {
        for(Object id : businessTimeMap.keySet()){
            TechnicianBusiness technicianBusiness = new TechnicianBusiness();
            technicianBusiness.setBusinessid(Integer.parseInt(id.toString()));
            technicianBusiness.setTechnicianid(technician.getId());
            technicianBusiness.setSpend(Integer.parseInt(businessTimeMap.get(id).toString()));
            technicianBusiness.setCreatetime(date);
            technicianBusinessMapper.insertSelective(technicianBusiness);
        }
    }

    @Override
    public void save(Technician domain) throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Technician findById(Integer id) {
        // TODO Auto-generated method stub
        return technicianMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteByTechnicianId(int technicianId) {
        technicianMapper.deleteByTechnicianId(technicianId);
    }

    @Override
    public List<Technician> selectTechnicians() {
        return technicianMapper.selectTechnicianList();
    }


}
