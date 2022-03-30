package com.ruowei.web.rest;

import com.ruowei.domain.*;
import com.ruowei.repository.*;
import com.ruowei.security.UserModel;
import com.ruowei.util.excel.GroupExcelExport;
import com.ruowei.web.rest.dto.DropDownDTO;
import com.ruowei.web.rest.dto.GroupDTO;
import com.ruowei.web.rest.dto.ThresholdDTO;
import com.ruowei.web.rest.errors.BadRequestProblem;
import com.ruowei.web.rest.vm.AccountVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;


@RestController
@RequestMapping("/api")
@Api(tags = "集团管理")
public class GroupResource {
    private final Logger log = LoggerFactory.getLogger(CraftResource.class);
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final CraftRepository craftRepository;
    private final SewEmiThresholdRepository sewEmiThresholdRepository;
    private final EmiDataRepository emiDataRepository;

    public GroupResource(GroupRepository groupRepository, UserRepository userRepository, EnterpriseRepository enterpriseRepository, CraftRepository craftRepository, SewEmiThresholdRepository sewEmiThresholdRepository, EmiDataRepository emiDataRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.enterpriseRepository = enterpriseRepository;
        this.craftRepository = craftRepository;
        this.sewEmiThresholdRepository = sewEmiThresholdRepository;
        this.emiDataRepository = emiDataRepository;
    }

    @PostMapping("/group")
    @Transactional
    @ApiOperation(value = "新增集团接口", notes = "作者：孙小楠")
    public ResponseEntity<Group> createCraft(Group group) throws URISyntaxException {
        log.debug("REST request to save SewGroup : {}", group);
        if (group.getId() != null) {
            throw new BadRequestProblem("新增失败", "新增时ID必须为空");
        }
        groupRepository.getFirstByGroupName(group.getGroupName())
            .ifPresent(so -> {
                throw new BadRequestProblem("新增失败", "工艺名称已存在");
            });

        Group result = groupRepository.save(group);
        return ResponseEntity.created(new URI("/api/group/" + result.getId()))
            .body(result);
    }
    @PutMapping("/group")
    @ApiOperation(value = "编辑集团接口", notes = "作者：孙小楠")
    public ResponseEntity<Group> editCraft(@Valid @RequestBody Group group) {
        log.debug("REST request to update SewGroup : {}", group);
        if (group.getId() == null) {
            throw new BadRequestProblem("编辑失败", "id不能为空");
        }
        Group group1 = groupRepository.findById(group.getId()).orElseThrow(() -> new BadRequestProblem("编辑失败", "该用户不存在"));
        if (StringUtils.isNotEmpty(group.getGroupName())) {
            group1.setGroupName(group.getGroupName());
        }
        if (StringUtils.isNotEmpty(group.getGroupContactName())) {
            group1.setGroupContactName(group.getGroupContactName());
        }
        if (group.getGroupLatitude() != null) {
            group1.setGroupLatitude(group.getGroupLatitude());
        }
        if (group.getGroupLongitude() != null) {
            group1.setGroupLongitude(group.getGroupLongitude());
        }
        if (StringUtils.isNotEmpty(group.getGroupContactPhone())) {
            group1.setGroupContactPhone(group.getGroupContactPhone());
        }
        Group result = groupRepository.save(group);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/group/{id}")
    @Transactional
    @ApiOperation(value = "删除集团接口", notes = "作者：孙小楠")
    public ResponseEntity<Void> deleteCraft(@PathVariable Long id) {
        log.debug("REST request to delete SewGroup : {}", id);
        Optional<Group> group = groupRepository.findById(id);
        if(group.isPresent()){
            Optional<User> byGroupCode = userRepository.findByGroupCode(group.get().getGroupCode());
            if (!byGroupCode.isEmpty()){
                throw new BadRequestProblem("删除失败", "集团用户不为空");
            }
        }
        groupRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/group/{id}")
    @ApiOperation(value = "查询集团详情接口", notes = "作者：孙小楠")
    public ResponseEntity<GroupDTO> getCraft(@PathVariable Long id) {
        log.debug("REST request to get Group : {}", id);
        Optional<Group> optional = groupRepository.findById(id);
        GroupDTO groupDTO = new GroupDTO();
        if (optional.isPresent()){
            BeanUtils.copyProperties(optional.get(), groupDTO);
            List<Enterprise> enterpriseList = enterpriseRepository.findByGroupCode(optional.get().getGroupCode());
            groupDTO.setEnterprises(enterpriseList);
        }
        return ResponseEntity.ok(groupDTO);
    }

    @PostMapping("/group/enterprise/dropdown")
    @ApiOperation(value = "查询集团下属水厂", notes = "作者：郑昊天")
    public ResponseEntity<List<DropDownDTO>> getEnterpriseDropdown(@ApiIgnore @AuthenticationPrincipal UserModel userModel) {
        List<Enterprise> enterpriseList = enterpriseRepository.findAllByGroupCode(userModel.getgroupCode());
        List<DropDownDTO> dropDownList = new ArrayList<>();
        for (Enterprise enterprise : enterpriseList) {
            DropDownDTO dropDownDTO = new DropDownDTO();
            dropDownDTO.setId(enterprise.getId());
            dropDownDTO.setCode(enterprise.getCode());
            dropDownDTO.setName(enterprise.getName());
            dropDownList.add(dropDownDTO);
        }
        return ResponseEntity.ok().body(dropDownList);
    }

/*    @ApiIgnore
    @PostMapping("/group/enterprise/result-save")
    @ApiOperation(value = "保存本次对多个工艺计算的结果", notes = "作者：郑昊天")
    public ResponseEntity<String> saveAccountResult(@Valid @RequestBody List<AccountVM> vms, @ApiIgnore @AuthenticationPrincipal UserModel userModel) {
        String result = "";
        for (AccountVM vm : vms) {
            int i = 1;
            EmiData emiData = new EmiData()
//                .documentCode()
//                .dataCode()
                .enterpriseCode(userModel.getcode())
//                .acctype(vm.getAcctype())
//                .accYear(vm.getAccYear())
//                .accMonth(vm.getAccMonth())
//                .accTimeStart(vm.getAccTime())
//                .accTimeStop(vm.getAccTime())
//                .predictTime()
//                .craftCode(vm.getCraftId())
                .totalOutN(vm.getTotalOutN())
                .outAN(vm.getOutAN())
                .carbonAdd(vm.getCarbonAdd())
                .phosphorusremover(vm.getPhosphorusremover());
            emiDataRepository.save(emiData);
            i++;
        }

        return ResponseEntity.ok().body(result);
    }*/

    @ApiIgnore
    @PostMapping("/group/excel-export")
    @ApiOperation(value = "结果导出", notes = "作者：郑昊天")
    public ResponseEntity<Resource> exportResult(@RequestBody @ApiParam(value = "计算历史记录编码列表") List<String> codes) {

        // TODO 组装参数
        byte[] buffer = GroupExcelExport.export(new ArrayList<>());
        if (buffer == null) {
            throw new BadRequestProblem("生成Excel失败", "Excel字节数组为空");
        }
        Resource resource = new ByteArrayResource(buffer);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;").body(resource);
    }


    @PostMapping("/group/enterprise/threshold")
    @ApiOperation(value = "查询阈值报警", notes = "作者：郑昊天")
    public ResponseEntity<ThresholdDTO> saveAccountResult(@ApiIgnore @AuthenticationPrincipal UserModel userModel) {
        Optional<SewEmiThreshold> threshold = sewEmiThresholdRepository.findByEnterpriseCode(userModel.getcode());
        if (threshold.isPresent()) {
            ThresholdDTO thresholdDTO = new ThresholdDTO();
            thresholdDTO.setInTn(threshold.get().getOutTotalNLimit());
            thresholdDTO.setInAmmonia(threshold.get().getOutTotalANLimit());
            return ResponseEntity.ok().body(thresholdDTO);
        } else {
            throw new BadRequestProblem("报警阈值不存在");
        }
    }

    @ApiIgnore
    @PostMapping("/group/nitrogen-model")
    @ApiOperation(value = "获取集团下所有预测总氮模型", notes = "作者：郑昊天")
    public ResponseEntity<Map<String,String>> getNitrogenModel() {
        var modelMap = new HashMap<String, String>();
        modelMap.put("LSTM总氮模型", "LSTM总氮模型code");
        return ResponseEntity.ok().body(modelMap);
    }

    @ApiIgnore
    @PostMapping("/group/ammonia-nitrogen-model")
    @ApiOperation(value = "获取集团下所有预测氨氮模型", notes = "作者：郑昊天")
    public ResponseEntity<Map<String,String>> getAmmoniaNitrogenModel() {
        var modelMap = new HashMap<String, String>();
        modelMap.put("LSTM氨氮模型", "LSTM氨氮模型code");
        return ResponseEntity.ok().body(modelMap);
    }
}
