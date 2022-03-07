package com.ruowei.modules.sys.web;

import com.ruowei.modules.sys.domain.SysEmployeeOffice;
import com.ruowei.modules.sys.repository.SysEmployeeOfficeRepository;
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
 * REST controller for managing {@link com.ruowei.domain.SysEmployeeOffice}.
 */
@RestController
@RequestMapping("/api")
public class SysEmployeeOfficeResource {

    private final Logger log = LoggerFactory.getLogger(SysEmployeeOfficeResource.class);

    private static final String ENTITY_NAME = "sysEmployeeOffice";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysEmployeeOfficeRepository sysEmployeeOfficeRepository;

    public SysEmployeeOfficeResource(SysEmployeeOfficeRepository sysEmployeeOfficeRepository) {
        this.sysEmployeeOfficeRepository = sysEmployeeOfficeRepository;
    }

    /**
     * {@code POST  /sys-employee-offices} : Create a new sysEmployeeOffice.
     *
     * @param sysEmployeeOffice the sysEmployeeOffice to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysEmployeeOffice, or with status {@code 400 (Bad Request)} if the sysEmployeeOffice has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-employee-offices")
    public ResponseEntity<SysEmployeeOffice> createSysEmployeeOffice(@Valid @RequestBody SysEmployeeOffice sysEmployeeOffice) throws URISyntaxException {
        log.debug("REST request to save SysEmployeeOffice : {}", sysEmployeeOffice);
        if (sysEmployeeOffice.getId() != null) {
            throw new BadRequestAlertException("A new sysEmployeeOffice cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysEmployeeOffice result = sysEmployeeOfficeRepository.save(sysEmployeeOffice);
        return ResponseEntity.created(new URI("/api/sys-employee-offices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-employee-offices} : Updates an existing sysEmployeeOffice.
     *
     * @param sysEmployeeOffice the sysEmployeeOffice to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysEmployeeOffice,
     * or with status {@code 400 (Bad Request)} if the sysEmployeeOffice is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysEmployeeOffice couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-employee-offices")
    public ResponseEntity<SysEmployeeOffice> updateSysEmployeeOffice(@Valid @RequestBody SysEmployeeOffice sysEmployeeOffice) throws URISyntaxException {
        log.debug("REST request to update SysEmployeeOffice : {}", sysEmployeeOffice);
        if (sysEmployeeOffice.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysEmployeeOffice result = sysEmployeeOfficeRepository.save(sysEmployeeOffice);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sysEmployeeOffice.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sys-employee-offices} : get all the sysEmployeeOffices.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysEmployeeOffices in body.
     */
    @GetMapping("/sys-employee-offices")
    public ResponseEntity<List<SysEmployeeOffice>> getAllSysEmployeeOffices(Pageable pageable) {
        log.debug("REST request to get a page of SysEmployeeOffices");
        Page<SysEmployeeOffice> page = sysEmployeeOfficeRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sys-employee-offices/:id} : get the "id" sysEmployeeOffice.
     *
     * @param id the id of the sysEmployeeOffice to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysEmployeeOffice, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-employee-offices/{id}")
    public ResponseEntity<SysEmployeeOffice> getSysEmployeeOffice(@PathVariable Long id) {
        log.debug("REST request to get SysEmployeeOffice : {}", id);
        Optional<SysEmployeeOffice> sysEmployeeOffice = sysEmployeeOfficeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sysEmployeeOffice);
    }

    /**
     * {@code DELETE  /sys-employee-offices/:id} : delete the "id" sysEmployeeOffice.
     *
     * @param id the id of the sysEmployeeOffice to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-employee-offices/{id}")
    public ResponseEntity<Void> deleteSysEmployeeOffice(@PathVariable Long id) {
        log.debug("REST request to delete SysEmployeeOffice : {}", id);
        sysEmployeeOfficeRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
