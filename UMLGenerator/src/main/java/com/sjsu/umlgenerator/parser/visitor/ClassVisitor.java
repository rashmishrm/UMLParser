package com.sjsu.umlgenerator.parser.visitor;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.sjsu.umlgenerator.parser.model.AppInfo;
import com.sjsu.umlgenerator.parser.model.ClassInfo;
import com.sjsu.umlgenerator.parser.model.RelationshipInfo;

public class ClassVisitor extends VoidVisitorAdapter<Object> {

    private AppInfo appInfo;

    public ClassVisitor(AppInfo appInfo) {
	this.appInfo = appInfo;
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration c, Object arg) {
	/*
	 * here you can access the attributes of the method. this method will be
	 * called for all methods in this CompilationUnit, including inner class
	 * methods
	 */
	if (arg instanceof ClassInfo) {
	    final ClassInfo classInfo = (ClassInfo) arg;
	    classInfo.setName(c.getNameAsString());
	    classInfo.setType(getType(c));

	    if (c.isInterface()) {
		appInfo.getInterfaces().add(c.getNameAsString());
	    }

	    for (final ClassOrInterfaceType x : c.getExtendedTypes()) {
		final RelationshipInfo rInfo = new RelationshipInfo("extends", x.getNameAsString(), classInfo.getName(),
			"", "", "extends");

		classInfo.getRelationshipInfos().add(rInfo);
		appInfo.getRelationsList().add(rInfo);
	    }

	    for (final ClassOrInterfaceType x : c.getImplementedTypes()) {
		final RelationshipInfo rInfo = new RelationshipInfo("implements", classInfo.getName(),
			x.getNameAsString(), "", "", "implements");
		classInfo.getRelationshipInfos().add(rInfo);
		appInfo.getRelationsList().add(rInfo);

	    }
	}
	super.visit(c, arg);
    }

    public void setAppInfo(AppInfo appInfo) {
	this.appInfo = appInfo;
    }

    public String getType(ClassOrInterfaceDeclaration c) {
	String type = "class";
	if (c.isInterface()) {
	    type = "interface";
	} else if (c.isAbstract()) {
	    type = "abstract";
	}
	return type;
    }

}