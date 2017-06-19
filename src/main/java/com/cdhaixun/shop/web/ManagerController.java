package com.cdhaixun.shop.web;

import com.cdhaixun.domain.Manager;
import com.cdhaixun.shop.service.IManagerService;
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

/**
 * create by tangxinmxo
 */
@Controller
@RequestMapping(value = "manage")
public class ManagerController {
    @Autowired
    IManagerService managerService;

    /**
     * 账号首页
     */
    @RequestMapping(value = "listIndex", method = RequestMethod.GET)
    public String listIndex() {
        return "manager/list";
    }

    /**
     * 查询账号分页
     *
     * @param httpServletRequest
     * @param manager
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public PageInfo<Manager> list(HttpServletRequest httpServletRequest, Manager manager) {
        Page<Manager> page = PageHelper.startPage(Integer.valueOf(httpServletRequest.getParameter("pageNum")), Integer.valueOf(httpServletRequest.getParameter("pageSize")), true);
        List<Manager> managerList = managerService.findManager(manager);

        PageInfo<Manager> pageInfo = page.toPageInfo();
        return pageInfo;
    }
}
