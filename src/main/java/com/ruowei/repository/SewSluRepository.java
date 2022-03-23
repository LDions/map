package com.ruowei.repository;

import com.ruowei.domain.SewSlu;
import liquibase.pro.packaged.L;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

/**
 * Spring Data SQL repository for the SewSlu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SewSluRepository extends JpaRepository<SewSlu, Long> {

    /**
     * 根据工艺ID查询化验数据
     * @param craftId 工艺ID
     * @return
     */
    List<SewSlu> findByCraftId(@NotNull Long craftId);

    List<SewSlu> findByDayTimeIsGreaterThanEqualAndDayTimeIsLessThanEqual(Instant time1,Instant time2);
}
