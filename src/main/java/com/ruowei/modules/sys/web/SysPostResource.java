package com.ruowei.modules.sys.web;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ruowei.common.error.exception.BadRequestAlertException;
import com.ruowei.common.querydsl.OptionalBooleanBuilder;
import com.ruowei.modules.sys.domain.table.QSysPost;
import com.ruowei.modules.sys.domain.table.SysPost;
import com.ruowei.modules.sys.domain.enumeration.PostStatusType;
import com.ruowei.modules.sys.pojo.vm.SysPostQM;
import com.ruowei.modules.sys.repository.SysPostRepository;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Api(tags = "岗位信息")
public class SysPostResource {

    private static final String ENTITY_NAME = "岗位";
    private static final QSysPost SYS_POST = QSysPost.sysPost;
    private final Logger log = LoggerFactory.getLogger(SysPostResource.class);
    private final SysPostRepository sysPostRepository;
    private final JPAQueryFactory queryFactory;

    public SysPostResource(SysPostRepository sysPostRepository, JPAQueryFactory queryFactory) {
        this.sysPostRepository = sysPostRepository;
        this.queryFactory = queryFactory;
    }

    @PostMapping("/sys-posts")
    @ApiOperation(value = "新增岗位", notes = "作者：李春浩")
    public ResponseEntity<Void> createSysPost(@RequestBody SysPost sysPost) {
        sysPostRepository.getOneByPostName(sysPost.getPostName())
            .ifPresent(sp -> {
                throw new BadRequestAlertException("岗位名称已存在", ENTITY_NAME, "postName.exist");
            });
        sysPostRepository.getOneByPostCode(sysPost.getPostCode())
            .ifPresent(sp -> {
                throw new BadRequestAlertException("岗位编码已存在", ENTITY_NAME, "postCode.exist");
            });
        sysPostRepository.save(sysPost);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/sys-posts")
    @ApiOperation(value = "修改岗位", notes = "作者：李春浩")
    public ResponseEntity<Void> updateSysPost(@RequestBody SysPost sysPost) {
        sysPostRepository.getOneByPostNameAndIdNot(sysPost.getPostName(), sysPost.getId())
            .ifPresent(sp -> {
                throw new BadRequestAlertException("岗位名称已存在", ENTITY_NAME, "postName.exist");
            });
        sysPostRepository.save(sysPost);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/sys-posts/{id}")
    @ApiOperation(value = "删除岗位", notes = "作者：李春浩")
    public ResponseEntity<Void> deleteSysPost(@PathVariable Long id) {
        sysPostRepository.findById(id)
            .map(sysPost -> {
                sysPost.setStatus(PostStatusType.DELETE);
                return sysPostRepository.save(sysPost);
            }).orElseThrow(() -> new BadRequestAlertException("未找到岗位信息", ENTITY_NAME, "id.notexist"));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/sys-posts")
    @ApiOperation(value = "查询岗位", notes = "作者：李春浩")
    public ResponseEntity<List<SysPost>> getAllSysPost(SysPostQM qm, Pageable pageable) {
        BooleanBuilder predicate = new OptionalBooleanBuilder()
            .notEmptyAnd(SYS_POST.postCode::eq, qm.getPostCode())
            .notEmptyAnd(SYS_POST.postName::contains, qm.getPostName())
            .notEmptyAnd(SYS_POST.postType::eq, qm.getPostType())
            .notEmptyAnd(SYS_POST.status::eq, qm.getStatus())
            .build();
        if (qm.getStatus() == null) {
            predicate.and(SYS_POST.status.in(PostStatusType.NORMAL, PostStatusType.DISABLE));
        }

        JPAQuery<SysPost> jpaQuery = queryFactory.selectFrom(SYS_POST)
            .where(predicate)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize());

        Page<SysPost> page = new PageImpl<>(jpaQuery.fetch(), pageable, jpaQuery.fetchCount());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);

        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/sys-posts/{id}")
    @ApiOperation(value = "岗位详情", notes = "作者：李春浩")
    public ResponseEntity<SysPost> getSysPost(@PathVariable Long id) {
        Optional<SysPost> sysPostOpt = sysPostRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sysPostOpt);
    }
}
