package com.vijfhart.casus.tree;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public abstract class AbstractNode<E extends AbstractNode<E>> implements Node<E> {

    private E parent;
    protected int childCount;

    public AbstractNode(E parent) {
        this.setParent(parent);
    }

    @Override
    public void setParent(E node) {
        if (isDescendant(parent)) {
            throw new RuntimeException("Unable to create node with a descendent as parent.");
        }

        node.childCount++;
        this.parent = node;
    }

    @Override
    public E getParent() {
        return parent;
    }

    @Override
    public int compareLevelTo(E node) {
        if (parent == node.getParent()) {
            // beide null of gelijk, dus gelijk
            return 0;
        } else if (parent == null) {
            // this heeft lager level
            return -1;
        } else if(node.getParent() == null) {
            // node heeft geen parent, dus this is hoger
            return 1;
        } else {
            // vergelijk parents
            return parent.compareLevelTo(node.getParent());
        }
    }

    @Override
    public int compareTo(E node) {
        return compareLevelTo(node);
    }

    @Override
    public boolean isLeaf() {
        return childCount == 0;
    }

    private boolean isDescendant(E node) {
        E parent = node.getParent();

        while (parent != null) {
            if (parent == this.parent) {
                return true;
            }
            parent = parent.getParent();
        }

        return false;
    }
}
