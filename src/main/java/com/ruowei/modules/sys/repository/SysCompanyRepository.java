package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.table.QSysCompany;
import com.ruowei.modules.sys.domain.table.SysCompany;
import com.ruowei.modules.sys.domain.enumeration.CompanyStatusType;
import com.ruowei.modules.sys.pojo.dto.TreeMinLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the SysCompany entity.
 *
 * @author 刘东奇
 */
@SuppressWarnings("unused")
@Repository
public interface SysCompanyRepository
    extends BaseRepository<Long, SysCompany, QSysCompany> {

    List<SysCompany> getAllByParentCodeIsNullAndStatus(CompanyStatusType companyStatusType);

    List<SysCompany> getAllByParentCodeAndStatus(String parentCode, CompanyStatusType companyStatusType);

    Optional<SysCompany> getOneByCompanyName(String companyName);

    Optional<SysCompany> getOneByCompanyCode(String companyCode);

    Optional<SysCompany> getOneByCompanyNameAndIdNot(String companyName, Long id);

    Optional<SysCompany> getOneByCompanyCodeAndIdNot(String companyCode, Long id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,
        value = "delete from sys_company where company_code = ?1 or find_in_set(?1, parent_codes) > 0")
    void deleteAllByCompanyCodeOrParentCodesContains(String companyCode);

    @Query(value = "select o.id from SysCompany o" +
        " where (?1 is null or o.companyCode like concat('%',?1,'%'))" +
        " and (?2 is null or o.companyName like concat('%',?2,'%'))" +
        " and (?3 is null or o.fullName like concat('%',?3,'%'))" +
        " and (?4 is null or o.status = ?4)")
    List<Long> getAllNodeByParam(String officeCode, String officeName, String fullName, String status);


    @Query(value = "select new com.ruowei.modules.sys.pojo.dto.TreeMinLevel(" +
        "substring(o.treeNames,1,locate('/',concat(o.treeNames,'/'),1)-1),min(o.treeLevel))" +
        " from SysCompany o where o.id in ?1" +
        " group by substring(o.treeNames,1,locate('/',concat(o.treeNames,'/'),1)-1)")
    List<TreeMinLevel> getAllTreeMinLevelByParam(List<Long> ids);

    @Query(value = "select o from SysCompany o" +
        " where concat(substring(o.treeNames,1,locate('/',concat(o.treeNames,'/'),1)-1),o.treeLevel) in ?1")
    Page<SysCompany> getAllByTreeMinLevels(List<String> treeMinLevels, Pageable pageable);

    List<SysCompany> getAllByParentCodeAndIdIn(String officeCode, List<Long> ids);

    List<SysCompany> getAllByParentCodeAndCompanyCodeNot(String parentCode, String companyCode);
}
