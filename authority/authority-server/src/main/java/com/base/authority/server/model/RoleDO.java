package com.base.authority.server.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import com.base.authority.client.model.RoleDTO;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

/**
 * @author:小M
 * @date:2020/7/30 12:49 AM
 */
@Entity
@Table(name = "role",
    indexes = {@Index(name = "idx_code",  columnList="code", unique = true),
        @Index(name = "idx_name",  columnList="name", unique = true)})
public class RoleDO {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column
    private Date gmtCreate;
    @Column
    private Date gmtModified;
    @Column(length = 64,nullable = false,unique = true)
    private String code;
    @Column(length = 64,nullable = false,unique = true)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
