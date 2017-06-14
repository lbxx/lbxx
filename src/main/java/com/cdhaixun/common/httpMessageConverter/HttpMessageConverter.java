package com.cdhaixun.common.httpMessageConverter;


import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.helpers.DateTimeDateFormat;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.List;

public class HttpMessageConverter extends AbstractHttpMessageConverter<Object> {
    @Value("#{configProperties['aes']}")
    private String aes;
    @Autowired
    private ObjectMapper objectMapper;
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

        try {

            Key key = new SecretKeySpec(Base64.decodeBase64(aes), "AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            String temp = StreamUtils.copyToString(httpInputMessage.getBody(), Charset.forName("UTF-8"));
            byte[] result = cipher.doFinal(Base64.decodeBase64(temp));
            System.out.printf(new String(result,"UTF-8"));
            Object object = objectMapper.readValue(result, aClass);
            return object;

        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void writeInternal(Object o, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        String s = objectMapper.writeValueAsString(o);
        try {

            Key key = new SecretKeySpec(Base64.decodeBase64(aes), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(s.getBytes("UTF-8"));
            httpOutputMessage.getBody().write(Base64.encodeBase64(result,true));

        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }


    }
}
