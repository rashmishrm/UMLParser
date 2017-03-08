package com.sjsu.umlgenerator.parser.visitor;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.sjsu.umlgenerator.parser.model.ClassInfo;
import com.sjsu.umlgenerator.parser.model.MethodInfo;

/**
 * Simple visitor implementation for visiting MethodDeclaration nodes.
 */
public class MethodVisitor extends VoidVisitorAdapter<Object> {
     @Override
     public void visit(MethodDeclaration n, Object arg) {
	 /*
	  * here you can access the attributes of the method. this method
	  * will be called for all methods in this CompilationUnit, including
	  * inner class methods
	  */
	 if (arg instanceof ClassInfo) {

	     final ClassInfo classInfo = (ClassInfo) arg;
	     final String parameters[] = new String[n.getParameters().size()];
	     int i = 0;
	     for (final Parameter param : n.getParameters()) {
		 parameters[i] = param.getType().toString();
		 i++;
	     }
	     String modfier = null;

	     for (final Modifier modifier : n.getModifiers()) {
		 modfier = modifier.toString();
	     }

	     final MethodInfo m = new MethodInfo(modfier, n.getType().toString(), n.getNameAsString(), parameters);
	     classInfo.getMethods().add(m);

	 }

	 super.visit(n, arg);
     }
 }