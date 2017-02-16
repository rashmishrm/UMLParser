package com.sjsu.project.tryout;

import java.io.FileInputStream;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class Sample {
    public static void main(String[] args) throws Exception {
	// creates an input stream for the file to be parsed
	// final FileInputStream in = new FileInputStream(
	// "//Users//rashmisharma/Documents/Spring
	// 2017//cmpe202//cmpe202-master//umlparser//uml-parser-test-1//A.java");

	final FileInputStream in = new FileInputStream(
		"/Users/rashmisharma/Documents/workspace/UMLGenerator/src/main/java/com/sjsu/project/tryout/TestClass.java");

	// parse the file
	final CompilationUnit cu = JavaParser.parse(in);

	// prints the resulting compilation unit to default system output
	// System.out.println(cu.toString());
	new MethodVisitor().visit(cu, null);

	new VariableVisitor().visit(cu, null);

    }

    /**
     * Simple visitor implementation for visiting MethodDeclaration nodes.
     */
    private static class MethodVisitor extends VoidVisitorAdapter<Void> {
	@Override
	public void visit(MethodDeclaration n, Void arg) {
	    /*
	     * here you can access the attributes of the method. this method
	     * will be called for all methods in this CompilationUnit, including
	     * inner class methods
	     */
	    System.out.println(n.getName());
	    super.visit(n, arg);
	}
    }

    private static class VariableVisitor extends VoidVisitorAdapter<Void> {
	@Override
	public void visit(FieldDeclaration n, Void arg) {
	    System.out.println(n);
	    System.out.println(n.getVariables());
	    super.visit(n, arg);
	}
	//
	// @Override
	// public void visit(final VariableDeclarator n, Void arg) {
	//
	// }
    }



}
