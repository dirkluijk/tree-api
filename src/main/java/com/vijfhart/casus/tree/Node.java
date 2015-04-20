package com.vijfhart.casus.tree;

/**
 * Represents a node in a tree with a parent relation
 *
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public interface Node<E extends Node<E>> extends LevelComparable<E> {

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
     * Checks whether this node is a leaf (e.g. has children).
     *
     * @return True if leaf, false if not.
     */
    public boolean isLeaf();
}
