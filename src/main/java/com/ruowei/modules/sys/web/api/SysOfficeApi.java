package com.ruowei.modules.sys.web.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 刘东奇
 * @date 2019/11/9
 */
public interface SysOfficeApi {
    /**
     * 根据根节点获取全部节点
     * @author 刘东奇
     * @date 2019/11/16
     * @param rootId
     * @return
     */
    ResponseEntity getSysOfficeAllNodesByRoot(@PathVariable Long rootId);

    /**
     * 根据根节点获取直接子节点
     * @author 刘东奇
     * @date 2019/11/16
     * @param rootId
     * @return
     */
    ResponseEntity getSysOfficeDirectNodesByRoot(@PathVariable Long rootId);
}
