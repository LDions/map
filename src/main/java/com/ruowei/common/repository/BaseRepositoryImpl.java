package com.ruowei.common.repository;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Predicate;
import com.ruowei.common.error.ErrorMessageUtils;
import com.ruowei.common.error.exception.DataNotFoundException;
import com.ruowei.common.lang.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 刘东奇
 * @date 2019/11/7
 */
public class BaseRepositoryImpl<ID extends Serializable,Entity,QEntity extends EntityPath<Entity>>
    extends QuerydslJpaRepository<Entity,ID>
    implements BaseRepository<ID,Entity,QEntity> {

    private final JpaEntityInformation<Entity, ID> entityInformation;
    private final EntityManager em;

    public BaseRepositoryImpl(JpaEntityInformation<Entity, ID> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.em = entityManager;
    }

    /**
     * 插入
     *
     * @param entity
     * @return
     * @author 刘东奇
     * @date 2019/11/7
     */
    @Override
    @Transactional
    public Entity insert(Entity entity) {
        Assert.isTrue(this.entityInformation.getId(entity) == null
                || !this.findById(this.entityInformation.getId(entity)).isPresent(),
            ErrorMessageUtils.getAlreadyExistMessage(
                entityInformation.getEntityName(), ObjectUtils.toString(entityInformation.getId(entity)))
            );
        this.em.persist(entity);
        return entity;
    }

    /**
     * 更新
     * 增量更新,忽略null属性
     *
     * @param entity
     * @return
     * @author 刘东奇
     * @date 2019/11/7
     */
    @Override
    @Transactional
    public Entity updateIgnoreNull(Entity entity) {
        if (this.entityInformation.isNew(entity)) {
            throw new DataNotFoundException(
                ErrorMessageUtils.getNotFoundMessage(
                    entityInformation.getEntityName(),entityInformation.getId(entity).toString()));
        } else {
            if(ObjectUtils.isUpdatable(entity)){
                //获取当前已存在的实体
                Entity exist = super.getOne(entityInformation.getId(entity));
                //获取空属性并处理成null
                String[] nullProperties = ObjectUtils.getNullProperties(entity);
                //更新非空属性
                BeanUtils.copyProperties(entity, exist, nullProperties);
                return this.em.merge(exist);
            }else {
                return entity;
            }
        }
    }

    /**
     * 插入或更新
     * 增量更新,忽略null属性
     *
     * @param entity
     * @return
     * @author 刘东奇
     * @date 2019/11/7
     */
    @Override
    public Entity insertOrUpdate(Entity entity) {
        if (this.entityInformation.isNew(entity)) {
            return this.insert(entity);
        } else {
            return this.updateIgnoreNull(entity);
        }
    }

    /**
     * 批量删除
     * @author 刘东奇
     * @date 2019/11/16
     * @param predicate
     */
    @Override
    public void deleteAll(Predicate predicate) {
         List<Entity> list= super.findAll(predicate);
         this.deleteAll(list);
    }

}
