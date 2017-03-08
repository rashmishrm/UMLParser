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
	System.out.println(c.getName());
	System.out.println(c.getExtendedTypes().size());

	if (arg instanceof ClassInfo) {
	    final ClassInfo classInfo = (ClassInfo) arg;
	    classInfo.setName(c.getNameAsString());

	    for (final ClassOrInterfaceType x : c.getExtendedTypes()) {
		final RelationshipInfo rInfo = new RelationshipInfo("extends", classInfo.getName(), x.getNameAsString(),
			null, null, "extends");

		classInfo.getRelationshipInfos().add(rInfo);
		appInfo.getRelationsList().add(rInfo);
		System.out.println("Ancestor" + x.getNameAsString());
	    }

	    for (final ClassOrInterfaceType x : c.getImplementedTypes()) {
		final RelationshipInfo rInfo = new RelationshipInfo("implements", classInfo.getName(),
			x.getNameAsString(), null, null, "implements");
		classInfo.getRelationshipInfos().add(rInfo);
		appInfo.getRelationsList().add(rInfo);

		System.out.println("Ancestor Interface" + x.getNameAsString());
	    }
	}
	super.visit(c, arg);
    }

    public void setAppInfo(AppInfo appInfo) {
	this.appInfo = appInfo;
    }

}