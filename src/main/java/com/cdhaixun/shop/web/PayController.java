package com.cdhaixun.shop.web;

import com.cdhaixun.common.emun.AppointmentState;
import com.cdhaixun.common.web.BaseController;
import com.cdhaixun.common.yyyVo.Pay;
import com.cdhaixun.common.yyyVo.PayResult;
import com.cdhaixun.domain.Appointment;
import com.cdhaixun.domain.Store;
import com.cdhaixun.shop.service.IAppointmentService;
import com.cdhaixun.shop.service.IPayInfoService;
import com.cdhaixun.shop.service.IStoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by tangxinmao on 2017/6/23.
 */
@Controller
@RequestMapping("pay")
public class PayController extends BaseController {
    private static HttpClient hc = HttpClients.createDefault();

    @Value("#{configProperties['scanpay']}")
    private String scanpay;

    @Value("#{configProperties['domain']}")
    private String domain;

    @Value("#{configProperties['notifyUrl']}")
    private String notifyUrl;
    @Autowired
    private IStoreService storeService;
    @Autowired
    private IPayInfoService payInfoService;
    @Autowired
    private IAppointmentService appointmentService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 手机扫码支付
     *
     * @param pay
     * @return
     */
    @RequestMapping(value = "scanpay", method = RequestMethod.POST)
    @ResponseBody
    public PayResult scanpay(@RequestBody Pay pay) throws IOException {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("appid", pay.getAppid()));
        params.add(new BasicNameValuePair("authcode", pay.getAuthcode()));
        params.add(new BasicNameValuePair("body", pay.getBody()));
        params.add(new BasicNameValuePair("cusid", pay.getCusid()));
        params.add(new BasicNameValuePair("key", pay.getKey()));
        params.add(new BasicNameValuePair("randomstr", pay.getRandomstr()));
        params.add(new BasicNameValuePair("remark", pay.getRemark()));
        params.add(new BasicNameValuePair("reqsn", pay.getReqsn()));
        params.add(new BasicNameValuePair("trxamt", pay.getTrxamt() + ""));
        String url = URLEncodedUtils.format(params, "utf-8");
        String sign = DigestUtils.md5Hex(url).toUpperCase();
        params.add(new BasicNameValuePair("sign", sign));
        HttpPost httppost = new HttpPost(scanpay + "scanpay?" + URLEncodedUtils.format(params, Charset.forName("UTF-8")));
        HttpResponse httpResponse = hc.execute(httppost);
        HttpEntity httpEntity = httpResponse.getEntity();
        String json = IOUtils.toString(httpEntity.getContent(), "utf-8");
        PayResult payResult = objectMapper.readValue(json, PayResult.class);
        return payResult;
    }

    /**
     * 生成订单
     *
     * @param pay
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "pay", method = RequestMethod.POST)
    @ResponseBody
    public PayResult pay(@RequestBody com.cdhaixun.common.appVo.Pay pay, HttpServletRequest httpServletRequest) throws IOException {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        Store store = storeService.findById(pay.getStoreid());
        params.add(new BasicNameValuePair("appid", store.getAppid()));
        params.add(new BasicNameValuePair("cusid", store.getCusid()));
        params.add(new BasicNameValuePair("key", store.getKey()));
        params.add(new BasicNameValuePair("notify_url", domain + "pay/callback"));
        params.add(new BasicNameValuePair("paytype", pay.getPaytype()));
        params.add(new BasicNameValuePair("randomstr", UUID.randomUUID().toString()));

        params.add(new BasicNameValuePair("reqsn", UUID.randomUUID().toString()));
        params.add(new BasicNameValuePair("sub_appid", store.getSubappid()));
        params.add(new BasicNameValuePair("sub_mchid", store.getSubmchid()));
        params.add(new BasicNameValuePair("trxamt", pay.getTrxamt().toString()));

        String url = URLEncodedUtils.format(params, "utf-8");
        String sign = DigestUtils.md5Hex(url).toUpperCase();
        params.add(new BasicNameValuePair("sign", sign));
        HttpPost httppost = new HttpPost(scanpay + "pay?" + URLEncodedUtils.format(params, Charset.forName("UTF-8")));
        HttpResponse httpResponse = hc.execute(httppost);
        HttpEntity httpEntity = httpResponse.getEntity();
        String json = IOUtils.toString(httpEntity.getContent(), "utf-8");
        PayResult payResult = objectMapper.readValue(json, PayResult.class);
        return payResult;
    }
   /* @RequestMapping(value = "callback", method = RequestMethod.POST)
    @ResponseBody
    public PayResult callback(@RequestParam String appid,
                              @RequestParam String outtrxid,
                              @RequestParam String trxcode,
                              @RequestParam String trxid,
                              @RequestParam String trxamt,
                              @RequestParam String trxdate,
                              @RequestParam String paytime,
                              @RequestParam String chnltrxid,
                              @RequestParam String trxstatus,
                              @RequestParam String cusid,
                              @RequestParam String termno,
                              @RequestParam String termbatchid,
                              @RequestParam String termtraceno,
                              @RequestParam String termauthno,
                              @RequestParam String termrefnum,
                              @RequestParam String trxreserved,
                              @RequestParam String srctrxid,
                              @RequestParam String cusorderid,
                              @RequestParam String acct,
                              @RequestParam String sign)  {
        PayResult payResult= new PayResult();
        PayInfo payInfo= payInfoService.findByTrxid(trxid);
       Appointment appointment= appointmentService.findById(Integer.parseInt(payInfo.getReqsn()));
         appointment.setPaystate(1);
         appointmentService.save(appointment);
        return  payResult;
    }

    */

    /**
     * 查询订单支付状态
     *
     * @param payResult
     * @return
     * @throws IOException
     *//*
    @RequestMapping(value = "verifyPayState", method = RequestMethod.POST)
    @ResponseBody
    public PayResult verifyPayState(@RequestBody  com.cdhaixun.common.appVo.Pay pay) throws IOException {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("appid", pay.getAppid()));
        params.add(new BasicNameValuePair("cusid", pay.getCusid()));
        params.add(new BasicNameValuePair("key", pay.getKey()));
        params.add(new BasicNameValuePair("randomstr", pay.getRandomstr()));
        params.add(new BasicNameValuePair("reqsn", pay.getReqsn()));
        String url = URLEncodedUtils.format(params, "utf-8");
        String sign = DigestUtils.md5Hex(url).toUpperCase();
        params.add(new BasicNameValuePair("sign", sign));
        HttpPost httppost = new HttpPost(scanpay+"pay?" +URLEncodedUtils.format(params, Charset.forName("UTF-8")));
        HttpResponse httpResponse = hc.execute(httppost);
        HttpEntity httpEntity = httpResponse.getEntity();
        String json = IOUtils.toString(httpEntity.getContent(),"utf-8");
        PayResult payResult= objectMapper.readValue(json, PayResult.class);
        return  payResult;
    }
*/
    @RequestMapping(value = "callback")
    @ApiOperation(value = "支付服务器回调地址")
    public void callback(
            @RequestParam String merchantId,
            @RequestParam String version,
            @RequestParam(required = false) String language,
            @RequestParam String signType,
            @RequestParam(required = false) String payType,
            @RequestParam(required = false) String issuerId,
            @RequestParam String paymentOrderId,
            @RequestParam String orderNo,
            @RequestParam String orderDatetime,
            @RequestParam String orderAmount,
            @RequestParam String payDatetime,
            @RequestParam String payAmount,
            @RequestParam(required = false) String ext1,
            @RequestParam(required = false) String ext2,
            @RequestParam String payResult,
            @RequestParam(required = false) String errorCode,
            @RequestParam String returnDatetime,
            @RequestParam String signMsg
    ) {
        if(signType.equals("0")){
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("merchantId", merchantId));
            params.add(new BasicNameValuePair("version", version));
            params.add(new BasicNameValuePair("language", language));
            params.add(new BasicNameValuePair("signType", signType));
            params.add(new BasicNameValuePair("payType", payType));
            params.add(new BasicNameValuePair("issuerId", issuerId));
            params.add(new BasicNameValuePair("paymentOrderId", paymentOrderId));
            params.add(new BasicNameValuePair("orderNo", orderNo));
            params.add(new BasicNameValuePair("orderDatetime", orderDatetime));
            params.add(new BasicNameValuePair("orderAmount", orderAmount));
            params.add(new BasicNameValuePair("payDatetime", payDatetime));
            params.add(new BasicNameValuePair("payAmount", payAmount));
            params.add(new BasicNameValuePair("ext1", ext1));
            params.add(new BasicNameValuePair("ext2", ext2));
            params.add(new BasicNameValuePair("payResult", payResult));
            params.add(new BasicNameValuePair("errorCode", errorCode));
            params.add(new BasicNameValuePair("returnDatetime", returnDatetime));
            String url = URLEncodedUtils.format(params, "utf-8");
            String sign = DigestUtils.md5Hex(url).toUpperCase();
            if(sign.equals(signMsg)){
                //修改订单状体
                Appointment appointment = appointmentService.findById(Integer.parseInt(orderNo));
                appointment.setState(AppointmentState.PAY.toString());
                appointmentService.save(appointment);
            }
        }
        if(signType.equals("1")){
          /*  String fileMd5String = SecurityUtil.MD5Encode(signString);
             boolean isVerified =
                    SecurityUtil.verifyByRSA(certPath,fileMd5String.getByte(),Base64.
                            decode(signMsg));*/

            //修改订单状体
            Appointment appointment = appointmentService.findById(Integer.parseInt(orderNo));
            appointment.setState(AppointmentState.PAY.toString());
            appointmentService.save(appointment);
        }
    }


}
