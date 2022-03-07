package com.ruowei.modules.sys.web;

import com.ruowei.modules.sys.domain.entity.SysEmployee;
import com.ruowei.common.error.exception.BadRequestAlertException;

import com.ruowei.modules.sys.repository.entity.SysEmployeeRepository;
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
 * REST controller for managing {@link com.ruowei.domain.SysEmployee}.
 */
@RestController
@RequestMapping("/api")
public class SysEmployeeResource {

    private final Logger log = LoggerFactory.getLogger(SysEmployeeResource.class);

    private static final String ENTITY_NAME = "sysEmployee";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysEmployeeRepository sysEmployeeRepository;

    public SysEmployeeResource(SysEmployeeRepository sysEmployeeRepository) {
        this.sysEmployeeRepository = sysEmployeeRepository;
    }

    /**
     * {@code POST  /sys-employees} : Create a new sysEmployee.
     *
     * @param sysEmployee the sysEmployee to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysEmployee, or with status {@code 400 (Bad Request)} if the sysEmployee has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-employees")
    public ResponseEntity<SysEmployee> createSysEmployee(@Valid @RequestBody SysEmployee sysEmployee) throws URISyntaxException {
        log.debug("REST request to save SysEmployee : {}", sysEmployee);
        if (sysEmployee.getId() != null) {
            throw new BadRequestAlertException("A new sysEmployee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysEmployee result = sysEmployeeRepository.save(sysEmployee);
        return ResponseEntity.created(new URI("/api/sys-employees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-employees} : Updates an existing sysEmployee.
     *
     * @param sysEmployee the sysEmployee to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysEmployee,
     * or with status {@code 400 (Bad Request)} if the sysEmployee is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysEmployee couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-employees")
    public ResponseEntity<SysEmployee> updateSysEmployee(@Valid @RequestBody SysEmployee sysEmployee) throws URISyntaxException {
        log.debug("REST request to update SysEmployee : {}", sysEmployee);
        if (sysEmployee.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysEmployee result = sysEmployeeRepository.save(sysEmployee);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sysEmployee.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sys-employees} : get all the sysEmployees.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysEmployees in body.
     */
    @GetMapping("/sys-employees")
    public ResponseEntity<List<SysEmployee>> getAllSysEmployees(Pageable pageable) {
        log.debug("REST request to get a page of SysEmployees");
        Page<SysEmployee> page = sysEmployeeRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sys-employees/:id} : get the "id" sysEmployee.
     *
     * @param id the id of the sysEmployee to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysEmployee, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-employees/{id}")
    public ResponseEntity<SysEmployee> getSysEmployee(@PathVariable Long id) {
        log.debug("REST request to get SysEmployee : {}", id);
        Optional<SysEmployee> sysEmployee = sysEmployeeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sysEmployee);
    }

    /**
     * {@code DELETE  /sys-employees/:id} : delete the "id" sysEmployee.
     *
     * @param id the id of the sysEmployee to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-employees/{id}")
    public ResponseEntity<Void> deleteSysEmployee(@PathVariable Long id) {
        log.debug("REST request to delete SysEmployee : {}", id);
        sysEmployeeRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
