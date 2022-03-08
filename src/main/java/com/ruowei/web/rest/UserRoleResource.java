package com.ruowei.web.rest;

import com.ruowei.repository.UserRoleRepository;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Transactional
@Api(tags = "系统管理")
public class UserRoleResource {

    private final Logger log = LoggerFactory.getLogger(UserRoleResource.class);
    private final UserRoleRepository userRoleRepository;

    public UserRoleResource(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

}
