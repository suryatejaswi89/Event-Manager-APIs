package com.events.model;

import java.util.Date;

import org.springframework.data.annotation.Id;


public class Event {
	
	@Id 
	private String eventID;
	private String customerName;
	//private Date date;
	private String hallID;
	
	

	public Event(String customerName, String hallID){
		this.customerName=customerName;
		//this.date=dDate;
		this.hallID=hallID;
	}

	public String getEventID() {
		return eventID;
	}
	public void setEventID(String eventID) {
		this.eventID = eventID;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getHallID() {
		return hallID;
	}

	public void setHallID(String hallID) {
		this.hallID = hallID;
	}
	

}
