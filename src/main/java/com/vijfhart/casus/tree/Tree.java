package com.vijfhart.casus.tree;

/**
 * A tree represents a collection of node.
 *
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public interface Tree<E extends Node<E>> extends TreeIterable<E> {

    /**
     * Adds a node to the tree
     *
     * @param node The node to add
     */
    public void add(E node);
}
