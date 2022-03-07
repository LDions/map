package com.ruowei.common.entity.idgen;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;

import java.io.Serializable;

/**
 * 主键生成注解
 * @author 刘东奇
 * @date 2019/9/8
 */
public class IdGenerator extends IdentityGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor s, Object obj) {

        /*
         * 生成id的代码，下面的1和2自定义
         */

        // 1.使用TwitterUtil生成id
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();

        // 2.使用UUID生成id
        // String id = UUID.randomUUID().toString();

        if (null != id) {
            return (Serializable) id;
        }

        return super.generate(s, obj);
    }
}
