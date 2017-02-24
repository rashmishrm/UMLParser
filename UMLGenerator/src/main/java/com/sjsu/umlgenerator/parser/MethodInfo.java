package com.sjsu.umlgenerator.parser;

public class MethodInfo {
    private String returnType;
    private String scope;
    private String[] arguemnts;
    private String name;

    public MethodInfo(String scope, String returnType, String name, String[] arguemnts) {
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

    public String[] getArguemnts() {
	return arguemnts;
    }

    public void setArguemnts(String[] arguemnts) {
	this.arguemnts = arguemnts;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

}
