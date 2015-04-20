package com.vijfhart.casus.tree;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public interface LevelComparable<E extends Node<E>> extends Comparable<E> {
    public int compareLevelTo(E node);
}
