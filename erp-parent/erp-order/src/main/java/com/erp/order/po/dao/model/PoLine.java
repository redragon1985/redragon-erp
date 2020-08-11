package com.erp.order.po.dao.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="po_line", schema="erp")
@DynamicInsert(true)
@DynamicUpdate(true)
public class PoLine implements java.io.Serializable {

    //serialVersionUID
    private static final long serialVersionUID = 1L;

    //Constructors
    public PoLine() {
    }
    
    //Fields
    
    //采购订单行id
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "po_line_id", unique = true, nullable = false)
    private Integer poLineId;
    
    public Integer getPoLineId() {
        return this.poLineId;
    }
    public void setPoLineId(Integer poLineId) {
        this.poLineId = poLineId;
    }
    
    //采购订单行编码
    @NotBlank(message="{code.NotBlank}")
    @Column(name = "po_line_code", unique = true, nullable = false, length = 45)
    private String poLineCode;
    
    public String getPoLineCode() {
        return this.poLineCode;
    }
    public void setPoLineCode(String poLineCode) {
        this.poLineCode = poLineCode;
    }
    
    //采购订单头编码
    @NotBlank(message="头编码不能为空")
    @Column(name = "po_head_code", unique = false, nullable = false, length = 45)
    private String poHeadCode;
    
    public String getPoHeadCode() {
        return this.poHeadCode;
    }
    public void setPoHeadCode(String poHeadCode) {
        this.poHeadCode = poHeadCode;
    }
    
    //物料编码
    @NotBlank(message="物料不能为空")
    @Column(name = "material_code", unique = false, nullable = false, length = 45)
    private String materialCode;
    
    public String getMaterialCode() {
        return this.materialCode;
    }
    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }
    
    //数量
    @NotNull(message="数量不能为空")
    @Column(name = "quantity", unique = false, nullable = false)
    private Double quantity;
    
    public Double getQuantity() {
        return this.quantity;
    }
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
    
    //单价
    @NotNull(message="单价不能为空")
    @Column(name = "price", unique = false, nullable = false)
    private Double price;
    
    public Double getPrice() {
        return this.price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    
    //金额
    @NotNull(message="金额不能为空")
    @Column(name = "amount", unique = false, nullable = true)
    private Double amount;
    
    public Double getAmount() {
        return this.amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
    //单位
    @NotNull(message="单位不能为空")
    @Column(name = "unit", unique = false, nullable = false, length = 45)
    private String unit;
    
    public String getUnit() {
        return this.unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    //摘要
    @Column(name = "memo", unique = false, nullable = true, length = 500)
    private String memo;
    
    public String getMemo() {
        return this.memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
    
    //版本
    @Column(name = "version", unique = false, nullable = false)
    private Integer version;
    
    public Integer getVersion() {
        return this.version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }
    
    //状态
    @Column(name = "status", unique = false, nullable = false, length = 10)
    private String status;
    
    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
    //创建时间
    @Column(name = "created_date", unique = false, nullable = false)
    private Date createdDate;
    
    public Date getCreatedDate() {
        return this.createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
    //创建人
    @Column(name = "created_by", unique = false, nullable = false, length = 45)
    private String createdBy;
    
    public String getCreatedBy() {
        return this.createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    //最后修改时间
    @Column(name = "last_updated_date", unique = false, nullable = true)
    private Date lastUpdatedDate;
    
    public Date getLastUpdatedDate() {
        return this.lastUpdatedDate;
    }
    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }
    
    //最后修改人
    @Column(name = "last_updated_by", unique = false, nullable = true, length = 45)
    private String lastUpdatedBy;
    
    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
    
    //组织机构
    @Column(name = "org_code", unique = false, nullable = false, length = 10)
    private String orgCode;
    
    public String getOrgCode() {
        return this.orgCode;
    }
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
    
    
}