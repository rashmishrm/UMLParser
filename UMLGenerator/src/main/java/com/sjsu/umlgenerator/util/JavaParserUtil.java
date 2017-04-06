package com.sjsu.umlgenerator.util;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.Parameter;
import com.sjsu.umlgenerator.parser.model.AppInfo;
import com.sjsu.umlgenerator.parser.model.ClassInfo;
import com.sjsu.umlgenerator.parser.model.RelationshipInfo;

public class JavaParserUtil {

    public static List<String> getParameters(NodeList<Parameter> parameters, AppInfo appInfo, ClassInfo classInfo) {

	final List<String> parametersList = new ArrayList<String>();

	for (final Parameter param : parameters) {

	    if (!Constants.INTERFACE.equals(classInfo.getType())
		    && appInfo.getInterfaces().contains(param.getType().toString())) {
		final RelationshipInfo info = new RelationshipInfo("uses", classInfo.getName(),
			param.getType().toString(), "", "", "");
		appInfo.getRelationsList().add(info);
	    }

	    parametersList.add(param.getNameAsString() + ":" + param.getType().toString());
	    System.out.println(parameters);
	}

	return parametersList;
    }

    public static String accessModifier(EnumSet<Modifier> modfiers) {
	String accessSpecifier = null;
	for (final Modifier modifier : modfiers) {
	    if (modifier.toString().equals(Constants.PROTECTED) || modifier.toString().equals(Constants.PUBLIC)
		    || modifier.toString().equals(Constants.PRIVATE)) {
		accessSpecifier = modifier.toString();

	    }
	}

	return accessSpecifier;
    }

    public static boolean isStatic(EnumSet<Modifier> modfiers) {
	boolean isStatic = false;
	for (final Modifier modifier : modfiers) {
	    if (modifier.toString().equals(Constants.STATIC)) {
		isStatic = true;
		break;
	    }
	}

	return isStatic;
    }

}
