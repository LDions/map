package com.ruowei.repository;

import com.ruowei.domain.SewPot;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Spring Data SQL repository for the SewPot entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SewPotRepository extends JpaRepository<SewPot, Long> {

    /**
     * 根据单据号查询污水厂药剂投加信息
     * @param documentCode 单据号
     * @return
     */
    List<SewPot> findByDocumentCode(@NotNull String documentCode);
}