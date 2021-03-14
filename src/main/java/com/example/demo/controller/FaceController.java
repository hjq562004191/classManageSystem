package com.example.demo.controller;

import com.example.demo.domain.Student;
import com.example.demo.model.ResultModel;
import com.example.demo.service.FaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @Author JQiang
 * @create 2021/3/7 21:24
 */
@RestController
@CrossOrigin
@SuppressWarnings("all")
public class FaceController {
    @Autowired
    FaceService faceService;

    @RequestMapping(value = "/face/addFace", method = POST)
    public ResultModel addFace(String image, String phone) {
        return faceService.addFace(image,phone);
    }

    @RequestMapping(value = "/face/searchFace", method = POST)
    public ResultModel searchFace(String image, String phone,int signId) {
        return faceService.searchFace(image,phone,signId);
    }
}
