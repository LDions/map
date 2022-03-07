package com.ruowei.modules.sys.web;


import com.ruowei.common.error.exception.BadRequestAlertException;
import com.ruowei.common.response.PaginationUtil;
import com.ruowei.modules.sys.domain.table.SysCompany;
import com.ruowei.modules.sys.domain.SysCompanyOffice;
import com.ruowei.modules.sys.domain.enumeration.CompanyStatusType;
import com.ruowei.modules.sys.mapper.SysCompanyMapper;
import com.ruowei.modules.sys.pojo.SysCompanyNode;
import com.ruowei.modules.sys.pojo.dto.SysCompanyDTO;
import com.ruowei.modules.sys.pojo.vm.SysCompanyQM;
import com.ruowei.modules.sys.repository.SysCompanyOfficeRepository;
import com.ruowei.modules.sys.repository.SysCompanyRepository;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Api(tags = "公司信息")
public class SysCompanyResource {

    private static final String ENTITY_NAME = "公司";
    private final Logger log = LoggerFactory.getLogger(SysCompanyResource.class);
    private final SysCompanyMapper sysCompanyMapper;
    private final SysCompanyRepository sysCompanyRepository;
    private final SysCompanyOfficeRepository sysCompanyOfficeRepository;

    public SysCompanyResource(SysCompanyMapper sysCompanyMapper, SysCompanyRepository sysCompanyRepository, SysCompanyOfficeRepository sysCompanyOfficeRepository) {
        this.sysCompanyMapper = sysCompanyMapper;
        this.sysCompanyRepository = sysCompanyRepository;
        this.sysCompanyOfficeRepository = sysCompanyOfficeRepository;
    }

    private void recursiveGet(SysCompanyNode sysCompanyNode) {
        sysCompanyNode.setChildren(
            sysCompanyRepository.getAllByParentCodeAndStatus(sysCompanyNode.getSysCompany().getCompanyCode(), CompanyStatusType.NORMAL)
                .stream().map(sysCompany -> {
                SysCompanyNode child = new SysCompanyNode();
                child.setSysCompany(sysCompany);
                return child;
            }).collect(Collectors.toList())
        );
        sysCompanyNode.getChildren().forEach(this::recursiveGet);
    }

    private void recursiveGetIn(SysCompanyNode sysCompanyNode, List<Long> ids) {
        sysCompanyNode.setChildren(
            sysCompanyRepository.getAllByParentCodeAndIdIn(sysCompanyNode.getSysCompany().getCompanyCode(), ids)
                .stream().map(sysCompany -> {
                SysCompanyNode child = new SysCompanyNode();
                child.setSysCompany(sysCompany);
                return child;
            }).collect(Collectors.toList())
        );
        sysCompanyNode.getChildren().forEach(childNode -> recursiveGetIn(childNode, ids));
    }

    @GetMapping("/sys-companys/tree")
    @ApiOperation(value = "获取公司树", notes = "作者：李春浩")
    public ResponseEntity<List<SysCompanyNode>> getSysCompanyTree() {
        List<SysCompanyNode> sysCompanyNodes = sysCompanyRepository.getAllByParentCodeIsNullAndStatus(CompanyStatusType.NORMAL)
            .stream().map(sysCompany -> {
                SysCompanyNode parent = new SysCompanyNode();
                parent.setSysCompany(sysCompany);
                recursiveGet(parent);
                return parent;
            })
            .collect(Collectors.toList());
        return ResponseEntity.ok(sysCompanyNodes);
    }

    @PostMapping("/sys-companys")
    @Transactional
    @ApiOperation(value = "新增公司", notes = "作者：李春浩")
    public ResponseEntity<Void> createSysCompany(@RequestBody SysCompanyDTO sysCompanyDTO) {
        SysCompany sysCompany = sysCompanyMapper.toEntity(sysCompanyDTO);
        sysCompanyRepository.getOneByCompanyName(sysCompany.getCompanyName())
            .ifPresent(so -> {
                throw new BadRequestAlertException("公司名称已存在", ENTITY_NAME, "companyName.exist");
            });
        sysCompanyRepository.getOneByCompanyCode(sysCompany.getCompanyCode())
            .ifPresent(so -> {
                throw new BadRequestAlertException("公司代码已存在", ENTITY_NAME, "companyCode.exist");
            });
        List<String> parentCodes = new ArrayList<>();
        AtomicReference<String> treeNames = new AtomicReference<>(sysCompany.getCompanyName());
        AtomicInteger treeLevel = new AtomicInteger(1);
        if (StringUtils.isNotEmpty(sysCompany.getParentCode())) {
            sysCompanyRepository.getOneByCompanyCode(sysCompany.getParentCode())
                .ifPresent(parentCompany -> {
                    parentCodes.add(parentCompany.getCompanyCode());
                    if (parentCompany.getParentCodes().split(",").length > 0) {
                        parentCodes.addAll(Arrays.asList(parentCompany.getParentCodes().split(",")));
                    }
                    treeNames.set(parentCompany.getTreeNames() + "/" + sysCompany.getCompanyName());
                    treeLevel.set(parentCompany.getTreeLevel() + 1);
                    // 更新treeLeaf
                    sysCompanyRepository.save(parentCompany.treeLeaf(false));
                });
        } else {
            sysCompany.setParentCode(null);
        }
        sysCompany.parentCodes(String.join(",", parentCodes))
            .treeLevel(treeLevel.intValue())
            .treeNames(treeNames.get())
            .status(CompanyStatusType.NORMAL)
            .treeLeaf(true);
        SysCompany newSysCompany = sysCompanyRepository.save(sysCompany);

        if (sysCompanyDTO.getSysOfficeIds() != null) {
            sysCompanyDTO.getSysOfficeIds().forEach(sysOfficeId ->
                sysCompanyOfficeRepository.saveAndFlush(new SysCompanyOffice().sysCompanyId(newSysCompany.getId()).sysOfficeId(Long.valueOf(sysOfficeId)))
            );
        }

        return ResponseEntity.ok().build();
    }

    @PutMapping("/sys-companys")
    @Transactional
    @ApiOperation(value = "修改公司", notes = "作者：李春浩")
    public ResponseEntity<Void> updateSysCompany(@RequestBody SysCompanyDTO sysCompanyDTO) {
        SysCompany sysCompany = sysCompanyMapper.toEntity(sysCompanyDTO);
        sysCompanyRepository.getOneByCompanyNameAndIdNot(sysCompany.getCompanyName(), sysCompany.getId())
            .ifPresent(sp -> {
                throw new BadRequestAlertException("公司名称已存在", ENTITY_NAME, "companyName.exist");
            });
        sysCompanyRepository.getOneByCompanyCodeAndIdNot(sysCompany.getCompanyCode(), sysCompany.getId())
            .ifPresent(so -> {
                throw new BadRequestAlertException("公司代码已存在", ENTITY_NAME, "companyCode.exist");
            });
        // 判断是否修改了上级公司或公司名称
        SysCompany sysCompanyDB = sysCompanyRepository.getOne(sysCompany.getId());
        if (!Objects.equals(sysCompany.getParentCode(), sysCompanyDB.getParentCode())) {
            sysCompanyRepository.getOneByCompanyCode(sysCompany.getParentCode())
                .map(parentCompany -> {
                    // 判断新的上级公司是否是此公司或此公司的下级公司
                    if (Objects.equals(parentCompany.getCompanyCode(), sysCompany.getCompanyCode())
                        || Arrays.asList(parentCompany.getParentCodes().split(",")).contains(sysCompany.getCompanyCode())) {
                        throw new BadRequestAlertException("不可将上级公司修改为本公司及下属公司", ENTITY_NAME, "parentCode.error");
                    }
                    sysCompany.setParentCodes(parentCompany.getParentCodes() + parentCompany.getCompanyCode() + ",");
                    sysCompany.setTreeNames(parentCompany.getTreeNames() + "/" + sysCompany.getCompanyName());
                    sysCompany.setTreeLevel(parentCompany.getTreeLevel() + 1);
                    sysCompany.setTreeLeaf(false);
                    // 原来的上级公司除了此公司外无其他公司
                    if (StringUtils.isNotEmpty(sysCompanyDB.getParentCode())
                        && sysCompanyRepository.getAllByParentCodeAndCompanyCodeNot(sysCompanyDB.getParentCode(), sysCompany.getCompanyCode()).isEmpty()) {
                        parentCompany.setTreeLeaf(true);
                        sysCompanyRepository.save(parentCompany);
                    }
                    return parentCompany;
                })
                .orElseGet(() -> {
                    sysCompany.setParentCodes("");
                    sysCompany.setTreeNames(sysCompany.getCompanyName());
                    sysCompany.setTreeLevel(1);
                    return sysCompany;
                });
        } else {
            if (!Objects.equals(sysCompany.getCompanyName(), sysCompanyDB.getCompanyName())) {
                sysCompanyRepository.getOneByCompanyCode(sysCompany.getParentCode())
                    .map(parentCompany -> {
                        sysCompany.setTreeNames(parentCompany.getTreeNames() + "/" + sysCompany.getCompanyName());
                        return parentCompany;
                    })
                    .orElseGet(() -> {
                        sysCompany.setTreeNames(sysCompany.getCompanyName());
                        return sysCompany;
                    });
            }
        }
        sysCompanyRepository.save(sysCompany);
        if (sysCompanyDTO.getSysOfficeIds() != null) {
            sysCompanyOfficeRepository.deleteAllBySysCompanyId(sysCompanyDTO.getId());
            sysCompanyDTO.getSysOfficeIds().forEach(sysOfficeId ->
                sysCompanyOfficeRepository.saveAndFlush(new SysCompanyOffice().sysCompanyId(sysCompanyDTO.getId()).sysOfficeId(Long.valueOf(sysOfficeId)))
            );
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/sys-companys/{companyCode}")
    @ApiOperation(value = "删除公司及下属公司", notes = "作者：李春浩")
    public ResponseEntity<Void> deleteSysCompany(@PathVariable String companyCode) {
        sysCompanyRepository.deleteAllByCompanyCodeOrParentCodesContains(companyCode);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/sys-companys")
    @ApiOperation(value = "公司列表", notes = "作者：李春浩")
    public ResponseEntity<List<SysCompanyNode>> getSysCompanys(SysCompanyQM vm, Pageable pageable) {
        // 获取满足查询条件的节点，对满足条件的节点按树分组
        List<Long> ids = sysCompanyRepository.getAllNodeByParam(vm.getCompanyCode(), vm.getCompanyName(), vm.getFullName(), vm.getStatus() == null ? null : vm.getStatus().toString());
        List<String> treeMinLevels = sysCompanyRepository.getAllTreeMinLevelByParam(ids.size() == 0 ? Collections.singletonList(-1L) : ids)
            .stream().map(treeMinLevel -> treeMinLevel.getTreeName() + treeMinLevel.getTreeLevel())
            .collect(Collectors.toList());
        // 取每个树最小层的所有节点
        Page<SysCompany> page = sysCompanyRepository.getAllByTreeMinLevels(treeMinLevels.size() == 0 ? Collections.singletonList("-1") : treeMinLevels, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "");
        List<SysCompanyNode> sysCompanyNodes = page.getContent().stream().map(rootCompany -> {
            SysCompanyNode parent = new SysCompanyNode();
            parent.setSysCompany(rootCompany);
            recursiveGetIn(parent, ids);
            return parent;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(sysCompanyNodes, headers, HttpStatus.OK);
    }

    @GetMapping("/sys-companys/{id}")
    @ApiOperation(value = "公司详情", notes = "作者：李春浩")
    public ResponseEntity<SysCompanyDTO> getSysCompany(@PathVariable Long id) {
        Optional<SysCompanyDTO> sysCompanyDTOOpt = sysCompanyRepository.findById(id)
            .map(sysCompany -> {
                SysCompanyDTO sysCompanyDTO = sysCompanyMapper.toDto(sysCompany);
                sysCompanyDTO.setSysOfficeIds(
                    sysCompanyOfficeRepository.findAllBySysCompanyId(id)
                        .stream()
                        .map(sysCompanyOffice -> String.valueOf(sysCompanyOffice.getSysOfficeId()))
                        .collect(Collectors.toList()));
                return sysCompanyDTO;
            });

        return ResponseUtil.wrapOrNotFound(sysCompanyDTOOpt);
    }
}
