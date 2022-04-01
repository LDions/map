package com.ruowei.web.rest;

import com.github.yitter.idgen.YitIdHelper;
import com.ruowei.config.ApplicationProperties;
import com.ruowei.security.UserModel;
import com.ruowei.service.SewEmiService;
import com.ruowei.web.rest.dto.SewEmiAccountOutputDTO;
import com.ruowei.web.rest.vm.SewEmiAccountVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequestMapping("/api")
@Api(tags = "核算展示")
@Transactional
public class SewEmiResource {

    private final Logger log = LoggerFactory.getLogger(SewEmiResource.class);

    private final SewEmiService sewEmiService;

    private final ApplicationProperties applicationProperties;

    public SewEmiResource(SewEmiService sewEmiService, ApplicationProperties applicationProperties) {
        this.sewEmiService = sewEmiService;
        this.applicationProperties = applicationProperties;
    }
    //TODO 核算
//    @PostMapping("/carbon-emission/water/accounting")
//    @ApiOperation(value = "数据核算接口", notes = "作者：")
//    public ResponseEntity<SewEmiAccountOutputDTO> accounting(@Valid @RequestBody SewEmiAccountVM vm,
//                                                             @ApiIgnore @AuthenticationPrincipal UserModel userModel) {

//        String documentCode = "HS".concat(new SimpleDateFormat("yyyyMMdd").format(new Date())).concat(String.valueOf(YitIdHelper.nextId()));
//        SewEmiAccountOutputDTO sewEmiAccountOutputDTO = new SewEmiAccountOutputDTO(
//            vm.getAccYear(),
//            vm.getAccMonth(),
//            documentCode
//        );
//        return ResponseEntity.ok().body(sewEmiAccountOutputDTO);
//    }

}
