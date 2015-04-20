package com.vijfhart.casus.tree;

import java.util.Iterator;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public interface TreeIterator<E extends Node<E>> extends Iterator<E> {
    public int level();
    public void startWith(E node);
    public boolean isLeaf();
}
