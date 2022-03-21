package com.ruowei.repository;

import com.ruowei.domain.OtherIndex;

import liquibase.pro.packaged.L;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Spring Data SQL repository for the SewSlu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OtherIndexRepository extends JpaRepository<OtherIndex, Long> {

    /**
     * 根据工艺查询污水厂污泥处置信息
     * @param craftId 工艺ID
     * @return
     */
    List<OtherIndex> findByCraftId(@NotNull Long craftId);
}
