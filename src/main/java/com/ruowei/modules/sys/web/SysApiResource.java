package com.ruowei.modules.sys.web;

import com.ruowei.modules.sys.domain.SysApi;
import com.ruowei.modules.sys.domain.SysRoleApi;
import com.ruowei.modules.sys.repository.SysApiRepository;
import com.ruowei.modules.sys.repository.SysRoleApiRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Api(tags = "用户权限信息")
public class SysApiResource {

    private final SysApiRepository sysApiRepository;
    private final SysRoleApiRepository sysRoleApiRepository;

    public SysApiResource(SysApiRepository sysApiRepository, SysRoleApiRepository sysRoleApiRepository) {
        this.sysApiRepository = sysApiRepository;
        this.sysRoleApiRepository = sysRoleApiRepository;
    }

    @GetMapping("/sysApis/role/{roleId}")
    @ApiOperation(value = "角色权限列表", notes = "作者：李春浩")
    public ResponseEntity<List<SysApi>> getRoleApis(@PathVariable Long roleId) {
        List<SysApi> list = sysApiRepository.getAllByRoleId(roleId);
        return ResponseEntity.ok(list);
    }

    @PutMapping("/sysApis/role/{roleId}")
    @Transactional
    @ApiOperation(value = "修改角色权限", notes = "作者：李春浩")
    public ResponseEntity<Void> modifyRoleApis(@PathVariable Long roleId,
                                               @ApiParam("接口ID列表") @RequestBody List<Long> sysApiIds) {
        sysRoleApiRepository.deleteAllBySysRoleId(roleId);
        if (!sysApiIds.isEmpty()) {
            sysRoleApiRepository.saveAll(
                sysApiIds.stream().map(sysApiId ->
                    new SysRoleApi().sysRoleId(roleId).sysApiId(sysApiId)
                ).collect(Collectors.toList())
            );
        }
        return ResponseEntity.ok().build();
    }
}
