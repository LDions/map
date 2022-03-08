package com.ruowei.repository;

import com.ruowei.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Spring Data SQL repository for the UserRole entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    @Query("select r.code from UserRole ur left join Role r on ur.roleId = r.id where ur.userId = ?1")
    Set<String> getAllRoleCodeByUserId(Long userId);

    List<UserRole> findAllByRoleId(@NotNull Long roleId);

    List<UserRole> findAllByUserId(@NotNull Long userId);

    void deleteAllByRoleId(@NotNull Long roleId);

    void deleteAllByUserId(@NotNull Long userId);
}
