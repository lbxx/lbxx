
package com.cdhaixun.shop.web;

import com.cdhaixun.domain.Store;
import com.cdhaixun.domain.User;
import com.cdhaixun.domain.UserType;
import com.cdhaixun.shop.service.IUserService;
import com.cdhaixun.util.ConfigContentUtils;
import com.cdhaixun.util.JsonMsgUtil;
import com.cdhaixun.util.MapUtils;
import com.cdhaixun.util.Pager;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Date;

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
        return PATH + "user_list";
    }

    /**
     * 查询用户列表
     * @param pager
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    public Object userList(Pager pager, HttpServletRequest request){
        try{
            Map<String, Object> parMap = MapUtils.getParamMapObj(request);
            Pager resPager = userService.getUserList(pager, parMap);
            return resPager;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 添加会员
     * @param request
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(HttpServletRequest request){
        List<UserType> typeList = userService.selectTypeList();
        request.setAttribute("typeList", typeList);
        return PATH + "user_input";
    }
    /**
     * 编辑会员
     * @param request
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(HttpServletRequest request){
        User user = null;
        try{
            Integer id = Integer.valueOf(request.getParameter("id"));
            user = userService.findById(id);
        }catch(Exception e){
            e.printStackTrace();
        }
        List<UserType> typeList = userService.selectTypeList();
        request.setAttribute("dto", user);
        request.setAttribute("typeList", typeList);
        return PATH + "user_input";
    }

    /**
     * 提交
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object save(User user, HttpServletRequest request){
        try {
            Integer id = user.getId();
            if(id == null ){
                user.setPassword(DigestUtils.md5(ConfigContentUtils.getString("upassword", "system.properties")).toString());
                user.setNickname(user.getName());
                user.setState(1);
                user.setIsdelete(0);
                user.setRegistertime(new Date());
                userService.save(user);
            }else{
                userService.update(user);
            }
            return JsonMsgUtil.getSuccessJsonMsg("操作成功");
        }catch (Exception e){
            e.printStackTrace();
            return JsonMsgUtil.getFailJsonMsg("操作失败");
        }
    }

    /**
     * 删除
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public Object remove(User user){
        try{
           userService.delete(user);
        }catch(Exception e){
            e.printStackTrace();
            return JsonMsgUtil.getFailJsonMsg("删除失败!");
        }
        return JsonMsgUtil.getSuccessJsonMsg("删除成功!");
    }
}