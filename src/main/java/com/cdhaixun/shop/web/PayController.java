package com.cdhaixun.shop.web;


import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.allinpay.ets.client.SecurityUtil;
import com.allinpay.ets.client.util.Base64;
import com.cdhaixun.common.emun.AppointmentState;
import com.cdhaixun.common.web.BaseController;
import com.cdhaixun.common.wechatPay.*;
import com.cdhaixun.common.yyyVo.Pay;
import com.cdhaixun.common.yyyVo.PayResult;
import com.cdhaixun.domain.Appointment;
import com.cdhaixun.domain.Store;
import com.cdhaixun.shop.service.IAppointmentService;
import com.cdhaixun.shop.service.IPayInfoService;
import com.cdhaixun.shop.service.IStoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

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
    @Value("#{configProperties['unifiedorder']}")
    private String unifiedorder;
    @Value("#{configProperties['orderquery']}")
    private String orderquery;


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
     * 通联生成订单
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

    /**
     * 通联回调支付回调地址
     *
     * @param merchantId
     * @param version
     * @param language
     * @param signType
     * @param payType
     * @param issuerId
     * @param paymentOrderId
     * @param orderNo
     * @param orderDatetime
     * @param orderAmount
     * @param payDatetime
     * @param payAmount
     * @param ext1
     * @param ext2
     * @param payResult
     * @param errorCode
     * @param returnDatetime
     * @param signMsg
     */
    @RequestMapping(value = "callback", method = RequestMethod.POST)
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
        StringBuffer signString = new StringBuffer();
        for (NameValuePair param : params) {
            if (param.getValue() != null) {
                signString.append("|" + param.getValue());
            }
        }
        if ("0".equals(signType)) {
            String fileString = signString.substring(1) + "|" + DigestUtils.md5Hex(merchantId);
            String fileMsg = SecurityUtil.MD5Encode(fileString);
            if (signMsg.equals(fileMsg)) {
                //修改订单状体
                Appointment appointment = appointmentService.findById(Integer.parseInt(orderNo));
                appointment.setState(AppointmentState.PAY.toString());
                appointmentService.save(appointment);
            }
        }
        if ("1".equals(signType)) {
            String fileMd5String = SecurityUtil.MD5Encode(signString.substring(1));
            boolean isVerified = SecurityUtil.verifyByRSA("", fileMd5String.getBytes(), Base64.
                    decode(signMsg));

            //修改订单状体
            if (isVerified) {
                Appointment appointment = appointmentService.findById(Integer.parseInt(orderNo));
                appointment.setState(AppointmentState.PAY.toString());
                appointmentService.save(appointment);
            }
        }
    }

    /**
     * 微信支付同一下单
     *
     * @param unifiedOrder
     * @return
     */
    @RequestMapping(value = "payunifiedorder", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "微信支付同一下单")
    public UnifiedOrderResult payunifiedorder(@RequestBody UnifiedOrder unifiedOrder) throws IOException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchAlgorithmException, InvalidKeyException {
        unifiedOrder.setSign_type("HMAC-SHA256");
        //生成随机数
        unifiedOrder.setNonce_str(RandomStringUtils.randomAlphabetic(32));
        //生成签名
        Map describe = BeanUtils.describe(unifiedOrder);
        describe.remove("class");
        Iterator iterator = describe.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry next = (Map.Entry) iterator.next();
            if (next.getValue() == null) {
                iterator.remove();
            }
        }
        TreeMap<Object, Object> treeMap = new TreeMap(describe);
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry o : treeMap.entrySet()) {
            stringBuilder.append("&" + o.getKey() + "=" + o.getValue());
        }
        String substring = stringBuilder.substring(1);
        String key = "192006250b4c09247ec02edce69f6a2d";
        String stringSignTemp = substring + "&key=" + key;//注：key为商户平台设置的密钥key
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacSHA256");
        mac.init(secretKeySpec);
        byte[] bytes = mac.doFinal(stringSignTemp.getBytes());
        String sign = Hex.encodeHexString(bytes).toUpperCase();
        unifiedOrder.setSign(sign);

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        Map map = new Hashtable();
        map.put(Marshaller.JAXB_ENCODING, "UTF-8");// 编码格式
        map.put(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        map.put(Marshaller.JAXB_FRAGMENT, true);
        marshaller.setMarshallerProperties(map);
        marshaller.setClassesToBeBound(UnifiedOrder.class);
        StringWriter stringWriter = new StringWriter();
        StreamResult streamResult = new StreamResult(stringWriter);
        marshaller.marshal(unifiedOrder, streamResult);
        HttpPost httppost = new HttpPost(unifiedorder);
        httppost.setEntity(new StringEntity(stringWriter.toString()));
        HttpResponse httpResponse = hc.execute(httppost);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode == org.apache.http.HttpStatus.SC_OK) {
            HttpEntity httpEntity = httpResponse.getEntity();
            String s = IOUtils.toString(httpEntity.getContent(), "utf-8");
            Jaxb2Marshaller marshaller1 = new Jaxb2Marshaller();
            marshaller1.setClassesToBeBound(UnifiedOrderResult.class);
            UnifiedOrderResult unifiedOrderResult = (UnifiedOrderResult) marshaller1.unmarshal(new StreamSource(new StringReader(s)));
            if ("SUCCESS".equals(unifiedOrderResult.getReturn_code())) {
                //校验签名
                String signReturn = unifiedOrderResult.getSign();
                unifiedOrderResult.setSign(null);
                Map describe1 = BeanUtils.describe(unifiedOrderResult);
                describe1.remove("class");
                Iterator iteratorRetrun = describe1.entrySet().iterator();
                while (iteratorRetrun.hasNext()) {
                    Map.Entry next = (Map.Entry) iteratorRetrun.next();
                    if (next.getValue() == null) {
                        iteratorRetrun.remove();
                    }
                }
                TreeMap<Object, Object> treeMapReturn = new TreeMap(describe1);
                StringBuilder stringBuilderReturn = new StringBuilder();
                for (Map.Entry o : treeMap.entrySet()) {
                    stringBuilderReturn.append("&" + o.getKey() + "=" + o.getValue());
                }
                String substringReturn = stringBuilderReturn.substring(1);
                String stringSignTempReturn = substringReturn + "&key=" + key;
                String s1 = DigestUtils.md5Hex(stringSignTempReturn).toUpperCase();
                if (s1.equals(signReturn)) {
                    return unifiedOrderResult;
                }
            } else {
                return unifiedOrderResult;
            }
        }
        return null;
    }

    /**
     * 微信支付结果通知
     *
     * @param payAction
     */
    @RequestMapping(value = "wechat_notify_url", method = RequestMethod.POST, consumes = "application/xml", produces = "application/xml")
    @ApiOperation(value = "微信支付结果通知")
    @ResponseBody
    public PayReturn wechatNotifyUrl(@RequestBody PayAction payAction) {
        PayReturn payReturn = new PayReturn();
        //业务逻辑
        payReturn.setReturn_code("sdfasdf");
        return payReturn;

    }

    /**
     * 微信订单查询
     *
     * @param orderQuery
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "payorderquery", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "微信订单查询")
    public QueryReturn payorderquery(@RequestBody OrderQuery orderQuery) throws IOException {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        Map map = new Hashtable();
        map.put(Marshaller.JAXB_ENCODING, "UTF-8");// 编码格式
        map.put(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        map.put(Marshaller.JAXB_FRAGMENT, true);
        marshaller.setMarshallerProperties(map);
        marshaller.setClassesToBeBound(OrderQuery.class);
        StringWriter stringWriter = new StringWriter();
        StreamResult streamResult = new StreamResult(stringWriter);
        marshaller.marshal(orderQuery, streamResult);
        HttpPost httppost = new HttpPost(orderquery);
        httppost.setEntity(new StringEntity(stringWriter.toString()));
        HttpResponse httpResponse = hc.execute(httppost);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode == org.apache.http.HttpStatus.SC_OK) {
            HttpEntity httpEntity = httpResponse.getEntity();
            String s = IOUtils.toString(httpEntity.getContent(), "utf-8");
            Jaxb2Marshaller marshaller1 = new Jaxb2Marshaller();
            marshaller1.setClassesToBeBound(QueryReturn.class);
            QueryReturn queryReturn = (QueryReturn) marshaller1.unmarshal(new StreamSource(new StringReader(s)));
            return queryReturn;
        }
        return null;
    }

    @RequestMapping(value = "alipay_notify_url", method = RequestMethod.POST)
    @ApiOperation(value = "支付宝支付结果通知")
    @ResponseBody
    public String alipayNotifyUrl(@RequestParam Date notify_time,
                                  @RequestParam String notify_type,
                                  @RequestParam String notify_id,
                                  @RequestParam String app_id,
                                  @RequestParam String charset,
                                  @RequestParam String version,
                                  @RequestParam String sign_type,
                                  @RequestParam String sign,
                                  @RequestParam String trade_no,
                                  @RequestParam String out_trade_no,
                                  @RequestParam(required = false) String out_biz_no,
                                  @RequestParam(required = false) String buyer_id,
                                  @RequestParam(required = false) String buyer_logon_id,
                                  @RequestParam(required = false) String seller_id,
                                  @RequestParam(required = false) String seller_email,
                                  @RequestParam(required = false) String trade_status,
                                  @RequestParam(required = false) BigDecimal total_amount,
                                  @RequestParam(required = false) BigDecimal receipt_amount,
                                  @RequestParam(required = false) BigDecimal invoice_amount,
                                  @RequestParam(required = false) BigDecimal buyer_pay_amount,
                                  @RequestParam(required = false) BigDecimal point_amount,
                                  @RequestParam(required = false) BigDecimal refund_fee,
                                  @RequestParam(required = false) String subject,
                                  @RequestParam(required = false) String body,
                                  @RequestParam(required = false) Date gmt_create,
                                  @RequestParam(required = false) Date gmt_payment,
                                  @RequestParam(required = false) Date gmt_refund,
                                  @RequestParam(required = false) Date gmt_close,
                                  @RequestParam(required = false) String fund_bill_list,
                                  @RequestParam(required = false) String passback_params,
                                  @RequestParam(required = false) String voucher_detail_list
                                , HttpServletRequest httpServletRequest
    ) throws AlipayApiException {
        TreeMap<String, String> treeMap = new TreeMap();
        treeMap.put("notify_time", DateFormatUtils.format(notify_time, "yyyy-MM-dd HH:mm:ss"));
        treeMap.put("notify_type", notify_type);
        treeMap.put("notify_id", notify_id);
        treeMap.put("app_id", app_id);
        treeMap.put("charset", charset);
        treeMap.put("version", version);
        treeMap.put("sign_type", sign_type);
        treeMap.put("sign", sign);
        treeMap.put("trade_no", trade_no);
        treeMap.put("out_trade_no", out_trade_no);
        treeMap.put("out_biz_no", out_biz_no);
        treeMap.put("buyer_id", buyer_id);
        treeMap.put("buyer_logon_id", buyer_logon_id);
        treeMap.put("seller_id", seller_id);
        treeMap.put("seller_email", seller_email);
        treeMap.put("trade_status", trade_status);
        treeMap.put("total_amount", total_amount.toString());
        treeMap.put("receipt_amount", receipt_amount.toString());
        treeMap.put("invoice_amount", invoice_amount.toString());
        treeMap.put("buyer_pay_amount", buyer_pay_amount.toString());
        treeMap.put("point_amount", point_amount.toString());
        if (refund_fee != null) {
            treeMap.put("refund_fee", refund_fee.toString());
        }
        treeMap.put("subject", subject);
        treeMap.put("body", body);
        treeMap.put("gmt_create", DateFormatUtils.format(gmt_create, "yyyy-MM-dd HH:mm:ss"));
        treeMap.put("gmt_payment", DateFormatUtils.format(gmt_payment, "yyyy-MM-dd HH:mm:ss"));
        if (gmt_refund != null) {
            treeMap.put("gmt_refund", DateFormatUtils.format(gmt_refund, "yyyy-MM-dd HH:mm:ss"));
        }
        if (gmt_close != null) {
            treeMap.put("gmt_close", DateFormatUtils.format(gmt_close, "yyyy-MM-dd HH:mm:ss"));
        }
        treeMap.put("fund_bill_list", fund_bill_list);
        treeMap.put("passback_params", passback_params);
        treeMap.put("voucher_detail_list", voucher_detail_list);
        //业务逻辑
        Iterator iterator = treeMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry next = (Map.Entry) iterator.next();
            if (next.getValue() == null) {
                iterator.remove();
            }
        }
        System.out.printf("%s", treeMap);
        // String signStr = treeMap.remove("sign");
     String signType = treeMap.get("sign_type");
      /*  byte[] signs = Base64.decode(treeMap.remove("sign"));
        treeMap.put("sign",new String(signs,Charset.forName("utf-8")));*/
        String charSet = treeMap.get("charset");
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = httpServletRequest.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
//切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
//boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        //调用SDK验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(params, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB", charSet, signType);

        if (signVerified) {
            if ("TRADE_SUCCESS".equals(treeMap.get("trade_status"))) {
                Appointment appointment = appointmentService.findById(Integer.parseInt(out_trade_no));
                appointment.setState(AppointmentState.PAY.toString());
                appointmentService.save(appointment);
                return "success";
            }
            // TODO 验签成功后
            //按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure
        } else {
            // return "failure";
            // TODO 验签失败则记录异常日志，并在response中返回failure.
        }

        return "failure";

    }


}
