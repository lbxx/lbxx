package com.cdhaixun.shop.service.impl;

import com.cdhaixun.common.vo.Result;
import com.cdhaixun.shop.service.IUploadService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by tangxinmao on 2017/7/5.
 */
@Service
public class UploadServiceImpl implements IUploadService {
    @Autowired
    private ObjectMapper objectMapper;
    @Value("#{configProperties['uploadDomainName']}")
    private String uploadDomainName;
    private static HttpClient hc = HttpClients.createDefault();
    @Override
    public Result upload(HttpServletRequest httpServletRequest, MultipartFile file) throws IOException {
        Result result = new Result();
        HttpPost httppost = new HttpPost(uploadDomainName);
        String BOUNDARY = "----------" + System.currentTimeMillis();
        httppost.addHeader("Content-Type", "multipart/form-data;boundary="
                + BOUNDARY);
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.setBoundary(BOUNDARY);
        String originalFilename = file.getOriginalFilename();
        String prefix=originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName= UUID.randomUUID()+prefix;
        multipartEntityBuilder.addBinaryBody("file", file.getInputStream(), ContentType.APPLICATION_OCTET_STREAM,fileName );
        multipartEntityBuilder.addTextBody("servletPath", httpServletRequest.getServletPath());
        httppost.setEntity(multipartEntityBuilder.build());
        HttpResponse httpResponse = hc.execute(httppost);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode == org.apache.http.HttpStatus.SC_OK) {
            HttpEntity httpEntity = httpResponse.getEntity();
            String s = IOUtils.toString(httpEntity.getContent(), "utf-8");
            Result res = objectMapper.readValue(s, Result.class);
            res.setData(httpServletRequest.getServletPath()+"/"+fileName);
            return res;
        }
        return result;
    }
}
