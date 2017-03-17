package com.sjsu.umlgenerator.parser.model;

import java.util.ArrayList;
import java.util.List;

public class ClassInfo {

    private final List<MethodInfo> methods;
    private final List<AttributeInfo> attributeInfos;

    private List<RelationshipInfo> relationshipInfos;

    private String name;

    public ClassInfo() {
	methods = new ArrayList<MethodInfo>();
	attributeInfos = new ArrayList<AttributeInfo>();
	relationshipInfos = new ArrayList<RelationshipInfo>();
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

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public List<RelationshipInfo> getRelationshipInfos() {
	return relationshipInfos;
    }
}
