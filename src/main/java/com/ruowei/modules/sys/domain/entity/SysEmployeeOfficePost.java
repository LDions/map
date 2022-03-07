package com.ruowei.modules.sys.domain.entity;

import com.ruowei.common.entity.PrimaryKeyAutoIncrementEntity;
import com.ruowei.modules.sys.domain.table.SysOffice;
import com.ruowei.modules.sys.domain.table.SysPost;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 员工的附属机构及岗位信息
 * @author 刘东奇
 */
@Entity
@Table(name = "sys_employee_office")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysEmployeeOfficePost extends PrimaryKeyAutoIncrementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 员工
     */
    @ManyToOne
    @JoinColumn(name="sys_employee_id",referencedColumnName ="id")
    private SysEmployee employee;

    /**
     * 机构
     */
    @ManyToOne
    @JoinColumn(name="sys_office_id",referencedColumnName ="id")
    private SysOffice office;

    /**
     * 岗位
     */
    @ManyToOne
    @JoinColumn(name="sys_post_id",referencedColumnName ="id")
    private SysPost post;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public SysEmployee getEmployee() {
        return employee;
    }

    public void setEmployee(SysEmployee employee) {
        this.employee = employee;
    }

    public SysOffice getOffice() {
        return office;
    }

    public void setOffice(SysOffice office) {
        this.office = office;
    }

    public SysPost getPost() {
        return post;
    }

    public void setPost(SysPost post) {
        this.post = post;
    }


}
