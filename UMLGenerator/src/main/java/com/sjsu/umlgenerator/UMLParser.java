package com.sjsu.umlgenerator;

import java.io.File;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.sjsu.umlgenerator.parser.IPackageParser;
import com.sjsu.umlgenerator.parser.PackageJavaParser;
import com.sjsu.umlgenerator.parser.model.AppInfo;
import com.sjsu.umlgenerator.util.logger.ConsoleLogger;

public class UMLParser {

    private final IUMLGenerator generator = new PlantUMLGenerator();
    private final IPackageParser javaParser = new PackageJavaParser();

    public static void main(String[] args) {
	// create Options object
	final Options options = new Options();

	// add t option
	options.addOption("d", true, "directory containing java files to be parsed");
	options.addOption("f", true, "output file name");
	options.addOption("t", true, "which type of diagram? 1-> class, 2-> sequence");

	final CommandLineParser parser = new DefaultParser();

	try {
	    final CommandLine cmd = parser.parse(options, args);

	    if (cmd.hasOption("d") && cmd.hasOption("f")) {
		final String inputFolder = cmd.getOptionValue("d");
		final String outputFileName = cmd.getOptionValue("f");
		String type = cmd.getOptionValue("t");
		type = type == null ? "1" : type;
		final File directory = new File(inputFolder);
		if (directory.exists()) {
		    final UMLParser umlParser = new UMLParser();
		    if ("1".equals(type)) {
			umlParser.generateClassDiagram(inputFolder, outputFileName);

		    } else if ("2".equals(type)) {
			umlParser.generateSequenceDiagram(inputFolder, outputFileName);

		    }
		} else {
		    ConsoleLogger.printLog("Please provide valid folder.  " + inputFolder);
		}

	    } else {
		final HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("umlparser", options);
	    }

	} catch (final ParseException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    public String generateClassDiagram(String projectDir, String outputFileName) {
	String fileName = null;
	final AppInfo appInfo = javaParser.buildAppInfo(projectDir);
	if (null != appInfo) {
	    fileName = generator.generateClassDiagram(appInfo, outputFileName);
	}

	return fileName;
    }

    public String generateSequenceDiagram(String projectDir, String outputFileName) {
	final AspectRunner runner = new AspectRunner();

	final String output = runner.run(projectDir);
	final String fileName = generator.generateSequenceDiagram(output, outputFileName);

	return fileName;
    }
}
