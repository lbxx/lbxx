
package com.cdhaixun.shop.web;

import com.cdhaixun.domain.ChainStore;
import com.cdhaixun.domain.UserType;
import com.cdhaixun.shop.service.IUserTypeService;
import com.cdhaixun.util.JsonMsgUtil;
import com.cdhaixun.util.MapUtils;
import com.cdhaixun.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 会员管理
 * @Author tanggm
 * @Date 2017/6/14 23:10
 */
@Controller
@RequestMapping(value = "/usertype")
public class UserTypeController {
    private static final String PATH = "usertype/";
    @Autowired
    private IUserTypeService userTypeService;
    /**
     * 首页
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(){
        return PATH + "type_list";
    }

    /**
     * 查询列表
     * @param pager
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryList", method = RequestMethod.GET)
    public Object userList(Pager pager, HttpServletRequest request){
        try{
            Map<String, Object> parMap = MapUtils.getParamMapObj(request);
            Pager resPager = userTypeService.getUserTypeList(pager, parMap);
            return resPager;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 添加
     * @param request
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(HttpServletRequest request){
        List<ChainStore> chainStoreList = userTypeService.getChainStoreList();
        request.setAttribute("chainStoreList", chainStoreList);
        return PATH + "type_input";
    }
    /**
     * 编辑
     * @param request
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(HttpServletRequest request){
        UserType userType = null;
        try{
            Integer id = Integer.valueOf(request.getParameter("id"));
            userType = userTypeService.findById(id);
        }catch(Exception e){
            e.printStackTrace();
        }
        request.setAttribute("dto", userType);
        return PATH + "type_input";
    }

    /**
     * 保存
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object save(UserType userType, HttpServletRequest request){
        try {
            Integer id = userType.getId();
            if(id == null ){
                userType.setState(1);
                userType.setIsdelete(0);
                userType.setCreatetime(new Date());
                userTypeService.save(userType);
            }else{
                userTypeService.update(userType);
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
    public Object remove(UserType userType){
        try{
            userTypeService.delete(userType);
        }catch(Exception e){
            e.printStackTrace();
            return JsonMsgUtil.getFailJsonMsg("删除失败!");
        }
        return JsonMsgUtil.getSuccessJsonMsg("删除成功!");
    }
}