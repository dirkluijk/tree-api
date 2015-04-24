package com.vijfhart.casus.tree.comparator;

import com.vijfhart.casus.tree.node.Node;

import java.util.Comparator;

/**
 * Compares the level of two nodes.
 *
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public class NodeLevelComparator<E extends Node<E>> implements Comparator<E> {

    /**
     * Compares two nodes by their relative levels.
     *
     * @param node1 First node to compare.
     * @param node2 Second node to compare.
     *
     * @return Returns -1 if node1 is closer to the root, 0 if the levels
     * are equal, 1 if node2 is closer to the root.
     */
    public int compare(E node1, E node2) {
        if (node1.getParent() == node2.getParent()) {
            return 0;
        } else if (node1.getParent() == null) {
            return -1;
        } else if(node2.getParent() == null) {
            return 1;
        } else {
            return compare(node1.getParent(), node2.getParent());
        }
    }
}
