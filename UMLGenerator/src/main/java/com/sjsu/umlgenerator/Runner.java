package com.sjsu.umlgenerator;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Runner {

    private String executeCommand(String command) {

	final StringBuffer output = new StringBuffer();
	System.out.println(command);
	Process p;
	try {
	    p = Runtime.getRuntime().exec(command);
	    p.waitFor();
	    final BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

	    String line = "";
	    while ((line = reader.readLine()) != null) {
		output.append(line + "\n");
	    }

	} catch (final Exception e) {
	    e.printStackTrace();
	}

	return output.toString();

    }

    public String run(String folder) {
	final String file = new File(".").getAbsolutePath();
	final ClassLoader classLoader = getClass().getClassLoader();
	final File fileRunner = new File(classLoader.getResource("aspectj/runner.sh").getFile());
	final File aspectLib = new File(classLoader.getResource("aspectj/lib").getFile());
	final File interceptorPath = new File(
		classLoader.getResource("com/sjsu/umlgenerator/aspect").getFile());

	final String command = "sh  " + fileRunner.getAbsolutePath() + " " + folder
		+ " " + aspectLib + " " + interceptorPath + " MethodInterceptor.aj";
	return executeCommand(command);

    }

    public static void main(String[] args) {
	final Runner runner = new Runner();
	final String folder = "/Users/rashmisharma/Documents/Spring_2017/cmpe202/personal-project/rashmi/UMLGenerator/src/main/resources/umlparser/uml-sequence-test";

	System.out.println(runner.run(folder));
    }

}
