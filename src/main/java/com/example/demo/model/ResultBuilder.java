package com.example.demo.model;
/**
 * @Author JQiang
 * @create 2021/3/2 24:23
 */
public class ResultBuilder {
    public static ResultModel getSuccess(Object obj) {
        return new ResultModel(0, obj, null);
    }

    public static ResultModel getFailure(int id, String message) {
        return new ResultModel(id, null, message);
    }

    public static ResultModel getSuccess(String message) {
        return new ResultModel(0, null, message);
    }

    public static ResultModel getSuccess(Object object, String message) {
        return new ResultModel(0, object, message);
    }
    public static ResultModel getSuccess(int id,Object object, String message) {
        return new ResultModel(id, object, message);
    }
    public static ResultModel getFailure(int id, Object object, String message) {
        return new ResultModel(id, object, message);
    }
    public static ResultModel getFailure(int id, Object object) {
        return new ResultModel(id, object, null);
    }
}
