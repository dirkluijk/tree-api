package com.vijfhart.casus.tree.node;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public class SimpleNode extends AbstractNode<SimpleNode> {

    public SimpleNode() {
        super(null);
    }

    public SimpleNode(SimpleNode parent) {
        super(parent);
    }
}
