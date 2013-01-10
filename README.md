whiplash-java-api
=================
V 0.01
Simple Java API library for [Whiplash](https://www.whiplashmerch.com/), you can find the documentation [here](https://www.whiplashmerch.com/documentation/api).

### Installation
export project as .jar and add to your libs

### Usage
#### create a whiplash object using only your API key
        Whiplash w = new Whiplash("apiKey"); 
OR

##### with an optional bool to tell the object to use the testing environment, if the apiKey is an empty string it will use the shared test apiKey
        Whiplash w = new Whiplash("apiKey",true);
        
#### sample get
      	ArrayList<Order> orders = OrderService.getOrders(w).getReturnObj();
    		if(orders!=null){
    			print "Got Orders from Whiplash ===== " + orders.size();
    			for(Order or : orders){
    				if(or.getStatus() == OrderStatus.SHIPPED){
    					print "Found a newly Shipped Order, now update internal tracking info for Order with originator Id :: "+or.getOriginatorId();
    				}
    			}
    		}

#### sample order create

        ArrayList<OrderItem> whiplashOrderItems = new ArrayList<OrderItem>();
        OrderItem wOi = new OrderItem(
              0
              ,20.00 //price
              ,new Date()
              ,false	
              ,true
              ,3 //quantity
              ,new Date()
              ,0
              ,0
              ,false
              ,"SKU-PRODUCT-UNIQUE"
              ,"Product Name"
              ,""+internalReferenceId //originator id
              ,0
    		  );
    		wOi.setTitle("Product Title");
    		whiplashOrderItems.add(wOi);
        Order whiplashOrder = new Order(
      			0
    				,"John Doe"
    				,"Company X"
    				,"NY"
    				,"123 Apple Drive"
    				,"Suite 15"
    				,"New York City"
    				,"11001"
    				,"United States"
    				,null
    				,null
    				,null
    				,null
    				,null
    				,"USPS Priority Mail"
    				,null
    				,new Date()
    				,false
    				,new Date()
    				,null
    				,null
    				,grandstToWhiplashShipping(gOrder.getShippingOption().getId())
    				,null //req shipmethodprice
    				,null
    				,false
    				,null
    				,false
    				,""+internalReferenceId
    				,0
    				,null
    				,false //isGift
    				,null
    				,"foo@bar.com"
    				,whiplashOrderItems
    				,"USPS Priority Mail"
    		);
        WhiplashReturn wRet = OrderService.createNewOrder(w,o);
    		if(wRet.getReturnObj()!=null){
            print "Returned a proper object from whiplash, updating it internally";
            System.out.println("WhiplashOrder "+((Order)wRet.getReturnObj()).getId()+ " was set to processing");
    		}


