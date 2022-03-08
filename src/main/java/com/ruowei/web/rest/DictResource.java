package com.ruowei.web.rest;

import com.ruowei.repository.DictRepository;
import com.ruowei.web.rest.dto.DictDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(tags = "系统管理")
public class DictResource {

    private final Logger log = LoggerFactory.getLogger(DictResource.class);
    private final DictRepository dictRepository;

    public DictResource(DictRepository dictRepository) {
        this.dictRepository = dictRepository;
    }

    @GetMapping("/dicts/byCatagory")
    @ApiOperation(value = "按分类获取数据字典列表")
    public List<DictDTO> getAllDictsByCatagory(@ApiParam(value = "分类编码", required = true) @RequestParam String catagory) {
        return dictRepository.findAllByCatagoryCodeAndDisabledIsFalseOrderBySortNo(catagory);
    }
}
