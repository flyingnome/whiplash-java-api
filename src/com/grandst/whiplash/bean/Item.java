package com.grandst.whiplash.bean;

import java.math.BigDecimal;
import java.util.Date;

public class Item {
	
	private long id;
	private BigDecimal price;
	private Boolean mediaMail;
	private String imageFileName;
	private Date createdAt;
	private String title;
	private Integer quantity;
	private Boolean available;
	private Date updatedAt;
	private BigDecimal weight;
	private Boolean bundle;
	private BigDecimal wholesaleCost;
	private String originator;
	private Integer expQuantity;
	private String sku;
	private Boolean packaging;
	private String imageOriginatorUrl;
	private String height;
	private String width;
	private String vendor;
	private long originatorId;
	private String description;
	private Date imageUpdatedAt;
	
	public Item(long id, BigDecimal price, Boolean mediaMail,
			String imageFileName, Date createdAt, String title,
			Integer quantity, Boolean available, Date updatedAt,
			BigDecimal weight, Boolean bundle, BigDecimal wholesaleCost,
			String originator, Integer expQuantity, String sku,
			Boolean packaging, String imageOriginatorUrl, String height,
			String width, String vendor, long originatorId, String description,
			Date imageUpdatedAt) {
		super();
		this.id = id;
		this.price = price;
		this.mediaMail = mediaMail;
		this.imageFileName = imageFileName;
		this.createdAt = createdAt;
		this.title = title;
		this.quantity = quantity;
		this.available = available;
		this.updatedAt = updatedAt;
		this.weight = weight;
		this.bundle = bundle;
		this.wholesaleCost = wholesaleCost;
		this.originator = originator;
		this.expQuantity = expQuantity;
		this.sku = sku;
		this.packaging = packaging;
		this.imageOriginatorUrl = imageOriginatorUrl;
		this.height = height;
		this.width = width;
		this.vendor = vendor;
		this.originatorId = originatorId;
		this.description = description;
		this.imageUpdatedAt = imageUpdatedAt;
	}

	public Item(){}

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

	public Boolean getMediaMail() {
		return mediaMail;
	}

	public void setMediaMail(Boolean mediaMail) {
		this.mediaMail = mediaMail;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public Boolean getBundle() {
		return bundle;
	}

	public void setBundle(Boolean bundle) {
		this.bundle = bundle;
	}

	public BigDecimal getWholesaleCost() {
		return wholesaleCost;
	}

	public void setWholesaleCost(BigDecimal wholesaleCost) {
		this.wholesaleCost = wholesaleCost;
	}

	public String getOriginator() {
		return originator;
	}

	public void setOriginator(String originator) {
		this.originator = originator;
	}

	public Integer getExpQuantity() {
		return expQuantity;
	}

	public void setExpQuantity(Integer expQuantity) {
		this.expQuantity = expQuantity;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Boolean getPackaging() {
		return packaging;
	}

	public void setPackaging(Boolean packaging) {
		this.packaging = packaging;
	}

	public String getImageOriginatorUrl() {
		return imageOriginatorUrl;
	}

	public void setImageOriginatorUrl(String imageOriginatorUrl) {
		this.imageOriginatorUrl = imageOriginatorUrl;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public long getOriginatorId() {
		return originatorId;
	}

	public void setOriginatorId(long originatorId) {
		this.originatorId = originatorId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getImageUpdatedAt() {
		return imageUpdatedAt;
	}

	public void setImageUpdatedAt(Date imageUpdatedAt) {
		this.imageUpdatedAt = imageUpdatedAt;
	}
	

}
