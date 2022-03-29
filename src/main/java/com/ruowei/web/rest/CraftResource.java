package com.ruowei.web.rest;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ruowei.domain.Craft;
import com.ruowei.domain.QCraft;
import com.ruowei.domain.SewProcess;
import com.ruowei.repository.CraftRepository;
import com.ruowei.security.UserModel;
import com.ruowei.web.rest.dto.DropDownDTO;
import com.ruowei.web.rest.errors.BadRequestProblem;
import com.ruowei.web.rest.vm.SituationAnalysisVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import tech.jhipster.web.util.ResponseUtil;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Api(tags = "工艺段约束")
public class CraftResource {

    private final Logger log = LoggerFactory.getLogger(CraftResource.class);
    private final JPAQueryFactory jpaQueryFactory;
    private final CraftRepository craftRepository;
    private QCraft qCraft = QCraft.craft;

    public CraftResource(JPAQueryFactory jpaQueryFactory, CraftRepository craftRepository) {
        this.jpaQueryFactory = jpaQueryFactory;
        this.craftRepository = craftRepository;
    }


    @PostMapping("/craft")
    @Transactional
    @ApiOperation(value = "新增工艺接口", notes = "作者：孙小楠")
    public ResponseEntity<Craft> createCraft(Craft craft) throws URISyntaxException {
        log.debug("REST request to save SewCraft : {}", craft);
        if (craft.getId() != null) {
            throw new BadRequestProblem("新增失败", "新增时ID必须为空");
        }
        craftRepository.getFirstByCraftName(craft.getCraftName())
            .ifPresent(so -> {
                throw new BadRequestProblem("新增失败", "工艺名称已存在");
            });

        Craft result = craftRepository.save(craft);
        return ResponseEntity.created(new URI("/api/craft/" + result.getId()))
            .body(result);
    }

    @PutMapping("/craft")
    @ApiOperation(value = "编辑工艺接口", notes = "作者：孙小楠")
    public ResponseEntity<Craft> editCraft(@Valid @RequestBody Craft craft) {
        log.debug("REST request to update SewCraft : {}", craft);
        if (craft.getId() == null) {
            throw new BadRequestProblem("编辑失败", "id不能为空");
        }

        Craft result = craftRepository.save(craft);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/carft/{id}")
    @Transactional
    @ApiOperation(value = "删除工艺接口", notes = "作者：孙小楠")
    public ResponseEntity<Void> deleteCraft(@PathVariable Long id) {
        log.debug("REST request to delete SewCraft : {}", id);
        craftRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/crafts")
//    @ApiOperation(value = "获取分页工艺列表接口", notes = "作者：孙小楠")
//    public ResponseEntity<List<Craft>> getAllCrafts(Craft craft, Pageable pageable) {
//        log.debug("REST request to get a page of SewCraft : {}", craft);
//        OptionalBooleanBuilder builder = new OptionalBooleanBuilder()
//            .notEmptyAnd();
//        Page<Craft> page = craftRepository.findAll(builder.build(), pageable);
//        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
//        return ResponseEntity.ok().headers(headers).body(page.getContent());
//    }

    @GetMapping("/craft/{id}")
    @ApiOperation(value = "查询工艺详情接口", notes = "作者：孙小楠")
    public ResponseEntity<Craft> getCraft(@PathVariable Long id) {
        log.debug("REST request to get Craft : {}", id);
        Optional<Craft> optional = craftRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(optional);
    }

    @GetMapping("/craft/drop-down{code}")
    @ApiOperation(value = "获取水厂工艺下拉列表接口", notes = "作者：张锴")
    public ResponseEntity<List<DropDownDTO>> getEnterpriseDropDown(@PathVariable String code) {
        List<DropDownDTO> result = new ArrayList<>();
        List<Craft> crafts = craftRepository.findCraftNameAndCraftIdByEntCode(code);

        for (Craft craft : crafts) {
            DropDownDTO dto = new DropDownDTO();
            dto.setId(craft.getId());
            dto.setCode(craft.getCraftCode());
            dto.setName(craft.getCraftName());
            result.add(dto);
        }
        return ResponseEntity.ok(result);
    }

}
