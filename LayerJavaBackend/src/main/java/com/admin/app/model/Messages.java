package com.admin.app.model;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings("deprecation")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Messages {

	public String sender_id;
	public List<Parts> parts;

	public String getSender_id() {
		return sender_id;
	}

	public void setSender_id(String sender_id) {
		this.sender_id = sender_id;
	}

	public List<Parts> getParts() {
		return parts;
	}

	public void setParts(List<Parts> parts) {
		this.parts = parts;
	}

	@Override
	public String toString() {
		return "Messages [sender_id=" + sender_id + ", parts=" + parts + "]";
	}
}
