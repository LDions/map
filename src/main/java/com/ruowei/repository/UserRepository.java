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

    Optional<User> findOneByLoginAndDeletedIsFalse(@NotNull String login);

    List<User> findAllByIdInAndDeletedIsFalse(Collection<Long> id);

    Optional<User> findByIdAndDeletedIsFalse(Long id);

    Optional<User> findFirstByLoginAndIdNotAndDeletedIsFalse(@NotNull String login, Long id);

    Optional<User> findByEnterpriseCodeAndDeletedIsFalse(String enterpriseCode);

    Optional<User> findByGroupCodeAndDeletedIsFalse(String groupCode);

    /**
     * 一条水厂用户数据
     *
     * @param enterpriseCode
     * @param groupCode
     * @param userCode
     * @return
     */
    Optional<User> findByEnterpriseCodeAndGroupCodeAndUserCodeAndDeletedIsFalse(String enterpriseCode, String groupCode, String userCode);

    /**
     * 一条集团用户数据
     *
     * @param groupCode
     * @param userCode
     * @return
     */
    Optional<User> findByGroupCodeAndUserCodeAndEnterpriseCodeIsNullAndDeletedIsFalse(String groupCode, String userCode);

    Optional<User> findByEnterpriseCodeAndUserCodeAndDeletedIsFalse(String enterpriseCode, String userCode);
}
