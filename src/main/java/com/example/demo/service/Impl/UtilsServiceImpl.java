package com.example.demo.service.Impl;


import com.example.demo.model.ResultBuilder;
import com.example.demo.model.ResultModel;
import com.example.demo.service.UtilsService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
@CrossOrigin
public class UtilsServiceImpl implements UtilsService {
    @Override
    public ResultModel notLoginIn() {
        return ResultBuilder.getFailure(-9, "未登录");
    }

    @Override
    public ResultModel logonExpires() {
        return ResultBuilder.getFailure(-9, "登录过期");
    }

    @Override
    public ResultModel loginException() {
        return ResultBuilder.getFailure(-9, "登录异常");
    }

    @Override
    public ResultModel fileNotAllow() {
        return ResultBuilder.getFailure(-9, "不支持的文件类型");
    }

    @Override
    public ResultModel adminNotLoginIn() {
        return ResultBuilder.getFailure(-9, "管理员未登录");
    }

    @Override
    public ResultModel noJurisdiction() {
        return ResultBuilder.getFailure(-9, "权限不足");
    }


}
