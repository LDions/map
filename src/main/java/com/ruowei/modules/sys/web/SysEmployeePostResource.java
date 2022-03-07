package com.ruowei.modules.sys.web;

import com.ruowei.modules.sys.domain.SysEmployeePost;
import com.ruowei.modules.sys.repository.SysEmployeePostRepository;
import com.ruowei.common.error.exception.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.ruowei.domain.SysEmployeePost}.
 */
@RestController
@RequestMapping("/api")
public class SysEmployeePostResource {

    private final Logger log = LoggerFactory.getLogger(SysEmployeePostResource.class);

    private static final String ENTITY_NAME = "sysEmployeePost";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysEmployeePostRepository sysEmployeePostRepository;

    public SysEmployeePostResource(SysEmployeePostRepository sysEmployeePostRepository) {
        this.sysEmployeePostRepository = sysEmployeePostRepository;
    }

    /**
     * {@code POST  /sys-employee-posts} : Create a new sysEmployeePost.
     *
     * @param sysEmployeePost the sysEmployeePost to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysEmployeePost, or with status {@code 400 (Bad Request)} if the sysEmployeePost has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-employee-posts")
    public ResponseEntity<SysEmployeePost> createSysEmployeePost(@Valid @RequestBody SysEmployeePost sysEmployeePost) throws URISyntaxException {
        log.debug("REST request to save SysEmployeePost : {}", sysEmployeePost);
        if (sysEmployeePost.getId() != null) {
            throw new BadRequestAlertException("A new sysEmployeePost cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysEmployeePost result = sysEmployeePostRepository.save(sysEmployeePost);
        return ResponseEntity.created(new URI("/api/sys-employee-posts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-employee-posts} : Updates an existing sysEmployeePost.
     *
     * @param sysEmployeePost the sysEmployeePost to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysEmployeePost,
     * or with status {@code 400 (Bad Request)} if the sysEmployeePost is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysEmployeePost couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-employee-posts")
    public ResponseEntity<SysEmployeePost> updateSysEmployeePost(@Valid @RequestBody SysEmployeePost sysEmployeePost) throws URISyntaxException {
        log.debug("REST request to update SysEmployeePost : {}", sysEmployeePost);
        if (sysEmployeePost.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysEmployeePost result = sysEmployeePostRepository.save(sysEmployeePost);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sysEmployeePost.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sys-employee-posts} : get all the sysEmployeePosts.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysEmployeePosts in body.
     */
    @GetMapping("/sys-employee-posts")
    public ResponseEntity<List<SysEmployeePost>> getAllSysEmployeePosts(Pageable pageable) {
        log.debug("REST request to get a page of SysEmployeePosts");
        Page<SysEmployeePost> page = sysEmployeePostRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sys-employee-posts/:id} : get the "id" sysEmployeePost.
     *
     * @param id the id of the sysEmployeePost to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysEmployeePost, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-employee-posts/{id}")
    public ResponseEntity<SysEmployeePost> getSysEmployeePost(@PathVariable Long id) {
        log.debug("REST request to get SysEmployeePost : {}", id);
        Optional<SysEmployeePost> sysEmployeePost = sysEmployeePostRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sysEmployeePost);
    }

    /**
     * {@code DELETE  /sys-employee-posts/:id} : delete the "id" sysEmployeePost.
     *
     * @param id the id of the sysEmployeePost to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-employee-posts/{id}")
    public ResponseEntity<Void> deleteSysEmployeePost(@PathVariable Long id) {
        log.debug("REST request to delete SysEmployeePost : {}", id);
        sysEmployeePostRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
