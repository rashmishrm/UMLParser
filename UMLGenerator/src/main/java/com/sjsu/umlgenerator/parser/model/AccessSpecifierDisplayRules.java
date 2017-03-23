package com.sjsu.umlgenerator.parser.model;

import java.util.HashSet;
import java.util.Set;

import com.sjsu.umlgenerator.util.Constants;

public class AccessSpecifierDisplayRules {
    private  static Set<String> methodRules = null;
    private  static Set<String> attributeRules = null;

    static {
	methodRules = new HashSet<String>();
	attributeRules = new HashSet<String>();
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
