package com.ruowei.modules.sys.service.api;

import com.ruowei.common.pojo.TreeDTO;
import com.ruowei.modules.sys.domain.table.SysOffice;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 刘东奇
 * @date 2019/11/8
 */
public interface SysOfficeApi {
    /**
     * 判断是否存在该机构
     * 如果存在，返回机构；如果不存在，抛异常
     * @author 刘东奇
     * @date 2019/11/2
     * @param id
     * @return SysOffice
     */
    SysOffice checkOfficeExistsById(Long id);

    /**
     * 根据根节点获取全部节点
     * @author 刘东奇
     * @date 2019/11/16
     * @param rootId
     * @return
     */
    TreeDTO getSysOfficeAllNodesByRoot(@PathVariable Long rootId);

    /**
     * 根据根节点获取直接子节点
     * @author 刘东奇
     * @date 2019/11/16
     * @param rootId
     * @return
     */
    TreeDTO getSysOfficeDirectNodesByRoot(@PathVariable Long rootId);
}
