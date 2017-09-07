
package com.cdhaixun.shop.web;

import com.cdhaixun.common.vo.Result;
import com.cdhaixun.domain.Image;
import com.cdhaixun.domain.Knowledge;
import com.cdhaixun.domain.KnowledgeType;
import com.cdhaixun.shop.service.IImageService;
import com.cdhaixun.shop.service.IKnowledgeService;
import com.cdhaixun.shop.service.IKnowledgeTypeService;
import com.cdhaixun.shop.service.IUploadService;
import com.cdhaixun.util.JsonMsgUtil;
import com.cdhaixun.util.MapUtils;
import com.cdhaixun.vo.KnowledgeVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 知识库类型管理
 * @Author tanggm
 * @Date 2017/6/14 23:10
 */
@Controller
@RequestMapping(value = "/knowledge")
public class KnowledgeController {
    private static final String PATH = "knowledge/";
    @Autowired
    private IKnowledgeService knowledgeService;
    @Autowired
    private IKnowledgeTypeService knowledgeTypeService;
    @Autowired
    IUploadService uploadService;
    @Autowired
    private IImageService imageService;
    /**
     * 首页
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(){
        return PATH + "knowledge_list";
    }

    /**
     * 查询列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryList", method = RequestMethod.GET)
    public PageInfo<KnowledgeVo> queryList(HttpServletRequest request){
        try{
            Map<String, Object> parMap = MapUtils.getParamMapObj(request);
            Page<KnowledgeVo> page = PageHelper.startPage(Integer.valueOf(parMap.get("pageNum").toString()), Integer.valueOf(parMap.get("pageSize").toString()), true);
            List<KnowledgeVo> list = knowledgeService.getList(parMap);
            PageInfo<KnowledgeVo> pageInfo = page.toPageInfo();
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
    public String add(HttpServletRequest request){
        List<KnowledgeType> list = knowledgeTypeService.findList();
        request.setAttribute("typeList", list);
        return PATH + "knowledge_input";
    }
    /**
     * 编辑
     * @param request
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(HttpServletRequest request){
        Knowledge knowledge = null;
        List<Image> imgList = null;
        try{
            Integer id = Integer.valueOf(request.getParameter("id"));
            knowledge = knowledgeService.findById(id);
            imgList = imageService.findByKnowledgeId(id);
        }catch(Exception e){
            e.printStackTrace();
        }
        List<KnowledgeType> list = knowledgeTypeService.findList();
        request.setAttribute("typeList", list);
        request.setAttribute("dto", knowledge);
        request.setAttribute("imgUrl", imgList.isEmpty()?null:imgList.get(0).getSource());
        return PATH + "knowledge_input";
    }

    /**
     * 保存
     * @param
     * @return
     * @throws IOException 
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object save(Knowledge knowledge,HttpServletRequest request,MultipartFile file) throws IOException{
        try {
            Integer id = knowledge.getId();
            Result result = null;
            Image image = new Image();
            if(file != null){
                result = uploadService.upload(request, file);
                image.setSource(result.getData().toString());
                image.setIsdelete(false);
            }
            if(id == null ){
                knowledge.setIsdelete(false);
                knowledgeService.save(knowledge);
                image.setKnowledgeid(knowledge.getId());
                imageService.save(image);
                
            }else{
                List<Image> imgList = imageService.findByKnowledgeId(id);
                knowledgeService.update(knowledge);
                if(!imgList.isEmpty()){
                    image = imgList.get(0);
                    image.setKnowledgeid(id);
                    if(result != null){
                        image.setSource(result.getData().toString());
                    }else{
                        image.setSource(imgList.get(0).getSource());
                    }
                    imageService.update(image);
                }else{
                    image.setKnowledgeid(id);
                    imageService.save(image);
                }
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
    public Object remove(Knowledge knowledge){
        try{
            knowledgeService.delete(knowledge);
        }catch(Exception e){
            e.printStackTrace();
            return JsonMsgUtil.getFailJsonMsg("删除失败!");
        }
        return JsonMsgUtil.getSuccessJsonMsg("删除成功!");
    }
}