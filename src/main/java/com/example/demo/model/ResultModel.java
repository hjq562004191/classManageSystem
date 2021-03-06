package com.example.demo.model;

import com.example.demo.View.SimpleView;
import com.fasterxml.jackson.annotation.JsonView;


/**
 * @Author JQiang
 * @create 2021/3/2 22:33
 */
public class ResultModel {
    @JsonView(SimpleView.class)
    private int status;
    @JsonView(SimpleView.class)
    private Object data;
    @JsonView(SimpleView.class)
    private String message;

    public ResultModel() {
    }

    public ResultModel(int status, Object data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
