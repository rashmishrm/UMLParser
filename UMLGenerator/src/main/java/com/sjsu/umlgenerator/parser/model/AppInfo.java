package com.sjsu.umlgenerator.parser.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AppInfo {

    private List<ClassInfo> classInfoList;
    private List<RelationshipInfo> relationsList;

    private Set<String> classes;

    private Set<String> interfaces;

    private String directory;

    public AppInfo() {
	classInfoList = new ArrayList<>();
	relationsList = new ArrayList<>();
	classes = new HashSet<String>();
	interfaces = new HashSet<String>();
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

    public String getDirectory() {
	return directory;
    }

    public void setDirectory(String directory) {
	this.directory = directory;
    }

    public Set<String> getInterfaces() {
	return interfaces;
    }
}
