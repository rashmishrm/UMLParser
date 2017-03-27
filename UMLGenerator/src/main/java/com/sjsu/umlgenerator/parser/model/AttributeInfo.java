package com.sjsu.umlgenerator.parser.model;

public class AttributeInfo {
    private String scope;
    private String name;
    private String type;
    private boolean collection;
    private String collectionLabel;

    public AttributeInfo(String scope, String name, String type) {
	super();
	this.scope = scope;
	this.name = name;
	this.type = type;
    }

    public String getScope() {
	return scope;
    }

    public void setScope(String scope) {
	this.scope = scope;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public boolean isCollection() {
	return collection;
    }

    public void setCollection(boolean collection) {
	this.collection = collection;
    }

    public String getCollectionLabel() {
	return collectionLabel;
    }

    public void setCollectionLabel(String collectionLabel) {
	this.collectionLabel = collectionLabel;
    }
}
