package com.sjsu.umlgenerator;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Runner {

    private String executeCommand(String command) {

	final StringBuffer output = new StringBuffer();

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

    public void run(String folder) {

	folder = "/Users/rashmisharma/Documents/Spring_2017/cmpe202/personal-project/rashmi/test-cases/umlparser/uml-sequence-test";
	final String command = "sh /Users/rashmisharma/Documents/Spring_2017/cmpe202/personal-project/rashmi/UMLGenerator/src/main/resources/aspectj/runner.sh";
	executeCommand(command);

    }

    public static void main(String[] args) {
	final Runner runner = new Runner();
	runner.run("");
    }

}
