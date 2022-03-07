package com.ruowei.common.service.crud;


import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ruowei.common.log.LogMessageUtils;
import com.ruowei.common.repository.BaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * 增删改 通用服务层
 * @author 刘东奇
 * @param <Entity> 实体
 * @param <Repository> dao层
 */
@Transactional
public abstract class CrudBaseService<
    Entity,
    QEntity extends EntityPath<Entity>,
    Repository extends BaseRepository<Long, Entity, QEntity>>
    implements CrudApi<Entity, Long> {

    private final Logger log = LoggerFactory.getLogger(CrudBaseService.class);

    @Autowired
    protected Repository jpaRepository;

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    protected JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(entityManager);
    }

    /**
     * 插入
     *
     * @author 刘东奇
     * @date 2019/9/6
     * @param entity
     */
    @Override
    public Entity insert(Entity entity) {
        return jpaRepository.insert(entity);
    }

    /**
     * 更新
     * 默认增量更新
     * @author 刘东奇
     * @date 2019/9/6
     * @param entity
     */
    @Override
    public Entity update(Entity entity) {
        return jpaRepository.updateIgnoreNull(entity);
    }

    /**
     * 插入或更新
     * 默认增量更新
     * @author 刘东奇
     * @date 2019/9/6
     * @param entity
     */
    @Override
    public Entity save(Entity entity) {
        return jpaRepository.insertOrUpdate(entity);
    }

    /**
     * 批量插入或更新
     * @author 刘东奇
     * @date 2019/9/23
     * @param list
     */
    @Override
    public List<Entity> saveAll(List<Entity> list) {
        log.debug(LogMessageUtils.getEnterMessage("saveAll",list));
        List<Entity> result = new ArrayList<>();
        for(Entity entity:list){
            result.add(this.save(entity));
        }
        log.debug(LogMessageUtils.getLeaveMessage("saveAll",result));
        return result;
    }

    /**
     * 删除
     * @author 刘东奇
     * @date 2019/11/4
     * @param id
     * @return
     */
    @Override
    public void deleteById(Long id){
        jpaRepository.deleteById(id);
    }

    /**
     * 删除
     * @author 刘东奇
     * @date 2019/9/6
     * @param entity
     */
    @Override
    public void delete(Entity entity){
        jpaRepository.delete(entity);
    }

    /**
     * 删除批量
     * @author 刘东奇
     * @date 2019/9/6
     * @param entities
     */
    @Override
    public void deleteAll(Iterable<? extends Entity> entities){
        jpaRepository.deleteAll(entities);
    }

    /**
     * 删除全部
     * @author 刘东奇
     * @date 2019/9/6
     */
    @Override
    public void deleteAll(){
        jpaRepository.deleteAll();
    }

    /**
     * 备用功能-提交数据到数据库
     * @author 刘东奇
     * @date 2019/11/4
     * @param
     * @return
     */
    @Override
    public void flush(){
        jpaRepository.flush();
    }
}
