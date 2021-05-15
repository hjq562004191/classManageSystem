package com.example.demo.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * @Author JQiang
 * @create 2021/5/12 21:37
 */
public class TimerUtil {
    public static void ScheduledTask(int teacherId,int hour) {
        Runnable runnable = new Runnable() {
            public void run() {
                JedisUtils.setToken("teacher_message_"+teacherId,"不要忘记您的签退,距离您设定的课时时间" +
                        "还有 15 分钟",12);
            }
        };
        //老师提前15分钟收到提示消息，并且可以进行签退
        int min = hour*60 - 15;
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.schedule(runnable, min-5, TimeUnit.SECONDS);

    }
}
