package com.sjsu.umlgenerator;

import org.junit.Before;
import org.junit.Test;

public class TestUmlParser {
    private UMLParser parser = null;

    @Before
    public void setup() {
	parser = new UMLParser();
    }

    @Test
    public void testCase1() {

	parser.generateClassDiagram(
		"/Users/rashmisharma/Documents/Spring_2017/cmpe202/personal-project/rashmi/UMLGenerator/src/test/resources/umlparser/uml-parser-test-1",
		"test-case-1");
    }

    @Test
    public void testCase2() {

	parser.generateClassDiagram(
		"/Users/rashmisharma/Documents/Spring_2017/cmpe202/personal-project/rashmi/UMLGenerator/src/test/resources/umlparser/uml-parser-test-2",
		"test-case-2");
    }

    @Test
    public void testCase3() {

	parser.generateClassDiagram(
		"/Users/rashmisharma/Documents/Spring_2017/cmpe202/personal-project/rashmi/UMLGenerator/src/test/resources/umlparser/uml-parser-test-3",
		"test-case-3");
    }

    @Test
    public void testCase4() {

	parser.generateClassDiagram(
		"/Users/rashmisharma/Documents/Spring_2017/cmpe202/personal-project/rashmi/UMLGenerator/src/test/resources/umlparser/uml-parser-test-4",
		"test-case-4");
    }

    @Test
    public void testCase5() {

	parser.generateClassDiagram(
		"/Users/rashmisharma/Documents/Spring_2017/cmpe202/personal-project/rashmi/UMLGenerator/src/test/resources/umlparser/uml-parser-test-5",
		"test-case-5");
    }

}
