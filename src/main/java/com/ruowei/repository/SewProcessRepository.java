package com.ruowei.repository;

import com.ruowei.domain.SewProcess;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data SQL repository for the SewProcess entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SewProcessRepository extends JpaRepository<SewProcess, Long> {

    /**
     * 根据单据号查询污水厂工艺水质信息
     * @param documentCode 单据号
     * @return
     */
    List<SewProcess> findByDocumentCode(@NotNull String documentCode);

    List<SewProcess> findByDayTimeIsGreaterThanEqualAndDayTimeIsLessThanEqual(Instant time1,Instant time2);
}
