package com.sjsu.umlgenerator.parser.visitor;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.sjsu.umlgenerator.parser.model.AppInfo;
import com.sjsu.umlgenerator.parser.model.ClassInfo;
import com.sjsu.umlgenerator.parser.model.MethodInfo;
import com.sjsu.umlgenerator.util.Constants;

public class ConstructorVisitor extends VoidVisitorAdapter<Object> {
    private final AppInfo appInfo;

    public ConstructorVisitor(AppInfo appInfo) {
	this.appInfo = appInfo;
    }

    @Override
    public void visit(ConstructorDeclaration n, Object arg) {
	if (arg instanceof ClassInfo) {
	    final ClassInfo classInfo = (ClassInfo) arg;

	    final String[] arguemnts = new String[n.getParameters().size()];
	    int i = 0;
	    for (final Parameter param : n.getParameters()) {

		arguemnts[i] = param.getNameAsString() + ":" + param.getType();
		i++;

	    }

	    String accessSpecifier = null;

	    for (final Modifier modifier : n.getModifiers()) {
		if (modifier.toString().equals(Constants.PROTECTED) || modifier.toString().equals(Constants.PUBLIC)
			|| modifier.toString().equals(Constants.PRIVATE)) {
		    accessSpecifier = modifier.toString();

		}
	    }

	    final MethodInfo constructor = new MethodInfo(accessSpecifier, null, n.getNameAsString(), arguemnts);
	    constructor.setConstructor(true);
	    classInfo.addMethodInfo(constructor);

	}
	super.visit(n, arg);

    }

}
