package com.workflow.demo.entity;

import java.io.Serializable;
import java.util.Date;

public class BusinessUrl implements Serializable {
    private Long id;

    private Date createTime;

    private String desc;

    private Date updateTime;

    private String url;

    private String custcode;

    private String productline;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getCustcode() {
        return custcode;
    }

    public void setCustcode(String custcode) {
        this.custcode = custcode == null ? null : custcode.trim();
    }

    public String getProductline() {
        return productline;
    }

    public void setProductline(String productline) {
        this.productline = productline == null ? null : productline.trim();
    }
}