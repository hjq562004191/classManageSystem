package com.example.demo.domain.Face;

/**
 * @Author JQiang
 * @create 2021/3/8 11:38
 */
public class User_list {
    private String group_id;

    private String user_id;

    private String user_info;

    private double score;

    public void setGroup_id(String group_id){
        this.group_id = group_id;
    }
    public String getGroup_id(){
        return this.group_id;
    }
    public void setUser_id(String user_id){
        this.user_id = user_id;
    }
    public String getUser_id(){
        return this.user_id;
    }
    public void setUser_info(String user_info){
        this.user_info = user_info;
    }
    public String getUser_info(){
        return this.user_info;
    }
    public void setScore(double score){
        this.score = score;
    }
    public double getScore(){
        return this.score;
    }

}
