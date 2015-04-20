package com.vijfhart.casus.tree;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public class NameNode implements Node<NameNode> {

    private NameNode parent;
    private String name;

    public NameNode(String name) {
        this(name, null);
    }

    public NameNode(String name, NameNode parent) {
        this.name = name;
        this.parent = parent;
    }

    @Override
    public NameNode getParent() {
        return parent;
    }

    @Override
    public void setParent(NameNode node) {
        this.parent = node;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public int compareLevelTo(NameNode node) {
        if (parent == null && node.getParent() == null) {
            // beide null, dus gelijk
            return 0;
        } else if (parent == null) {
            // this heeft lager level
            return -1;
        } else if(node.getParent() == null) {
            // node heeft geen parent, dus this is hoger
            return 1;
        } else if (parent == node.getParent()) {
            // gelijk
            return 0;
        } else {
            // vergelijk parents
            return parent.compareLevelTo(node.getParent());
        }
    }

    @Override
    public int compareTo(NameNode node) {
        return compareLevelTo(node);
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
