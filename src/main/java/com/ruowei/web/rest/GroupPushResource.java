package com.ruowei.web.rest;

import com.ruowei.domain.*;
import com.ruowei.repository.*;
import com.ruowei.util.ObjectUtils;
import com.ruowei.web.rest.errors.BadRequestAlertException;
import com.ruowei.web.rest.vm.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    private final SewMeterRepository sewMeterRepository;
    private final SewEmiThresholdRepository sewEmiThresholdRepository;
    private final UserRepository userRepository;

    public GroupPushResource(EnterpriseRepository enterpriseRepository, GroupRepository groupRepository, CraftRepository craftRepository, SewProcessRepository sewProcessRepository, SewSluRepository sewSluRepository, SewPotRepository sewPotRepository, SewMeterRepository sewMeterRepository, SewEmiThresholdRepository sewEmiThresholdRepository, UserRepository userRepository) {
        this.enterpriseRepository = enterpriseRepository;
        this.groupRepository = groupRepository;
        this.craftRepository = craftRepository;
        this.sewProcessRepository = sewProcessRepository;
        this.sewSluRepository = sewSluRepository;
        this.sewPotRepository = sewPotRepository;
        this.sewMeterRepository = sewMeterRepository;
        this.sewEmiThresholdRepository = sewEmiThresholdRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/comprehensive")
    @Transactional
    @ApiOperation(value = "集团接收水厂推送仪表，化验，日报数据接口", notes = "作者：韩宗晏")
    public ResponseEntity<String> createData(@RequestBody ComprehensiveDataVM vm) {
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
        //TODO 新增校表数据
        result = "推送成功";
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/alter_sewProcess")
    @Transactional
    @ApiOperation(value = "集团接收水厂更新仪表数据", notes = "作者：韩宗晏")
    public ResponseEntity<String> editSewProcess(@RequestBody EditSewProcessVM vm) {
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
    public ResponseEntity<String> editSewSlu(@RequestBody EditSewSluVM vm) {
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
    public ResponseEntity<String> editSewPot(@RequestBody EditSewPotVM vm) {
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

    @PostMapping("/alter_sewMeter")
    @Transactional
    @ApiOperation(value = "集团接收水厂更新校表数据", notes = "作者：韩宗晏")
    public ResponseEntity<String> editSewMeter(@RequestBody EditSewMeterVM vm) {
        AtomicReference<String> result = new AtomicReference<>("");
        Optional<Enterprise> enterprise = enterpriseRepository.findByCodeAndIsTryIsTrue(vm.getCode());
        if (!enterprise.isPresent()) {
            throw new BadRequestAlertException("水厂不存在", "", "");
        }
        //根据工艺编码和编码确定唯一一条日报数据
        sewMeterRepository.findByCraftCodeAndMeterCode(vm.getCraftCode(), vm.getMeterCode())
            .map(sewMeter -> {
                ObjectUtils.copyPropertiesIgnoreNull(vm, sewMeter);
                sewMeterRepository.save(sewMeter);
                result.set("推送成功");
                return sewMeter;
            }).orElseThrow(() -> new BadRequestAlertException("校表数据不存在", "", "编辑失败"));
        return ResponseEntity.ok().body(result.get());
    }

    @PostMapping("/sewEmithreshold")
    @Transactional
    @ApiOperation(value = "集团接收试点水厂新增编辑设定数据", notes = "作者：韩宗晏")
    public ResponseEntity<String> sewEmithreshold(@RequestBody SewEmithresholdVM vm) {
        AtomicReference<String> result = new AtomicReference<>("");
        Optional<Enterprise> enterprise = enterpriseRepository.findByCodeAndIsTryIsTrue(vm.getEnterpriseCode());
        if (!enterprise.isPresent()) {
            throw new BadRequestAlertException("水厂不存在", "", "");
        }
        //一个水厂对应一条设定数据
        if (vm.getOperate().equals(0)) {
            //新增设定数据
            SewEmiThreshold sewEmiThreshold = new SewEmiThreshold();
            ObjectUtils.copyPropertiesIgnoreNull(vm, sewEmiThreshold);
            sewEmiThresholdRepository.save(sewEmiThreshold);
        } else {
            //编辑设定数据
            sewEmiThresholdRepository.findByEnterpriseCode(vm.getEnterpriseCode())
                .map(sewEmiThreshold -> {
                    ObjectUtils.copyPropertiesIgnoreNull(vm, sewEmiThreshold);
                    sewEmiThresholdRepository.save(sewEmiThreshold);
                    return sewEmiThreshold;
                }).orElseThrow(() -> new BadRequestAlertException("设定数据不存在", "", ""));
        }
        return ResponseEntity.ok().body(result.get());
    }

    @PostMapping("/enterprise_user")
    @Transactional
    @ApiOperation(value = "集团接收水厂新增编辑用户数据", notes = "作者：韩宗晏")
    public ResponseEntity<String> addUser(@RequestBody EnterpriseUserVM vm) {
        AtomicReference<String> result = new AtomicReference<>("");
        Optional<Enterprise> enterprise = enterpriseRepository.findByCode(vm.getEnterpriseCode());
        if (!enterprise.isPresent()) {
            throw new BadRequestAlertException("水厂不存在", "", "");
        }
        if (vm.getOperate().equals(0)) {
            //集团新增水厂用户数据
            User user = new User();
            ObjectUtils.copyPropertiesIgnoreNull(vm, user);
            userRepository.save(user);
            result.set("推送成功");
        } else {
            //集团更新水厂用户信息 集团编码和水厂编码都不为空是水厂用户
            userRepository.findByEnterpriseCodeAndGroupCodeAndUserCode(vm.getEnterpriseCode(), vm.getGroupCode(), vm.getUserCode())
                .map(user -> {
                    ObjectUtils.copyPropertiesIgnoreNull(vm, user);
                    userRepository.save(user);
                    return user;
                }).orElseThrow(() -> new BadRequestAlertException("用户不存在", "", ""));
            result.set("推送成功");
        }
        return ResponseEntity.ok().body(result.get());
    }

    @PostMapping("/delete_enterprise_user")
    @Transactional
    @ApiOperation(value = "集团接收水厂删除用户数据", notes = "作者：韩宗晏")
    public ResponseEntity<String> deleteUser(@RequestParam String userCode, @RequestParam String enterpriseCode) {
        AtomicReference<String> result = new AtomicReference<>("");
        Optional<Enterprise> enterprise = enterpriseRepository.findByCode(enterpriseCode);
        if (!enterprise.isPresent()) {
            throw new BadRequestAlertException("该水厂不存在", "", "无法删除用户");
        }
        //集团删除水厂用户信息
        userRepository.findByEnterpriseCodeAndUserCode(enterpriseCode, userCode)
            .map(user -> {
                userRepository.delete(user);
                result.set("推送成功");
                return user;
            }).orElseThrow(() -> new BadRequestAlertException("用户不存在", "", ""));
        return ResponseEntity.ok().body(result.get());
    }

    @PostMapping("/decision_resultData")
    @Transactional
    @ApiOperation(value = "集团接收试点水厂决策预测计算结果数据", notes = "作者：韩宗晏")
    public ResponseEntity<String> decisionData() {
        AtomicReference<String> result = new AtomicReference<>("");

        return ResponseEntity.ok().body(result.get());
    }
}
