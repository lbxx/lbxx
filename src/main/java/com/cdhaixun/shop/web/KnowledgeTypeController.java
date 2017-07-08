
package com.cdhaixun.shop.web;

import com.cdhaixun.domain.KnowledgeType;
import com.cdhaixun.shop.service.IKnowledgeTypeService;
import com.cdhaixun.util.JsonMsgUtil;
import com.cdhaixun.util.MapUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 知识库类型管理
 * @Author tanggm
 * @Date 2017/6/14 23:10
 */
@Controller
@RequestMapping(value = "/knowledgetype")
public class KnowledgeTypeController {
    private static final String PATH = "knowledgetype/";
    @Autowired
    private IKnowledgeTypeService knowledgeTypeService;
    /**
     * 首页
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(){
        return PATH + "type_list";
    }

    /**
     * 查询列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryList", method = RequestMethod.GET)
    public PageInfo<KnowledgeType> queryList(HttpServletRequest request){
        try{
            Map<String, Object> parMap = MapUtils.getParamMapObj(request);
            Page<KnowledgeType> page = PageHelper.startPage(Integer.valueOf(parMap.get("pageNum").toString()), Integer.valueOf(parMap.get("pageSize").toString()), true);
            List<KnowledgeType> list = knowledgeTypeService.getTypeList(parMap);
            PageInfo<KnowledgeType> pageInfo = page.toPageInfo();
            return pageInfo;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 添加
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(){
        return PATH + "type_input";
    }
    /**
     * 编辑
     * @param request
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(HttpServletRequest request){
        KnowledgeType knowledgeType = null;
        try{
            Integer id = Integer.valueOf(request.getParameter("id"));
            knowledgeType = knowledgeTypeService.findById(id);
        }catch(Exception e){
            e.printStackTrace();
        }
        request.setAttribute("dto", knowledgeType);
        return PATH + "type_input";
    }

    /**
     * 保存
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object save(KnowledgeType knowledgeType, HttpServletRequest request){
        try {
            Integer id = knowledgeType.getId();
            if(id == null ){
                knowledgeType.setIsdelete(false);
                knowledgeTypeService.save(knowledgeType);
            }else{
                knowledgeTypeService.update(knowledgeType);
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
    public Object remove(KnowledgeType knowledgeType){
        try{
            knowledgeTypeService.delete(knowledgeType);
        }catch(Exception e){
            e.printStackTrace();
            return JsonMsgUtil.getFailJsonMsg("删除失败!");
        }
        return JsonMsgUtil.getSuccessJsonMsg("删除成功!");
    }
}