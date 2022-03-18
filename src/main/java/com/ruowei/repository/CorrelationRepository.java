package com.ruowei.repository;

import com.ruowei.domain.Correlation;
import com.ruowei.domain.Craft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the Correlation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CorrelationRepository extends JpaRepository<Correlation, Long>, QuerydslPredicateExecutor<Correlation> {

    List<Correlation> findByRelevanceId(Long id);

}
