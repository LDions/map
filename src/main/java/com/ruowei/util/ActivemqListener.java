package com.ruowei.util;


import com.google.gson.Gson;
import com.ruowei.service.SewEmiService;
import com.ruowei.web.rest.vm.SewEmiAccountVM;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


import javax.jms.*;
import java.time.Instant;

@Component
public class ActivemqListener {
    private final SewEmiService sewEmiService;

    public ActivemqListener(SewEmiService sewEmiService) {
        this.sewEmiService = sewEmiService;
    }

//    @JmsListener(destination = "activemq-topic-test1")
//    public void receive(String msg) {
//        System.out.println("监听器收到msg:" + msg);
//        MYSQLControl control = new MYSQLControl("localhost:3306", "db", "root", "123456");
//        String sql = "insert into test" + "(" + "cod" + ")values('" + msg + "');";
//        control.executeUpdate(sql);
//        list.add(msg);
//    }
//
//    @JmsListener(destination = "activemq-topic-test2")
//    public void receive2(String msg) {
//        System.out.println("监听器收到msg:" + msg);
//        MYSQLControl control = new MYSQLControl("localhost:3306", "db", "root", "123456");
//        String sql = "insert into test" + "(" + "aop" + ")values('" + msg + "');";
//        control.executeUpdate(sql);
//    }
//
//    @JmsListener(destination = "activemq-topic-test4", containerFactory = "topicListener")
//    public void readActiveQueue(String message) {
//        System.out.println("topic接受到：" + message);
//    }

    @JmsListener(destination = "activemq-topic-test3", containerFactory = "topicListener")
    public void readActiveQueue2(Message message) {
        System.out.println("topic2接受到：");

        try {
            final MapMessage mapmessage=(MapMessage) message;
            String json =mapmessage.getString("json");
            final Gson gson= new Gson();
            final SewEmiAccountVM.SewProcessVM vm = gson.fromJson(json, SewEmiAccountVM.SewProcessVM.class);
            sewEmiService.saveAccountingResultToMySQL(1L,vm, Instant.now());
            System.out.println("s");
        } catch (final JMSException e) {
            e.printStackTrace();
        }
    }

}


