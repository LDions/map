package com.ruowei.modules.sys.mapper;

import com.ruowei.common.pojo.TreeDTO;
import com.ruowei.modules.sys.domain.table.SysOffice;
import com.ruowei.modules.sys.pojo.dto.SysOfficeDTO;
import com.ruowei.modules.sys.pojo.SysOfficeTreeDTO;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-10-19T11:23:47+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_211 (Oracle Corporation)"
)
@Component
public class SysOfficeMapperImpl implements SysOfficeMapper {

    @Override
    public SysOffice toEntity(SysOfficeDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SysOffice sysOffice = new SysOffice();

        sysOffice.setId( dto.getId() );
        sysOffice.setOfficeCode( dto.getOfficeCode() );
        sysOffice.setParentCode( dto.getParentCode() );
        sysOffice.setParentCodes( dto.getParentCodes() );
        sysOffice.setTreeSort( dto.getTreeSort() );
        sysOffice.setTreeSorts( dto.getTreeSorts() );
        sysOffice.setTreeLeaf( dto.isTreeLeaf() );
        sysOffice.setTreeLevel( dto.getTreeLevel() );
        sysOffice.setTreeNames( dto.getTreeNames() );
        sysOffice.setViewCode( dto.getViewCode() );
        sysOffice.setOfficeName( dto.getOfficeName() );
        sysOffice.setFullName( dto.getFullName() );
        sysOffice.setOfficeType( dto.getOfficeType() );
        sysOffice.setLeader( dto.getLeader() );
        sysOffice.setPhone( dto.getPhone() );
        sysOffice.setAddress( dto.getAddress() );
        sysOffice.setZipCode( dto.getZipCode() );
        sysOffice.setEmail( dto.getEmail() );
        sysOffice.setRemarks( dto.getRemarks() );
        sysOffice.setStatus( dto.getStatus() );

        return sysOffice;
    }

    @Override
    public SysOfficeDTO toDto(SysOffice entity) {
        if ( entity == null ) {
            return null;
        }

        SysOfficeDTO sysOfficeDTO = new SysOfficeDTO();

        sysOfficeDTO.setId( entity.getId() );
        sysOfficeDTO.setOfficeCode( entity.getOfficeCode() );
        sysOfficeDTO.setParentCode( entity.getParentCode() );
        sysOfficeDTO.setParentCodes( entity.getParentCodes() );
        sysOfficeDTO.setTreeSort( entity.getTreeSort() );
        sysOfficeDTO.setTreeSorts( entity.getTreeSorts() );
        sysOfficeDTO.setTreeLeaf( entity.isTreeLeaf() );
        sysOfficeDTO.setTreeLevel( entity.getTreeLevel() );
        sysOfficeDTO.setTreeNames( entity.getTreeNames() );
        sysOfficeDTO.setViewCode( entity.getViewCode() );
        sysOfficeDTO.setOfficeName( entity.getOfficeName() );
        sysOfficeDTO.setFullName( entity.getFullName() );
        sysOfficeDTO.setOfficeType( entity.getOfficeType() );
        sysOfficeDTO.setLeader( entity.getLeader() );
        sysOfficeDTO.setPhone( entity.getPhone() );
        sysOfficeDTO.setAddress( entity.getAddress() );
        sysOfficeDTO.setZipCode( entity.getZipCode() );
        sysOfficeDTO.setEmail( entity.getEmail() );
        sysOfficeDTO.setRemarks( entity.getRemarks() );
        sysOfficeDTO.setStatus( entity.getStatus() );

        return sysOfficeDTO;
    }

    @Override
    public List<SysOffice> toEntity(List<SysOfficeDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<SysOffice> list = new ArrayList<SysOffice>( dtoList.size() );
        for ( SysOfficeDTO sysOfficeDTO : dtoList ) {
            list.add( toEntity( sysOfficeDTO ) );
        }

        return list;
    }

    @Override
    public List<SysOfficeDTO> toDto(List<SysOffice> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<SysOfficeDTO> list = new ArrayList<SysOfficeDTO>( entityList.size() );
        for ( SysOffice sysOffice : entityList ) {
            list.add( toDto( sysOffice ) );
        }

        return list;
    }

    @Override
    public TreeDTO toTreeDTO(SysOffice sysOffice) {
        if ( sysOffice == null ) {
            return null;
        }

        TreeDTO treeDTO = new TreeDTO();

        treeDTO.setName( sysOffice.getOfficeName() );
        treeDTO.setCode( sysOffice.getOfficeCode() );
        treeDTO.setParentCode( sysOffice.getParentCode() );
        if ( sysOffice.getId() != null ) {
            treeDTO.setId( String.valueOf( sysOffice.getId() ) );
        }

        return treeDTO;
    }

    @Override
    public SysOfficeTreeDTO toSysOfficeTree(SysOffice sysOffice) {
        if ( sysOffice == null ) {
            return null;
        }

        SysOfficeTreeDTO sysOfficeTree = new SysOfficeTreeDTO();

        sysOfficeTree.setName( sysOffice.getOfficeName() );
        sysOfficeTree.setCode( sysOffice.getOfficeCode() );
        sysOfficeTree.setParentCode( sysOffice.getParentCode() );
        if ( sysOffice.getId() != null ) {
            sysOfficeTree.setId( String.valueOf( sysOffice.getId() ) );
        }
        sysOfficeTree.setCreatedBy( sysOffice.getCreatedBy() );
        sysOfficeTree.setCreatedDate( sysOffice.getCreatedDate() );
        sysOfficeTree.setLastModifiedBy( sysOffice.getLastModifiedBy() );
        sysOfficeTree.setLastModifiedDate( sysOffice.getLastModifiedDate() );
        sysOfficeTree.setOfficeCode( sysOffice.getOfficeCode() );
        sysOfficeTree.setOfficeName( sysOffice.getOfficeName() );
        sysOfficeTree.setFullName( sysOffice.getFullName() );
        sysOfficeTree.setTreeSort( sysOffice.getTreeSort() );
        sysOfficeTree.setTreeSorts( sysOffice.getTreeSorts() );
        sysOfficeTree.setOfficeType( sysOffice.getOfficeType() );
        sysOfficeTree.setRemarks( sysOffice.getRemarks() );
        sysOfficeTree.setStatus( sysOffice.getStatus() );

        return sysOfficeTree;
    }

    public List<TreeDTO> toTreeDTOs(List<SysOffice> sysOfficeList) {
        if ( sysOfficeList == null ) {
            return null;
        }

        List<TreeDTO> list = new ArrayList<TreeDTO>( sysOfficeList.size() );
        for ( SysOffice sysOffice : sysOfficeList ) {
            list.add( toTreeDTO( sysOffice ) );
        }

        return list;
    }
}
