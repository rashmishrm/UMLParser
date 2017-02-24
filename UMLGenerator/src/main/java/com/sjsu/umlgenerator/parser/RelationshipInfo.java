package com.sjsu.umlgenerator.parser;

public class RelationshipInfo {
    private String type;
    private ClassInfo source;
    private ClassInfo destination;
    private String labelSource;
    private String labelDestination;
    private String labelRelationship;

    public RelationshipInfo(String type, ClassInfo source, ClassInfo destination, String labelSource,
	    String labelDestination, String labelRelationship) {
	super();
	this.type = type;
	this.source = source;
	this.destination = destination;
	this.labelSource = labelSource;
	this.labelDestination = labelDestination;
	this.labelRelationship = labelRelationship;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public ClassInfo getSource() {
	return source;
    }

    public void setSource(ClassInfo source) {
	this.source = source;
    }

    public ClassInfo getDestination() {
	return destination;
    }

    public void setDestination(ClassInfo destination) {
	this.destination = destination;
    }

    public String getLabelSource() {
	return labelSource;
    }

    public void setLabelSource(String labelSource) {
	this.labelSource = labelSource;
    }

    public String getLabelDestination() {
	return labelDestination;
    }

    public void setLabelDestination(String labelDestination) {
	this.labelDestination = labelDestination;
    }

    public String getLabelRelationship() {
	return labelRelationship;
    }

    public void setLabelRelationship(String labelRelationship) {
	this.labelRelationship = labelRelationship;
    }

}
