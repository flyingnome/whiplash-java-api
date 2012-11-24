package com.grandst.whiplash.api;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.ClientProtocolException;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.grandst.whiplash.Whiplash;
import com.grandst.whiplash.bean.Item;
import com.grandst.whiplash.bean.Order;
import com.grandst.whiplash.bean.OrderItem;
import com.grandst.whiplash.util.WhiplashReturn;

public class OrderService {
	
	public static WhiplashReturn getOrders(Whiplash w) throws ClientProtocolException, ParseException, IOException{
		return parseOrderListJson(API.get("/orders.json", w));
	}
	public static WhiplashReturn getOrderById(Whiplash w, long orderId) throws ClientProtocolException, ParseException, IOException{
		return parseOrderJson(API.get("/orders/"+orderId, w));
	}
	public static WhiplashReturn getOrderByOriginatorId(Whiplash w, long originatorId) throws ClientProtocolException, ParseException, IOException{
		return parseOrderJson(API.get("/orders/originator/"+originatorId, w));
	}
	public static WhiplashReturn createNewOrder(Whiplash w, Order o) throws ClientProtocolException, ParseException, IOException{
		for(OrderItem oi : o.getOrderItems()){
			//check if the item is on the API already
			Item i =  (Item)ItemService.getItemByOriginatorId(w, oi.getItemId()).getReturnObj();
			if(i==null || i.getId()<=0){ //it's not so create it
				i.setSku(oi.getSku());
				i.setTitle(oi.getTitle());
				i.setDescription(oi.getDescription());
				i.setOriginatorId(oi.getItemId());
				i = (Item)ItemService.createItem(w, i).getReturnObj();
			}
		}
		return parseOrderJson(API.post("/orders", w, o.getSerializedOrderForApiCreate(), 3000, 3000));
	}
	public static Order updateOrder(Whiplash w, Order o){
		//TODO: this too
		return o;
	}
	public static Order deleteOrder(Whiplash w, Order o){
		//TODO: this too
		return o;
	}
	private static WhiplashReturn parseOrderJson(String apiJson) throws  ParseException{
		WhiplashReturn retObj = new WhiplashReturn();
		if(retObj.tryParseError(apiJson))
			return retObj;
		apiJson = cleanDateFormat(apiJson); // ugh! only Java 7+ supports date formats with Timezone X eg. yyyy-MM-dd'T'HH:mm:ssX so we need to change the format to yyyy-MM-dd'T'HH:mm:ssZ
		JsonParser parser = new JsonParser();
		GsonBuilder gb = new GsonBuilder()
			.setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
			.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		Gson gson = gb.create();
		JsonObject orderObj = parser.parse(apiJson).getAsJsonObject();
		Order o = new Order();
		ArrayList<OrderItem> oiList = new ArrayList<OrderItem>();
		JsonArray itemArray = orderObj.getAsJsonArray("order_items");
		for(int j = 0; j<itemArray.size();j++){
			OrderItem oi = new OrderItem();
			oi = gson.fromJson(itemArray.get(j).getAsJsonObject(), OrderItem.class);
			oiList.add(oi);
		}
		o = gson.fromJson(orderObj, Order.class);
		o.setOrderItems(oiList);
		retObj.setReturnObj(o);
		return retObj;
	}
	
	private static WhiplashReturn parseOrderListJson(String apiJson) throws  ParseException{
		WhiplashReturn retObj = new WhiplashReturn();
		if(retObj.tryParseError(apiJson))
			return retObj;
		apiJson = cleanDateFormat(apiJson); // ugh! only Java 7+ supports date formats with Timezone X eg. yyyy-MM-dd'T'HH:mm:ssX so we need to change the format to yyyy-MM-dd'T'HH:mm:ssZ
		ArrayList<Order> retList = new ArrayList<Order>();
		JsonParser parser = new JsonParser();
		GsonBuilder gb = new GsonBuilder()
			.setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
			.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		Gson gson = gb.create();
		JsonArray orderArray = parser.parse(apiJson).getAsJsonArray();
		for(int i = 0; i < orderArray.size(); i++){
			Order o = new Order();
			JsonObject orderObj = orderArray.get(i).getAsJsonObject();
			ArrayList<OrderItem> oiList = new ArrayList<OrderItem>();
			JsonArray itemArray = orderObj.getAsJsonArray("order_items");
			for(int j = 0; j<itemArray.size();j++){
				OrderItem oi = new OrderItem();
				oi = gson.fromJson(itemArray.get(j).getAsJsonObject(), OrderItem.class);
				oiList.add(oi);
			}
			o = gson.fromJson(orderArray.get(i).getAsJsonObject(), Order.class);
			o.setOrderItems(oiList);
			retList.add(o);
		}
		retObj.setReturnObj(retList);
		return retObj;	
	}
	
	private static String cleanDateFormat(String json){
		Pattern regex = Pattern.compile("\\d\\d:\\d\\d:\\d\\d[-\\+]\\d\\d:\\d\\d"); 
		Matcher regexMatcher = regex.matcher(json);
		StringBuffer buff = new StringBuffer();
		while(regexMatcher.find()){
			regexMatcher.appendReplacement(buff, getSubOfMatch(regexMatcher));
		}
		regexMatcher.appendTail(buff);
		return buff.toString();
	}
	
	private static String getSubOfMatch(Matcher matcher){
		StringBuilder sb = new StringBuilder(matcher.group(0));
		sb.deleteCharAt(sb.length()-3);
		return sb.toString();
	}
}
