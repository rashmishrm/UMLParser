package com.sjsu.umlgenerator.parser;

import java.util.ArrayList;
import java.util.List;

public class AppInfo {

    private List<ClassInfo> classInfoList;
    private List<RelationshipInfo> relationsList;

    public AppInfo() {
	classInfoList = new ArrayList<>();
	relationsList = new ArrayList<>();
    }

    public AppInfo(List<ClassInfo> classInfoList, List<RelationshipInfo> relationsList) {
	super();
	this.classInfoList = classInfoList;
	this.relationsList = relationsList;
    }

    public List<ClassInfo> getClassInfoList() {
	return classInfoList;
    }

    public void setClassInfoList(List<ClassInfo> classInfoList) {
	this.classInfoList = classInfoList;
    }

    public List<RelationshipInfo> getRelationsList() {
	return relationsList;
    }

    public void setRelationsList(List<RelationshipInfo> relationsList) {
	this.relationsList = relationsList;
    }

}
