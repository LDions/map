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
     * 根据工艺查询仪表数据
     * @param craftId 工艺ID
     * @return
     */
    List<SewProcess> findByCraftId(@NotNull Long craftId);

    List<SewProcess> findByDayTimeIsGreaterThanEqualAndDayTimeIsLessThanEqual(Instant time1,Instant time2);
}
