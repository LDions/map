package com.ruowei.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.ruowei.config.ApplicationProperties;
import com.ruowei.web.rest.errors.BadRequestAlertException;
import io.undertow.util.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Instant;

@Service
@Slf4j
public class PushService {

    private final RestTemplate restTemplate;
    private final ApplicationProperties applicationProperties;

    public PushService(RestTemplate restTemplate, ApplicationProperties applicationProperties) {
        this.restTemplate = restTemplate;
        this.applicationProperties = applicationProperties;
    }

    /**
     * 请求头
     */
    private HttpEntity<Void> requestEntity() {
        HttpHeaders headers = new HttpHeaders();
        String timespan = String.valueOf(Instant.now().getEpochSecond());
//        headers.add("Timespan", timespan);
//        headers.add("Token", DigestUtils.md5Hex(key + timespan + secretKey).toUpperCase());
        return new HttpEntity<>(headers);
    }

    /**
     * get请求
     *
     * @param host      ip地址
     * @param url       接口api
     * @param urlParams 参数
     * @return json格式返回值
     */
    public JsonNode getForData(String host, String url, LinkedMultiValueMap<String, String> urlParams) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(host + url).queryParams(urlParams);
        JsonNode jsonNode = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, requestEntity(), JsonNode.class).getBody();
        if (jsonNode == null) {
            log.info("调用接口" + url + "失败，返回结果为空");
            throw new BadRequestAlertException("查询失败", "", "");
        }
        return jsonNode;
    }

    public String postForData(String host, String api, Object... urlParams) {
        String url = host + api;
        String result = restTemplate.postForEntity(url, urlParams, String.class).getBody();
        if (result == null) {
            log.info("调用接口" + url + "失败");
            throw new BadRequestAlertException("请求失败", "", "");
        }
        return result;
    }

    /**
     * post请求
     *
     * @param host      ip地址
     * @param url       接口api
     * @param urlParams 参数
     * @return json格式返回值
     */
    public JsonNode postForData(String host, String url, LinkedMultiValueMap<String, String> urlParams) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(host + url).queryParams(urlParams);
        JsonNode jsonNode = restTemplate.exchange(builder.build().toUri(), HttpMethod.POST, requestEntity(), JsonNode.class).getBody();
        if (jsonNode == null) {
            log.info("调用接口" + url + "失败");
            throw new BadRequestAlertException("添加失败", "", "");
        }
        return jsonNode;
    }

    public String postForData(String api, Object urlParams) {
        String url = applicationProperties.getHost() + api;
        return restTemplate.postForEntity(url, urlParams, String.class).getBody();
    }
}
