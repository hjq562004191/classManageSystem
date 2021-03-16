package com.example.demo.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.Face.Root;
import com.example.demo.domain.Face.User_list;
import com.example.demo.mapper.SignMapper;
import com.example.demo.model.ResultBuilder;
import com.example.demo.model.ResultModel;
import com.example.demo.service.FaceService;
import com.example.demo.utils.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author JQiang
 * @create 2021/3/2 21:38
 */
@Service
@CrossOrigin
public class FaceServiceImpl implements FaceService {

    @Resource
    SignMapper signMapper;

    @Override
    public ResultModel addFace(String image, String phone) {
        image = image.substring(22,image.length()-1);
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", image);
            map.put("group_id", "group_class");
            map.put("user_id", phone);
            map.put("user_info", phone);
            map.put("liveness_control", "NORMAL");
            map.put("image_type", "BASE64");
            map.put("quality_control", "NORMAL");


            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = GetFaceToken.getAuth();

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            JSONObject Str = JSONObject.parseObject(result);
            Root face = JSONObject.toJavaObject(Str, Root.class);
            if (face.getError_code() == 0) {
                return ResultBuilder.getSuccess(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultBuilder.getFailure(-1, "添加人脸失败");
    }

    @Override
    public ResultModel searchFace(String searchImage, String phone,int signId,int userId) {
        searchImage = searchImage.substring(22,searchImage.length()-1);
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/search";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", searchImage);
            map.put("liveness_control", "NORMAL");
            map.put("group_id_list", "group_class");
            map.put("image_type", "BASE64");
            map.put("quality_control", "NORMAL");

            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = GetFaceToken.getAuth();

            String result = HttpUtil.post(url, accessToken, "application/json", param);

            //匹配度
            double score = 0;
            JSONObject Str = JSONObject.parseObject(result);
            Root face = JSONObject.toJavaObject(Str, Root.class);
            if (face.getResult() == null){
                return ResultBuilder.getFailure(-1, "请添加清楚人脸照片");
            }
            for (User_list user :
                    face.getResult().getUser_list()) {
                if (user.getUser_id().equals(phone) && user.getScore() > 80.0){
                    score = user.getScore();
                }
            }
            System.out.println(searchImage);
            if (score > 80.00) {
                signMapper.setSign(signId,userId);
                return ResultBuilder.getSuccess("识别成功");
            } else {
                return ResultBuilder.getFailure(-1, "人脸相似度低");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultBuilder.getFailure(-1, "对比人脸失败");
    }
}
