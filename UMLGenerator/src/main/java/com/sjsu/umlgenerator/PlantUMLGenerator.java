package com.sjsu.umlgenerator;

import java.io.FileOutputStream;
import java.io.OutputStream;

import com.sjsu.umlgenerator.parser.model.AppInfo;
import com.sjsu.umlgenerator.parser.model.ClassInfo;

import net.sourceforge.plantuml.SourceStringReader;

public class PlantUMLGenerator implements IUMLGenerator {

    @Override
    public String generateClassDiagram(AppInfo appInfo) {
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
	    png = new FileOutputStream(appInfo.getDirectory() + ".png");
	    final SourceStringReader reader = new SourceStringReader(str.toString());
	    reader.generateImage(png);
	} catch (final Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return appInfo.getDirectory() + ".png";
    }

    @Override
    public String generateSequenceDiagram() {
	// TODO Auto-generated method stub
	return null;
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

	classInfo.getMethods().stream().forEach(

		v ->

		{
		    String argument = "";
		    for (final String argu : v.getArguemnts()) {
			argument.concat(argu + ",");
		    }
		    if (argument.length() > 1) {
			argument = argument.substring(0, argument.length() - 1);
		    }

		    buffer.append(getScopePlantUml(v.getScope())).append(v.getReturnType()).append(" ")
		    .append(v.getName()).append(" (" + argument + ")").append("\n");
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
		    if (v.getType().equals("contains")) {
			buffer.append(v.getSource() + "  \"" + v.getLabelSource() + "\" "
				+ getRelationSymbol(v.getType()) + "  \"" + v.getLabelDestination() + "\"  "
				+ v.getDestination() + " : " + v.getLabelRelationship() + "\n");
		    } else {
			buffer.append(v.getSource() + getRelationSymbol(v.getType()) + v.getDestination() + " : "
				+ v.getLabelRelationship() + "\n");
		    }
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

}
