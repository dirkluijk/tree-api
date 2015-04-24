package com.vijfhart.casus.tree.node;

/**
 * An interface to obtain strings from nodes.
 *
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public interface NodeString<E extends Node<E>> {
    public String get(E node);
}
