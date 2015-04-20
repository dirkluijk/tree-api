package com.vijfhart.casus.tree;

import java.util.Iterator;

/**
 * Provides functionality to iterate over a {@link Tree}, a collection of nodes.
 *
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public interface TreeIterator<E extends Node<E>> extends Iterator<E> {

    /**
     * Returns the level of the current node
     *
     * @return The zero-based level of the current node
     */
    public int level();

    /**
     * Change the pointer of the iterator to a specific node
     *
     * @param node The node to start with.
     */
    public void startWith(E node);

    /**
     * Checks whether the current node is a leaf node.
     *
     * @return true of leaf, false if not.
     */
    public boolean isLeaf();
}
