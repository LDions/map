package com.ruowei.modules.sys.web;

import com.ruowei.modules.sys.domain.SysCompanyOffice;
import com.ruowei.modules.sys.repository.SysCompanyOfficeRepository;
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
 * REST controller for managing {@link SysCompanyOffice}.
 */
@RestController
@RequestMapping("/api")
public class SysCompanyOfficeResource {

    private final Logger log = LoggerFactory.getLogger(SysCompanyOfficeResource.class);

    private static final String ENTITY_NAME = "sysCompanyOffice";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysCompanyOfficeRepository sysCompanyOfficeRepository;

    public SysCompanyOfficeResource(SysCompanyOfficeRepository sysCompanyOfficeRepository) {
        this.sysCompanyOfficeRepository = sysCompanyOfficeRepository;
    }

    /**
     * {@code POST  /sys-company-offices} : Create a new sysCompanyOffice.
     *
     * @param sysCompanyOffice the sysCompanyOffice to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysCompanyOffice, or with status {@code 400 (Bad Request)} if the sysCompanyOffice has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-company-offices")
    public ResponseEntity<SysCompanyOffice> createSysCompanyOffice(@Valid @RequestBody SysCompanyOffice sysCompanyOffice) throws URISyntaxException {
        log.debug("REST request to save SysCompanyOffice : {}", sysCompanyOffice);
        if (sysCompanyOffice.getId() != null) {
            throw new BadRequestAlertException("A new sysCompanyOffice cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysCompanyOffice result = sysCompanyOfficeRepository.save(sysCompanyOffice);
        return ResponseEntity.created(new URI("/api/sys-company-offices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-company-offices} : Updates an existing sysCompanyOffice.
     *
     * @param sysCompanyOffice the sysCompanyOffice to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysCompanyOffice,
     * or with status {@code 400 (Bad Request)} if the sysCompanyOffice is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysCompanyOffice couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-company-offices")
    public ResponseEntity<SysCompanyOffice> updateSysCompanyOffice(@Valid @RequestBody SysCompanyOffice sysCompanyOffice) throws URISyntaxException {
        log.debug("REST request to update SysCompanyOffice : {}", sysCompanyOffice);
        if (sysCompanyOffice.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysCompanyOffice result = sysCompanyOfficeRepository.save(sysCompanyOffice);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sysCompanyOffice.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sys-company-offices} : get all the sysCompanyOffices.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysCompanyOffices in body.
     */
    @GetMapping("/sys-company-offices")
    public ResponseEntity<List<SysCompanyOffice>> getAllSysCompanyOffices(Pageable pageable) {
        log.debug("REST request to get a page of SysCompanyOffices");
        Page<SysCompanyOffice> page = sysCompanyOfficeRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sys-company-offices/:id} : get the "id" sysCompanyOffice.
     *
     * @param id the id of the sysCompanyOffice to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysCompanyOffice, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-company-offices/{id}")
    public ResponseEntity<SysCompanyOffice> getSysCompanyOffice(@PathVariable Long id) {
        log.debug("REST request to get SysCompanyOffice : {}", id);
        Optional<SysCompanyOffice> sysCompanyOffice = sysCompanyOfficeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sysCompanyOffice);
    }

    /**
     * {@code DELETE  /sys-company-offices/:id} : delete the "id" sysCompanyOffice.
     *
     * @param id the id of the sysCompanyOffice to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-company-offices/{id}")
    public ResponseEntity<Void> deleteSysCompanyOffice(@PathVariable Long id) {
        log.debug("REST request to delete SysCompanyOffice : {}", id);
        sysCompanyOfficeRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
