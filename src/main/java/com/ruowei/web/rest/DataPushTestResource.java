package com.ruowei.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api")
@Api(tags = "数据推送测试")
public class DataPushTestResource {

    private final Logger log = LoggerFactory.getLogger(DataPushTestResource.class);
    private final ObjectMapper objectMapper;
    private final JPAQueryFactory jpaQueryFactory;

    public DataPushTestResource(ObjectMapper objectMapper, JPAQueryFactory jpaQueryFactory) {
        this.objectMapper = objectMapper;
        this.jpaQueryFactory = jpaQueryFactory;
    }


    @PostMapping("/push-test/response")
    @ApiOperation(value = "响应接口", notes = "作者：郑昊天")
    public String pushResponse() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        log.info("已经响应");
        return "调用成功,当前时间为 " + dateFormat.format(new Date());
    }

    @PostMapping("/push-test/request")
    @ApiOperation(value = "请求接口", notes = "作者：郑昊天")
//    @Scheduled(fixedRate = 5000)
    public void pushRequest() {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject("http://localhost:7777/api/push-test/response", null, String.class);
        log.info(result);
    }

    @PostMapping("/push-test/response-test")
    @ApiOperation(value = "响应测试接口", notes = "作者：郑昊天")
    public String scheduleTest() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        log.info("已经响应");
        return "调用成功,当前时间为 " + dateFormat.format(new Date());
    }

    @PostMapping("/push-test/request-test")
    @ApiOperation(value = "请求测试接口", notes = "作者：郑昊天")
//    @Scheduled(fixedRate = 5000)
    public void scheduleTest1() {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject("http://localhost:7777/api/push-test/response-test", null, String.class);
        log.info(result);
    }

}
