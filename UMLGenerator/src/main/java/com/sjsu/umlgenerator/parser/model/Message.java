package com.sjsu.umlgenerator.parser.model;

public class Message {
    private String sender;
    private String reciever;
    private String message;

    public String getSender() {
	return sender;
    }

    public void setSender(String sender) {
	this.sender = sender;
    }

    public String getReciever() {
	return reciever;
    }

    public void setReciever(String reciever) {
	this.reciever = reciever;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }


}
