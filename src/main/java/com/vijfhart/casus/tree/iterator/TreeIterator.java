package com.vijfhart.casus.tree.iterator;

import com.vijfhart.casus.tree.node.Node;
import com.vijfhart.casus.tree.node.NodeString;

import java.util.Iterator;

/**
 * Provides functionality to iterate over a {@link com.vijfhart.casus.tree.tree.Tree}.
 *
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public interface TreeIterator<E extends Node<E>> extends Iterator<E> {

    /**
     * Returns the level of the current node.
     *
     * @return The zero-based level of the current node.
     */
    public int level();

    /**
     * Change the pointer of the iterator to a specific node.
     *
     * @param node The node to start with.
     */
    public void startWith(E node);

    /**
     * Returns the path of the current node, based
     * on its toString and a '/' separator
     *
     * @return The path of the current node.
     */
    public String path();

    /**
     * Returns the path of the current node.
     *
     * @param nodeString An NodeString which can return the string representation of a node.
     * @param separator The separator to use in the path.
     *
     * @return The path of the current node.
     */
    public String path(NodeString<E> nodeString, String separator);
}
