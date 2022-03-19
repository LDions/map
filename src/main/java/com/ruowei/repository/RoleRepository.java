package com.ruowei.repository;

import com.ruowei.domain.Role;
import com.ruowei.domain.enumeration.RoleStatusType;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data SQL repository for the Role entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, QuerydslPredicateExecutor<Role> {

    Optional<Role> getFirstByName(@NotNull String name);

    Optional<Role> getFirstByCode(@NotNull String code);

    Optional<Role> getByCode(@NotNull String code);

    Optional<Role> getFirstByNameAndIdNot(@NotNull String name, Long id);

    Optional<Role> getFirstByCodeAndIdNot(@NotNull String code, Long id);

    List<Role> findAllByIdIn(Collection<Long> id);
}
