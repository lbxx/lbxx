package cn.lingco.shop.web;
import cn.lingco.common.emun.Code;
import cn.lingco.common.util.Result;
import cn.lingco.common.util.SMSUtil;
import cn.lingco.common.vo.Captcha;
import cn.lingco.common.web.BaseController;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLXML;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

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
     * @return
     */
    @RequestMapping(value = "captcha",method = RequestMethod.POST)
    @ResponseBody
    public Result captcha( @RequestBody String mobile,HttpSession httpSession){
        Result result=new Result();
        String randomNumeric = RandomStringUtils.randomNumeric(6);//随机生成六位数字
        HashMap re = SMSUtil.sendSMS(mobile,randomNumeric);
        if("000000".equals(re.get("statusCode"))){
            result.setResult(true);
            Captcha captcha=     new Captcha();
            captcha.setCaptcha(randomNumeric);
            captcha.setCreateTime(new Date());
            redisTemplate.boundHashOps("captcha").put(mobile,captcha);//发送成功后将验证码放入redis
            //正常返回输出data包体信息（map）
            HashMap<String,Object> data = (HashMap<String, Object>) re.get("data");
            Set<String> keySet = data.keySet();
            for(String key:keySet){
                Object object = data.get(key);
                System.out.println(key +" = "+object);
            }
        }else{
            //异常返回输出错误码和错误信息
            result.setCode((Code) re.get("statusCode"));
            result.setMsg("错误码=" + re.get("statusCode") +" 错误信息= "+re.get("statusMsg"));

        }

        return  result;
    }
    /**
     * 登陆
     * @return
     */
    @RequestMapping(value = "login")
    @ResponseBody
    public Result login(){
        Result result=new Result();
        result.setResult(true);
        return  result;
    }
}
