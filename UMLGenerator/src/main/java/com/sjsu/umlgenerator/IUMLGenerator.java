package com.sjsu.umlgenerator;

import com.sjsu.umlgenerator.parser.model.AppInfo;

public interface IUMLGenerator {

    public String generateClassDiagram(AppInfo appInfo, String fileName);

    public String generateSequenceDiagram(String intermediateText, String fileName);

    public String buildPlantUmlIntermediateText(AppInfo appInfo);
}
