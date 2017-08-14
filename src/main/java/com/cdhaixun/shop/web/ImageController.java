
package com.cdhaixun.shop.web;

import com.cdhaixun.common.vo.Result;
import com.cdhaixun.domain.Carousel;
import com.cdhaixun.domain.Image;
import com.cdhaixun.domain.Knowledge;
import com.cdhaixun.shop.service.ICarouselService;
import com.cdhaixun.shop.service.IImageService;
import com.cdhaixun.shop.service.IKnowledgeService;
import com.cdhaixun.shop.service.IUploadService;
import com.cdhaixun.util.JsonMsgUtil;
import com.cdhaixun.util.MapUtils;
import com.cdhaixun.vo.ImageVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping(value = "/image")
public class ImageController {
    private static final String PATH = "image/";
    @Autowired
    private IImageService imageService;
    @Autowired
    private IKnowledgeService knowledgeService;
    @Autowired
    IUploadService uploadService;
    @Autowired
    ICarouselService carouselService;
    @Value("#{configProperties['imgServer']}")
    private String imgRoot;
    /**
     * 首页
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(){
        return PATH + "image_list";
    }

    /**
     * 查询列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryList", method = RequestMethod.GET)
    public PageInfo<ImageVo> queryList(HttpServletRequest request){
        try{
            Map<String, Object> parMap = MapUtils.getParamMapObj(request);
            Page<ImageVo> page = PageHelper.startPage(Integer.valueOf(parMap.get("pageNum").toString()), Integer.valueOf(parMap.get("pageSize").toString()), true);
            List<ImageVo> list = imageService.getList(parMap);
            for(ImageVo img : list){
                List<Carousel> carousel = carouselService.findByImageId(img.getId());
                if(carousel != null && !carousel.isEmpty()){
                    img.setSource(carousel.get(0).getPic());
                }
            }
            PageInfo<ImageVo> pageInfo = page.toPageInfo();
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
        List<Knowledge> list = knowledgeService.findList();
        request.setAttribute("knowledgeList", list);
        return PATH + "image_input";
    }
    /**
     * 编辑
     * @param request
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(HttpServletRequest request){
        Image image = null;
        List<Carousel> carouselList = null;
        try{
            Integer id = Integer.valueOf(request.getParameter("id"));
            image = imageService.findById(id);
            carouselList = carouselService.findByImageId(id);
        }catch(Exception e){
            e.printStackTrace();
        }
        List<Knowledge> list = knowledgeService.findList();
        request.setAttribute("knowledgeList", list);
        request.setAttribute("dto", image);
        // 获取图片nginx地址 + 真实地址
        String pic = carouselList.isEmpty()?null:carouselList.get(0).getPic();
        request.setAttribute("imgUrl", imgRoot + pic);
        return PATH + "image_input";
    }

    /**
     * 保存
     * @param
     * @return
     * @throws IOException 
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object save(Image image,HttpServletRequest request,MultipartFile file) throws IOException{
        Result result = null;
        Carousel carousel = new Carousel() ;
        if(file != null){
            result = uploadService.upload(request, file);
            if(result.isResult()){
                carousel.setPic(result.getData().toString());
                carousel.setIsdelete(false);
            }
        }
        try {
            Integer id = image.getId();
            if(id == null ){
                image.setIsdelete(false);
                imageService.save(image);
                carousel.setImageid(image.getId());
                carouselService.save(carousel);
            }else{
                List<Carousel> carousels = carouselService.findByImageId(id);
                imageService.update(image);
                if(!carousels.isEmpty()){
                    carousel = carousels.get(0);
                    carousel.setImageid(id);
                    carousel.setPic(result.getData().toString());
                    carouselService.update(carousel);
                }else{
                    carousel.setImageid(id);
                    carouselService.save(carousel);
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
    public Object remove(Image image){
        try{
            imageService.delete(image);
        }catch(Exception e){
            e.printStackTrace();
            return JsonMsgUtil.getFailJsonMsg("删除失败!");
        }
        return JsonMsgUtil.getSuccessJsonMsg("删除成功!");
    }
}