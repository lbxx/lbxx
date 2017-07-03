package com.cdhaixun.shop.web;

import com.cdhaixun.common.appVo.Mobile;
import com.cdhaixun.common.constant.SessionConstant;
import com.cdhaixun.common.emun.Code;
import com.cdhaixun.common.redisVo.Captcha;
import com.cdhaixun.common.vo.Result;
import com.cdhaixun.common.web.BaseController;
import com.cdhaixun.domain.Baby;
import com.cdhaixun.domain.Manager;
import com.cdhaixun.domain.User;
import com.cdhaixun.shop.service.IBabyService;
import com.cdhaixun.shop.service.IManagerService;
import com.cdhaixun.shop.service.IUserService;
import com.cdhaixun.util.SMSUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by tangxinmao on 2017/5/30.
 */
@Controller
@RequestMapping(value = "login")
public class LoginController extends BaseController {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private IManagerService managerService;
    @Autowired
    private IBabyService babyService;
    @Autowired
    private IUserService userService;


    /**
     * app端请求验证码
     *
     * @return
     */
    @RequestMapping(value = "captcha", method = RequestMethod.POST )
    @ResponseBody
    public com.cdhaixun.common.appVo.Result captcha(@RequestBody Mobile mobile, HttpSession httpSession) {
        com.cdhaixun.common.appVo.Result result = new com.cdhaixun.common.appVo.Result();
        String randomNumeric = RandomStringUtils.randomNumeric(6);//随机生成六位数字
        HashMap re = SMSUtil.sendSMS(mobile.getMobile(), randomNumeric);
        if ("000000".equals(re.get("statusCode"))) {
            result.setResult(true);
            Captcha captcha = new Captcha();
            captcha.setCaptcha(randomNumeric);
            captcha.setCreateTime(new Date());
            redisTemplate.boundHashOps("captcha").put(mobile, captcha);//发送成功后将验证码放入redis
            //正常返回输出data包体信息（map）
            HashMap<String, Object> data = (HashMap<String, Object>) re.get("data");
            Set<String> keySet = data.keySet();
            for (String key : keySet) {
                Object object = data.get(key);
                System.out.println(key + " = " + object);
            }
        } else {
            //异常返回输出错误码和错误信息
           // result.setCode((Code) re.get("statusCode"));
            result.setMsg("错误码=" + re.get("statusCode") + " 错误信息= " + re.get("statusMsg"));

        }

        return result;
    }
    @RequestMapping(value = "appLogin", method = RequestMethod.POST )
    @ResponseBody
    public com.cdhaixun.common.appVo.Result appLogin(@RequestBody Mobile mobile, HttpSession httpSession) throws Exception {
        com.cdhaixun.common.appVo.Result result = new com.cdhaixun.common.appVo.Result();
        Captcha captcha = (Captcha) redisTemplate.boundHashOps("captcha").entries().remove(mobile.getMobile());
        if(captcha!=null&&captcha.getCaptcha().equals(mobile.getCaptcha())&&(new Date().getTime()-captcha.getCreateTime().getTime())<3*60*1000){
            User user= userService.findByMobile(mobile.getMobile());
            if(user==null){
                userService.save(user);
            }else{
               List<Baby> babyList= babyService.findByUserId(user.getId());
                user.setBabyList(babyList);
            }
            result.setData(user);
            result.setResult(true);
        }else{
            result.setCode(Code.CAPTCHA_ERROR);
        }
        return result;
    }
    /**
     * 登陆
     *
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(String account, String password, String kaptcha,HttpSession httpSession, HttpServletRequest request) {
        Result result = new Result();
        if (!kaptcha.equals(httpSession.getAttribute(SessionConstant.KAPTCHA_SESSION_KEY))) {
            result.setCode(Code.CAPTCHA_ERROR);
            result.setMsg("验证码错误");
            return  result;
        }
        UsernamePasswordToken token = new UsernamePasswordToken(account,  DigestUtils.sha512Hex(password));
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
            result.setResult(true);
            result.setMsg("登陆成功！");
            return result;
        } catch (UnknownAccountException uae) {
            result.setCode(Code.UNKNOWN_ACCOUNT);
            result.setMsg("账号未注册");
        } catch (IncorrectCredentialsException ice) {
            result.setCode(Code.PASSWORD_ERROR);
            result.setMsg( "密码不正确");
        } catch (LockedAccountException lae) {
            result.setCode(Code.ACCOUNT_LOCKED);
            result.setMsg( "账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            result.setCode(Code.EXCESSIVE_ATTEMPTS);
            result.setMsg("登录次数过多");
        } catch (AuthenticationException ae) {
            result.setCode(Code.ACCOUNT_OR_PASSWORD_ERROR);
            result.setMsg( "用户名或密码不正确");
        }
        return result;
    }

    /**
     * 异步验证验证码
     *
     * @return
     */
    @RequestMapping(value = "checkKaptcha", method = RequestMethod.POST)
    @ResponseBody
    public boolean checkKaptcha(String kaptcha, HttpSession httpSession) {
        if (kaptcha.equals(httpSession.getAttribute(SessionConstant.KAPTCHA_SESSION_KEY))) {
            return true;
        } else {
            return false;
        }

    }   

}
