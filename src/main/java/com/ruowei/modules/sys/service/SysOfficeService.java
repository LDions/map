package com.ruowei.modules.sys.service;

import com.ruowei.common.pojo.TreeDTO;
import com.ruowei.common.service.BaseService;
import com.ruowei.modules.sys.domain.table.SysOffice;
import com.ruowei.modules.sys.mapper.SysOfficeMapper;
import com.ruowei.modules.sys.repository.SysOfficeRepository;
import com.ruowei.modules.sys.service.api.SysOfficeApi;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * @author 刘东奇
 * @date 2019/11/8
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysOfficeService extends BaseService implements SysOfficeApi {

    private final SysOfficeRepository sysOfficeRepository;

    private final SysOfficeMapper sysOfficeMapper;

    public SysOfficeService(SysOfficeRepository sysOfficeRepository, SysOfficeMapper sysOfficeMapper) {
        this.sysOfficeRepository = sysOfficeRepository;
        this.sysOfficeMapper = sysOfficeMapper;
    }

    /**
     * 判断是否存在机构
     * 如果存在，返回机构编码；如果不存在，抛异常
     *
     * @param id
     * @return officeCode
     * @author 刘东奇
     * @date 2019/11/2
     */
    @Override
    public SysOffice checkOfficeExistsById(Long id) {
        Assert.notNull(id,"机构ID不能为空");
        Optional<SysOffice> sysOffice = sysOfficeRepository.findById(id);
        Assert.isTrue(sysOffice.isPresent(),"机构："+id+"不存在");
        return sysOffice.get();
    }

    /**
     * 根据根节点获取全部节点
     * @author 刘东奇
     * @date 2019/11/16
     * @param rootId
     * @return
     */
    @Override
    public TreeDTO getSysOfficeAllNodesByRoot(Long rootId) {
        SysOffice root;
        //获取根节点
        if(rootId == null){
            root = sysOfficeRepository.findFirstByParentCodeIsNullOrderByTreeSortAsc();
        }else{
            root = sysOfficeRepository.getOne(rootId);
        }
        if(root != null){
            TreeDTO result = sysOfficeMapper.toTreeDTO(root);
            //找到该节点下的所有节点
            List<SysOffice> sysOfficeList = sysOfficeRepository.findAllByParentCodesLikeOrderByTreeSortsAsc(root.getOfficeCode());
            HashMap<String,List<TreeDTO>> childrenMap = new HashMap<String,List<TreeDTO>>();
            //遍历节点，给每个节点找到父节点
            for(SysOffice sysOffice:sysOfficeList){
                //当前节点
                List<TreeDTO> list = childrenMap.get(sysOffice.getParentCode());
                if(list == null){
                    list = new ArrayList<TreeDTO>();
                    childrenMap.put(sysOffice.getParentCode(),list);
                }
                list.add(sysOfficeMapper.toTreeDTO(sysOffice));
            }

            recursiveTree(result,childrenMap);
            return result;
        }
        return null;
    }

    /**
     * 递归树
     * @author 刘东奇
     * @date 2019/9/30
     * @param node
     * @param childrenMap
     */
    private void recursiveTree(TreeDTO node, HashMap<String,List<TreeDTO>> childrenMap){
        List<TreeDTO> list = childrenMap.get(node.getCode());
        node.setChildren(list);
        if(list == null){
            //说明本节点是叶子节点
            return;
        }else{
            for(TreeDTO treeDTO :list){
                recursiveTree(treeDTO,childrenMap);
            }
        }
    }

    /**
     * 根据根节点获取直接子节点
     * @author 刘东奇
     * @date 2019/11/16
     * @param rootId
     * @return
     */
    @Override
    public TreeDTO getSysOfficeDirectNodesByRoot(Long rootId) {
        SysOffice root;
        //获取根节点
        if(rootId == null){
            root = sysOfficeRepository.findFirstByParentCodeIsNullOrderByTreeSortAsc();
        }else{
            root = sysOfficeRepository.getOne(rootId);
        }
        if(root != null){
            TreeDTO result = sysOfficeMapper.toTreeDTO(root);
            //找到该节点下的直接子节点
            List<SysOffice> sysOfficeList = sysOfficeRepository.findAllByParentCodeOrderByTreeSortAsc(root.getOfficeCode());
            List<TreeDTO> treeDTOList = sysOfficeMapper.toTreeDTOs(sysOfficeList);
            result.setChildren(treeDTOList);
            return result;
        }
        return null;
    }
}
