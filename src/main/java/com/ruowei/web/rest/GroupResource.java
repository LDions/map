package com.ruowei.web.rest;

import com.ruowei.domain.*;
import com.ruowei.repository.*;
import com.ruowei.security.UserModel;
import com.ruowei.web.rest.dto.DropDownDTO;
import com.ruowei.web.rest.dto.GroupDTO;
import com.ruowei.web.rest.dto.ThresholdDTO;
import com.ruowei.web.rest.errors.BadRequestProblem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
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

    public GroupResource(GroupRepository groupRepository, UserRepository userRepository, EnterpriseRepository enterpriseRepository, CraftRepository craftRepository, SewEmiThresholdRepository sewEmiThresholdRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.enterpriseRepository = enterpriseRepository;
        this.craftRepository = craftRepository;
        this.sewEmiThresholdRepository = sewEmiThresholdRepository;
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
            BeanUtils.copyProperties(optional,groupDTO);
            List<Enterprise> enterpriseList = enterpriseRepository.findByGroupCode(optional.get().getGroupCode());
            groupDTO.setEnterprises(enterpriseList);
        }
        return ResponseEntity.ok(groupDTO);
    }

    @PostMapping("/group/enterprise/dropdown")
    @ApiOperation(value = "查询集团下属水厂", notes = "作者：郑昊天")
    public ResponseEntity<List<DropDownDTO>> getEnterpriseDropdown(@ApiIgnore UserModel userModel) {
        List<Enterprise> enterpriseList = enterpriseRepository.findAllByGroupCode(userModel.getgroupCode());
        List<DropDownDTO> dropDownList = new ArrayList<>();
        for (Enterprise enterprise : enterpriseList) {
            DropDownDTO dropDownDTO = new DropDownDTO();
            dropDownDTO.setId(enterprise.getId());
            dropDownDTO.setName(enterprise.getName());
            dropDownList.add(dropDownDTO);
        }
        return ResponseEntity.ok().body(dropDownList);
    }

//    @PostMapping("/group/enterprise/craft-dropdown")
//    @ApiOperation(value = "保存本次对多个工艺计算的结果", notes = "作者：郑昊天")
//    public ResponseEntity<EmiData> saveAccountResult(EmiData emiData) {
//        return ResponseEntity.ok();
//    }

    @PostMapping("/group/enterprise/threshold")
    @ApiOperation(value = "查询阈值报警", notes = "作者：郑昊天")
    public ResponseEntity<ThresholdDTO> saveAccountResult(@ApiIgnore UserModel userModel) {
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
