package com.example.demo.domain.Face;

/**
 * @Author JQiang
 * @create 2021/3/8 11:39
 */
public class Root {
    private int error_code;

    private String error_msg;

    private int log_id;

    private int timestamp;

    private int cached;

    private Result result;

    public void setError_code(int error_code){
        this.error_code = error_code;
    }
    public int getError_code(){
        return this.error_code;
    }
    public void setError_msg(String error_msg){
        this.error_msg = error_msg;
    }
    public String getError_msg(){
        return this.error_msg;
    }
    public void setLog_id(int log_id){
        this.log_id = log_id;
    }
    public int getLog_id(){
        return this.log_id;
    }
    public void setTimestamp(int timestamp){
        this.timestamp = timestamp;
    }
    public int getTimestamp(){
        return this.timestamp;
    }
    public void setCached(int cached){
        this.cached = cached;
    }
    public int getCached(){
        return this.cached;
    }
    public void setResult(Result result){
        this.result = result;
    }
    public Result getResult(){
        return this.result;
    }

}
