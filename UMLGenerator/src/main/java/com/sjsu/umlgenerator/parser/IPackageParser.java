package com.sjsu.umlgenerator.parser;

import com.sjsu.umlgenerator.parser.model.AppInfo;

public interface IPackageParser {
    public AppInfo buildAppInfo(String projectDir);

}
