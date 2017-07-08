package com.cdhaixun.common.shiro;

import com.cdhaixun.common.constant.SessionConstant;
import com.cdhaixun.domain.Manager;
import com.cdhaixun.domain.Menu;
import com.cdhaixun.domain.Operate;
import com.cdhaixun.domain.RoleOperate;
import com.cdhaixun.persistence.ManagerMapper;
import com.cdhaixun.shop.service.IManagerService;
import com.cdhaixun.shop.service.IMenuService;
import com.cdhaixun.shop.service.IOperateService;
import com.cdhaixun.shop.service.IRoleOperateService;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private IManagerService managerService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IRoleOperateService roleOperateService;
    @Autowired
    private IOperateService operateService;


    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        String currentUsername = (String) super.getAvailablePrincipal(principals);
          SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
             Subject currentUser = SecurityUtils.getSubject();
             Session session = currentUser.getSession();
            simpleAuthorInfo.addStringPermissions((Collection<String>) session.getAttribute(SessionConstant.PERMISSION_LIST));
            return simpleAuthorInfo;

    }


    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        Manager    manager = managerService.findOneByAccount(token.getUsername());


        if (manager != null) {
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(manager.getAccount(), manager.getPassword(), this.getName());
            this.setSession(SessionConstant.MANAGER, manager);

            String role = manager.getRole();
            List<Menu> menuList = menuService.getMenus(role);
            this.setSession(SessionConstant.MENU_LIST, menuList);

            List<String> permissionList = new ArrayList<String>();
            List<RoleOperate> roleOperateList=roleOperateService.findByRole(role);
            for (RoleOperate roleOperate:roleOperateList) {
                Operate operate=operateService.findById(roleOperate.getOperateid());
                if(operate!=null&&StringUtils.isNotEmpty(operate.getMenucode()))
                    permissionList.add(operate.getMenucode()+":"+operate.getPermission());
            }
            this.setSession(SessionConstant.PERMISSION_LIST, permissionList);
            return authcInfo;
        }
        return null;
    }


    /**
     * 将一些数据放到ShiroSession中,以便于其它地方使用
     * 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
     */
    private void setSession(Object key, Object value) {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            if (null != session) {
                session.setAttribute(key, value);
            }
        }
    }
}