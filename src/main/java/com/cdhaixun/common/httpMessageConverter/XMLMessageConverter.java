package com.cdhaixun.common.httpMessageConverter;


import com.cdhaixun.common.wechatPay.PayAction;
import com.cdhaixun.common.wechatPay.PayReturn;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.util.StreamUtils;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class XMLMessageConverter extends AbstractHttpMessageConverter<Object> {

    @Override
    protected boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(PayAction.class)||aClass.isAssignableFrom(PayReturn.class);
    }

    @Override
    protected Object readInternal(Class<?>aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(aClass);
        Object object = marshaller.unmarshal(new StreamSource(httpInputMessage.getBody()));
        return object;
    }

    @Override
    protected void writeInternal(Object payReturn, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        Map map = new Hashtable();
        map.put(Marshaller.JAXB_ENCODING, "UTF-8");// 编码格式
        map.put(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        map.put(Marshaller.JAXB_FRAGMENT, true);
        marshaller.setMarshallerProperties(map);
        marshaller.setClassesToBeBound(payReturn.getClass());
        StringWriter stringWriter = new StringWriter();
        StreamResult streamResult = new StreamResult(stringWriter);
        marshaller.marshal(payReturn, streamResult);
        httpOutputMessage.getBody().write(stringWriter.toString().getBytes());
    }
}
