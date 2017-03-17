package com.sjsu.umlgenerator.parser.model;

import java.util.HashSet;
import java.util.Set;

import com.sjsu.umlgenerator.util.logger.Constants;

public class AccessSpecifierDisplayRules {
    private final static Set<String> methodRules = new HashSet<String>();
    private final static Set<String> attributeRules = new HashSet<String>();

    public AccessSpecifierDisplayRules() {

	methodRules.add(Constants.PUBLIC);
	attributeRules.add(Constants.PUBLIC);
	attributeRules.add(Constants.PRIVATE);
    }

    public static Set<String> getAttributeRules() {
	return attributeRules;
    }

    public static Set<String> getMethodRules() {
	return methodRules;
    }
}
