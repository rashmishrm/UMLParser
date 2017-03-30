package com.sjsu.umlgenerator;

import org.junit.Before;
import org.junit.Test;

import com.sjsu.umlgenerator.parser.IPackageParser;
import com.sjsu.umlgenerator.parser.PackageJavaParser;
import com.sjsu.umlgenerator.parser.model.AppInfo;

public class TestPlantUmlGenerator {

    private IUMLGenerator generator = null;
    private IPackageParser javaParser = null;

    @Before
    public void setup() {
	generator = new PlantUMLGenerator();
	javaParser = new PackageJavaParser();
    }

    @Test
    public void testCase1() {

	final AppInfo appInfo = javaParser.buildAppInfo(
		"/Users/rashmisharma/Documents/Spring_2017/cmpe202/personal-project/rashmi/UMLGenerator/src/test/resources/umlparser/uml-parser-test-1");
	final String outputText = generator.buildPlantUmlIntermediateText(appInfo);
	System.out.println(outputText);

    }

    @Test
    public void testCase2() {

	final AppInfo appInfo = javaParser.buildAppInfo(
		"/Users/rashmisharma/Documents/Spring_2017/cmpe202/personal-project/rashmi/UMLGenerator/src/test/resources/umlparser/uml-parser-test-2");
	final String outputText = generator.buildPlantUmlIntermediateText(appInfo);
	System.out.println(outputText);

    }

    @Test
    public void testCase3() {

	final AppInfo appInfo = javaParser.buildAppInfo(
		"/Users/rashmisharma/Documents/Spring_2017/cmpe202/personal-project/rashmi/UMLGenerator/src/test/resources/umlparser/uml-parser-test-3");
	final String outputText = generator.buildPlantUmlIntermediateText(appInfo);
	System.out.println(outputText);

    }
}
