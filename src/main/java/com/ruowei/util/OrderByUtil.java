package com.ruowei.util;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.EntityPathBase;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class OrderByUtil {

    /**
     * 通过DSL查询对象和Spring排序对象，生成DSL需要的排序对象
     * @param entityPathBase
     * @param sort
     * @return
     */
    public static OrderSpecifier[] createOrderSpecifierBy(EntityPathBase entityPathBase, Sort sort){
        List<OrderSpecifier> orderSpecifierList=new ArrayList<OrderSpecifier>();
        if (sort == null) {
            sort = Sort.by(Sort.Direction.DESC,"id");
        }
        //1、遍历所有排序规则
        for (Sort.Order order : sort) {
            Object querydslEntityProperty = null;
            OrderSpecifier orderSpecifier = null;
            try {
                Field field = entityPathBase.getClass().getDeclaredField(order.getProperty());
                if(field!=null){
                    field.setAccessible(true);
                    //2、解析排序字段
                    querydslEntityProperty= field.get(entityPathBase);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            addOrderSpecifier(orderSpecifierList, order, querydslEntityProperty, orderSpecifier);

        }
        return orderSpecifierList.toArray(new OrderSpecifier[orderSpecifierList.size()]);
    }

    /**
     *
     * @param orderSpecifierList
     * @param order
     * @param querydslEntityProperty
     * @param orderSpecifier
     */
    private static void addOrderSpecifier(List<OrderSpecifier> orderSpecifierList, Sort.Order order, Object querydslEntityProperty, OrderSpecifier orderSpecifier) {
        if(querydslEntityProperty != null){
            Method method=null;
            try {
                switch (order.getDirection()){
                    case ASC:
                        method = querydslEntityProperty.getClass().getMethod("asc");
                        break;
                    case DESC:
                    default:
                        method = querydslEntityProperty.getClass().getMethod("desc");
                        break;
                }
                method.setAccessible(true);
                //3、生成排序对象
                orderSpecifier= (OrderSpecifier) method.invoke(querydslEntityProperty);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(orderSpecifier != null){
                orderSpecifierList.add(orderSpecifier);
            }

        }
    }
}
