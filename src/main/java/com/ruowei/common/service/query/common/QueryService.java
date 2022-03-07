package com.ruowei.common.service.query.common;

import com.querydsl.core.types.EntityPath;
import com.ruowei.common.repository.BaseRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 查询 通用服务层
 * @author 刘东奇
 *
 *
 * @param <Entity> 实体
 * @param <QEntity> 实体路径类（Querydsl用）
 * @param <ListModel> 列表页数据模型
 * @param <DetailModel> 详情页数据模型
 * @param <Repository> dao层
 */
@Transactional(readOnly = true)
public abstract class QueryService<
        Entity,
        QEntity extends EntityPath<Entity>,
        ListModel,
        DetailModel,
        Repository extends BaseRepository<Long,Entity,QEntity>>
    extends com.ruowei.common.service.query.QueryBaseService<Entity,QEntity,Repository>
    implements QueryCommonApi<Long,Entity,ListModel, DetailModel> {

}
