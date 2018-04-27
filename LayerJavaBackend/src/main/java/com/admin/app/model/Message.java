package com.admin.app.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings("deprecation")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Message {

	public String id;
	public String url;
	public String receipts_url;
	public long position;
	public Conversation conversation;
	public List<Parts> parts;
	public String sent_at;
	public boolean is_unread;
	public String received_at;
	@JsonIgnore
	public String recipient_status;
	@JsonIgnore
	public String reply_to;
	@JsonIgnore
	public String notification;
	public String updated_at;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getReceipts_url() {
		return receipts_url;
	}

	public void setReceipts_url(String receipts_url) {
		this.receipts_url = receipts_url;
	}

	public long getPosition() {
		return position;
	}

	public void setPosition(long position) {
		this.position = position;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public List<Parts> getParts() {
		return parts;
	}

	public void setParts(List<Parts> parts) {
		this.parts = parts;
	}

	public String getSent_at() {
		return sent_at;
	}

	public void setSent_at(String sent_at) {
		this.sent_at = sent_at;
	}

	public boolean isIs_unread() {
		return is_unread;
	}

	public void setIs_unread(boolean is_unread) {
		this.is_unread = is_unread;
	}

	public String getReceived_at() {
		return received_at;
	}

	public void setReceived_at(String received_at) {
		this.received_at = received_at;
	}

	public String getRecipient_status() {
		return recipient_status;
	}

	public void setRecipient_status(String recipient_status) {
		this.recipient_status = recipient_status;
	}

	public String getReply_to() {
		return reply_to;
	}

	public void setReply_to(String reply_to) {
		this.reply_to = reply_to;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", url=" + url + ", receipts_url=" + receipts_url + ", position=" + position
				+ ", conversation=" + conversation + ", parts=" + parts + ", sent_at=" + sent_at + ", is_unread="
				+ is_unread + ", received_at=" + received_at + ", recipient_status=" + recipient_status + ", reply_to="
				+ reply_to + ", notification=" + notification + ", updated_at=" + updated_at + "]";
	}
}
