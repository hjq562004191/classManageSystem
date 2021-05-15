package com.example.demo.controller;

import com.example.demo.model.ResultBuilder;
import com.example.demo.model.ResultModel;
import com.example.demo.utils.JedisUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @Author JQiang
 * @create 2021/5/13 23:26
 */
@RestController
@CrossOrigin
public class MessageController {

    @RequestMapping(value = "/message/getStuMessage", method = POST)
    public ResultModel getStuMessage(int stuId) {
        if (stuId == -1){
            return ResultBuilder.getFailure(-1,"还没有消息");
        }
        boolean exist = JedisUtils.isExists("stu_message_"+stuId);
        if (exist){
            ResultModel resultBuilder = ResultBuilder.getSuccess(JedisUtils.getToken("stu_message_"+stuId));
            JedisUtils.setToken("stu_message_"+stuId," ",0);
            return resultBuilder;
        }else {
            return ResultBuilder.getFailure(-1,"还没有消息");
        }
    }

    @RequestMapping(value = "/message/getTeacherMessage", method = POST)
    public ResultModel getTeacherMessage(int teacherId) {
        if (teacherId == -1){
            return ResultBuilder.getFailure(-1,"还没有消息");
        }
        boolean exist = JedisUtils.isExists("teacher_message_"+teacherId);
        if (exist){
            ResultModel resultModel = ResultBuilder.getSuccess(JedisUtils.getToken("teacher_message_"+teacherId));
            JedisUtils.setToken("teacher_message_"+teacherId ," ",0);
            return resultModel;
        }else {
            return ResultBuilder.getFailure(-1,"还没有消息");
        }
    }
}
