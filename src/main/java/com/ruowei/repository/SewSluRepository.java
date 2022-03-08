package com.ruowei.repository;

import com.ruowei.domain.SewSlu;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Spring Data SQL repository for the SewSlu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SewSluRepository extends JpaRepository<SewSlu, Long> {

    /**
     * 根据单据号查询污水厂污泥处置信息
     * @param documentCode 单据号
     * @return
     */
    List<SewSlu> findByDocumentCode(@NotNull String documentCode);
}
