package com.ruowei.web.rest;

import com.ruowei.service.DistrictService;
import com.ruowei.web.rest.dto.DistrictDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(tags = "省市区管理")
public class DistrictResource {

    private final Logger log = LoggerFactory.getLogger(DistrictResource.class);
    private final DistrictService districtService;

    public DistrictResource(DistrictService districtService) {
        this.districtService = districtService;
    }

    @GetMapping("/district")
    @ApiOperation(value = "获取省市区下拉列表接口", notes = "作者：林宏栋")
    @Cacheable(cacheNames = "com.ruowei.domain.District")
    public ResponseEntity<List<DistrictDTO>> getProvinceCityDistricts() {
        List<DistrictDTO> dtoList = districtService.getDistrictTree();
        return ResponseEntity.ok().body(dtoList);
    }
}
