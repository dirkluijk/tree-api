package com.vijfhart.casus.tree.node;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public class StringWrapperNode extends WrapperNode<StringWrapperNode, String> {

    public StringWrapperNode(String object) {
        super(object);
    }

    public StringWrapperNode(String object, StringWrapperNode parent) {
        super(object, parent);
    }
}
