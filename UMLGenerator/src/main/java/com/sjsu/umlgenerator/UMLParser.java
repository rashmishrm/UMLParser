package com.sjsu.umlgenerator;

import com.sjsu.umlgenerator.parser.IPackageParser;
import com.sjsu.umlgenerator.parser.PackageJavaParser;
import com.sjsu.umlgenerator.parser.model.AppInfo;

public class UMLParser {

    private final IUMLGenerator generator = new PlantUMLGenerator();
    private final IPackageParser javaParser = new PackageJavaParser();

    public static void main(String[] args) {

	final String folderPath = "/Users/rashmisharma/Documents/Spring 2017/cmpe202/personal-project/rashmi/UMLGenerator/src/main/resources/umlparser/uml-parser-test-4";

	final UMLParser umlParser = new UMLParser();
	umlParser.generateClassDiagram(folderPath);
    }

    public String generateClassDiagram(String projectDir) {

	final AppInfo appInfo = javaParser.buildAppInfo(projectDir);
	final String fileName = generator.generateClassDiagram(appInfo);

	return fileName;
    }

}
