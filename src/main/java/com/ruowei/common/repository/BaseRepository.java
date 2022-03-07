package com.ruowei.common.repository;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * repository扩展接口类
 * 继承了JPA的CrudRepository、JpaRepository
 * 继承了querydsl的QuerydslPredicateExecutor
 * @author 刘东奇
 * @param <ID>
 * @param <Entity>
 */
@NoRepositoryBean
public interface BaseRepository<ID,Entity,QEntity extends EntityPath<Entity>>
    extends JpaRepository<Entity, ID>,
    JpaSpecificationExecutor<Entity>,
    QuerydslPredicateExecutor<Entity>,
    QuerydslBinderCustomizer<QEntity>
{

    /**
     * querydsl查询条件匹配规则
     * @author 刘东奇
     * @date 2019/11/5
     * @param querydslBindings
     * @param qEntity
     * @return
     */
    @Override
    default void customize(QuerydslBindings querydslBindings, QEntity qEntity) {
        //默认字符串是不区分大小写的模糊匹配
        querydslBindings.bind(String.class)
            .first(
                (StringPath path, String value) -> {
                    return path.containsIgnoreCase(value);
                }
            );
    }

    /**
     * 插入
     * @author 刘东奇
     * @date 2019/11/7
     * @param entity
     * @return
     */
    Entity insert(Entity entity);

    /**
     * 更新
     * 增量更新,忽略null属性
     * @author 刘东奇
     * @date 2019/11/7
     * @param entity
     * @return
     */
    Entity updateIgnoreNull(Entity entity);

    /**
     * 插入或更新
     * 增量更新,忽略null属性
     * @author 刘东奇
     * @date 2019/11/7
     * @param entity
     * @return
     */
    Entity insertOrUpdate(Entity entity);

    /**
     * 批量删除
     * @author 刘东奇
     * @date 2019/11/16
     * @param predicate
     */
    void deleteAll(Predicate predicate);
}
