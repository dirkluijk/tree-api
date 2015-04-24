package com.vijfhart.casus.tree.node;

/**
 * An example implementation of a node.
 *
 * Once assigned to a parent, it will increase its number of descendants.
 *
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public abstract class AbstractNode<E extends AbstractNode<E>> implements Node<E> {

    /**
     * The parent of this node.
     */
    private E parent;

    /**
     * The number of descendants.
     */
    protected int descendantCount = 0;

    /**
     * Constructs a node without a parent.
     */
    public AbstractNode() {
        this(null);
    }

    /**
     * Constructs a node with a parent.
     *
     * @param parent The parent of the constructed node.
     */
    public AbstractNode(E parent) {
        this.setParent(parent);
    }

    @Override
    public void setParent(E parent) {
        if (parent == null) {
            return;
        }

        if (parent.equals(this)) {
           throw new RuntimeException("Unable to create node with same instance as parent.");
        } else if (isDescendant(parent)) {
            throw new RuntimeException("Unable to create node with a descendent as parent.");
        }

        if (this.parent != null) {
            this.parent.descendantCount--;
        }

        this.parent = parent;
        this.parent.descendantCount++;
    }

    @Override
    public E getParent() {
        return parent;
    }

    @Override
    public boolean isLeaf() {
        return descendantCount == 0;
    }

    private boolean isDescendant(E node) {
        Node parent = node.getParent();

        while (parent != null) {
            if (parent == this) {
                return true;
            }
            parent = parent.getParent();
        }

        return false;
    }
}
