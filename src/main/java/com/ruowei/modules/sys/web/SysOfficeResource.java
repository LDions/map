package com.ruowei.modules.sys.web;

import com.ruowei.common.error.exception.BadRequestAlertException;
import com.ruowei.common.response.PaginationUtil;
import com.ruowei.modules.sys.domain.table.SysOffice;
import com.ruowei.modules.sys.domain.enumeration.OfficeStatusType;
import com.ruowei.modules.sys.pojo.SysOfficeNode;
import com.ruowei.modules.sys.pojo.vm.SysOfficeQM;
import com.ruowei.modules.sys.repository.SysOfficeRepository;
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
@Api(tags = "组织机构")
public class SysOfficeResource {

    private static final String ENTITY_NAME = "机构";
    private final Logger log = LoggerFactory.getLogger(SysOfficeResource.class);
    private final SysOfficeRepository sysOfficeRepository;

    public SysOfficeResource(SysOfficeRepository sysOfficeRepository) {
        this.sysOfficeRepository = sysOfficeRepository;
    }

    private void recursiveGet(SysOfficeNode sysOfficeNode) {
        sysOfficeNode.setChildren(
            sysOfficeRepository.getAllByParentCodeAndStatus(sysOfficeNode.getSysOffice().getOfficeCode(), OfficeStatusType.NORMAL)
                .stream().map(sysOffice -> {
                SysOfficeNode child = new SysOfficeNode();
                child.setSysOffice(sysOffice);
                return child;
            }).collect(Collectors.toList())
        );
        sysOfficeNode.getChildren().forEach(this::recursiveGet);
    }

    private void recursiveGetIn(SysOfficeNode sysOfficeNode, List<Long> ids) {
        sysOfficeNode.setChildren(
            sysOfficeRepository.getAllByParentCodeAndIdIn(sysOfficeNode.getSysOffice().getOfficeCode(), ids)
                .stream().map(sysOffice -> {
                SysOfficeNode child = new SysOfficeNode();
                child.setSysOffice(sysOffice);
                return child;
            }).collect(Collectors.toList())
        );
        sysOfficeNode.getChildren().forEach(childNode -> recursiveGetIn(childNode, ids));
    }

    @GetMapping("/sys-offices/tree")
    @ApiOperation(value = "获取组织机构树", notes = "作者：李春浩")
    public ResponseEntity<List<SysOfficeNode>> getSysOfficeTree() {
        List<SysOfficeNode> sysOfficeNodes = sysOfficeRepository.getAllByParentCodeIsNullAndStatus(OfficeStatusType.NORMAL)
            .stream().map(sysOffice -> {
                SysOfficeNode parent = new SysOfficeNode();
                parent.setSysOffice(sysOffice);
                recursiveGet(parent);
                return parent;
            })
            .collect(Collectors.toList());
        return ResponseEntity.ok(sysOfficeNodes);
    }

    @PostMapping("/sys-offices")
    @ApiOperation(value = "新增机构", notes = "作者：李春浩")
    public ResponseEntity<Void> createSysOffice(@RequestBody SysOffice sysOffice) {
        sysOfficeRepository.getOneByOfficeName(sysOffice.getOfficeName())
            .ifPresent(so -> {
                throw new BadRequestAlertException("机构名称已存在", ENTITY_NAME, "officeName.exist");
            });
        sysOfficeRepository.getOneByOfficeCode(sysOffice.getOfficeCode())
            .ifPresent(so -> {
                throw new BadRequestAlertException("机构代码已存在", ENTITY_NAME, "officeCode.exist");
            });
        List<String> parentCodes = new ArrayList<>();
        AtomicReference<String> treeNames = new AtomicReference<>(sysOffice.getOfficeName());
        AtomicInteger treeLevel = new AtomicInteger(1);
        if (StringUtils.isNotEmpty(sysOffice.getParentCode())) {
            sysOfficeRepository.getOneByOfficeCode(sysOffice.getParentCode())
                .ifPresent(parentOffice -> {
                    parentCodes.add(parentOffice.getOfficeCode());
                    parentCodes.addAll(Arrays.asList(parentOffice.getParentCodes().split(",")));
                    treeNames.set(parentOffice.getTreeNames() + "/" + sysOffice.getOfficeName());
                    treeLevel.set(parentOffice.getTreeLevel() + 1);
                    // 更新treeLeaf
                    sysOfficeRepository.save(parentOffice.treeLeaf(false));
                });
        } else {
            sysOffice.setParentCode(null);
        }

        sysOffice.parentCodes(String.join(",", parentCodes))
            .treeLevel(treeLevel.intValue())
            .treeNames(treeNames.get())
            .status(OfficeStatusType.NORMAL)
            .treeLeaf(true);
        sysOfficeRepository.save(sysOffice);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/sys-offices")
    @Transactional
    @ApiOperation(value = "修改机构", notes = "作者：李春浩")
    public ResponseEntity<Void> updateSysOffice(@RequestBody SysOffice sysOffice) {
        sysOfficeRepository.getOneByOfficeNameAndIdNot(sysOffice.getOfficeName(), sysOffice.getId())
            .ifPresent(sp -> {
                throw new BadRequestAlertException("机构名称已存在", ENTITY_NAME, "officeName.exist");
            });
        sysOfficeRepository.getOneByOfficeCodeAndIdNot(sysOffice.getOfficeCode(), sysOffice.getId())
            .ifPresent(so -> {
                throw new BadRequestAlertException("机构代码已存在", ENTITY_NAME, "officeCode.exist");
            });
        // 判断是否修改了上级机构或机构名称
        SysOffice sysOfficeDB = sysOfficeRepository.getOne(sysOffice.getId());
        if (!Objects.equals(sysOffice.getParentCode(), sysOfficeDB.getParentCode())) {
            sysOfficeRepository.getOneByOfficeCode(sysOffice.getParentCode())
                .map(parentOffice -> {
                    // 判断新的上级机构是否是此机构或此机构的下级机构
                    if (Objects.equals(parentOffice.getOfficeCode(), sysOffice.getOfficeCode())
                        || Arrays.asList(parentOffice.getParentCodes().split(",")).contains(sysOffice.getOfficeCode())) {
                        throw new BadRequestAlertException("不可将上级机构修改为本机构及下属机构", ENTITY_NAME, "parentCode.error");
                    }
                    sysOffice.setParentCodes(parentOffice.getParentCodes() + parentOffice.getOfficeCode() + ",");
                    sysOffice.setTreeNames(parentOffice.getTreeNames() + "/" + sysOffice.getOfficeName());
                    sysOffice.setTreeLevel(parentOffice.getTreeLevel() + 1);
                    sysOffice.setTreeLeaf(false);
                    // 原来的上级机构除了此机构外无其他机构
                    if (StringUtils.isNotEmpty(sysOfficeDB.getParentCode())
                        && sysOfficeRepository.getAllByParentCodeAndOfficeCodeNot(sysOfficeDB.getParentCode(), sysOffice.getOfficeCode()).isEmpty()) {
                        parentOffice.setTreeLeaf(true);
                        sysOfficeRepository.save(parentOffice);
                    }
                    return parentOffice;
                })
                .orElseGet(() -> {
                    sysOffice.setParentCodes("");
                    sysOffice.setTreeNames(sysOffice.getOfficeName());
                    sysOffice.setTreeLevel(1);
                    return sysOffice;
                });
        } else {
            if (!Objects.equals(sysOffice.getOfficeName(), sysOfficeDB.getOfficeName())) {
                sysOfficeRepository.getOneByOfficeCode(sysOffice.getParentCode())
                    .map(parentOffice -> {
                        sysOffice.setTreeNames(parentOffice.getTreeNames() + "/" + sysOffice.getOfficeName());
                        return parentOffice;
                    })
                    .orElseGet(() -> {
                        sysOffice.setTreeNames(sysOffice.getOfficeName());
                        return sysOffice;
                    });
            }
        }
        sysOfficeRepository.save(sysOffice);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/sys-offices/{officeCode}")
    @Transactional
    @ApiOperation(value = "删除机构及下属机构", notes = "作者：李春浩")
    public ResponseEntity<Void> deleteSysOffice(@PathVariable String officeCode) {
        sysOfficeRepository.deleteAllByOfficeCodeOrParentCodesContains(officeCode);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/sys-offices")
    @ApiOperation(value = "机构列表", notes = "作者：李春浩")
    public ResponseEntity<List<SysOfficeNode>> getSysOffices(SysOfficeQM vm, Pageable pageable) {
        // 获取满足查询条件的节点，对满足条件的节点按树分组
        List<Long> ids = sysOfficeRepository.getAllNodeByParam(vm.getOfficeId(), vm.getOfficeCode(), vm.getOfficeName(), vm.getFullName(), vm.getOfficeType() == null ? null : vm.getOfficeType().toString(), vm.getStatus() == null ? null : vm.getStatus().toString());
        List<String> treeMinLevels = sysOfficeRepository.getAllTreeMinLevelByParam(ids.size() == 0 ? Collections.singletonList(-1L) : ids)
            .stream().map(treeMinLevel -> treeMinLevel.getTreeName() + treeMinLevel.getTreeLevel())
            .collect(Collectors.toList());
        // 取每个树最小层的所有节点
        Page<SysOffice> page = sysOfficeRepository.getAllByTreeMinLevels(treeMinLevels.size() == 0 ? Collections.singletonList("-1") : treeMinLevels, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "");
        List<SysOfficeNode> sysOfficeNodes = page.getContent().stream().map(rootOffice -> {
            SysOfficeNode parent = new SysOfficeNode();
            parent.setSysOffice(rootOffice);
            recursiveGetIn(parent, ids);
            return parent;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(sysOfficeNodes, headers, HttpStatus.OK);
    }

    @GetMapping("/sys-offices/{id}")
    @ApiOperation(value = "机构详情", notes = "作者：李春浩")
    public ResponseEntity<SysOffice> getSysOffice(@PathVariable Long id) {
        Optional<SysOffice> sysOfficeOptional = sysOfficeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sysOfficeOptional);
    }
}
