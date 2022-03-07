package com.ruowei.modules.sys.web;

import com.ruowei.modules.sys.domain.SysRoleDataScope;
import com.ruowei.modules.sys.repository.SysRoleDataScopeRepository;
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
 * REST controller for managing {@link SysRoleDataScope}.
 */
@RestController
@RequestMapping("/api")
public class SysRoleDataScopeResource {

    private final Logger log = LoggerFactory.getLogger(SysRoleDataScopeResource.class);

    private static final String ENTITY_NAME = "sysRoleDataScope";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysRoleDataScopeRepository sysRoleDataScopeRepository;

    public SysRoleDataScopeResource(SysRoleDataScopeRepository sysRoleDataScopeRepository) {
        this.sysRoleDataScopeRepository = sysRoleDataScopeRepository;
    }

    /**
     * {@code POST  /sys-role-data-scopes} : Create a new sysRoleDataScope.
     *
     * @param sysRoleDataScope the sysRoleDataScope to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysRoleDataScope, or with status {@code 400 (Bad Request)} if the sysRoleDataScope has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-role-data-scopes")
    public ResponseEntity<SysRoleDataScope> createSysRoleDataScope(@Valid @RequestBody SysRoleDataScope sysRoleDataScope) throws URISyntaxException {
        log.debug("REST request to save SysRoleDataScope : {}", sysRoleDataScope);
        if (sysRoleDataScope.getId() != null) {
            throw new BadRequestAlertException("A new sysRoleDataScope cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysRoleDataScope result = sysRoleDataScopeRepository.save(sysRoleDataScope);
        return ResponseEntity.created(new URI("/api/sys-role-data-scopes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-role-data-scopes} : Updates an existing sysRoleDataScope.
     *
     * @param sysRoleDataScope the sysRoleDataScope to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysRoleDataScope,
     * or with status {@code 400 (Bad Request)} if the sysRoleDataScope is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysRoleDataScope couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-role-data-scopes")
    public ResponseEntity<SysRoleDataScope> updateSysRoleDataScope(@Valid @RequestBody SysRoleDataScope sysRoleDataScope) throws URISyntaxException {
        log.debug("REST request to update SysRoleDataScope : {}", sysRoleDataScope);
        if (sysRoleDataScope.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysRoleDataScope result = sysRoleDataScopeRepository.save(sysRoleDataScope);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sysRoleDataScope.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sys-role-data-scopes} : get all the sysRoleDataScopes.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysRoleDataScopes in body.
     */
    @GetMapping("/sys-role-data-scopes")
    public ResponseEntity<List<SysRoleDataScope>> getAllSysRoleDataScopes(Pageable pageable) {
        log.debug("REST request to get a page of SysRoleDataScopes");
        Page<SysRoleDataScope> page = sysRoleDataScopeRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sys-role-data-scopes/:id} : get the "id" sysRoleDataScope.
     *
     * @param id the id of the sysRoleDataScope to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysRoleDataScope, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-role-data-scopes/{id}")
    public ResponseEntity<SysRoleDataScope> getSysRoleDataScope(@PathVariable Long id) {
        log.debug("REST request to get SysRoleDataScope : {}", id);
        Optional<SysRoleDataScope> sysRoleDataScope = sysRoleDataScopeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sysRoleDataScope);
    }

    /**
     * {@code DELETE  /sys-role-data-scopes/:id} : delete the "id" sysRoleDataScope.
     *
     * @param id the id of the sysRoleDataScope to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-role-data-scopes/{id}")
    public ResponseEntity<Void> deleteSysRoleDataScope(@PathVariable Long id) {
        log.debug("REST request to delete SysRoleDataScope : {}", id);
        sysRoleDataScopeRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
