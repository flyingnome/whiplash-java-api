package com.grandst.whiplash.bean;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.http.entity.StringEntity;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class OrderItem {
	public OrderItem(long id, BigDecimal price, Date createdAt,
			Boolean unshippable, Boolean available, Integer quantity,
			Date updatedAt, Integer packed, Integer picked, Boolean packaging,
			String sku, String description, long originatorId, long itemId) {
		super();
		this.id = id;
		this.price = price;
		this.createdAt = createdAt;
		this.unshippable = unshippable;
		this.available = available;
		this.quantity = quantity;
		this.updatedAt = updatedAt;
		this.packed = packed;
		this.picked = picked;
		this.packaging = packaging;
		this.sku = sku;
		this.description = description;
		this.originatorId = originatorId;
		this.itemId = itemId;
	}
	
	public OrderItem(){}
	
	private long id;
	private BigDecimal price;
	private Date createdAt;
	private Boolean unshippable;
	private Boolean available;
	private Integer quantity;
	private Date updatedAt;
	private Integer packed;
	private Integer picked;
	private Boolean packaging;
	private String sku;
	private String description;
	private long originatorId;
	private long itemId;
	private long orderId;
	private String title;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Boolean getUnshippable() {
		return unshippable;
	}
	public void setUnshippable(Boolean unshippable) {
		this.unshippable = unshippable;
	}
	public Boolean getAvailable() {
		return available;
	}
	public void setAvailable(Boolean available) {
		this.available = available;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Integer getPacked() {
		return packed;
	}
	public void setPacked(Integer packed) {
		this.packed = packed;
	}
	public Integer getPicked() {
		return picked;
	}
	public void setPicked(Integer picked) {
		this.picked = picked;
	}
	public Boolean getPackaging() {
		return packaging;
	}
	public void setPackaging(Boolean packaging) {
		this.packaging = packaging;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getOriginatorId() {
		return originatorId;
	}
	public void setOriginatorId(long originatorId) {
		this.originatorId = originatorId;
	}
	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	
	public StringEntity getSerializedOrderItemForApi() throws UnsupportedEncodingException{
		ApiOrderItem ao = new ApiOrderItem();
		ao.quantity = this.getQuantity();
		ao.itemId = this.getItemId();
		ao.orderId = this.getOrderId();
		GsonBuilder gb = new GsonBuilder()
			.setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
			.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		Gson gson = gb.create();
		String jObj = gson.toJson(ao, ApiOrderItem.class);
		return new StringEntity(jObj);
	}
	
	private class ApiOrderItem{
		public ApiOrderItem(){}
		private Integer quantity;
		private long itemId;
		private long orderId;
		
	}
	
}
