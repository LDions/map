package com.ruowei.repository;

import com.ruowei.domain.Craft;
import com.ruowei.domain.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data SQL repository for the Craft entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CraftRepository extends JpaRepository<Craft, Long>, QuerydslPredicateExecutor<Craft> {
//
//    List<Craft> findByEntCode(String entCode);
//
//    Craft findByCraftCode(String code);


    List<Craft> findByEntCode(String entCode);

    Optional<Craft> getFirstByCraftName(String name);

    Optional<Craft> findByCraftCode(String craftCode);
}
