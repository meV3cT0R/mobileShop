package com.vector.mobileshop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Message {
	@Id
	@GeneratedValue
	private int id;
	private String fullName;
	private String email;
	private String messageData;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessageData() {
		return messageData;
	}
	public void setMessageData(String messageData) {
		this.messageData = messageData;
	}
	
	
}
