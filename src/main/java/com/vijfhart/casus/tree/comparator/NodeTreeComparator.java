package com.vijfhart.casus.tree.comparator;

import com.vijfhart.casus.tree.node.Node;

import java.util.Comparator;

/**
 * Compares the relative position of two nodes in a tree-like structure.
 * Can be used to maintain the order of the nodes in a tree.
 *
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public class NodeTreeComparator<E extends Node<E>> implements Comparator<E> {

    /**
     * A comparator to compare the nodes levels.
     */
    private Comparator<E> levelComparator;

    /**
     * An optional comparator for siblings.
     */
    private Comparator<E> siblingsComparator;

    /**
     * Constructs a new NodeTreeComparator.
     */
    public NodeTreeComparator() {
        levelComparator = new NodeLevelComparator<>();
    }

    /**
     * Compares two nodes, based on their relative position.
     *
     * - When parent-child, levels are compared.
     * - When siblings, it compares using toString or uses the siblings comparator.
     * - When same level, compare parents.
     * - When different levels, use the parent of the node furtherst removed from the root.
     */
    @Override
    public int compare(E node1, E node2) {
        int levelDifference = levelComparator.compare(node1, node2);

        if (isDescendant(node1, node2) || isDescendant(node2, node1)) {
            return levelDifference;
        } else if (node1.getParent() == node2.getParent()) {
            if (siblingsComparator == null) {
                return node1.toString().compareToIgnoreCase(node2.toString());
            }
            return siblingsComparator.compare(node1, node2);
        } if (levelDifference == 0) {
            return compare(node1.getParent(), node2.getParent());
        } if (levelDifference < 0) {
            return compare(node1, node2.getParent());
        } else {
            return compare(node1.getParent(), node2);
        }
    }

    /**
     * Sets a new comparator to compare siblings.
     *
     * @param siblingsComparator The new comparator to use.
     */
    public void setSiblingsComparator(Comparator<E> siblingsComparator) {
        this.siblingsComparator = siblingsComparator;
    }

    private boolean isDescendant(E parentNode, E childNode) {
        E parent = childNode.getParent();

        while (parent != null) {
            if (parent == parentNode) {
                return true;
            }
            parent = parent.getParent();
        }

        return false;
    }
}
