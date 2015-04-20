package com.vijfhart.casus.tree;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public interface TreeIterable<E extends Node<E>> extends Iterable<E> {
    public TreeIterator<E> treeIterator();
}
