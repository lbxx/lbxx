package com.cdhaixun.shop.web;

import com.cdhaixun.common.vo.File;
import com.cdhaixun.common.vo.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by tangxinmao on 2017/7/5.
 */
@Controller
@RequestMapping("upload")
public class UploadController {

    private static HttpClient hc = HttpClients.createDefault();
    @Autowired
    private org.codehaus.jackson.map.ObjectMapper objectMapper;

    @ResponseBody
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public Result upload(HttpServletRequest httpServletRequest, MultipartFile file) throws IOException {
        Result result = new Result();
        HttpPost httppost = new HttpPost("http://localhost:8080/upload/uploadFile");
        String BOUNDARY = "----------" + System.currentTimeMillis();
        httppost.addHeader("Content-Type", "multipart/form-data;boundary="
                + BOUNDARY);
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.setBoundary(BOUNDARY);
        String originalFilename = file.getOriginalFilename();
        String prefix=originalFilename.substring(originalFilename.lastIndexOf("."));
        multipartEntityBuilder.addBinaryBody("file", file.getInputStream(), ContentType.APPLICATION_OCTET_STREAM, UUID.randomUUID()+prefix);
        multipartEntityBuilder.addTextBody("secret", "123456");
        multipartEntityBuilder.addTextBody("servletPath", httpServletRequest.getServletPath());
        httppost.setEntity(multipartEntityBuilder.build());
        HttpResponse httpResponse = hc.execute(httppost);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode == org.apache.http.HttpStatus.SC_OK) {
            HttpEntity httpEntity = httpResponse.getEntity();
            String s = IOUtils.toString(httpEntity.getContent(), "utf-8");
            Result res = objectMapper.readValue(s, Result.class);
            return res;
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "uploadFile", method = RequestMethod.POST)
    public Result uploadFile(HttpServletRequest httpServletRequest, MultipartFile file, File f) throws IOException {
        Result result = new Result();
        if (!f.getSecret().equals("123456")) {
            return result;
        }
        java.io.File destination = new java.io.File("d:/image/" + f.getServletPath() + "/" + file.getOriginalFilename());
        FileUtils.copyInputStreamToFile(file.getInputStream(), destination);
        result.setResult(true);
        return result;
    }
}
