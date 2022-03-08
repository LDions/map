package com.ruowei.repository;

import com.ruowei.domain.Menu;
import com.ruowei.domain.enumeration.MenuStatusType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data SQL repository for the Menu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    Optional<Menu> findByIdAndStatus(Long id, @NotNull MenuStatusType status);

    List<Menu> findAllByParentIdAndStatus(Long parentId, @NotNull MenuStatusType status);

    List<Menu> findAllByStatusOrderByMenuSortAsc(@NotNull MenuStatusType status);
}
