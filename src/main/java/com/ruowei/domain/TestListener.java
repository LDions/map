package com.ruowei.domain;

import com.google.gson.Gson;
import com.ruowei.config.Constants;
import com.ruowei.domain.enumeration.UserStatusType;
import com.ruowei.security.UserModel;
import com.ruowei.util.JsonUtil;
import com.ruowei.web.rest.dto.DataDto;
import com.ruowei.web.rest.errors.BadRequestProblem;
import com.ruowei.web.rest.vm.UserVM;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jms.*;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TestListener {
    List<String> list = new ArrayList<>();
    @JmsListener(destination = "activemq-topic-test1")
    public void receive(String msg) {
        System.out.println("监听器收到msg:" + msg);
//        MYSQLControl control = new MYSQLControl("localhost:3306", "db", "root", "123456");
//        String sql = "insert into test" + "(" + "cod" + ")values('" + msg + "');";
//        control.executeUpdate(sql);
        list.add(msg);
    }

    @JmsListener(destination = "activemq-topic-test2")
    public void receive2(String msg) {
        System.out.println("监听器收到msg:" + msg);
//        MYSQLControl control = new MYSQLControl("localhost:3306", "db", "root", "123456");
//        String sql = "insert into test" + "(" + "aop" + ")values('" + msg + "');";
//        control.executeUpdate(sql);
    }

    @JmsListener(destination = "activemq-topic-test4", containerFactory = "topicListener")
    public void readActiveQueue(String message) {
        System.out.println("topic接受到：" + message);
    }

    @JmsListener(destination = "activemq-topic-test3", containerFactory = "topicListener")
    public void readActiveQueue2(Message message) {
        System.out.println("topic2接受到："+message);
//        try {
//            final MapMessage mapmessage=(MapMessage) message;
//            final String json =mapmessage.getString("json");
//
//            DataDto dataDto = JsonUtil.toEntity(json, DataDto.class,"a");
////            System.out.println();
//
//
//        } catch (final JMSException e) {
//            e.printStackTrace();
//        }
    }
//    @PostMapping("/api/sys-send/")
//    @JmsListener(destination = "activemq-topic-test3", containerFactory = "topicListener")
//    @ApiOperation(value = "重置用户密码接口", notes = "作者：林宏栋")
//    public ResponseEntity<String> resetUserPassword(Message message) {
//        System.out.println("topic2接受到：");
//        try {
//            final MapMessage mapmessage=(MapMessage) message;
//            final String json =mapmessage.getString("json");
//
//            DataDto dataDto = JsonUtil.toEntity(json, DataDto.class,"a");
////            System.out.println();
//
//        } catch (final JMSException e) {
//            e.printStackTrace();
//        }
//        return ResponseEntity.ok().body("asd");
//    }

}
