package com.ruowei.repository;

import com.ruowei.domain.OtherIndex;

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
     * 根据单据号查询污水厂污泥处置信息
     * @param documentCode 单据号
     * @return
     */
    List<OtherIndex> findByDocumentCode(@NotNull String documentCode);
}
