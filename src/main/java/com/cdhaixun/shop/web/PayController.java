package com.cdhaixun.shop.web;

import com.cdhaixun.common.yyyVo.Pay;
import com.cdhaixun.common.yyyVo.PayResult;
import com.cdhaixun.common.appVo.Result;
import com.cdhaixun.common.web.BaseController;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangxinmao on 2017/6/23.
 */
@Controller
@RequestMapping("pay")
public class PayController extends BaseController{
    private static HttpClient hc = HttpClients.createDefault();

    @Value("#{configProperties['scanpay']}")
    private String scanpay;
    @Autowired
    private ObjectMapper objectMapper;
    /**
     * 手机扫码支付
     * @param pay
     * @return
     */
    @RequestMapping(value = "scanpay", method = RequestMethod.POST)
    @ResponseBody
    public PayResult pay(@RequestBody  Pay pay) throws IOException {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("appid", pay.getAppid()));
        params.add(new BasicNameValuePair("authcode", pay.getAuthcode()));
        params.add(new BasicNameValuePair("body", pay.getBody()));
        params.add(new BasicNameValuePair("cusid", pay.getCusid()));
        params.add(new BasicNameValuePair("key", pay.getKey()));
        params.add(new BasicNameValuePair("randomstr", pay.getRandomstr()));
        params.add(new BasicNameValuePair("remark", pay.getRemark()));
        params.add(new BasicNameValuePair("reqsn", pay.getReqsn()));
        params.add(new BasicNameValuePair("trxamt",pay.getTrxamt()+""));
        String url = URLEncodedUtils.format(params, "utf-8");
        String sign = DigestUtils.md5Hex(url).toUpperCase();
        params.add(new BasicNameValuePair("sign", sign));
        HttpPost httppost = new HttpPost(scanpay+"?" +URLEncodedUtils.format(params, "utf-8"));
        HttpResponse httpResponse = hc.execute(httppost);
        HttpEntity httpEntity = httpResponse.getEntity();
        String json = IOUtils.toString(httpEntity.getContent());
        PayResult payResult= objectMapper.readValue(json, PayResult.class);
        return  payResult;
    }
}
