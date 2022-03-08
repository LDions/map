package com.ruowei.repository;

import com.ruowei.domain.Role;
import com.ruowei.domain.RoleMenu;
import com.ruowei.domain.User;
import com.ruowei.domain.UserRole;
import com.ruowei.domain.enumeration.UserStatusType;
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

    Optional<User> findOneByLoginAndStatusNot(@NotNull String login, @NotNull UserStatusType status);

    List<User> findAllByIdInAndStatusNot(Collection<Long> id, @NotNull UserStatusType status);

    Optional<User> findFirstByLoginAndIdNotAndStatusNot(@NotNull String login, Long id, @NotNull UserStatusType status);

    Optional<User> findByIdAndStatus(Long id, @NotNull UserStatusType status);

    Optional<User> findOneByLoginAndStatus(@NotNull String login, @NotNull UserStatusType status);

    Optional<User> findByEnterpriseId(Long enterpriseId);
}
