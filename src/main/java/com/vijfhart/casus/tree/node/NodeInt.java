package com.vijfhart.casus.tree.node;

/**
 * An interface to obtain integers from nodes.
 *
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public interface NodeInt<E extends Node<E>> {
    public int get(E node);
}
