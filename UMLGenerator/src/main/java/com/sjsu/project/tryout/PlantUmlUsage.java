package com.sjsu.project.tryout;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.sourceforge.plantuml.GeneratedImage;
import net.sourceforge.plantuml.SourceFileReader;

public class PlantUmlUsage {

    public static void main(String[] args) {
	final File inputFile = new File(
		"/Users/rashmisharma/Documents/Spring 2017/cmpe202/rashmi/UMLGenerator/src/main/resources/uml-test/test-case3.txt");

	SourceFileReader reader;
	try {
	    reader = new SourceFileReader(inputFile);
	    final List<GeneratedImage> list = reader.getGeneratedImages();
	    list.get(0).getPngFile();
	} catch (final IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }
}
