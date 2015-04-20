package com.vijfhart.casus.tree;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public class NameNode extends AbstractNode<NameNode> {

    private NameNode parent;
    private String name;

    public NameNode(String name) {
        this(name, null);
    }

    public NameNode(String name, NameNode parent) {
        super(parent);
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NameNode nameNode = (NameNode) o;

        if (!name.equals(nameNode.name)) return false;
        if (parent != null ? !parent.equals(nameNode.parent) : nameNode.parent != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = parent != null ? parent.hashCode() : 0;
        result = 31 * result + name.hashCode();
        return result;
    }
}
