package com.grandst.whiplash.bean;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.entity.StringEntity;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.grandst.whiplash.constants.OrderStatus;

public class Order {
	private long id;
	//address info
	private String shippingName;
	private String shippingCompany;
	private String shippingState;
	private String shippingAddress;
	private String shippingAddress2;
	private String shippingCity;
	private String shippingZip;
	private String shippingCountry;
	private String shippingPhone;
	
	//3rd party address info
	private String ship3rdPartyAccount;
	private String ship3rdPartyCountry;
	private BigDecimal ship3rdPartyCost;
	private String ship3rdPartyZip;
	
	private String reqShipMethodCode;
	private BigDecimal reqShipMethodPrice;
	
	private String shipNotes;
	private Date createdAt;
	private Boolean trackingSent;
	private Date updatedAt;
	private String tracking;
	private Date ShippedOn;
	private String shipMethod;
	
	private String originiator;
	private Boolean billed;
	private BigDecimal shipActualCost;
	private Boolean originatorNotified;
	private String originatorId;
	private long status;
	private String orderOrig;
	private Boolean gift;
	private String publicNote;
	private String email;
	private List<OrderItem> orderItems;
	
	public Order(){}
	
	public Order(long id, String shippingName, String shippingCompany,
			String shippingState, String shippingAddress,
			String shippingAddress2, String shippingCity, String shippingZip,
			String shippingCountry, String shippingPhone,
			String ship3rdPartyAccount, String ship3rdPartyCountry,
			BigDecimal ship3rdPartyCost, String ship3rdPartyZip,
			String reqShipMethodCode, String shipNotes, Date createdAt,
			Boolean trackingSent, Date updatedAt, String tracking,
			Date shippedOn, String shipMethod, BigDecimal reqShipMethodPrice,
			String originiator, Boolean billed, BigDecimal shipActualCost,
			Boolean originatorNotified, String originatorId, long status,
			String orderOrig, Boolean gift, String publicNote, String email,
			List<OrderItem> orderItems) {
		super();
		this.id = id;
		this.shippingName = shippingName;
		this.shippingCompany = shippingCompany;
		this.shippingState = shippingState;
		this.shippingAddress = shippingAddress;
		this.shippingAddress2 = shippingAddress2;
		this.shippingCity = shippingCity;
		this.shippingZip = shippingZip;
		this.shippingCountry = shippingCountry;
		this.shippingPhone = shippingPhone;
		this.ship3rdPartyAccount = ship3rdPartyAccount;
		this.ship3rdPartyCountry = ship3rdPartyCountry;
		this.ship3rdPartyCost = ship3rdPartyCost;
		this.ship3rdPartyZip = ship3rdPartyZip;
		this.reqShipMethodCode = reqShipMethodCode;
		this.shipNotes = shipNotes;
		this.createdAt = createdAt;
		this.trackingSent = trackingSent;
		this.updatedAt = updatedAt;
		this.tracking = tracking;
		ShippedOn = shippedOn;
		this.shipMethod = shipMethod;
		this.reqShipMethodPrice = reqShipMethodPrice;
		this.originiator = originiator;
		this.billed = billed;
		this.shipActualCost = shipActualCost;
		this.originatorNotified = originatorNotified;
		this.originatorId = originatorId;
		this.status = status;
		this.orderOrig = orderOrig;
		this.gift = gift;
		this.publicNote = publicNote;
		this.email = email;
		this.orderItems = orderItems;
	}	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getShippingName() {
		return shippingName;
	}
	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}
	public String getShippingCompany() {
		return shippingCompany;
	}
	public void setShippingCompany(String shippingCompany) {
		this.shippingCompany = shippingCompany;
	}
	public String getShippingState() {
		return shippingState;
	}
	public void setShippingState(String shippingState) {
		this.shippingState = shippingState;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public String getShippingAddress2() {
		return shippingAddress2;
	}
	public void setShippingAddress2(String shippingAddress2) {
		this.shippingAddress2 = shippingAddress2;
	}
	public String getShippingCity() {
		return shippingCity;
	}
	public void setShippingCity(String shippingCity) {
		this.shippingCity = shippingCity;
	}
	public String getShippingZip() {
		return shippingZip;
	}
	public void setShippingZip(String shippingZip) {
		this.shippingZip = shippingZip;
	}
	public String getShippingCountry() {
		return shippingCountry;
	}
	public void setShippingCountry(String shippingCountry) {
		this.shippingCountry = shippingCountry;
	}
	public String getShippingPhone() {
		return shippingPhone;
	}
	public void setShippingPhone(String shippingPhone) {
		this.shippingPhone = shippingPhone;
	}
	public String getShip3rdPartyAccount() {
		return ship3rdPartyAccount;
	}
	public void setShip3rdPartyAccount(String ship3rdPartyAccount) {
		this.ship3rdPartyAccount = ship3rdPartyAccount;
	}
	public String getShip3rdPartyCountry() {
		return ship3rdPartyCountry;
	}
	public void setShip3rdPartyCountry(String ship3rdPartyCountry) {
		this.ship3rdPartyCountry = ship3rdPartyCountry;
	}
	public BigDecimal getShip3rdPartyCost() {
		return ship3rdPartyCost;
	}
	public void setShip3rdPartyCost(BigDecimal ship3rdPartyCost) {
		this.ship3rdPartyCost = ship3rdPartyCost;
	}
	public String getShip3rdPartyZip() {
		return ship3rdPartyZip;
	}
	public void setShip3rdPartyZip(String ship3rdPartyZip) {
		this.ship3rdPartyZip = ship3rdPartyZip;
	}
	public String getReqShipMethodCode() {
		return reqShipMethodCode;
	}
	public void setReqShipMethodCode(String reqShipMethodCode) {
		this.reqShipMethodCode = reqShipMethodCode;
	}
	public String getShipNotes() {
		return shipNotes;
	}
	public void setShipNotes(String shipNotes) {
		this.shipNotes = shipNotes;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Boolean getTrackingSent() {
		return trackingSent;
	}
	public void setTrackingSent(Boolean trackingSent) {
		this.trackingSent = trackingSent;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getTracking() {
		return tracking;
	}
	public void setTracking(String tracking) {
		this.tracking = tracking;
	}
	public Date getShippedOn() {
		return ShippedOn;
	}
	public void setShippedOn(Date shippedOn) {
		ShippedOn = shippedOn;
	}
	public String getShipMethod() {
		return shipMethod;
	}
	public void setShipMethod(String shipMethod) {
		this.shipMethod = shipMethod;
	}
	public BigDecimal getReqShipMethodPrice() {
		return reqShipMethodPrice;
	}
	public void setReqShipMethodPrice(BigDecimal reqShipMethodPrice) {
		this.reqShipMethodPrice = reqShipMethodPrice;
	}
	public String getOriginiator() {
		return originiator;
	}
	public void setOriginiator(String originiator) {
		this.originiator = originiator;
	}
	public Boolean getBilled() {
		return billed;
	}
	public void setBilled(Boolean billed) {
		this.billed = billed;
	}
	public BigDecimal getShipActualCost() {
		return shipActualCost;
	}
	public void setShipActualCost(BigDecimal shipActualCost) {
		this.shipActualCost = shipActualCost;
	}
	public Boolean getOriginatorNotified() {
		return originatorNotified;
	}
	public void setOriginatorNotified(Boolean originatorNotified) {
		this.originatorNotified = originatorNotified;
	}
	public String getOriginatorId() {
		return originatorId;
	}
	public void setOriginatorId(String originatorId) {
		this.originatorId = originatorId;
	}
	public long getStatus() {
		return status;
	}
	public void setStatus(long status) {
		this.status = status;
	}
	public String getOrderOrig() {
		return orderOrig;
	}
	public void setOrderOrig(String orderOrig) {
		this.orderOrig = orderOrig;
	}
	public Boolean getGift() {
		return gift;
	}
	public void setGift(Boolean gift) {
		this.gift = gift;
	}
	public String getPublicNote() {
		return publicNote;
	}
	public void setPublicNote(String publicNote) {
		this.publicNote = publicNote;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	//for display purps
	public String getShippingString(){
		StringBuilder sb = new StringBuilder();
		sb.append(this.getShippingAddress());
		sb.append(", ");
		if(this.getShippingAddress2()!=null && !this.getShippingAddress2().equals("")){
			sb.append(this.getShippingAddress2());
			sb.append(", ");
		}
		if(this.getShippingCity()!=null && !this.getShippingCity().equals("")){
			sb.append(this.getShippingCity());
			sb.append(", ");
		}
		if(this.getShippingState()!=null && !this.getShippingState().equals("")){
			sb.append(this.getShippingState());
			sb.append(", ");
		}
		if(this.getShippingCountry()!=null && !this.getShippingCountry().equals("")){
			sb.append(this.getShippingCountry());
			sb.append(", ");
		}
		if(this.getShippingZip() !=null && !this.getShippingZip().equals(""))
			sb.append(this.getShippingZip());
		return sb.toString();
	}
	
	public String getStatusString(){
		long statusCode = this.getStatus();
		if(statusCode == OrderStatus.CANCELLED)
			return "Cancelled";
		else if(statusCode == OrderStatus.CLOSED_BY_ORIGINATOR)
			return "Closed By Originator";
		else if(statusCode == OrderStatus.EXCHANGED)
			return "Exchanged";
		else if(statusCode == OrderStatus.ITEMS_UNAVAILABLE)
			return "Items Unavailable";
		else if(statusCode == OrderStatus.PACKED)
			return "Packed";
		else if(statusCode == OrderStatus.PICKED)
			return "Picked";
		else if(statusCode == OrderStatus.PAUSED)
			return "Paused";
		else if(statusCode == OrderStatus.PRINTED)
			return "Printed";
		else if(statusCode == OrderStatus.PROCESSING)
			return "Processing";
		else if(statusCode == OrderStatus.REFUND_REQUESTED)
			return "Refund Requested";
		else if(statusCode == OrderStatus.REPLACEMENT_REQUESTED)
			return "Replacement Requested";
		else if(statusCode == OrderStatus.RETURNED_UNDELIVERABLE)
			return "Returned Undeliverable";
		else if(statusCode == OrderStatus.SHIPPED)
			return "Shipped";
		else if(statusCode == OrderStatus.UNPAID)
			return "Unpaid";
		else
			return "Unknown";
	}
	
	public StringEntity getSerializedOrderForApiCreate() throws UnsupportedEncodingException{
		ApiOrderHolder oh = new ApiOrderHolder();
		ApiOrder ao = new ApiOrder();
		ao.shippingName = this.getShippingName();
		ao.shippingAddress1 = this.getShippingAddress();
		ao.shippingAddress2 = this.getShippingAddress2();
		ao.shippingCity = this.getShippingCity();
		ao.shippingState = this.getShippingState();
		ao.shippingZip = this.getShippingZip();
		ao.email = this.getEmail();
		ao.orderItemsAttributes = new ArrayList<ApiItem>();
		for(OrderItem i : this.getOrderItems()){
			ApiItem ai = new ApiItem();
			ai.quantity = i.getQuantity();
			ai.itemId = i.getItemId();
			ao.orderItemsAttributes.add(ai);
		}
		oh.order = ao;
		GsonBuilder gb = new GsonBuilder()
			.setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
			.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		Gson gson = gb.create();
		String jObj = gson.toJson(oh, ApiOrderHolder.class);
		return new StringEntity(jObj);
	}
	public StringEntity getSerializedOrderForApiSansItems() throws UnsupportedEncodingException{
		ApiOrderHolder oh = new ApiOrderHolder();
		ApiOrder ao = new ApiOrder();
		ao.shippingName = this.getShippingName();
		ao.shippingAddress1 = this.getShippingAddress();
		ao.shippingAddress2 = this.getShippingAddress2();
		ao.shippingCity = this.getShippingCity();
		ao.shippingState = this.getShippingState();
		ao.shippingZip = this.getShippingZip();
		ao.email = this.getEmail();
		oh.order = ao;
		GsonBuilder gb = new GsonBuilder()
			.setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
			.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		Gson gson = gb.create();
		String jObj = gson.toJson(oh, ApiOrderHolder.class);
		return new StringEntity(jObj);
	}
	
	private class ApiOrder{
		public ApiOrder(){}
		private String shippingName;
		@SerializedName("shipping_address_1") private String shippingAddress1;
		@SerializedName("shipping_address_2") private String shippingAddress2;
		private String shippingCity;
		private String shippingState;
		private String shippingZip;
		private String email;
		private ArrayList<ApiItem> orderItemsAttributes;
		
	}
	private class ApiItem{
		public ApiItem(){}
		private Integer quantity;
		private long itemId;
		
	}
	private class ApiOrderHolder{
		public ApiOrderHolder(){}
		private ApiOrder order;
	}
	

}
