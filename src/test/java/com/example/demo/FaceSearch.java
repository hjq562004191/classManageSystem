package com.example.demo;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.Face.Root;
import com.example.demo.domain.Face.User_list;
import com.example.demo.utils.*;

import java.io.IOException;
import java.util.*;

/**
 * 人脸搜索
 */
public class FaceSearch {

    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    public static String faceSearch() {

        String searchImage = null;
        try {
            searchImage = Base64Util.encode(FileUtil.readFileByBytes("D:\\002.JPG"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            String scoreStr = result.substring(result.indexOf("\"score\":")+8,result.length()-5);
            double score = Double.parseDouble(scoreStr);
            System.out.println(score);

            JSONObject jsStr = JSONObject.parseObject(result);
            Root face = JSONObject.toJavaObject(jsStr,Root.class);
            for (User_list user:
            face.getResult().getUser_list()){

            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        FaceSearch.faceSearch();
    }
}