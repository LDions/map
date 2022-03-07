package com.ruowei.common.service.query;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ruowei.common.repository.BaseRepository;
import io.github.jhipster.service.filter.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * @author 刘东奇
 * @date 2019/11/7
 */
public abstract class QueryBaseService<
    Entity,
    QEntity extends EntityPath<Entity>,
    Repository extends BaseRepository<Long,Entity,QEntity>>
    extends io.github.jhipster.service.QueryService<Entity> {
    private final Logger log = LoggerFactory.getLogger(QueryBaseService.class);

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
     * 根据ID查找有一个对象
     * @author 刘东奇
     * @date 2019/11/4
     * @param id
     * @return
     */
    public Optional<Entity> findById(Long id){
        return jpaRepository.findById(id);
    }

    /**
     * 根据ID判断对象是否存在
     * @author 刘东奇
     * @date 2019/11/4
     * @param id
     * @return
     */
    public boolean existsById(Long id){
        return jpaRepository.existsById(id);
    }

    /**
     * QueryByExampleExecutor用法
     * https://blog.csdn.net/zhao_tuo/article/details/78604324
     * @author 刘东奇
     * @date 2019/11/4
     */

    /**
     * 根据“实例”查找一个对象
     * @author 刘东奇
     * @date 2019/11/4
     * @param entityExample
     * @return
     */
    public <S extends Entity> Optional<S> findOne(Example<S> entityExample){
        return jpaRepository.findOne(entityExample);
    }
    /**
     * 根据“实例”查找一批对象
     * @author 刘东奇
     * @date 2019/11/4
     * @param entityExample
     * @return
     */
    public <S extends Entity> Iterable<S> findAll(Example<S> entityExample){
        return jpaRepository.findAll(entityExample);
    }

    /**
     * 根据“实例”查找一批对象，且排序
     * @author 刘东奇
     * @date 2019/11/4
     * @param entityExample
     * @param sort
     * @return
     */
    public <S extends Entity> Iterable<S> findAll(Example<S> entityExample, Sort sort){
        return jpaRepository.findAll(entityExample,sort);
    }

    /**
     * 根据“实例”查找一批对象，且排序和分页
     * @author 刘东奇
     * @date 2019/11/4
     * @param entityExample
     * @param pageable
     * @return
     */
    public <S extends Entity> Page<S> findAll(Example<S> entityExample, Pageable pageable){
        return jpaRepository.findAll(entityExample,pageable);
    }

    /**
     * 根据“实例”查找，返回符合条件的对象个数
     * @author 刘东奇
     * @date 2019/11/4
     * @param entityExample
     * @return
     */
    public <S extends Entity> long count(Example<S> entityExample){
        return jpaRepository.count(entityExample);
    }

    /**
     * 根据“实例”判断是否有符合条件的对象
     * @author 刘东奇
     * @date 2019/11/4
     * @param entityExample
     * @return
     */
    public <S extends Entity> boolean exists(Example<S> entityExample){
        return jpaRepository.exists(entityExample);
    }

    /**
     * JpaSpecificationExecutor用法
     * https://www.jianshu.com/p/86da78fd14f2
     * @author 刘东奇
     * @date 2019/11/4
     */

    /**
     * 根据复杂动态查询条件查找一个对象
     * @author 刘东奇
     * @date 2019/11/4
     * @param specification
     * @return
     */
    public Optional<Entity> findOne(@Nullable Specification<Entity> specification){
        return jpaRepository.findOne(specification);
    }

    /**
     * 根据复杂动态查询条件查找一批对象
     * @author 刘东奇
     * @date 2019/11/4
     * @param specification
     * @return
     */
    public List<Entity> findAll(@Nullable Specification<Entity> specification){
        return jpaRepository.findAll(specification);
    }

    /**
     * 根据复杂动态查询条件查找一批对象,且排序
     * @author 刘东奇
     * @date 2019/11/4
     * @param specification
     * @return
     */
    public List<Entity> findAll(@Nullable Specification<Entity> specification, Sort sort){
        return jpaRepository.findAll(specification,sort);
    }

    /**
     * 根据复杂动态查询条件查找一批对象,且排序和分页
     * @author 刘东奇
     * @date 2019/11/4
     * @param specification
     * @return
     */
    public Page<Entity> findAll(@Nullable Specification<Entity> specification, Pageable pageable){
        return jpaRepository.findAll(specification,pageable);
    }

    /**
     * 根据复杂动态查询条件查找，返回符合条件的对象个数
     * @author 刘东奇
     * @date 2019/11/4
     * @param specification
     * @return
     */
    public long count(@Nullable Specification<Entity> specification){
        return jpaRepository.count(specification);
    }

    /**
     * 根据Querydsl查找一个对象
     * @author 刘东奇
     * @date 2019/11/4
     * @param predicate
     * @return
     */

    public Optional<Entity> findOne(Predicate predicate){
        return jpaRepository.findOne(predicate);
    }

    /**
     * 根据Querydsl查找一批对象
     * @author 刘东奇
     * @date 2019/11/4
     * @param predicate
     * @return
     */

    public Iterable<Entity> findAll(Predicate predicate){
        return jpaRepository.findAll(predicate);
    }

    /**
     * 根据Querydsl查找一批对象,且排序
     * @author 刘东奇
     * @date 2019/11/4
     * @param predicate
     * @return
     */

    public Iterable<Entity> findAll(Predicate predicate, Sort sort){
        return jpaRepository.findAll(predicate,sort);
    }

    /**
     * 根据Querydsl查找一批对象,且排序
     * @author 刘东奇
     * @date 2019/11/4
     * @param predicate
     * @param orderSpecifiers
     * @return
     */

    public Iterable<Entity> findAll(Predicate predicate, OrderSpecifier<?>...orderSpecifiers){
        return jpaRepository.findAll(predicate,orderSpecifiers);
    }

    /**
     * 根据Querydsl查找一批对象,且排序
     * @author 刘东奇
     * @date 2019/11/4
     * @param orderSpecifiers
     * @return
     */

    public Iterable<Entity> findAll(OrderSpecifier<?>...orderSpecifiers){
        return jpaRepository.findAll(orderSpecifiers);
    }

    /**
     * 根据Querydsl查找一批对象,且排序和分页
     * @author 刘东奇
     * @date 2019/11/4
     * @param predicate
     * @return
     */

    public Page<Entity> findAll(Predicate predicate, Pageable pageable){
        return jpaRepository.findAll(predicate,pageable);
    }

    /**
     * 根据Querydsl查找，返回符合条件的对象个数
     * @author 刘东奇
     * @date 2019/11/4
     * @param predicate
     * @return
     */

    public long count(Predicate predicate){
        return jpaRepository.count(predicate);
    }


    /**
     * 根据Querydsl判断是否有符合条件的对象
     * @author 刘东奇
     * @date 2019/11/4
     * @param predicate
     * @return
     */

    public boolean exists(Predicate predicate){
        return jpaRepository.exists(predicate);
    }

    /**
     * 拼接某一字段的查询条件
     * 字段数据类型:Long
     * @author 刘东奇
     * @date 2019/9/11
     * @param longFilter
     * @param longNumberPath
     */
    public BooleanBuilder createBooleanBuilder(LongFilter longFilter, NumberPath<Long> longNumberPath){
        return createAllBooleanBuilder(createEqualsSpecifiedInBooleanBuilder(longFilter,longNumberPath), createCompareBooleanBuilder(longFilter,longNumberPath));
    }

    /**
     * 拼接某一字段的查询条件
     * 字段数据类型:String
     * @author 刘东奇
     * @date 2019/9/11
     * @param stringFilter
     * @param stringPath
     */
    public BooleanBuilder createBooleanBuilder(StringFilter stringFilter, StringPath stringPath){
        BooleanBuilder builder = new BooleanBuilder();
        BooleanBuilder equalsSpecifiedInBuilder = createEqualsSpecifiedInBooleanBuilder(stringFilter,stringPath);
        if(equalsSpecifiedInBuilder.hasValue()){
            builder.and(equalsSpecifiedInBuilder);
        }
        if(stringFilter.getContains() != null){
            builder.and(stringPath.likeIgnoreCase(stringFilter.getContains()));
        }
        return builder;
    }

    /**
     * 拼接某一字段的查询条件
     * 字段数据类型:枚举
     * @author 刘东奇
     * @date 2019/9/11
     * @param enumFilter
     * @param enumPath
     */
    public BooleanBuilder createBooleanBuilder(Filter enumFilter, EnumPath enumPath){
        BooleanBuilder builder =  createEqualsSpecifiedInBooleanBuilder(enumFilter,enumPath);
        return builder;
    }

    /**
     * 拼接某一字段的查询条件
     * 字段数据类型:Instant
     * @author 刘东奇
     * @date 2019/9/11
     * @param instantFilter
     * @param dateTimePath
     */
    public BooleanBuilder createBooleanBuilder(InstantFilter instantFilter, DateTimePath dateTimePath){
        return createAllBooleanBuilder(createEqualsSpecifiedInBooleanBuilder(instantFilter, dateTimePath), createCompareBooleanBuilder(instantFilter, dateTimePath));
    }

    /**
     * 构建所有
     * @author 刘东奇
     * @date 2019/9/11
     * @param equalsSpecifiedInBooleanBuilder
     * @param compareBooleanBuilder2
     */
    private BooleanBuilder createAllBooleanBuilder(BooleanBuilder equalsSpecifiedInBooleanBuilder, BooleanBuilder compareBooleanBuilder2) {
        BooleanBuilder builder = new BooleanBuilder();
        BooleanBuilder equalsSpecifiedInBuilder = equalsSpecifiedInBooleanBuilder;
        if(equalsSpecifiedInBuilder.hasValue()){
            builder.and(equalsSpecifiedInBuilder);
        }else {
            BooleanBuilder compareBooleanBuilder = compareBooleanBuilder2;
            if(compareBooleanBuilder.hasValue()){
                builder.and(compareBooleanBuilder);
            }
        }
        return builder;
    }

    /**
     * 拼接某一字段的等值、判空、集合查询条件
     * @author 刘东奇
     * @date 2019/9/11
     * @param filter
     * @param simpleExpression
     */
    private BooleanBuilder createEqualsSpecifiedInBooleanBuilder(Filter filter, SimpleExpression simpleExpression){
        BooleanBuilder builder = new BooleanBuilder();
        if(filter.getEquals() != null){
            builder.and(simpleExpression.eq(filter.getEquals()));
        }else if(filter.getSpecified() != null){
            if(filter.getSpecified() == true){
                builder.and(simpleExpression.isNotNull());
            }else{
                builder.and(simpleExpression.isNull());
            }
        } else if(filter.getIn() !=null){
            builder.and(simpleExpression.in(filter.getIn()));
        }
        return builder;
    }

    /**
     * 拼接某一字段的比较查询条件
     * @author 刘东奇
     * @date 2019/9/11
     * @param rangeFilter
     * @param comparableExpression
     */
    private BooleanBuilder createCompareBooleanBuilder(RangeFilter rangeFilter, ComparableExpression comparableExpression){
        BooleanBuilder builder = new BooleanBuilder();
        if(rangeFilter.getGreaterThan() != null){
            builder.and(comparableExpression.gt(rangeFilter.getGreaterThan()));
        }
        if(rangeFilter.getGreaterThanOrEqual()!=null){
            builder.and(comparableExpression.goe(rangeFilter.getGreaterThanOrEqual()));
        }
        if(rangeFilter.getLessThan()!=null){
            builder.and(comparableExpression.lt(rangeFilter.getLessThan()));
        }
        if(rangeFilter.getLessThanOrEqual()!=null){
            builder.and(comparableExpression.loe(rangeFilter.getLessThanOrEqual()));
        }
        return builder;
    }

    /**
     * 拼接某一字段的比较查询条件
     * @author 刘东奇
     * @date 2019/9/11
     * @param rangeFilter
     * @param numberExpression
     */
    private BooleanBuilder createCompareBooleanBuilder(RangeFilter rangeFilter, NumberExpression numberExpression){
        BooleanBuilder builder = new BooleanBuilder();
        if(rangeFilter.getGreaterThan() != null){
            builder.and(numberExpression.gt((Number) rangeFilter.getGreaterThan()));
        }
        if(rangeFilter.getGreaterThanOrEqual()!=null){
            builder.and(numberExpression.goe((Number)  rangeFilter.getGreaterThanOrEqual()));
        }
        if(rangeFilter.getLessThan()!=null){
            builder.and(numberExpression.lt((Number)  rangeFilter.getLessThan()));
        }
        if(rangeFilter.getLessThanOrEqual()!=null){
            builder.and(numberExpression.loe((Number)  rangeFilter.getLessThanOrEqual()));
        }
        return builder;
    }
}
