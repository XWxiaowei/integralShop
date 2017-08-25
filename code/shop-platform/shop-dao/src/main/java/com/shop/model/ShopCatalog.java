package com.shop.model;

import java.util.Date;

public class ShopCatalog {
    private Long id;

    private Long catalogId;

    private Long parentCatalogid;

    private Long catalogLevel;

    private String catalogName;

    private Date createTime;

    private Date updateTime;

    private Integer deleteFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
    }

    public Long getParentCatalogid() {
        return parentCatalogid;
    }

    public void setParentCatalogid(Long parentCatalogid) {
        this.parentCatalogid = parentCatalogid;
    }

    public Long getCatalogLevel() {
        return catalogLevel;
    }

    public void setCatalogLevel(Long catalogLevel) {
        this.catalogLevel = catalogLevel;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName == null ? null : catalogName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}