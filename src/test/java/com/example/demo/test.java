package com.example.demo;

import java.util.Date;

/**
 * @Author JQiang
 * @create 2021/3/12 23:45
 */
public class test {
    public static void main(String[] args){
        Date date = new Date();

        int q = (int) (System.currentTimeMillis()/1000);
        System.out.println(q);
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int q1 = (int) (System.currentTimeMillis()/1000);

        System.out.println(q1);


    }
}
