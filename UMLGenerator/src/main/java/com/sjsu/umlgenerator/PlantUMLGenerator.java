package com.sjsu.umlgenerator;

import java.io.FileOutputStream;
import java.io.OutputStream;

import com.sjsu.umlgenerator.parser.model.AppInfo;
import com.sjsu.umlgenerator.parser.model.ClassInfo;
import com.sjsu.umlgenerator.util.logger.ConsoleLogger;

import net.sourceforge.plantuml.SourceStringReader;

public class PlantUMLGenerator implements IUMLGenerator {

    @Override
    public String generateClassDiagram(AppInfo appInfo, String fileName) {
	final String intermediateText = buildPlantUmlIntermediateText(appInfo);
	ConsoleLogger.printLog("Intermediate Text:" + intermediateText);
	OutputStream png;
	try {
	    png = new FileOutputStream(fileName + ".png");
	    final SourceStringReader reader = new SourceStringReader(intermediateText);
	    reader.generateImage(png);
	} catch (final Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return fileName + ".png";
    }

    @Override
    public String generateSequenceDiagram() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String buildPlantUmlIntermediateText(AppInfo appInfo) {
	final StringBuffer str = new StringBuffer("@startuml\n");
	appInfo.getClassInfoList().stream().forEach(v -> {
	    str.append(convertClasstoPlantUml(v).append("\n"));

	});
	str.append(convertRelationtoPlantUML(appInfo));
	str.append("@enduml");
	return str.toString();
    }

    public static StringBuffer convertClasstoPlantUml(ClassInfo classInfo) {
	final StringBuffer buffer = new StringBuffer();
	buffer.append(classInfo.getType() + " " + classInfo.getName() + "{\n");

	classInfo.getAttributeInfos().stream().forEach(v ->

	{
	    if (v.isCollection()) {

		buffer.append(getScopePlantUml(v.getScope()) + " " + getScopePlantUml(v.getScope())).append(v.getName())
		.append(" : ").append(v.getType() + "[" + v.getCollectionLabel() + "]").append("\n");
	    } else {
		buffer.append(getScopePlantUml(v.getScope()) + " " + getScopePlantUml(v.getScope())).append(v.getName())
		.append(" : ").append(v.getType()).append("\n");

	    }
	}

		);

	classInfo.getMethods().stream().forEach(

		v ->

		{
		    String argument = "";
		    for (final String argu : v.getArguemnts()) {
			argument += argu + ",";
		    }
		    System.out.println("first" + argument);

		    if (argument.length() > 1) {
			argument = argument.substring(0, argument.length() - 1);
		    }
		    System.out.println("secomd" + argument);
		    final String plantUMLScope = getScopePlantUml(v.getScope());
		    if (v.isConstructor()) {
			buffer.append(plantUMLScope + " " + plantUMLScope).append(v.getName())
			.append("(" + argument + ") : ").append(" ").append(" ").append("\n");

		    } else {
			buffer.append(plantUMLScope + " " + plantUMLScope).append(v.getName())
			.append("(" + argument + ") : ").append(v.getReturnType()).append(" ").append("\n");
		    }
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
		    if (v.getType().equals("association")) {
			buffer.append(
				v.getSource() + "  \"" + v.getLabelSource() + "\" " + getRelationSymbol(v.getType())
				+ "  \"" + v.getLabelDestination() + "\"  " + v.getDestination() + "\n");
		    } else {
			buffer.append(v.getSource() + getRelationSymbol(v.getType()) + v.getDestination() + "\n");
		    }
		}

		);

	buffer.append("\n");

	return buffer;
    }

    public static String getRelationSymbol(String relation) {

	String result = ".";

	if (relation != null) {
	    switch (relation) {
	    case "extends":
		result = "<|--";
		break;
	    case "contains":
		result = "*--";
		break;
	    case "implements":
		result = " ..|>";
		break;
	    case "uses":
		result = "..>";
		break;
	    case "association":
		result = "--";
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

}
