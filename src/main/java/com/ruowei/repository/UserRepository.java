package com.ruowei.repository;

import com.ruowei.domain.Role;
import com.ruowei.domain.RoleMenu;
import com.ruowei.domain.User;
import com.ruowei.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the {@link User} entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User> {

    Optional<User> findOneByLogin(@NotNull String login);

    List<User> findAllByIdIn(Collection<Long> id);

    Optional<User> findFirstByLoginAndIdNot(@NotNull String login, Long id);

    Optional<User> findByEnterpriseCode(String enterpriseCode);
}
