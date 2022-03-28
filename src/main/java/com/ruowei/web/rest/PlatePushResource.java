package com.ruowei.web.rest;

import com.ruowei.domain.*;
import com.ruowei.repository.*;
import com.ruowei.util.ObjectUtils;
import com.ruowei.web.rest.errors.BadRequestAlertException;
import com.ruowei.web.rest.vm.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import liquibase.pro.packaged.S;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/api/push")
@Api(tags = "平台接收推送数据相关接口")
public class PlatePushResource {

    private final EnterpriseRepository enterpriseRepository;
    private final GroupRepository groupRepository;
    private final CraftRepository craftRepository;
    private final SewProcessRepository sewProcessRepository;
    private final SewSluRepository sewSluRepository;
    private final SewPotRepository sewPotRepository;
    private final SewMeterRepository sewMeterRepository;
    private final SewEmiThresholdRepository sewEmiThresholdRepository;
    private final UserRepository userRepository;


    public PlatePushResource(EnterpriseRepository enterpriseRepository, GroupRepository groupRepository, CraftRepository craftRepository, SewProcessRepository sewProcessRepository, SewSluRepository sewSluRepository, SewPotRepository sewPotRepository, SewMeterRepository sewMeterRepository, SewEmiThresholdRepository sewEmiThresholdRepository, UserRepository userRepository) {
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

    @PostMapping("/plate/comprehensive")
    @Transactional
    @ApiOperation(value = "平台接收水厂（或集团）推送新增仪表，化验，日报数据接口", notes = "作者：韩宗晏")
    public ResponseEntity<String> createData(@RequestBody PlateComprehensiveDataVM vm) {
        AtomicReference<String> result = new AtomicReference<>("");
        groupRepository.findByGroupCode(vm.getGroupCode())
            .map(group -> {
                //根据水厂编码和所属集团id确定一个水厂,是试点水厂就是水厂推送过来的新增数据，不是试点水厂就是集团推送过来的非试点水厂新增数据
                Optional<Enterprise> enterprise = enterpriseRepository.findByCodeAndGroupCodeAndIsTry(vm.getCode(), group.getGroupCode(), vm.getIsTry());
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
                result.set("推送成功");
                //TODO 新增校表数据
                return group;
            }).orElseThrow(() -> new BadRequestAlertException("集团不存在", "", ""));
        return ResponseEntity.ok().body(result.get());
    }

    @PostMapping("/plate/alter_sewProcess")
    @Transactional
    @ApiOperation(value = "平台接收水厂（或集团）更新仪表数据", notes = "作者：韩宗晏")
    public ResponseEntity<String> editSewProcess(@RequestBody PlateEditSewProcessVM vm) {
        AtomicReference<String> result = new AtomicReference<>("");
        groupRepository.findByGroupCode(vm.getGroupCode())
            .map(group -> {
                //根据水厂编码和所属集团id确定一个水厂,是试点水厂就是水厂推送过来的更新仪表数据，不是试点水厂就是集团推送过来的非试点水厂更新仪表数据
                Optional<Enterprise> enterprise = enterpriseRepository.findByCodeAndGroupCodeAndIsTry(vm.getCode(), group.getGroupCode(), vm.getIsTry());
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
                return group;
            }).orElseThrow(() -> new BadRequestAlertException("集团不存在", "", "编辑失败"));
        return ResponseEntity.ok().body(result.get());
    }

    @PostMapping("/plate/alter_sewSlu")
    @Transactional
    @ApiOperation(value = "平台接收水厂(或集团)更新化验数据", notes = "作者：韩宗晏")
    public ResponseEntity<String> editSewSlu(@RequestBody PlateEditSewSluVM vm) {
        AtomicReference<String> result = new AtomicReference<>("");
        groupRepository.findByGroupCode(vm.getGroupCode())
            .map(group -> {
                //根据水厂编码和所属集团id确定一个水厂,是试点水厂就是水厂推送过来的更新化验数据，不是试点水厂就是集团推送过来的非试点水厂更新化验数据
                Optional<Enterprise> enterprise = enterpriseRepository.findByCodeAndGroupCodeAndIsTry(vm.getCode(), group.getGroupCode(), vm.getIsTry());
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
                return group;
            }).orElseThrow(() -> new BadRequestAlertException("集团不存在", "", "编辑失败"));
        return ResponseEntity.ok().body(result.get());
    }

    @PostMapping("/plate/alter_sewPot")
    @Transactional
    @ApiOperation(value = "平台接收水厂(或集团)更新日报数据", notes = "作者：韩宗晏")
    public ResponseEntity<String> editSewPot(@RequestBody PlateEditSewPotVM vm) {
        AtomicReference<String> result = new AtomicReference<>("");
        groupRepository.findByGroupCode(vm.getGroupCode())
            .map(group -> {
                //根据水厂编码和所属集团id确定一个水厂,是试点水厂就是水厂推送过来的更新日报数据，不是试点水厂就是集团推送过来的非试点水厂更新日报数据
                Optional<Enterprise> enterprise = enterpriseRepository.findByCodeAndGroupCodeAndIsTry(vm.getCode(), group.getGroupCode(), vm.getIsTry());
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
                return group;
            }).orElseThrow(() -> new BadRequestAlertException("集团不存在", "", "编辑失败"));
        return ResponseEntity.ok().body(result.get());
    }

    @PostMapping("/plate/alter_sewMeter")
    @Transactional
    @ApiOperation(value = "平台接收水厂（或集团）更新校表数据", notes = "作者：韩宗晏")
    public ResponseEntity<String> editSewPot(@RequestBody PlateEditSewMeterVM vm) {
        AtomicReference<String> result = new AtomicReference<>("");
        groupRepository.findByGroupCode(vm.getGroupCode())
            .map(group -> {
                //根据水厂编码和所属集团id确定一个水厂,是试点水厂就是水厂推送过来的更新校表数据，不是试点水厂就是集团推送过来的非试点水厂更新校表数据
                Optional<Enterprise> enterprise = enterpriseRepository.findByCodeAndGroupCodeAndIsTry(vm.getCode(), group.getGroupCode(), vm.getIsTry());
                if (!enterprise.isPresent()) {
                    throw new BadRequestAlertException("水厂不存在", "", "");
                }
                //根据工艺编码和编码确定唯一一条校表数据
                sewMeterRepository.findByCraftCodeAndMeterCode(vm.getCraftCode(), vm.getMeterCode())
                    .map(sewMeter -> {
                        ObjectUtils.copyPropertiesIgnoreNull(vm, sewMeter);
                        sewMeterRepository.save(sewMeter);
                        result.set("推送成功");
                        return sewMeter;
                    }).orElseThrow(() -> new BadRequestAlertException("校表数据不存在", "", "编辑失败"));
                return group;
            }).orElseThrow(() -> new BadRequestAlertException("集团不存在", "", "编辑失败"));
        return ResponseEntity.ok().body(result.get());
    }

    @PostMapping("/plate/sewEmithreshold")
    @Transactional
    @ApiOperation(value = "平台接收试点水厂新增编辑设定数据", notes = "作者：韩宗晏")
    public ResponseEntity<String> sewEmithreshold(@RequestBody PlateSewEmithresholdVM vm) {
        AtomicReference<String> result = new AtomicReference<>("");
        groupRepository.findByGroupCode(vm.getGroupCode())
            .map(group -> {
                //根据水厂编码和所属集团id确定一个水厂,是试点水厂就是水厂推送过来的更新校表数据，不是试点水厂就是集团推送过来的非试点水厂更新校表数据
                Optional<Enterprise> enterprise = enterpriseRepository.findByCodeAndGroupCodeAndIsTry(vm.getEnterpriseCode(), group.getGroupCode(), vm.getIsTry());
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
                return group;
            }).orElseThrow(() -> new BadRequestAlertException("集团不存在", "", "编辑失败"));
        return ResponseEntity.ok().body(result.get());
    }

    @PostMapping("/plate/enterprise_user")
    @Transactional
    @ApiOperation(value = "平台接收水厂新增编辑用户数据", notes = "作者：韩宗晏")
    public ResponseEntity<String> addUser(@RequestBody EnterpriseUserVM vm) {
        AtomicReference<String> result = new AtomicReference<>("");
        groupRepository.findByGroupCode(vm.getGroupCode())
            .map(group -> {
                Optional<Enterprise> enterprise = enterpriseRepository.findByCodeAndGroupCode(vm.getEnterpriseCode(), group.getGroupCode());
                if (!enterprise.isPresent()) {
                    throw new BadRequestAlertException("水厂不存在", "", "");
                }
                if (vm.getOperate().equals(0)) {
                    //新增用户数据
                    User user = new User();
                    ObjectUtils.copyPropertiesIgnoreNull(vm, user);
                    userRepository.save(user);
                } else {
                    //编辑用户信息 水厂编码和集团编码 用户编码都存在说明是一个水厂用户数据
                    userRepository.findByEnterpriseCodeAndGroupCodeAndUserCode(vm.getEnterpriseCode(), vm.getGroupCode(), vm.getUserCode())
                        .map(user -> {
                            ObjectUtils.copyPropertiesIgnoreNull(vm, user);
                            userRepository.save(user);
                            return user;
                        }).orElseThrow(() -> new BadRequestAlertException("用户不存在", "", ""));
                }
                return group;
            }).orElseThrow(() -> new BadRequestAlertException("集团不存在", "", "编辑失败"));
        return ResponseEntity.ok().body(result.get());
    }

    @PostMapping("/plate/group_user")
    @Transactional
    @ApiOperation(value = "平台接收集团新增编辑用户数据", notes = "作者：韩宗晏")
    public ResponseEntity<String> addGroupUser(@RequestBody EnterpriseUserVM vm) {
        AtomicReference<String> result = new AtomicReference<>("");
        groupRepository.findByGroupCode(vm.getGroupCode())
            .map(group -> {
                if (vm.getOperate().equals(0)) {
                    //新增用户数据
                    User user = new User();
                    ObjectUtils.copyPropertiesIgnoreNull(vm, user);
                    userRepository.save(user);
                } else {
                    //编辑用户信息 水厂编码为空 集团编码 用户编码存在说明是一条集团的用户数据
                    userRepository.findByGroupCodeAndUserCodeAndEnterpriseCodeIsNull(vm.getGroupCode(), vm.getUserCode())
                        .map(user -> {
                            ObjectUtils.copyPropertiesIgnoreNull(vm, user);
                            userRepository.save(user);
                            return user;
                        }).orElseThrow(() -> new BadRequestAlertException("用户不存在", "", ""));
                }
                return group;
            }).orElseThrow(() -> new BadRequestAlertException("集团不存在", "", "编辑失败"));
        return ResponseEntity.ok().body(result.get());
    }

    @PostMapping("/plate/delete_enterprise_user")
    @Transactional
    @ApiOperation(value = "平台接收水厂删除用户数据", notes = "作者：韩宗晏")
    public ResponseEntity<String> deleteUser(@RequestParam String userCode,
                                             @RequestParam String enterpriseCode,
                                             @RequestParam String groupCode) {
        AtomicReference<String> result = new AtomicReference<>("");
        groupRepository.findByGroupCode(groupCode)
            .map(group -> {
                Optional<Enterprise> enterprise = enterpriseRepository.findByCodeAndGroupCode(enterpriseCode, group.getGroupCode());
                if (!enterprise.isPresent()) {
                    throw new BadRequestAlertException("水厂不存在", "", "");
                }
                //水厂编码和集团编码 用户编码都存在说明是一个水厂用户数据
                userRepository.findByEnterpriseCodeAndGroupCodeAndUserCode(enterpriseCode, group.getGroupCode(), userCode)
                    .map(user -> {
                        userRepository.delete(user);
                        return user;
                    }).orElseThrow(() -> new BadRequestAlertException("所删除的用户不存在", "", ""));
                return group;
            }).orElseThrow(() -> new BadRequestAlertException("集团不存在", "", "删除失败"));
        return ResponseEntity.ok().body(result.get());
    }

    @PostMapping("/plate/delete_group_user")
    @Transactional
    @ApiOperation(value = "平台接收集团删除用户数据", notes = "作者：韩宗晏")
    public ResponseEntity<String> deleteGroupUser(@RequestParam String userCode,
                                                  @RequestParam String groupCode) {
        AtomicReference<String> result = new AtomicReference<>("");
        groupRepository.findByGroupCode(groupCode)
            .map(group -> {
                //水厂编码为空 集团编码 用户编码存在说明是一条集团的用户数据
                userRepository.findByGroupCodeAndUserCodeAndEnterpriseCodeIsNull(group.getGroupCode(), userCode)
                    .map(user -> {
                        userRepository.delete(user);
                        return user;
                    }).orElseThrow(() -> new BadRequestAlertException("用户不存在", "", ""));
                return group;
            }).orElseThrow(() -> new BadRequestAlertException("集团不存在", "", "删除失败"));
        return ResponseEntity.ok().body(result.get());
    }

    @PostMapping("/plate/decision_resultData")
    @Transactional
    @ApiOperation(value = "平台接收试点水厂决策预测计算结果数据", notes = "作者：韩宗晏")
    public ResponseEntity<String> decisionData() {
        AtomicReference<String> result = new AtomicReference<>("");

        return ResponseEntity.ok().body(result.get());
    }
}
