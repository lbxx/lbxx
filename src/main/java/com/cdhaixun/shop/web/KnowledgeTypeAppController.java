package com.cdhaixun.shop.web;

import com.cdhaixun.common.appVo.Result;
import com.cdhaixun.domain.KnowledgeType;
import com.cdhaixun.shop.service.IKnowledgeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("knowledgeTypeApp")
public class KnowledgeTypeAppController {
    @Autowired
    private IKnowledgeTypeService knowledgeTypeService;

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public Result list(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Result result = new Result();
        List<KnowledgeType> knowledgeTypeList = knowledgeTypeService.findList();
        result.setData(knowledgeTypeList);
        result.setResult(true);
        return result;
    }

}
