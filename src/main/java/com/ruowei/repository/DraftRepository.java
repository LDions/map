package com.ruowei.repository;

import com.ruowei.domain.Draft;
import com.ruowei.domain.enumeration.DraftType;
import com.ruowei.web.rest.dto.DraftListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data SQL repository for the Draft entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DraftRepository extends JpaRepository<Draft, Long> {

    List<DraftListDTO> findAllByUserIdAndTypeOrderByModifyTimeDesc(Long id, DraftType type);

    Long countAllByUserIdAndType(Long id, DraftType type);

    Long countAllByUserIdAndTypeAndIndustryCode(Long id, DraftType type,String industryCode);

    void deleteAllByUserIdAndType(Long id, DraftType type);

    Optional<Draft> findByUserIdAndTypeAndIndustryCode(Long id, DraftType type,String industryCode);
}
