package com.sjsu.umlgenerator.parser.visitor;

import java.util.List;

import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.sjsu.umlgenerator.parser.model.AppInfo;
import com.sjsu.umlgenerator.parser.model.ClassInfo;
import com.sjsu.umlgenerator.parser.model.MethodInfo;
import com.sjsu.umlgenerator.util.JavaParserUtil;

public class ConstructorVisitor extends VoidVisitorAdapter<Object> {
    private final AppInfo appInfo;

    public ConstructorVisitor(AppInfo appInfo) {
	this.appInfo = appInfo;
    }

    @Override
    public void visit(ConstructorDeclaration n, Object arg) {
	if (arg instanceof ClassInfo) {
	    final ClassInfo classInfo = (ClassInfo) arg;

	    final List<String> parameters = JavaParserUtil.getParameters(n.getParameters(), appInfo, classInfo);

	    final String accessSpecifier = JavaParserUtil.accessModifier(n.getModifiers());


	    final MethodInfo constructor = new MethodInfo(accessSpecifier, null, n.getNameAsString(), parameters);
	    constructor.setConstructor(true);
	    classInfo.addMethodInfo(constructor);

	}
	super.visit(n, arg);

    }

}
