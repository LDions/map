package com.ruowei.web.rest;

import com.ruowei.domain.Draft;
import com.ruowei.domain.enumeration.DraftType;
import com.ruowei.repository.DraftRepository;
import com.ruowei.security.UserModel;
import com.ruowei.web.rest.dto.DraftListDTO;
import com.ruowei.web.rest.dto.DraftNumberDTO;
import com.ruowei.web.rest.errors.BadRequestProblem;
import com.ruowei.web.rest.vm.DraftContentVM;
import com.ruowei.web.rest.vm.DraftVM;
import com.ruowei.web.rest.vm.OldDataDraftVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import tech.jhipster.web.util.ResponseUtil;

import javax.validation.Valid;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Api(tags = "草稿箱")
public class DraftResource {

    private final Logger log = LoggerFactory.getLogger(DraftResource.class);
    private final DraftRepository draftRepository;

    public DraftResource(DraftRepository draftRepository) {
        this.draftRepository = draftRepository;
    }

    @PostMapping("/drafts")
    @ApiOperation(value = "添加草稿", notes = "作者:张锴")
    public ResponseEntity<Void> createDraft(@Valid @RequestBody DraftVM vm,
                                            @ApiIgnore @AuthenticationPrincipal UserModel userModel) {
        Long draftNum = draftRepository.countAllByUserIdAndType(userModel.getUserId(), vm.getType());
        if (draftNum >= 10) {
            throw new BadRequestProblem("保存失败", "您的草稿数量已达上限");
        }
        Draft draft = new Draft()
            .userId(userModel.getUserId())
            .modifyTime(Instant.now())
            .industryCode(vm.getIndustryCode())
            .documentCode("CG" + DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now()))
            .content(vm.getContent())
            .type(vm.getType());
        draftRepository.save(draft);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/drafts/{id}")
    @ApiOperation(value = "更新草稿", notes = "作者:张锴")
    public ResponseEntity<Void> updateDraft(@PathVariable(value = "id", required = false) final Long id,
                                            @Valid @RequestBody DraftContentVM vm) {
        draftRepository.findById(id)
            .ifPresent(draft -> draftRepository.save(draft.content(vm.getContent()).modifyTime(Instant.now())));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/drafts")
    @ApiOperation(value = "获取草稿列表", notes = "作者:张锴")
    public List<DraftListDTO> getAllDrafts(@ApiParam(value = "草稿类型", required = true) @RequestParam DraftType draftType,
                                           @ApiIgnore @AuthenticationPrincipal UserModel userModel) {
        return draftRepository.findAllByUserIdAndTypeOrderByModifyTimeDesc(userModel.getUserId(), draftType);
    }

    @GetMapping("/drafts/{id}")
    @ApiOperation(value = "获取草稿详情", notes = "作者:张锴")
    public ResponseEntity<Draft> getDraft(@PathVariable Long id) {
        Optional<Draft> draft = draftRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(draft);
    }

    @DeleteMapping("/drafts/{id}")
    @ApiOperation(value = "删除草稿", notes = "作者:张锴")
    public ResponseEntity<Void> deleteDraft(@PathVariable Long id) {
        draftRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/drafts/draftNumber")
    @ApiOperation(value = "获取草稿数量", notes = "作者:张锴")
    public ResponseEntity<DraftNumberDTO> getDraftNumber(@ApiParam(value = "草稿类型", required = true) @RequestParam DraftType draftType,
                                                         @ApiIgnore @AuthenticationPrincipal UserModel userModel) {
        DraftNumberDTO draftNumberDTO = new DraftNumberDTO(draftRepository.countAllByUserIdAndType(userModel.getUserId(), draftType));
        return ResponseEntity.ok(draftNumberDTO);
    }

    @PostMapping("/drafts/saveOldDraft")
    @ApiOperation(value = "保存旧数据草稿", notes = "作者:张锴")
    public ResponseEntity<Void> createOldDataDraft(@Valid @RequestBody OldDataDraftVM vm,
                                            @ApiIgnore @AuthenticationPrincipal UserModel userModel) {
        Long draftNum = draftRepository.countAllByUserIdAndTypeAndIndustryCode(userModel.getUserId(),DraftType.OLD,vm.getIndustryCode());
        if (draftNum >= 1) {
            draftRepository.findByUserIdAndTypeAndIndustryCode(userModel.getUserId(),DraftType.OLD,vm.getIndustryCode())
                .ifPresent(draft -> {
                    draftRepository.save(draft.content(vm.getContent()).modifyTime(Instant.now()));
                    draftRepository.save(draft.industryCode(vm.getIndustryCode()));
                    draftRepository.save(draft.documentCode("CG" + DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now())));
                });
            return ResponseEntity.ok().build();
        }else {
            Draft draft = new Draft()
                .userId(userModel.getUserId())
                .modifyTime(Instant.now())
                .industryCode(vm.getIndustryCode())
                .documentCode("CG" + DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now()))
                .content(vm.getContent())
                .type(DraftType.OLD);
            draftRepository.save(draft);
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping("/drafts/getOldDraft")
    @ApiOperation(value = "获取旧数据草稿", notes = "作者:张锴")
    public ResponseEntity<Draft> getOldDataDraft(@ApiIgnore @AuthenticationPrincipal UserModel userModel,
                                                 @ApiParam(value = "行业类型编码", required = true) @RequestParam String industryCode) {
        Optional<Draft> draftOpt = draftRepository.findByUserIdAndTypeAndIndustryCode(userModel.getUserId(),DraftType.OLD,industryCode);
        return draftOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.ok().build());
    }
}
