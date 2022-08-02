package com.ruowei.web.rest.dto;

import lombok.Data;

import java.util.Map;

/**
 * @description:
 * @author: ruowei
 * @time: 2022/7/22 11:48
 */
@Data
public class LocationVM {

    private Long id;
    private String type;
    private String  coordinate;
}
