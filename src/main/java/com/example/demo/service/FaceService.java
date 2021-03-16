package com.example.demo.service;

import com.example.demo.domain.Student;
import com.example.demo.model.ResultModel;

/**
 * @Author JQiang
 * @create 2021/3/2 21:35
 */
public interface FaceService {
    ResultModel addFace(String image, String phone);
    ResultModel searchFace(String searchImage,String phone,int signId,int userId);
}
