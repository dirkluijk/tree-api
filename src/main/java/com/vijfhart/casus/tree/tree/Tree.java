package com.vijfhart.casus.tree.tree;

import com.vijfhart.casus.tree.iterator.TreeIterable;
import com.vijfhart.casus.tree.node.Node;
import com.vijfhart.casus.tree.node.NodeDouble;
import com.vijfhart.casus.tree.node.NodeInt;

import java.util.Comparator;

/**
 * A tree represents a collection of nodes which can be iterated.
 *
 * Besides that, the tree will be able to perform operations for
 * descendants of nodes.
 *
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public interface Tree<E extends Node<E>> extends TreeIterable<E> {

    /**
     * Adds a node to the tree.
     *
     * @param node The node to add.
     */
    public void add(E node);

    /**
     * Changes the way siblings are compared.
     *
     * @param comparator A comparator to compare siblings.
     */
    public void setSiblingsComparator(Comparator<E> comparator);

    /**
     * Returns a new tree, containing the descendants of the passed node.
     *
     * @param node The node which descendants will be used.
     * @return A tree containing descendant nodes.
     */
    public Tree<E> descendantsOf(E node);

    /**
     * Counts the number of descendants.
     *
     * @param node The root node which descendants will be counted.
     * @return An integer equals or larger then 0.
     */
    public int descendantsCount(E node);

    /**
     * Returns the sum of the integer values of the nodes descendants.
     *
     * @param node The root node which descendants will be summed.
     * @param nodeInt A NodeInt implementation to obtain the integer values.
     *
     * @return The sum of the obtained integer values.
     */
    public int descendantsSum(E node, NodeInt<E> nodeInt);

    /**
     * Returns the sum of the double values of the nodes descendants.
     *
     * @param node The root node which descendants will be summed.
     * @param nodeDouble A NodeDouble implementation to obtain the double values.
     *
     * @return The sum of the obtained double values.
     */
    public double descendantsSum(E node, NodeDouble<E> nodeDouble);

    /**
     * Returns the average of the integer values of the nodes descendants.
     *
     * @param node The root node which descendants will be used.
     * @param nodeInt A NodeInt implementation to obtain the integer values.
     *
     * @return The average of the obtained integer values.
     */
    public double descendantsAvg(E node, NodeInt<E> nodeInt);

    /**
     * Returns the average of the double values of the nodes descendants.
     *
     * @param node The root node which descendants will be used.
     * @param nodeDouble A NodeDouble implementation to obtain the double values.
     *
     * @return The average of the obtained double values.
     */
    public double descendantsAvg(E node, NodeDouble<E> nodeDouble);

    /**
     * Returns the size of the whole tree.
     *
     * @return An integer equals or larger than 0.
     */
    public int size();
}
