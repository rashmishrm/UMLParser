package com.sjsu.umlgenerator.parser.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClassInfo {

    private final List<MethodInfo> methods;
    private final List<AttributeInfo> attributeInfos;

    private Set<RelationshipInfo> relationshipInfos;

    private String name;

    private String type;

    public ClassInfo() {
	methods = new ArrayList<MethodInfo>();
	attributeInfos = new ArrayList<AttributeInfo>();
	relationshipInfos = new HashSet<RelationshipInfo>();
    }

    public ClassInfo(String name, List<MethodInfo> methods, List<AttributeInfo> attributeInfos) {
	super();
	this.name = name;
	this.methods = methods;
	this.attributeInfos = attributeInfos;
    }

    public void addMethodInfo(MethodInfo methodInfo) {
	if (AccessSpecifierDisplayRules.getMethodRules().contains(methodInfo.getScope())) {
	    methods.add(methodInfo);
	}
    }

    public void addAtributeInfo(AttributeInfo attributeInfo) {

	if (AccessSpecifierDisplayRules.getAttributeRules().contains(attributeInfo.getScope())) {

	    attributeInfos.add(attributeInfo);
	}
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

    public Set<RelationshipInfo> getRelationshipInfos() {
	return relationshipInfos;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

}
