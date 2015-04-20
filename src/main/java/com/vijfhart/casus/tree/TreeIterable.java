package com.vijfhart.casus.tree;

/**
 * An object implementing TreeIterable will be able to provide a TreeIterator
 *
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public interface TreeIterable<E extends Node<E>> extends Iterable<E> {

    /**
     * Will return a TreeIterator
     *
     * @return TreeIterator
     */
    public TreeIterator<E> treeIterator();
}
