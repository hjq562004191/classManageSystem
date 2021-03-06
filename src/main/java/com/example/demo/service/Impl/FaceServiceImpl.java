package com.example.demo.service.Impl;

import com.example.demo.domain.Student;
import com.example.demo.model.ResultBuilder;
import com.example.demo.model.ResultModel;
import com.example.demo.service.FaceService;
import com.example.demo.utils.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author JQiang
 * @create 2021/3/2 21:38
 */
@Service
public class FaceServiceImpl implements FaceService {
    @Override
    public ResultModel addFace(String image, Student student) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", image);
            map.put("group_id", "group_test");
            map.put("user_id", student.getStudentName());
            map.put("user_info", student.toString());
            map.put("liveness_control", "NORMAL");
            map.put("image_type", "BASE64");
            map.put("quality_control", "LOW");

            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = GetFaceToken.getAuth();

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return ResultBuilder.getSuccess(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultBuilder.getFailure(-1,"添加人脸失败");
    }

    @Override
    public ResultModel searchFace(String searchImage) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/search";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", searchImage);
            map.put("liveness_control", "NORMAL");
            map.put("group_id_list", "group_test");
            map.put("image_type", "BASE64");
            map.put("quality_control", "LOW");

            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = GetFaceToken.getAuth();

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return ResultBuilder.getSuccess(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultBuilder.getFailure(-1,"对比人脸失败");
    }
}
