package com.cdhaixun.shop.web;

import com.cdhaixun.domain.Store;
import com.cdhaixun.shop.service.IUserService;
import com.cdhaixun.util.MapUtils;
import com.cdhaixun.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 会员管理
 * @Author tanggm
 * @Date 2017/6/14 23:10
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    private static final String PATH = "user/";
    @Autowired
    IUserService userService;
    /**
     * 会员首页
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model){
        // 查询店铺列表
        List<Store> storeList = userService.selectStoreList();
        model.addAttribute("storeList", storeList);
        return PATH + "userList";
    }
    @ResponseBody
    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    public Object userList(Pager pager, HttpServletRequest request){
        try{
            Map<String, Object> parMap = MapUtils.getParamMapObj(request);
            /*Map<String, Object> parMap = new HashMap<>();
            parMap.put("registertime", parMap1.get("registertime"));
            parMap.put("storeId", parMap1.get("storeId"));
            parMap.put("type", parMap1.get("type"));
            parMap.put("typeval", parMap1.get("typeval"));*/
            Pager resPager = userService.getUserList(pager, parMap);
            return resPager;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
