package com.sjsu.umlgenerator.parser;

import java.util.ArrayList;
import java.util.List;

public class ClassInfo {

    private final List<MethodInfo> methods;
    private final List<AttributeInfo> attributeInfos;
    private String name;

    public ClassInfo() {
	methods = new ArrayList<MethodInfo>();
	attributeInfos = new ArrayList<AttributeInfo>();
    }

    public ClassInfo(String name, List<MethodInfo> methods, List<AttributeInfo> attributeInfos) {
	super();
	this.name = name;
	this.methods = methods;
	this.attributeInfos = attributeInfos;

    }

    public List<MethodInfo> getMethods() {
	return methods;
    }

    public List<AttributeInfo> getAttributeInfos() {
	return attributeInfos;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

}
