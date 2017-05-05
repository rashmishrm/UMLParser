package com.sjsu.umlgenerator.parser.model;

import com.github.javaparser.ast.Node;

public class FileIterator {
    public interface NodeHandler {
	boolean handle(Node node);
    }

    private final NodeHandler nodeHandler;

    public FileIterator(NodeHandler nodeHandler) {
	this.nodeHandler = nodeHandler;
    }

    public void explore(Node node) {
	if (nodeHandler.handle(node)) {
	    for (final Node child : node.getChildNodes()) {
		explore(child);
	    }
	}
    }
}