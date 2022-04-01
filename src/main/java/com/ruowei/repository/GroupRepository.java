package com.ruowei.repository;

import com.ruowei.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data SQL repository for the Group entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GroupRepository extends JpaRepository<Group, Long>, QuerydslPredicateExecutor<Group> {

    Optional<Group> findByGroupCode(String groupCode);

    Optional<Group> getFirstByGroupName(String groupName);

    Optional<Group> getFirstByGroupCode(String groupCode);
}
