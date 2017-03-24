package com.sjsu.umlgenerator.parser.visitor;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.sjsu.umlgenerator.parser.model.AppInfo;
import com.sjsu.umlgenerator.parser.model.ClassInfo;
import com.sjsu.umlgenerator.parser.model.MethodInfo;
import com.sjsu.umlgenerator.parser.model.RelationshipInfo;
import com.sjsu.umlgenerator.util.Constants;

/**
 * Simple visitor implementation for visiting MethodDeclaration nodes.
 */
public class MethodVisitor extends VoidVisitorAdapter<Object> {
    private final AppInfo appInfo;

    public MethodVisitor(AppInfo appInfo) {
	this.appInfo = appInfo;
    }

    @Override
    public void visit(MethodDeclaration n, Object arg) {
	/*
	 * here you can access the attributes of the method. this method will be
	 * called for all methods in this CompilationUnit, including inner class
	 * methods
	 */
	if (arg instanceof ClassInfo) {

	    final ClassInfo classInfo = (ClassInfo) arg;
	    final String parameters[] = new String[n.getParameters().size()];
	    int i = 0;
	    for (final Parameter param : n.getParameters()) {
		parameters[i] = param.getType().toString();
		i++;
	    }
	    String accessSpecifier = null;

	    for (final Modifier modifier : n.getModifiers()) {
		if (modifier.toString().equals(Constants.PROTECTED) || modifier.toString().equals(Constants.PUBLIC)
			|| modifier.toString().equals(Constants.PRIVATE)) {
		    accessSpecifier = modifier.toString();

		}
	    }

	    for (final Node eachItem : n.getChildNodes()) {
		if (eachItem instanceof BlockStmt) {

		    final BlockStmt bstmt = (BlockStmt) eachItem;
		    bstmt.getStatements().stream().forEach(v -> {

			appInfo.getInterfaces().stream().forEach(a -> {

			    if (v.toString().contains(a)) {

				final RelationshipInfo info = new RelationshipInfo("uses", classInfo.getName(), a, "",
					"", "");
				appInfo.getRelationsList().add(info);
			    }
			});

		    });
		    break;

		}

	    }

	    final MethodInfo m = new MethodInfo(accessSpecifier, n.getType().toString(), n.getNameAsString(),
		    parameters);
	    classInfo.addMethodInfo(m);

	}

	super.visit(n, arg);
    }

    public void identifyRelations(MethodDeclaration n) {

    }
}