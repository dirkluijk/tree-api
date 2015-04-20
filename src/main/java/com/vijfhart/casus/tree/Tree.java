package com.vijfhart.casus.tree;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public interface Tree<E extends Node<E>> extends TreeIterable<E> {
    public void add(E node);
}
