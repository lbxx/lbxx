
package com.cdhaixun.shop.web;

import com.cdhaixun.domain.PotionCategory;
import com.cdhaixun.shop.service.IPotionCategoryService;
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
import java.util.Map;

/**
 * 药水分类管理
 * @Author tanggm
 * @Date 2017/8/13 18:10
 */
@Controller
@RequestMapping(value = "/potionCategory")
public class PotionCategoryController {
    private static final String PATH = "potionCategory/";
    @Autowired
    private IPotionCategoryService potionCategoryService;
    /**
     * 首页
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(){
        return PATH + "potionCategory_list";
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
            Pager resPager = potionCategoryService.getPotionCategoryList(pager, parMap);
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
        return PATH + "potionCategory_input";
    }
    /**
     * 编辑
     * @param request
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(HttpServletRequest request){
        PotionCategory potionCategory = null;
        try{
            Integer id = Integer.valueOf(request.getParameter("id"));
            potionCategory = potionCategoryService.findById(id);
        }catch(Exception e){
            e.printStackTrace();
        }
        request.setAttribute("dto", potionCategory);
        return PATH + "potionCategory_input";
    }

    /**
     * 保存
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object save(PotionCategory potionCategory){
        try {
            Integer id = potionCategory.getId();
            if(id == null ){
                potionCategory.setIsdelete(false);
                potionCategory.setCreatetime(new Date());
                potionCategoryService.save(potionCategory);
            }else{
                potionCategoryService.update(potionCategory);
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
    public Object remove(PotionCategory potionCategory){
        try{
            potionCategory.setIsdelete(true);
            potionCategoryService.update(potionCategory);
        }catch(Exception e){
            e.printStackTrace();
            return JsonMsgUtil.getFailJsonMsg("删除失败!");
        }
        return JsonMsgUtil.getSuccessJsonMsg("删除成功!");
    }
}