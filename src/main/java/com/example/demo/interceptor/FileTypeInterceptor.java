package com.example.demo.interceptor;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class FileTypeInterceptor extends HandlerInterceptorAdapter {

    private final String ALLOW_FILE = "jpg,gif,png";

    private final String ALLOW_URL = "utils";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = true;

        if (request.getServletPath().contains(ALLOW_URL)) {
            return true;
        }

        // 判断文件是否为上传请求
        if (request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> files = mrequest.getFileMap();
            Iterator<String> iterator = files.keySet().iterator();
            // 对请求的资源进行遍历
            while (iterator.hasNext()) {
                String formKey = iterator.next();
                List<MultipartFile> multipartFiles = mrequest.getFiles(formKey);
                if (multipartFiles.size() == 0) {
                    String file = multipartFiles.get(0).getOriginalFilename();
                    if (!checkFile(file)) {
                        flag = false;
                    }
                } else {
                    for (MultipartFile multipartFile : multipartFiles) {
                        String file = multipartFile.getOriginalFilename();
                        if (!checkFile(file)) {
                            flag = false;
                        }
                    }
                }
            }
            if (flag == false) {
                request.getRequestDispatcher("/utils/fileNotAllow").forward(request, response);
            }
        }
        return flag;
    }

    // 判断文件是否是上传的文件
    private boolean checkFile(String fileName) {
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        if (ALLOW_FILE.contains(suffix.trim().toLowerCase())) {
            return true;
        }

        return false;
    }
}
