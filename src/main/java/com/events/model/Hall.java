package com.events.model;

import org.springframework.data.annotation.Id;
import java.util.*;

public class Hall {
	
	@Id
	private String id;
	private String name;
	private String address;
	private int capacity;
	
	
	public Hall(String name, String address, int capacity){
		this.name=name;
		this.address=address;
		this.capacity=capacity;
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	
	
}
