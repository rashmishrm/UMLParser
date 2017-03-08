package com.sjsu.umlgenerator;

import com.sjsu.umlgenerator.parser.model.AppInfo;

public interface IUMLGenerator {

    public String generateClassDiagram(AppInfo appInfo);

    public String generateSequenceDiagram();
}
