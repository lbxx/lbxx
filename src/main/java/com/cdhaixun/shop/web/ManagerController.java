package com.cdhaixun.shop.web;

import com.cdhaixun.common.vo.Result;
import com.cdhaixun.domain.Manager;
import com.cdhaixun.domain.Role;
import com.cdhaixun.shop.service.IManagerService;
import com.cdhaixun.shop.service.IRoleSevice;
import com.cdhaixun.shop.service.IUploadService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * create by tangxinmxo
 */
@Controller
@RequestMapping(value = "/manage")
public class ManagerController {
    @Autowired
    IManagerService managerService;
    @Autowired
    IRoleSevice roleSevice;
    @Autowired
    private IUploadService uploadService;

    /**
     * 账号首页
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
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
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public PageInfo<Manager> list(HttpServletRequest httpServletRequest, Manager manager) {
        Page<Manager> page = PageHelper.startPage(Integer.valueOf(httpServletRequest.getParameter("pageNum")), Integer.valueOf(httpServletRequest.getParameter("pageSize")), true);
        List<Manager> managerList = managerService.findByManager(manager);
        PageInfo<Manager> pageInfo = page.toPageInfo();
        return pageInfo;
    }

    /**
     * 添加账号页面
     */
    @RequestMapping(value = {"/add","/edit"}, method = RequestMethod.GET)
    public String addIndex(Model model,Manager manager) {
        List<Role> roleList= roleSevice.findAll();
        if(manager.getId()!=null){
            Manager managerDb=   managerService.findById(manager.getId());
            model.addAttribute("manager",managerDb);
        }
        model.addAttribute("roleList",roleList);
        return "manager/add";
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(HttpServletRequest httpServletRequest, Manager manager,MultipartFile file) throws IOException {
        Result result = new Result();
        String img = uploadService.upload(httpServletRequest,file).getData().toString();
        manager.setImg(img);
        manager.setPassword(DigestUtils.sha512Hex(manager.getPassword()));
        managerService.save(manager);
        result.setResult(true);
        return result;
    }
}
