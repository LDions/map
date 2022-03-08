package com.ruowei.repository;

import com.ruowei.domain.RoleMenu;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

/**
 * Spring Data SQL repository for the RoleMenu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RoleMenuRepository extends JpaRepository<RoleMenu, Long> {

    List<RoleMenu> findAllByRoleIdIn(Collection<@NotNull Long> roleId);

    void deleteAllByRoleId(@NotNull Long roleId);
}
