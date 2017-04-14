package com.sjsu.umlgenerator.parser.model;

import java.util.List;

import net.sourceforge.plantuml.sequencediagram.Message;

public class SequenceInfo {

    List<Message> messageList;

    public List<Message> getMessageList() {
	return messageList;
    }

    public void setMessageList(List<Message> messageList) {
	this.messageList = messageList;
    }

}
