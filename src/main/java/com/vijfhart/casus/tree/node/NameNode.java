package com.vijfhart.casus.tree.node;

/**
 * An example node with a name.
 *
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public class NameNode extends AbstractNode<NameNode> {

    /**
     * The name of the node.
     */
    private String name;

    /**
     * Constructs a node with a name.
     *
     * @param name The name of the constructed node.
     */
    public NameNode(String name) {
        this(name, null);
    }

    /**
     * Constructs a node with a name and a parent.
     *
     * @param name The name of the constructed node.
     * @param parent The parent of the constructed node.
     */
    public NameNode(String name, NameNode parent) {
        super(parent);
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
