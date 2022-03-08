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

    Optional<Role> getFirstByNameAndStatusNot(@NotNull String name, @NotNull RoleStatusType status);

    Optional<Role> getFirstByCodeAndStatusNot(@NotNull String code, @NotNull RoleStatusType status);

    Optional<Role> getByCodeAndStatus(@NotNull String code, @NotNull RoleStatusType status);

    Optional<Role> getFirstByNameAndIdNotAndStatusNot(@NotNull String name, Long id, @NotNull RoleStatusType status);

    Optional<Role> getFirstByCodeAndIdNotAndStatusNot(@NotNull String code, Long id, @NotNull RoleStatusType status);

    List<Role> findAllByIdInAndStatus(Collection<Long> id, @NotNull RoleStatusType status);
}
