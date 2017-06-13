package com.cdhaixun.common.httpMessageConverter;


import org.apache.commons.codec.binary.Base64;
import org.codehaus.jackson.map.ObjectMapper;
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
import java.util.List;

public class HttpMessageConverter extends AbstractHttpMessageConverter<Object> {
    @Value("#{configProperties['aes']}")
    private String aes;
@Autowired
private ObjectMapper objectMapper;
    @Override
    protected boolean supports(Class<?> aClass) {
        return  aClass.getPackage().getName().startsWith("com.cdhaixun.common.appVo");
    }

    @Override
    public void setSupportedMediaTypes(List<MediaType> supportedMediaTypes) {
        super.setSupportedMediaTypes(supportedMediaTypes);
    }

    @Override
    protected Object readInternal(Class<?> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {

        try {
            Cipher cipher =Cipher.getInstance("AES/ECB/PKCS5Padding");
            Key key = new SecretKeySpec(Base64.decodeBase64(aes), "AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            String temp = StreamUtils.copyToString(httpInputMessage.getBody(), Charset.forName("UTF-8"));
            byte[] result = cipher.doFinal(temp.getBytes());
            Object object = objectMapper.readValue(result, aClass);
            return object;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
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
        httpOutputMessage.getBody().write(objectMapper.writeValueAsBytes(o));
    }
}
