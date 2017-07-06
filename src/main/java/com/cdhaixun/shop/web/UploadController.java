package com.cdhaixun.shop.web;

import com.cdhaixun.common.vo.Result;
import com.cdhaixun.shop.service.IUploadService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by tangxinmao on 2017/7/5.
 */
@Controller
@RequestMapping("upload")
public class UploadController {
   @Autowired
    private  IUploadService uploadService;
    @Value("#{configProperties['allowDomainName']}")
    private String allowDomainName;
    @Value("#{configProperties['imgRoot']}")
    private String imgRoot;

    @ResponseBody
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public Result upload(HttpServletRequest httpServletRequest, MultipartFile file) throws IOException {
        Result result = uploadService.upload(httpServletRequest, file);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "uploadFile", method = RequestMethod.POST)
    public Result uploadFile(HttpServletRequest httpServletRequest, MultipartFile file, String servletPath) throws IOException {
        Result result = new Result();
        String serverName = httpServletRequest.getServerName();
        StringUtils.split(allowDomainName,",");
        String[] allowDomainNames = StringUtils.split(allowDomainName, ",");
        List<String> allowDomainNameList=new ArrayList<>();
        CollectionUtils.addAll(allowDomainNameList,allowDomainNames);
        if(!allowDomainNameList.contains(serverName)){
            return  result;
        }
        java.io.File destination = new java.io.File(imgRoot + servletPath + "/" + file.getOriginalFilename());
        FileUtils.copyInputStreamToFile(file.getInputStream(), destination);
        result.setResult(true);
        return result;
    }
}
