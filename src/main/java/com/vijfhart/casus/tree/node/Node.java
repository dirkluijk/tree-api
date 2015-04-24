package com.vijfhart.casus.tree.node;

/**
 * Represents a node with a parent relationship.
 *
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public interface Node<E extends Node<E>> {

    /**
     * Retreives the parent of this node.
     *
     * @return The parent node.
     */
    public E getParent();

    /**
     * Sets the parent of this node.
     *
     * @param node The parent, which must be a Node or subclass of Node.
     */
    public void setParent(E node);

    /**
     * Returns whether this node is a leaf (e.g. has no descendants).
     *
     * @return True if the node is leaf, false if not.
     */
    public boolean isLeaf();
}
