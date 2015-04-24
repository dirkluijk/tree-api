package com.vijfhart.casus.tree.node;

/**
 * An interface to obtain doubles from nodes.
 *
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public interface NodeDouble<E extends Node<E>> {
    public double get(E node);
}
