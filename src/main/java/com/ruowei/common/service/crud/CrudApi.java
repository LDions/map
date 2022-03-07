package com.ruowei.common.service.crud;

import java.util.List;

/**
 * 增删改 通用接口
 * @author 刘东奇
 * @date 2019/9/2
 * @param <Entity> 实体
 * @param <ID> 主键
 */
public interface CrudApi<Entity,ID>{

    /**
     * 插入
     * @param entity
     * @return
     */
    Entity insert(Entity entity);

    /**
     * 更新
     * 默认增量更新
     * @param entity
     * @return
     */
    Entity update(Entity entity);

    /**
     * 插入或更新
     * @param entity
     * @return
     */
    Entity save(Entity entity);

    /**
     * 批量插入或更新
     * @param list
     */
    List<Entity> saveAll(List<Entity> list);

    /**
     * 删除
     * @param id
     */
    void deleteById(ID id);

    /**
     * 删除
     * @param entity
     */
    void delete(Entity entity);

    /**
     * 删除批量
     * @param entities
     */
    void deleteAll(Iterable<? extends Entity> entities);

    /**
     * 删除全部
     */
    void deleteAll();

    /**
     * 备用功能-提交数据到数据库
     */
    void flush();

}
