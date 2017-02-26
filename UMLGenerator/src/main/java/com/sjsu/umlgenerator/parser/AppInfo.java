package com.sjsu.umlgenerator.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AppInfo {

    private List<ClassInfo> classInfoList;
    private List<RelationshipInfo> relationsList;

    private Set<String> classes;

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

    public Set<String> getClasses() {
	return classes;
    }

    public void setClasses(Set<String> classes) {
	this.classes = classes;
    }

}
