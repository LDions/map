package com.ruowei.web.rest;

import com.ruowei.domain.Enterprise;
import com.ruowei.domain.SewPot;
import com.ruowei.domain.SewProcess;
import com.ruowei.domain.SewSlu;
import com.ruowei.repository.*;
import com.ruowei.util.ObjectUtils;
import com.ruowei.web.rest.errors.BadRequestAlertException;
import com.ruowei.web.rest.vm.ComprehensiveDataVM;
import com.ruowei.web.rest.vm.EditSewPotVM;
import com.ruowei.web.rest.vm.EditSewProcessVM;
import com.ruowei.web.rest.vm.EditSewSluVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;


@RestController
@RequestMapping("/api/push")
@Api(tags = "集团接收推送数据相关接口")
public class GroupPushResource {

    private final EnterpriseRepository enterpriseRepository;
    private final GroupRepository groupRepository;
    private final CraftRepository craftRepository;
    private final SewProcessRepository sewProcessRepository;
    private final SewSluRepository sewSluRepository;
    private final SewPotRepository sewPotRepository;

    public GroupPushResource(EnterpriseRepository enterpriseRepository, GroupRepository groupRepository, CraftRepository craftRepository, SewProcessRepository sewProcessRepository, SewSluRepository sewSluRepository, SewPotRepository sewPotRepository) {
        this.enterpriseRepository = enterpriseRepository;
        this.groupRepository = groupRepository;
        this.craftRepository = craftRepository;
        this.sewProcessRepository = sewProcessRepository;
        this.sewSluRepository = sewSluRepository;
        this.sewPotRepository = sewPotRepository;
    }

    @PostMapping("/comprehensive")
    @Transactional
    @ApiOperation(value = "集团接收水厂推送仪表，化验，日报数据接口", notes = "作者：韩宗晏")
    public ResponseEntity<String> createData(ComprehensiveDataVM vm) {
        String result = "";
        Optional<Enterprise> enterprise = enterpriseRepository.findByCodeAndIsTryIsTrue(vm.getCode());
        if (!enterprise.isPresent()) {
            throw new BadRequestAlertException("水厂不存在", "", "");
        }
        //仪表数据
        SewProcess sewProcess = new SewProcess();
        ObjectUtils.copyPropertiesIgnoreNull(vm, sewProcess);
        sewProcessRepository.save(sewProcess);
        //化验数据
        SewSlu sewSlu = new SewSlu();
        ObjectUtils.copyPropertiesIgnoreNull(vm, sewSlu);
        sewSluRepository.save(sewSlu);
        //日报表数据
        SewPot sewPot = new SewPot();
        ObjectUtils.copyPropertiesIgnoreNull(vm, sewPot);
        sewPotRepository.save(sewPot);
        result = "推送成功";
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/alter_sewProcess")
    @Transactional
    @ApiOperation(value = "集团接收水厂更新仪表数据", notes = "作者：韩宗晏")
    public ResponseEntity<String> editSewProcess(EditSewProcessVM vm) {
        AtomicReference<String> result = new AtomicReference<>("");
        Optional<Enterprise> enterprise = enterpriseRepository.findByCodeAndIsTryIsTrue(vm.getCode());
        if (!enterprise.isPresent()) {
            throw new BadRequestAlertException("水厂不存在", "", "");
        }
        //根据工艺编码和仪表编码确定唯一一条仪表数据
        sewProcessRepository.findByCraftCodeAndProcessCode(vm.getCraftCode(), vm.getProcessCode())
            .map(sewProcess -> {
                ObjectUtils.copyPropertiesIgnoreNull(vm, sewProcess);
                //根据工艺编码查到工艺信息,确保与集团端的工艺信息能对应上
                craftRepository.findByCraftCode(vm.getCraftCode())
                    .ifPresent(craft -> {
                        sewProcess.setCraftId(craft.getId());
                    });
                sewProcessRepository.save(sewProcess);
                result.set("推送成功");
                return sewProcess;
            }).orElseThrow(() -> new BadRequestAlertException("仪表数据不存在", "", "编辑失败"));
        return ResponseEntity.ok().body(result.get());
    }

    @PostMapping("/alter_sewSlu")
    @Transactional
    @ApiOperation(value = "集团接收水厂更新化验数据", notes = "作者：韩宗晏")
    public ResponseEntity<String> editSewSlu(EditSewSluVM vm) {
        AtomicReference<String> result = new AtomicReference<>("");
        Optional<Enterprise> enterprise = enterpriseRepository.findByCodeAndIsTryIsTrue(vm.getCode());
        if (!enterprise.isPresent()) {
            throw new BadRequestAlertException("水厂不存在", "", "");
        }
        //根据工艺编码和编码确定唯一一条仪表数据
        sewSluRepository.findByCraftCodeAndSluCode(vm.getCraftCode(), vm.getSluCode())
            .map(sewSlu -> {
                ObjectUtils.copyPropertiesIgnoreNull(vm, sewSlu);
                //根据工艺编码查到工艺信息,确保与集团端的工艺信息能对应上
                craftRepository.findByCraftCode(vm.getCraftCode())
                    .ifPresent(craft -> {
                        sewSlu.setCraftId(craft.getId());
                    });
                sewSluRepository.save(sewSlu);
                result.set("推送成功");
                return sewSlu;
            }).orElseThrow(() -> new BadRequestAlertException("化验数据不存在", "", "编辑失败"));
        return ResponseEntity.ok().body(result.get());
    }

    @PostMapping("/alter_sewPot")
    @Transactional
    @ApiOperation(value = "集团接收水厂更新日报数据", notes = "作者：韩宗晏")
    public ResponseEntity<String> editSewPot(EditSewPotVM vm) {
        AtomicReference<String> result = new AtomicReference<>("");
        Optional<Enterprise> enterprise = enterpriseRepository.findByCodeAndIsTryIsTrue(vm.getCode());
        if (!enterprise.isPresent()) {
            throw new BadRequestAlertException("水厂不存在", "", "");
        }
        //根据工艺编码和编码确定唯一一条日报数据
        sewPotRepository.findByCraftCodeAndPotCode(vm.getCraftCode(), vm.getPotCode())
            .map(sewPot -> {
                ObjectUtils.copyPropertiesIgnoreNull(vm, sewPot);
                //根据工艺编码查到工艺信息,确保与集团端的工艺信息能对应上
                craftRepository.findByCraftCode(vm.getCraftCode())
                    .ifPresent(craft -> {
                        sewPot.setCraftId(craft.getId());
                    });
                sewPotRepository.save(sewPot);
                result.set("推送成功");
                return sewPot;
            }).orElseThrow(() -> new BadRequestAlertException("日报数据不存在", "", "编辑失败"));
        return ResponseEntity.ok().body(result.get());
    }
}
