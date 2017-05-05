package com.sjsu.umlgenerator.parser;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.sjsu.umlgenerator.parser.model.AppInfo;
import com.sjsu.umlgenerator.parser.model.ClassInfo;
import com.sjsu.umlgenerator.parser.model.DirectoryIterator;
import com.sjsu.umlgenerator.parser.visitor.ClassVisitor;
import com.sjsu.umlgenerator.parser.visitor.ConstructorVisitor;
import com.sjsu.umlgenerator.parser.visitor.MethodVisitor;
import com.sjsu.umlgenerator.parser.visitor.VariableVisitor;

public class PackageJavaParser implements IPackageParser {

    @Override
    public AppInfo buildAppInfo(String projectDir) {


	final File directory = new File(projectDir);
	if (directory.exists()) {
	    final AppInfo appInfo = new AppInfo();
	    final String[] name = directory.list();
	    final Set<String> files = new HashSet<String>(Arrays.asList(name));
	    files.stream().forEach(v -> {
		if (v.contains(".java")) {
		    appInfo.getClasses().add(v.replaceAll(".java", ""));
		}

	    });

	    appInfo.setDirectory(directory.getName());

	    new DirectoryIterator((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
		try {
		    final CompilationUnit cu = JavaParser.parse(file);
		    final ClassInfo cInfo = new ClassInfo();

		    appInfo.getClassInfoList().add(cInfo);

		    new ClassVisitor(appInfo).visit(cu, cInfo);
		    new MethodVisitor(appInfo).visit(cu, cInfo);

		    new VariableVisitor(appInfo).visit(cu, cInfo);
		    new ConstructorVisitor(appInfo).visit(cu, cInfo);

		} catch (final IOException e) {
		    new RuntimeException(e);
		}
	    }).explore(directory);
	    return appInfo;

	}
	return null;
    }
}
