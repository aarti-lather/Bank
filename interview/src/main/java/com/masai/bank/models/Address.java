package com.masai.bank.models;

import lombok.Data;

import javax.persistence.*;

@Data

@Embeddable
public class Address {
	
	private String city;
    private String state;
    private Integer zipCode;

    public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getZipCode() {
		return zipCode;
	}
	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}
	
    
    
    
    
}
