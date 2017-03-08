package com.sjsu.umlgenerator.parser;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.sjsu.umlgenerator.parser.model.AppInfo;
import com.sjsu.umlgenerator.parser.model.ClassInfo;
import com.sjsu.umlgenerator.parser.model.DirExplorer;
import com.sjsu.umlgenerator.parser.visitor.ClassVisitor;
import com.sjsu.umlgenerator.parser.visitor.MethodVisitor;
import com.sjsu.umlgenerator.parser.visitor.VariableVisitor;

public class PackageJavaParser implements IPackageParser {

    @Override
    public AppInfo buildAppInfo(String projectDir) {
	final AppInfo appInfo = new AppInfo();

	final File directory = new File(projectDir);
	final String[] name = directory.list();
	appInfo.setClasses(new HashSet<String>(Arrays.asList(name)));

	new DirExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
	    try {
		final CompilationUnit cu = JavaParser.parse(file);
		final ClassInfo cInfo = new ClassInfo();

		appInfo.getClassInfoList().add(cInfo);

		new ClassVisitor(appInfo).visit(cu, cInfo);
		new MethodVisitor().visit(cu, cInfo);

		new VariableVisitor(appInfo).visit(cu, cInfo);

		System.out.println(); // empty line
	    } catch (final IOException e) {
		new RuntimeException(e);
	    }
	}).explore(directory);

	return appInfo;
    }
}
