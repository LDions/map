package com.ruowei.repository;

import com.ruowei.domain.SewEmi;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * Spring Data SQL repository for the SewEmi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SewEmiRepository extends JpaRepository<SewEmi, Long> {

    /**
     * 根据单据号查询污水厂碳排放数据
     * @param documentCode 单据号
     * @return
     */
    Optional<SewEmi> findByDocumentCode(@NotNull String documentCode);

    boolean existsByReporterId(@NotNull Long reporterId);
}
