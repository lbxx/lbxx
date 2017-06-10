package com.cdhaixun.shop.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdhaixun.common.constant.SessionConstant;
import com.cdhaixun.common.emun.Code;
import com.cdhaixun.common.redisVo.Captcha;
import com.cdhaixun.common.util.SMSUtil;
import com.cdhaixun.common.vo.Result;
import com.cdhaixun.common.web.BaseController;
import com.cdhaixun.domain.Manager;

/**
 * Created by tangxinmao on 2017/5/30.
 */
@Controller
@RequestMapping(value = "login")
public class LoginController extends BaseController {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * app端请求验证码
     *
     * @return
     */
    @RequestMapping(value = "captcha", method = RequestMethod.POST)
    @ResponseBody
    public Result captcha(@RequestBody String mobile, HttpSession httpSession) {
        Result result = new Result();
        String randomNumeric = RandomStringUtils.randomNumeric(6);//随机生成六位数字
        HashMap re = SMSUtil.sendSMS(mobile, randomNumeric);
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
            result.setCode((Code) re.get("statusCode"));
            result.setMsg("错误码=" + re.get("statusCode") + " 错误信息= " + re.get("statusMsg"));

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
