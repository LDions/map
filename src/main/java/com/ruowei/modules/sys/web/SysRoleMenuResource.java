package com.ruowei.modules.sys.web;

import com.ruowei.modules.sys.domain.SysRoleMenu;
import com.ruowei.modules.sys.repository.SysRoleMenuRepository;
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
 * REST controller for managing {@link com.ruowei.domain.SysRoleMenu}.
 */
@RestController
@RequestMapping("/api")
public class SysRoleMenuResource {

    private final Logger log = LoggerFactory.getLogger(SysRoleMenuResource.class);

    private static final String ENTITY_NAME = "sysRoleMenu";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysRoleMenuRepository sysRoleMenuRepository;

    public SysRoleMenuResource(SysRoleMenuRepository sysRoleMenuRepository) {
        this.sysRoleMenuRepository = sysRoleMenuRepository;
    }

    /**
     * {@code POST  /sys-role-menus} : Create a new sysRoleMenu.
     *
     * @param sysRoleMenu the sysRoleMenu to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysRoleMenu, or with status {@code 400 (Bad Request)} if the sysRoleMenu has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-role-menus")
    public ResponseEntity<SysRoleMenu> createSysRoleMenu(@Valid @RequestBody SysRoleMenu sysRoleMenu) throws URISyntaxException {
        log.debug("REST request to save SysRoleMenu : {}", sysRoleMenu);
        if (sysRoleMenu.getId() != null) {
            throw new BadRequestAlertException("A new sysRoleMenu cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysRoleMenu result = sysRoleMenuRepository.save(sysRoleMenu);
        return ResponseEntity.created(new URI("/api/sys-role-menus/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-role-menus} : Updates an existing sysRoleMenu.
     *
     * @param sysRoleMenu the sysRoleMenu to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysRoleMenu,
     * or with status {@code 400 (Bad Request)} if the sysRoleMenu is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysRoleMenu couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-role-menus")
    public ResponseEntity<SysRoleMenu> updateSysRoleMenu(@Valid @RequestBody SysRoleMenu sysRoleMenu) throws URISyntaxException {
        log.debug("REST request to update SysRoleMenu : {}", sysRoleMenu);
        if (sysRoleMenu.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysRoleMenu result = sysRoleMenuRepository.save(sysRoleMenu);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sysRoleMenu.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sys-role-menus} : get all the sysRoleMenus.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysRoleMenus in body.
     */
    @GetMapping("/sys-role-menus")
    public ResponseEntity<List<SysRoleMenu>> getAllSysRoleMenus(Pageable pageable) {
        log.debug("REST request to get a page of SysRoleMenus");
        Page<SysRoleMenu> page = sysRoleMenuRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sys-role-menus/:id} : get the "id" sysRoleMenu.
     *
     * @param id the id of the sysRoleMenu to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysRoleMenu, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-role-menus/{id}")
    public ResponseEntity<SysRoleMenu> getSysRoleMenu(@PathVariable Long id) {
        log.debug("REST request to get SysRoleMenu : {}", id);
        Optional<SysRoleMenu> sysRoleMenu = sysRoleMenuRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sysRoleMenu);
    }

    /**
     * {@code DELETE  /sys-role-menus/:id} : delete the "id" sysRoleMenu.
     *
     * @param id the id of the sysRoleMenu to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-role-menus/{id}")
    public ResponseEntity<Void> deleteSysRoleMenu(@PathVariable Long id) {
        log.debug("REST request to delete SysRoleMenu : {}", id);
        sysRoleMenuRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
