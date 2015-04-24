package com.vijfhart.casus.tree.iterator;

import com.vijfhart.casus.tree.node.Node;

/**
 * This interface indicates that it provides a TreeIterator.
 *
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public interface TreeIterable<E extends Node<E>> extends Iterable<E> {

    /**
     * Creates a new TreeIterator.
     *
     * @return TreeIterator
     */
    public TreeIterator<E> iterator();
}
