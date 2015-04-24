package com.vijfhart.casus.tree.node;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public class NumberNode extends AbstractNode<NumberNode> {
    private int intValue;
    private double doubleValue;

    public NumberNode() {
        super();
    }

    public NumberNode(NumberNode parent) {
        super(parent);
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(double doubleValue) {
        this.doubleValue = doubleValue;
    }
}
