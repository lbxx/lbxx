
package com.cdhaixun.shop.web;

import com.cdhaixun.domain.Potion;
import com.cdhaixun.domain.PotionCategory;
import com.cdhaixun.shop.service.IPotionService;
import com.cdhaixun.shop.service.IUploadService;
import com.cdhaixun.util.JsonMsgUtil;
import com.cdhaixun.util.MapUtils;
import com.cdhaixun.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 药水管理
 * @Author tanggm
 * @Date 2017/8/13 18:30
 */
@Controller
@RequestMapping(value = "/potion")
public class PotionController {
    private static final String PATH = "potion/";
    @Autowired
    IPotionService potionService;
    @Autowired
    IUploadService uploadService;
    /**
     * 会员首页
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(){
        return PATH + "potion_list";
    }

    /**
     * 查询用户列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryList", method = RequestMethod.GET)
    public Object userList(Pager pager, HttpServletRequest request){
        try{
            Map<String, Object> parMap = MapUtils.getParamMapObj(request);
            Pager resPager = potionService.getPotionList(pager, parMap);
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
        List<PotionCategory> potionCategoryList = potionService.selectPotionCategoryList();
        request.setAttribute("potionCategoryList", potionCategoryList);
        return PATH + "potion_input";
    }
    /**
     * 编辑会员
     * @param request
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(HttpServletRequest request){
        Potion potion = null;
        try{
            Integer id = Integer.valueOf(request.getParameter("id"));
            potion = potionService.findById(id);
        }catch(Exception e){
            e.printStackTrace();
        }
        List<PotionCategory> potionCategoryList = potionService.selectPotionCategoryList();
        request.setAttribute("potionCategoryList", potionCategoryList);
        request.setAttribute("dto", potion);
        return PATH + "potion_input";
    }

    /**
     * 提交
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object save(Potion potion, HttpServletRequest request, @RequestParam(value="file",required=false) MultipartFile file){
        try {
            Integer id = potion.getId();
            if(file != null){
                String picPath =uploadService.upload(request,file).getData().toString();
                potion.setPic(picPath);
            }
            if(id == null ){
                potion.setIsdelete(false);
                potion.setCreatetime(new Date());
                potionService.save(potion);
            }else{
                potionService.update(potion);
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
    public Object remove(Potion potion){
        try{
            potion.setIsdelete(true);
           potionService.update(potion);
        }catch(Exception e){
            e.printStackTrace();
            return JsonMsgUtil.getFailJsonMsg("删除失败!");
        }
        return JsonMsgUtil.getSuccessJsonMsg("删除成功!");
    }
}