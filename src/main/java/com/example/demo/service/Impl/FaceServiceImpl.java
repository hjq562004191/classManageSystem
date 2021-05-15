package com.example.demo.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.ClassHour;
import com.example.demo.domain.ClassPOJO;
import com.example.demo.domain.Face.Root;
import com.example.demo.domain.Face.User_list;
import com.example.demo.domain.Student;
import com.example.demo.mapper.*;
import com.example.demo.model.ResultBuilder;
import com.example.demo.model.ResultModel;
import com.example.demo.service.FaceService;
import com.example.demo.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Resource
    StudentMapper studentMapper;
    @Resource
    ClassMapper classMapper;
    @Resource
    HourMapper hourMapper;
    @Resource
    TeacherMapper teacherMapper;

    @Override
    public ResultModel addFace(String image, String phone) {
        Student student = studentMapper.findStudentByPhone(phone);
        int faceTotal = 0;
        if (student != null){
            faceTotal = student.getFaceTotal();
            String  className = student.getClassName();
            ClassPOJO classPOJO = classMapper.getClassByName(className);
            if (classPOJO == null){
                return ResultBuilder.getFailure(-3, "请先加入班级");
            }
            boolean exist = JedisUtils.isExists("class_"+classPOJO.getId());
            if (!exist){
                return ResultBuilder.getFailure(-2, "教师暂未开放上传人脸");
            }
        }else {
            return ResultBuilder.getFailure(-2, "学生信息不存在");
        }
        image = image.substring(22);
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/update";
        if (faceTotal == 0){
            url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";
        }
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
                if (faceTotal == 0){
                    studentMapper.updateFaceTotal(student.getId());
                }
                return ResultBuilder.getSuccess(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultBuilder.getFailure(-1, "添加人脸失败");
    }

    @Override
    public ResultModel searchFace(String searchImage, String phone,int signId,int userId) {
        searchImage = searchImage.substring(22);
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/search";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", searchImage);
            map.put("liveness_control", "NORMAL");
            map.put("group_id_list", "group_class");
            map.put("user_id",phone); //指定用户对比
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

    @Override
    public ResultModel teacherAddFace(String image, String phone) {
        image = image.substring(22);
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
    public ResultModel teacherSearchFace(String searchImage, String phone, int hourId, int userId) {
        searchImage = searchImage.substring(22);
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/search";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", searchImage);
            map.put("liveness_control", "NORMAL");
            map.put("group_id_list", "group_class");
            map.put("user_id",phone); //指定用户对比
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
                    break;
                }
            }
            if (score > 80.00) {
                //教师成功打卡后计算时长
                ClassHour classHour = hourMapper.getClassHourById(hourId);
                int creatTime = classHour.getCreateTime();
                int now = (int) (System.currentTimeMillis()/1000);
                int time = (now - creatTime);
                int hour = 0;
                //1小时为1课时
                if ( time <= TimeUtil.minToSecond(30)){
                    hourMapper.setTeacherSign(hourId,now,0);
                    return ResultBuilder.getSuccess(1,null,"课时时长较短，不进行累计");
                }else if ( TimeUtil.minToSecond(30) <time &&
                        time < TimeUtil.minToSecond(90)){
                    //0.5小时到1.5小时之间算1课时，课时累加
                    hour = 1;
                    teacherMapper.addClassHour(classHour.getTeacherId(),1);
                }else if ( TimeUtil.minToSecond(90) < time &&
                        time < TimeUtil.minToSecond(150)){
                    //1.5小时到2.5小时之间算2课时
                    hour = 2;
                    teacherMapper.addClassHour(classHour.getTeacherId(),2);
                }else if ( TimeUtil.minToSecond(150) < time){
                    //2.5小时之后算3课时
                    hour = 3;
                    teacherMapper.addClassHour(classHour.getTeacherId(),3);
                }
                hourMapper.setTeacherSign(hourId,now,hour);
                return ResultBuilder.getSuccess(2,hour,"识别成功，累加课时");
            } else {
                return ResultBuilder.getFailure(-1, "人脸相似度低");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultBuilder.getFailure(-1, "对比人脸失败");
    }
}
