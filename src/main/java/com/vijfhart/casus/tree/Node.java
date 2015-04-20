package com.vijfhart.casus.tree;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public interface Node<E extends Node<E>> extends LevelComparable<E> {
    public E getParent();
    public void setParent(E node);
    public boolean isLeaf();
}
