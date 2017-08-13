package com.cdhaixun;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by tangxinmao on 2017/6/23.
 */
public class HttpclientWeixinTest {
    private static HttpClient hc = HttpClients.createDefault();

    public static void main(String[] args) throws IOException {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("appid", "00000051"));
    //    params.add(new BasicNameValuePair("authcode", "281175886193629630"));
//        params.add(new BasicNameValuePair("body", "123"));
        params.add(new BasicNameValuePair("cusid", "990581007426001"));
        params.add(new BasicNameValuePair("key", "allinpay888"));
        params.add(new BasicNameValuePair("paytype", "2"));
        params.add(new BasicNameValuePair("randomstr", "1450432107647"));
/*        params.add(new BasicNameValuePair("remark", "123"));*/
        params.add(new BasicNameValuePair("reqsn", UUID.randomUUID().toString()));
        params.add(new BasicNameValuePair("sub_appid", "wxd211818e35c66f0c"));
        params.add(new BasicNameValuePair("sub_mchid", "1485189052"));
        params.add(new BasicNameValuePair("trxamt", "1"));
        URLCodec urlCodec = new URLCodec();
        String str = URLEncodedUtils.format(params, "utf-8");
        System.out.println(str);
       /* String hex = "appid=00000000&body=商品名称&cusid=990440153996000&key=43df939f1e7f5c6909b3f4b63f893994&paytype=0&randomstr=1450432107647&remark=备注信息&reqsn=1450432107647&trxamt=1";
        System.out.println(hex);
        System.out.println(DigestUtils.md5Hex(hex));*/
        String sign = DigestUtils.md5Hex(str.getBytes("UTF-8")).toUpperCase();
        System.out.println(sign);
        params.add(new BasicNameValuePair("sign", sign));
        // params.add(new BasicNameValuePair("authcode", "234"));
        System.out.println(URLEncodedUtils.format(params, "utf-8"));
        HttpPost httppost = new HttpPost("http://113.108.182.3:10080/apiweb/weixin/pay?" + URLEncodedUtils.format(params, "utf-8"));
        HttpResponse httpResponse = hc.execute(httppost);
        HttpEntity httpEntity = httpResponse.getEntity();
        String s = IOUtils.toString(httpEntity.getContent(), "utf-8");
        System.out.println(s);
    }
}
