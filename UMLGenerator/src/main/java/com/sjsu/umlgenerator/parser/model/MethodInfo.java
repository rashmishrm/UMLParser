package com.sjsu.umlgenerator.parser.model;

import java.util.List;

public class MethodInfo {
    private String returnType;
    private String scope;
    private List<String> arguemnts;
    private String name;
    private boolean constructor;

    private boolean isStatic;
    private boolean isAbstract;

    public MethodInfo(String scope, String returnType, String name, List<String> arguemnts) {
	super();
	this.returnType = returnType;
	this.scope = scope;
	this.arguemnts = arguemnts;
	this.name = name;
    }

    public String getReturnType() {
	return returnType;
    }

    public void setReturnType(String returnType) {
	this.returnType = returnType;
    }

    public String getScope() {
	return scope;
    }

    public void setScope(String scope) {
	this.scope = scope;
    }

    public List<String> getArguemnts() {
	return arguemnts;
    }

    public void setArguemnts(List<String> arguemnts) {
	this.arguemnts = arguemnts;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public boolean isConstructor() {
	return constructor;
    }

    public void setConstructor(boolean constructor) {
	this.constructor = constructor;
    }

    public boolean isAbstract() {
	return isAbstract;
    }

    public boolean isStatic() {
	return isStatic;
    }

    public void setAbstract(boolean isAbstract) {
	this.isAbstract = isAbstract;
    }

    public void setStatic(boolean isStatic) {
	this.isStatic = isStatic;
    }

}
