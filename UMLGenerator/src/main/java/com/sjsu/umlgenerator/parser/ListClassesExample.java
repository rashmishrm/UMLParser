package com.sjsu.umlgenerator.parser;

import java.io.File;
import java.io.IOException;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ListClassesExample {

    public AppInfo appInfo = new AppInfo();

    public static void listClasses(File projectDir) {
	new DirExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
	    // System.out.println(path);
	    try {

		// parse the file
		final CompilationUnit cu = JavaParser.parse(file);


		// prints the resulting compilation unit to default system
		// output
		// System.out.println(cu.toString());
		final ClassInfo cInfo = new ClassInfo();

		new ClassVistor().visit(cu, cInfo);
		new MethodVisitor().visit(cu, cInfo);

		new VariableVisitor().visit(cu, cInfo);


		System.out.println(); // empty line
	    } catch (final IOException e) {
		new RuntimeException(e);
	    }
	}).explore(projectDir);
    }

    /**
     * Simple visitor implementation for visiting MethodDeclaration nodes.
     */
    private static class MethodVisitor extends VoidVisitorAdapter<Object> {
	@Override
	public void visit(MethodDeclaration n, Object arg) {
	    /*
	     * here you can access the attributes of the method. this method
	     * will be called for all methods in this CompilationUnit, including
	     * inner class methods
	     */
	    if (arg instanceof ClassInfo) {

		final ClassInfo classInfo=(ClassInfo)arg;
		final String parameters[] = new String[n.getParameters().size()];
		int i = 0;
		for (final Parameter param : n.getParameters()) {
		    parameters[i] = param.getType().toString();
		    i++;
		}
		String modfier=null;

		for( final Modifier modifier:  n.getModifiers()) {
		    modfier = modifier.toString();
		}

		final MethodInfo m = new MethodInfo(modfier, n.getType().toString(), n.getNameAsString(), parameters);
		classInfo.getMethods().add(m);

	    }

	    super.visit(n, arg);
	}
    }

    private static class ClassVistor extends VoidVisitorAdapter<Object> {
	@Override
	public void visit(ClassOrInterfaceDeclaration c, Object arg) {
	    /*
	     * here you can access the attributes of the method. this method
	     * will be called for all methods in this CompilationUnit, including
	     * inner class methods
	     */
	    System.out.println(c.getName());
	    System.out.println(c.getExtendedTypes().size());

	    if (arg instanceof ClassInfo) {
		final ClassInfo classInfo = (ClassInfo) arg;
		classInfo.setName(c.getNameAsString());


	    }

	    for (final ClassOrInterfaceType x : c.getExtendedTypes()) {

		System.out.println("Ancestor" + x.getNameAsString());
	    }

	    for (final ClassOrInterfaceType x : c.getImplementedTypes()) {

		System.out.println("Ancestor Interface" + x.getNameAsString());
	    }
	    super.visit(c, arg);
	}
    }

    private static class VariableVisitor extends VoidVisitorAdapter<Object> {
	@Override
	public void visit(FieldDeclaration n, Object arg) {
	    System.out.println(n);
	    // System.out.println(n.getVariables());

	    if (arg instanceof ClassInfo) {
		String modfier = null;
		final ClassInfo classInfo = (ClassInfo) arg;

		for (final Modifier modifier : n.getModifiers()) {
		    modfier = modifier.toString();
		}

		final AttributeInfo info = new AttributeInfo(modfier, n.getVariables().toString(),
			n.getElementType().toString());
		classInfo.getAttributeInfos().add(info);
	    }

	    super.visit(n, arg);
	}
	//
	// @Override
	// public void visit(final VariableDeclarator n, Void arg) {
	//
	// }
    }

    public static void main(String[] args) {
	final File projectDir = new File(
		"/Users/rashmisharma/Documents/Spring 2017/cmpe202/rashmi/UMLGenerator/src/main/resources/umlparser/uml-parser-test-3");
	listClasses(projectDir);
    }
}