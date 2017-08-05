package com.cdhaixun.shop.web;

import com.cdhaixun.domain.Role;
import com.cdhaixun.shop.service.IManagerService;
import com.cdhaixun.shop.service.IRoleSevice;
import com.cdhaixun.shop.service.IUploadService;
import com.cdhaixun.vo.PermissionVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 角色管理
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController {
    @Autowired
    IManagerService managerService;
    @Autowired
    IRoleSevice roleSevice;
    @Autowired
    private IUploadService uploadService;

    /**
     * 角色首页
     */
    @RequestMapping(value = "listIndex", method = RequestMethod.GET)
    public String listIndex() {
        return "role/list";
    }

    /**
     * 列表
     * @param httpServletRequest
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public PageInfo<Role> list(HttpServletRequest httpServletRequest) {
        Page<Role> page = PageHelper.startPage(Integer.valueOf(httpServletRequest.getParameter("pageNum")), Integer.valueOf(httpServletRequest.getParameter("pageSize")), true);
        List<Role> roleList = roleSevice.findAll();
        PageInfo<Role> pageInfo = page.toPageInfo();
        return pageInfo;
    }
    /**
     * 查看编辑权限
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String view(HttpServletRequest request){
        // 查询选择的具体需要修改的角色
        Role role = roleSevice.findById(Integer.valueOf(request.getParameter("id")));
        String code = "";
        if(role != null){
            code = role.getCode();
        }
        //Manager manager = (Manager) request.getSession().getAttribute(SessionConstant.MANAGER);
        //String role = manager.getRole();
        //这里查询权限可供选择的时候，查询权限最多的，权限列表展示完全
        String perRole = "admin";
        //admin管理员最多的权限查询
        List<PermissionVo> permissionList = managerService.getPermissionList(perRole);
        // 查询需要修改的角色的权限
        List<PermissionVo> mypermissionList = managerService.getPermissionList(code);
        // 循环判断是否选中
        for (PermissionVo permissionVo : permissionList) {
            for (PermissionVo myPermissionVo : mypermissionList) {
                if(permissionVo.getId().compareTo(myPermissionVo.getId()) == 0){
                    permissionVo.setChecked(true);
                    permissionVo.setOpen(true);
                    break;
                }
            }
        }
        JSONArray jsonArray = JSONArray.fromObject(permissionList);
        request.setAttribute("permissionStr", jsonArray);
        // 返回选择的具体需要修改的角色
        request.setAttribute("code", code);
        return "role/rolePermission_input";
    }

    /**
     * 保存权限
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/savePermission", method = RequestMethod.POST)
    public Object savePermission(HttpServletRequest request){
        String permissions = request.getParameter("permissions");
        String code = request.getParameter("code");
        int i = 0;
        try{
           i = roleSevice.savePermission(permissions, code);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        if(i > 0){
            return true;
        }else{
            return false;
        }
    }

}
