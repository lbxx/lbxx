package com.cdhaixun.shop.service;

import com.cdhaixun.common.vo.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by tangxinmao on 2017/7/5.
 */
public interface IUploadService {
    Result upload(HttpServletRequest httpServletRequest, MultipartFile file) throws IOException;
}
