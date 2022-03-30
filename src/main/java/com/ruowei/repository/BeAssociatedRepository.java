package com.ruowei.repository;

import com.ruowei.domain.BeAssociated;
import com.ruowei.domain.Craft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface BeAssociatedRepository extends JpaRepository<BeAssociated, Long>, QuerydslPredicateExecutor<BeAssociated> {

    Optional<BeAssociated> findByBeAssociatedName(String name);

    Optional<BeAssociated> findFirstByAssociatedCode(String associatedCode);
}
