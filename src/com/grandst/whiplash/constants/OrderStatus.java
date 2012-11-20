package com.grandst.whiplash.constants;

public class OrderStatus {
	
	/* 
	  	40: cancelled
		45: closed by originator
		50: unpaid
		80: items unavailable
		90: paused
		100: processing
		120: printed
		150: picked
		160: packed
		300: shipped
		400: returned undeliverable
		410: replacement requested
		430: exchanged
		450: refund requested
	 */
	
	
	public static final long CANCELLED = 40l;
	public static final long CLOSED_BY_ORIGINATOR = 45l;
	public static final long UNPAID = 50l;
	public static final long ITEMS_UNAVAILABLE = 80l;
	public static final long PAUSED = 90l;
	public static final long PROCESSING = 100l;
	public static final long PRINTED = 120l;
	public static final long PICKED = 150l;
	public static final long PACKED = 160l;
	public static final long SHIPPED = 300l;
	public static final long RETURNED_UNDELIVERABLE = 400l;
	public static final long REPLACEMENT_REQUESTED = 410l;
	public static final long EXCHANGED = 430l;
	public static final long REFUND_REQUESTED = 450l;
		

}
