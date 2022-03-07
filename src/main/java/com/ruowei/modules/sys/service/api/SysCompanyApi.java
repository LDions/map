package com.ruowei.modules.sys.service.api;

import com.ruowei.modules.sys.domain.table.SysCompany;

/**
 * @author 刘东奇
 * @date 2019/11/8
 */
public interface SysCompanyApi {
    /**
     * 判断是否存在该公司
     * 如果存在，返回公司；如果不存在，抛异常
     * @author 刘东奇
     * @date 2019/11/2
     * @param id
     * @return SysCompany
     */
    SysCompany checkCompanyExistsById(Long id);
}
