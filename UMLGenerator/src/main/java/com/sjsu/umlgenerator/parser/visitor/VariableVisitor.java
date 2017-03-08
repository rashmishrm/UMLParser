package com.sjsu.umlgenerator.parser.visitor;

import java.util.Collection;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.sjsu.umlgenerator.parser.model.AppInfo;
import com.sjsu.umlgenerator.parser.model.AttributeInfo;
import com.sjsu.umlgenerator.parser.model.ClassInfo;
import com.sjsu.umlgenerator.parser.model.RelationshipInfo;

public class VariableVisitor extends VoidVisitorAdapter<Object> {

    private final AppInfo appInfo;

    public VariableVisitor(AppInfo appInfo) {
	this.appInfo = appInfo;
    }
    @Override
    public void visit(FieldDeclaration n, Object arg) {
	System.out.println(n);
	// System.out.println(n.getVariables());

	final Node childNode = n.getVariable(0);
	String variableName = null;
	String type = null;
	boolean isCollection = false;

	for (final Node eachItem : childNode.getChildNodes()) {
	    if (eachItem instanceof SimpleName) {
		variableName = eachItem.toString();

	    } else if (eachItem instanceof ClassOrInterfaceType) {
		type = eachItem.toString();

		final ClassOrInterfaceType cType = (ClassOrInterfaceType) eachItem;

		if (type.contains("<")) {
		    final String array[] = type.split("<");
		    type = array[0];
		    final String genericType = array[1].substring(0, array[1].length() - 1);

		}

		try {
		    if (Class.forName("java.util." + type).isAssignableFrom(Collection.class)) {
			isCollection = true;
			System.out.println(isCollection);
		    }
		} catch (final ClassNotFoundException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}

	    } else if (eachItem instanceof SimpleName) {

	    }

	}

	if (arg instanceof ClassInfo) {
	    String modfier = null;
	    final ClassInfo classInfo = (ClassInfo) arg;

	    for (final Modifier modifier : n.getModifiers()) {
		modfier = modifier.toString();
	    }

	    final AttributeInfo info = new AttributeInfo(modfier, n.getChildNodes().get(0).toString(),
		    n.getElementType().toString());
	    classInfo.getAttributeInfos().add(info);

	    if (appInfo.getClasses().contains(type + ".java")) {
		final String cardinality = isCollection ? "many" : "1";
		final RelationshipInfo rInfo = new RelationshipInfo("contains", classInfo.getName(), variableName, "1",
			cardinality, "contains");
		classInfo.getRelationshipInfos().add(rInfo);
		appInfo.getRelationsList().add(rInfo);

	    }
	}

	super.visit(n, arg);
    }
    //
    // @Override
    // public void visit(final VariableDeclarator n, Void arg) {
    //
    // }
}
