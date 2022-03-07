package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.table.QSysOffice;
import com.ruowei.modules.sys.domain.table.SysOffice;
import com.ruowei.modules.sys.domain.enumeration.OfficeStatusType;
import com.ruowei.modules.sys.pojo.dto.TreeMinLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the SysOffice entity.
 *
 * @author 刘东奇
 */
@SuppressWarnings("unused")
@Repository
public interface SysOfficeRepository
    extends BaseRepository<Long, SysOffice, QSysOffice> {
    /**
     * 查询机构树根节点
     *
     * @return SysOffice
     * @author 刘东奇
     * @date 2019/9/30
     */
    SysOffice findFirstByParentCodeIsNullOrderByTreeSortAsc();

    /**
     * 根据父节点查询下一级别所有子节点
     *
     * @param parentCode
     * @return
     * @author 刘东奇
     * @date 2019/9/30
     */
    List<SysOffice> findAllByParentCodeOrderByTreeSortAsc(String parentCode);

    /**
     * 查所有，按所有级别排序
     *
     * @return
     * @author 刘东奇
     * @date 2019/9/30
     */
    List<SysOffice> findAllByOrderByTreeSortsAsc();

    /**
     * 根据父节点编码，查询所有级别子节点，按所有级别排序
     *
     * @param parentCodes 父节点编码
     * @return
     * @author 刘东奇
     * @date 2019/9/30
     */
    List<SysOffice> findAllByParentCodesLikeOrderByTreeSortsAsc(@Size(max = 1000) String parentCodes);

    List<SysOffice> findAllByStatus(OfficeStatusType officeStatusType);

    List<SysOffice> getAllByParentCodeIsNullAndStatus(OfficeStatusType officeStatusType);

    List<SysOffice> getAllByParentCodeAndStatus(String parentCode, OfficeStatusType officeStatusType);

    Optional<SysOffice> getOneByOfficeCode(String officeCode);

    Optional<SysOffice> getOneByOfficeName(String officeName);

    Optional<SysOffice> getOneByOfficeNameAndIdNot(String officeName, Long id);

    Optional<SysOffice> getOneByOfficeCodeAndIdNot(String officeCode, Long id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,
        value = "delete from sys_office where office_code = ?1 or find_in_set(?1, parent_codes) > 0")
    void deleteAllByOfficeCodeOrParentCodesContains(String officeCode);

    @Query(value = "select o.id from SysOffice o" +
        " where (?1 is null or o.id = ?1)" +
        " and (?2 is null or o.officeCode like concat('%',?2,'%'))" +
        " and (?3 is null or o.officeName like concat('%',?3,'%'))" +
        " and (?4 is null or o.fullName like concat('%',?4,'%'))" +
        " and (?5 is null or o.officeType = ?5)" +
        " and (?6 is null or o.status = ?6)")
    List<Long> getAllNodeByParam(Long officeId, String officeCode, String officeName, String fullName, String officeType, String status);


    @Query(value = "select new com.ruowei.modules.sys.pojo.dto.TreeMinLevel(" +
        "substring(o.treeNames,1,locate('/',concat(o.treeNames,'/'),1)-1),min(o.treeLevel))" +
        " from SysOffice o where o.id in ?1" +
        " group by substring(o.treeNames,1,locate('/',concat(o.treeNames,'/'),1)-1)")
    List<TreeMinLevel> getAllTreeMinLevelByParam(List<Long> ids);

    @Query(value = "select o from SysOffice o" +
        " where concat(substring(o.treeNames,1,locate('/',concat(o.treeNames,'/'),1)-1),o.treeLevel) in ?1")
    Page<SysOffice> getAllByTreeMinLevels(List<String> treeMinLevels, Pageable pageable);

    List<SysOffice> getAllByParentCodeAndIdIn(String officeCode, List<Long> ids);

    List<SysOffice> getAllByParentCodeAndOfficeCodeNot(String parentCode, String officeCode);
}
