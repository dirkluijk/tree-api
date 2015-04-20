package com.vijfhart.casus.tree;

/**
 * Objects implementing this interface will be able to compare with the level
 * of another object
 *
 * @param <E> A subclass of Node
 *
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public interface LevelComparable<E extends Node<E>> extends Comparable<E> {

    /**
     * Compares the level of a node to the level of another node
     *
     * @param node An object extending Node to compare with
     * @return A negative integer, zero, or a positive integer as this object
     *          is less than, equal to, or greater than the specified object.
     */
    public int compareLevelTo(E node);
}
