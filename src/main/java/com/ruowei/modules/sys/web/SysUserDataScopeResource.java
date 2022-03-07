package com.ruowei.modules.sys.web;

import com.ruowei.modules.sys.domain.SysUserDataScope;
import com.ruowei.modules.sys.repository.SysUserDataScopeRepository;
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
 * REST controller for managing {@link SysUserDataScope}.
 */
@RestController
@RequestMapping("/api")
public class SysUserDataScopeResource {

    private final Logger log = LoggerFactory.getLogger(SysUserDataScopeResource.class);

    private static final String ENTITY_NAME = "sysUserDataScope";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysUserDataScopeRepository sysUserDataScopeRepository;

    public SysUserDataScopeResource(SysUserDataScopeRepository sysUserDataScopeRepository) {
        this.sysUserDataScopeRepository = sysUserDataScopeRepository;
    }

    /**
     * {@code POST  /sys-user-data-scopes} : Create a new sysUserDataScope.
     *
     * @param sysUserDataScope the sysUserDataScope to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysUserDataScope, or with status {@code 400 (Bad Request)} if the sysUserDataScope has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-user-data-scopes")
    public ResponseEntity<SysUserDataScope> createSysUserDataScope(@Valid @RequestBody SysUserDataScope sysUserDataScope) throws URISyntaxException {
        log.debug("REST request to save SysUserDataScope : {}", sysUserDataScope);
        if (sysUserDataScope.getId() != null) {
            throw new BadRequestAlertException("A new sysUserDataScope cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysUserDataScope result = sysUserDataScopeRepository.save(sysUserDataScope);
        return ResponseEntity.created(new URI("/api/sys-user-data-scopes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-user-data-scopes} : Updates an existing sysUserDataScope.
     *
     * @param sysUserDataScope the sysUserDataScope to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysUserDataScope,
     * or with status {@code 400 (Bad Request)} if the sysUserDataScope is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysUserDataScope couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-user-data-scopes")
    public ResponseEntity<SysUserDataScope> updateSysUserDataScope(@Valid @RequestBody SysUserDataScope sysUserDataScope) throws URISyntaxException {
        log.debug("REST request to update SysUserDataScope : {}", sysUserDataScope);
        if (sysUserDataScope.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysUserDataScope result = sysUserDataScopeRepository.save(sysUserDataScope);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sysUserDataScope.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sys-user-data-scopes} : get all the sysUserDataScopes.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysUserDataScopes in body.
     */
    @GetMapping("/sys-user-data-scopes")
    public ResponseEntity<List<SysUserDataScope>> getAllSysUserDataScopes(Pageable pageable) {
        log.debug("REST request to get a page of SysUserDataScopes");
        Page<SysUserDataScope> page = sysUserDataScopeRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sys-user-data-scopes/:id} : get the "id" sysUserDataScope.
     *
     * @param id the id of the sysUserDataScope to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysUserDataScope, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-user-data-scopes/{id}")
    public ResponseEntity<SysUserDataScope> getSysUserDataScope(@PathVariable Long id) {
        log.debug("REST request to get SysUserDataScope : {}", id);
        Optional<SysUserDataScope> sysUserDataScope = sysUserDataScopeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sysUserDataScope);
    }

    /**
     * {@code DELETE  /sys-user-data-scopes/:id} : delete the "id" sysUserDataScope.
     *
     * @param id the id of the sysUserDataScope to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-user-data-scopes/{id}")
    public ResponseEntity<Void> deleteSysUserDataScope(@PathVariable Long id) {
        log.debug("REST request to delete SysUserDataScope : {}", id);
        sysUserDataScopeRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
