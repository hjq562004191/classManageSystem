package com.example.demo.service;

import com.example.demo.model.ResultModel;

public interface UtilsService {

    ResultModel notLoginIn();

    ResultModel logonExpires();
    ResultModel loginException();

    ResultModel fileNotAllow();

    ResultModel adminNotLoginIn();

    ResultModel noJurisdiction();
}
