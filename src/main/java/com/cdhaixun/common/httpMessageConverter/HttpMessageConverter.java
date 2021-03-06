package com.cdhaixun.common.httpMessageConverter;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.List;

public class HttpMessageConverter extends AbstractHttpMessageConverter<Object> {
    @Value("#{configProperties['aes']}")
    private String aes;
    @Value("#{configProperties['allowHost']}")
    private String allowHost;


    public static Logger logger = LogManager.getLogger(HttpMessageConverter.class.getName());


    private Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");


    public HttpMessageConverter() throws NoSuchPaddingException, NoSuchAlgorithmException {
    }

    @Override
    protected boolean supports(Class<?> aClass) {
        return aClass.getPackage().getName().startsWith("com.cdhaixun.common.appVo");
    }

    @Override
    public void setSupportedMediaTypes(List<MediaType> supportedMediaTypes) {
        super.setSupportedMediaTypes(supportedMediaTypes);
    }


    @Override
    protected Object readInternal(Class<?> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {


      /*  try {*/
        ObjectMapper objectMapper = new ObjectMapper();
        //或略不知道的属性
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String temp = StreamUtils.copyToString(httpInputMessage.getBody(), Charset.forName("UTF-8"));
        logger.info("host................................." + httpInputMessage.getHeaders().get("host"));
           //objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
           /* String[] split = StringUtils.split(allowHost, ",");
            List<String> allowHosts=new ArrayList<>();
            CollectionUtils.addAll(allowHosts,split);
            if (allowHosts.contains(httpInputMessage.getHeaders().get("host").get(0))) {*/
        return objectMapper.readValue(temp, aClass);
         /*   }
            Key key = new SecretKeySpec(Base64.decodeBase64(aes), "AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] result = cipher.doFinal(Base64.decodeBase64(temp));
            System.out.printf(new String(result, "UTF-8"));
            Object object = objectMapper.readValue(result, aClass);
            return object;

        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        return null;*/
    }

    @Override
    protected void addDefaultHeaders(HttpHeaders headers, Object o, MediaType contentType) throws IOException {
        super.addDefaultHeaders(headers, o, contentType);
    }

    @Override
    protected void writeInternal(Object o, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String s = objectMapper.writeValueAsString(o);
        httpOutputMessage.getBody().write(s.getBytes("UTF-8"));
      /*  try {
            Key key = new SecretKeySpec(Base64.decodeBase64(aes), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(s.getBytes("UTF-8"));
            httpOutputMessage.getBody().write(Base64.encodeBase64(result, true));

        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }*/


    }
}
