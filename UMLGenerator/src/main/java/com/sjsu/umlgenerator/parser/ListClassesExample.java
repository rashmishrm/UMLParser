package com.sjsu.umlgenerator.parser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashSet;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import net.sourceforge.plantuml.SourceStringReader;

public class ListClassesExample {

    public static AppInfo appInfo = new AppInfo();

    public static void listClasses(File projectDir) {

	final String[] name = projectDir.list();
	appInfo.setClasses(new HashSet<String>(Arrays.asList(name)));

	new DirExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
	    // System.out.println(path);
	    try {

		// parse the file
		final CompilationUnit cu = JavaParser.parse(file);

		// prints the resulting compilation unit to default system
		// output
		// System.out.println(cu.toString());
		final ClassInfo cInfo = new ClassInfo();

		appInfo.getClassInfoList().add(cInfo);

		new ClassVistor().visit(cu, cInfo);
		new MethodVisitor().visit(cu, cInfo);

		new VariableVisitor().visit(cu, cInfo);

		System.out.println(); // empty line
	    } catch (final IOException e) {
		new RuntimeException(e);
	    }
	}).explore(projectDir);

	final StringBuffer str = new StringBuffer("@startuml\n");
	appInfo.getClassInfoList().stream().forEach(v -> {
	    str.append(convertClasstoPlantUml(v).append("\n"));
	}

		);

	str.append(convertRelationtoPlantUML(appInfo));

	str.append("@enduml");

	System.out.println(str);
	OutputStream png;
	try {
	    png = new FileOutputStream(projectDir.getName() + ".png");
	    final SourceStringReader reader = new SourceStringReader(str.toString());
	    reader.generateImage(png);
	} catch (final Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    public static StringBuffer convertClasstoPlantUml(ClassInfo classInfo) {
	final StringBuffer buffer = new StringBuffer();
	buffer.append("class " + classInfo.getName() + "{\n");

	classInfo.getAttributeInfos().stream().forEach(

		v ->

		{
		    buffer.append(getScopePlantUml(v.getScope())).append(v.getType()).append(" ").append(v.getName())
		    .append("\n");
		}

		);

	buffer.append("}\n");

	return buffer;

    }

    public static StringBuffer convertRelationtoPlantUML(AppInfo appInfo) {
	final StringBuffer buffer = new StringBuffer();

	appInfo.getRelationsList().stream().forEach(

		v ->

		{
		    buffer.append(v.getSource() + "  \"" + v.getLabelSource() + "\" " + getRelationSymbol(v.getType())
		    + "  \"" + v.getLabelDestination() + "\"  " + v.getDestination() + " : "
		    + v.getLabelRelationship());
		}

		);

	buffer.append("\n");

	return buffer;
    }

    public static String getRelationSymbol(String relation) {

	String result = "~";

	if (relation != null) {
	    switch (relation) {
	    case "extends":
		result = "<|--";
		break;
	    case "contains":
		result = "*--";
		break;
	    case "implements":
		result = " . ";
		break;

	    }
	}

	return result;
    }
    public static String getScopePlantUml(String scope) {
	String result = "~";

	if (scope != null) {
	    switch (scope) {
	    case "PRIVATE":
		result = "-";
		break;
	    case "PUBLIC":
		result = "+";
		break;
	    case "PROTECTED":
		result = "#";
		break;

	    }
	}

	return result;
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

		for (final ClassOrInterfaceType x : c.getExtendedTypes()) {
		    final RelationshipInfo rInfo = new RelationshipInfo("extends", classInfo.getName(),
			    x.getNameAsString(), "1", "1", "extends");

		    classInfo.getRelationshipInfos().add(rInfo);
		    appInfo.getRelationsList().add(rInfo);
		    System.out.println("Ancestor" + x.getNameAsString());
		}

		for (final ClassOrInterfaceType x : c.getImplementedTypes()) {
		    final RelationshipInfo rInfo = new RelationshipInfo("implements", classInfo.getName(),
			    x.getNameAsString(), "1", "1", "implements");
		    classInfo.getRelationshipInfos().add(rInfo);
		    appInfo.getRelationsList().add(rInfo);

		    System.out.println("Ancestor Interface" + x.getNameAsString());
		}
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

		final AttributeInfo info = new AttributeInfo(modfier, n.getChildNodes().get(0).toString(),
			n.getElementType().toString());
		classInfo.getAttributeInfos().add(info);

		if (appInfo.getClasses().contains(n.getElementType() + ".java")) {
		    final RelationshipInfo rInfo = new RelationshipInfo("contains", classInfo.getName(),
			    n.getElementType().toString(), "1", "1", "contains");
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

    public static void main(String[] args) {
	final File projectDir = new File(
		"/Users/rashmisharma/Documents/Spring 2017/cmpe202/rashmi/UMLGenerator/src/main/resources/umlparser/uml-parser-test-3");
	listClasses(projectDir);
    }
}