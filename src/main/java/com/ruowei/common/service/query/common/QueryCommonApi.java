package com.ruowei.common.service.query.common;


import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Optional;

/**
 * 查询 通用接口
 * @author 刘东奇
 * @date 2019/9/2
 * @param <ID> 主键
 * @param <Entity> 实体
 * @param <ListModel> 列表页数据模型
 * @param <DetailModel> 详情页数据模型
 */
public interface QueryCommonApi<ID, Entity,ListModel, DetailModel>
    extends
    QueryByExampleExecutor<Entity>,
    JpaSpecificationExecutor<Entity>,
    QuerydslPredicateExecutor<Entity> {

    /**
     * 根据ID查找有一个对象
     * @author 刘东奇
     * @date 2019/11/4
     * @param id
     * @return
     */
    Optional<Entity> findById(ID id);

    /**根据ID判断对象是否存在
     * @author 刘东奇
     * @date 2019/11/4
     * @param id
     * @return
     */
    boolean existsById(ID id);

    /**
     * 列表model
     * model由界面内容决定，
     * @author 刘东奇
     * @date 2019/11/5
     * @param
     * @return
     */
    JPAQuery<ListModel> selectFromListModel();

    /**
     * 详情model
     * @author 刘东奇
     * @date 2019/11/5
     * @param
     * @return
     */
    JPAQuery<DetailModel> selectFromDetailModel();
}
