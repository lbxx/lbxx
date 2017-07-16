package com.cdhaixun.shop.web;

import com.cdhaixun.common.appVo.Knowledge;
import com.cdhaixun.common.appVo.Result;
import com.cdhaixun.domain.Image;
import com.cdhaixun.domain.KnowledgeType;
import com.cdhaixun.shop.service.IImageService;
import com.cdhaixun.shop.service.IKnowledgeService;
import com.cdhaixun.shop.service.IKnowledgeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 2017-07-16.
 */
@Controller
@RequestMapping("knowledgeApp")
public class KnowledgeAppController {
    @Autowired
    private IKnowledgeService knowledgeService;
    @Autowired
    private IImageService imageService;

    @RequestMapping(value = "listByType", method = RequestMethod.POST)
    @ResponseBody
    public Result listByType(@RequestBody Knowledge knowledge, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Result result = new Result();
        List<com.cdhaixun.domain.Knowledge> knowledgeList = knowledgeService.findByTypeId(knowledge.getTypeid());
        for (com.cdhaixun.domain.Knowledge knowledge1 : knowledgeList) {
            List<Image> imageList = imageService.findByKnowledgeId(knowledge1.getId());
            knowledge1.setImageList(imageList);
        }
        result.setData(knowledgeList);
        result.setResult(true);
        return result;
    }

    @RequestMapping(value = "knowledgeDetail", method = {RequestMethod.POST,RequestMethod.GET})
    public String knowledgeDetail(Knowledge knowledge, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Model model) {
        com.cdhaixun.domain.Knowledge knowledge1 = knowledgeService.findById(knowledge.getId());
        model.addAttribute("knowledge", knowledge1);
        return "app/knowledgeDetail";
    }

}
