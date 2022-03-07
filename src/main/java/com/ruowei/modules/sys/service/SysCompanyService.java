package com.ruowei.modules.sys.service;

import com.ruowei.common.service.BaseService;
import com.ruowei.modules.sys.domain.table.SysCompany;
import com.ruowei.modules.sys.repository.SysCompanyRepository;
import com.ruowei.modules.sys.service.api.SysCompanyApi;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * @author 刘东奇
 * @date 2019/11/8
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysCompanyService extends BaseService implements SysCompanyApi {

    private final SysCompanyRepository sysCompanyRepository;

    public SysCompanyService(SysCompanyRepository sysCompanyRepository) {
        this.sysCompanyRepository = sysCompanyRepository;
    }

    /**
     * 判断是否存在该公司
     * 如果存在，返回公司编码；如果不存在，抛异常
     *
     * @param id
     * @return companyCode
     * @author 刘东奇
     * @date 2019/11/2
     */
    @Override
    public SysCompany checkCompanyExistsById(Long id) {
        Assert.notNull(id,"公司ID不能为空");
        Optional<SysCompany> sysCompany = sysCompanyRepository.findById(id);
        Assert.isTrue(sysCompany.isPresent(),"公司："+id+"不存在");
        return sysCompany.get();
    }
}
