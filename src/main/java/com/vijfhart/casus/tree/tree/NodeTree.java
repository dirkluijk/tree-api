package com.vijfhart.casus.tree.tree;

import com.vijfhart.casus.tree.comparator.NodeTreeComparator;
import com.vijfhart.casus.tree.iterator.TreeIterator;
import com.vijfhart.casus.tree.node.Node;
import com.vijfhart.casus.tree.node.NodeDouble;
import com.vijfhart.casus.tree.node.NodeInt;
import com.vijfhart.casus.tree.node.NodeString;

import java.util.*;

/**
 * An implementation of a tree.
 *
 * Nodes are stored in internal list, which will be sorted
 * using a NodeTreeComparator when requesting an iterator.
 *
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public class NodeTree<E extends Node<E>> implements Tree<E> {

    /**
     * The internal list in which the nodes are stored.
     */
    private List<E> nodeList;

    /**
     * A comparator to maintain the tree-like ordering of nodes.
     */
    private NodeTreeComparator<E> nodeTreeComparator;

    /**
     * Constructs an empty tree.
     */
    public NodeTree() {
        this(new ArrayList<E>());
    }

    /**
     * Constructs a tree, filled with the passed nodes.
     *
     * @param nodes A list of nodes which will be stored in the tree.
     */
    public NodeTree(List<E> nodes) {
        this.nodeList = new ArrayList<>(nodes);
        this.nodeTreeComparator = new NodeTreeComparator<>();
    }

    @Override
    public void add(E node) {
        nodeList.add(node);
    }

    @Override
    public Tree<E> descendantsOf(E node) {
        if (!nodeList.contains(node)) {
            throw new IllegalArgumentException("Node does not exist in this tree.");
        }

        List<E> newList = new ArrayList<>();

        TreeIterator<E> iterator = this.iterator();
        iterator.startWith(node);

        while (iterator.hasNext()) {
            newList.add(iterator.next());
        }

        return new NodeTree<>(newList);
    }

    @Override
    public int descendantsCount(E node) {
        return descendantsOf(node).size();
    }

    @Override
    public int descendantsSum(E node, NodeInt<E> nodeInt) {
        int sum = 0;

        Tree<E> descendants = descendantsOf(node);

        for (E d : descendants) {
            sum += nodeInt.get(d);
        }

        return sum;
    }

    @Override
    public double descendantsSum(E node, NodeDouble<E> nodeDouble) {
        double sum = 0;

        Tree<E> descendants = descendantsOf(node);

        for (E d : descendants) {
            sum += nodeDouble.get(d);
        }

        return sum;
    }

    @Override
    public double descendantsAvg(E node, NodeInt<E> nodeInt) {
        return descendantsSum(node, nodeInt) / descendantsCount(node);
    }

    @Override
    public double descendantsAvg(E node, NodeDouble<E> nodeDouble) {
        return descendantsSum(node, nodeDouble) / descendantsCount(node);
    }

    @Override
    public int size() {
        return nodeList.size();
    }

    @Override
    public TreeIterator<E> iterator() {
        Collections.sort(nodeList, nodeTreeComparator);
        return new NodeTreeIterator();
    }

    @Override
    public void setSiblingsComparator(Comparator<E> comparator) {
        nodeTreeComparator.setSiblingsComparator(comparator);
    }

    /**
     * An implementation of TreeIterator, which wraps arounds an internal iterator.
     *
     * @author Dirk Luijk <dirk.luijk@ordina.nl>
     */
    private class NodeTreeIterator implements TreeIterator<E> {

        /**
         * An iterator which will be used.
         */
        private Iterator<E> iterator;

        /**
         * The current node.
         */
        private E current;

        /**
         * The node to start the iteration with.
         */
        private E startNode;

        /**
         * Constructs a new TreeIterator for a NodeTree.
         */
        public NodeTreeIterator() {
            iterator = nodeList.iterator();
        }

        @Override
        public int level() {
            E compareWith = startNode;
            E parent = current.getParent();

            if (parent == compareWith) {
                return 0;
            }

            int level = 0;
            while (parent != compareWith) {
                level++;
                parent = parent.getParent();
            }

            return level;
        }

        @Override
        public void startWith(E node) {
            if (current != null) {
                // next has already been called!
                throw new RuntimeException(
                        "Method startWith cannot be used, because next() has already been called."
                );
            }

            startNode = node;

            // maak nieuwe lijst vanaf meegegeven node
            List<E> list = new ArrayList<>();

            while (iterator.hasNext()) {
                E next = iterator.next();
                if (descendantOfStartWith(next)) {
                    list.add(next);
                }
            }

            iterator = list.iterator();
        }

        @Override
        public String path() {
            return path(null, "/");
        }

        @Override
        public String path(NodeString<E> nodeString, String separator) {
            StringBuilder path = new StringBuilder();

            E current = this.current;

            while (current != null) {
                if (nodeString == null) {
                    path.insert(0, current.toString());
                } else {
                    path.insert(0, nodeString.get(current));
                }
                path.insert(0, separator);
                current = current.getParent();
            }

            return path.toString();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public E next() {
            current = iterator.next();
            return current;
        }

        @Override
        public void remove() {
            if (current == null) {
                throw new IllegalStateException(
                        "You must call next() first before you can remove a node."
                );
            }

            if (!current.isLeaf()) {
                throw new UnsupportedOperationException(
                        "Current object is not a leaf, thus cannot be removed."
                );
            }

            iterator.remove();
        }

        /**
         * Checks whether passed node is a descendent of our startNode
         *
         * @param node The node which parents will be checked
         * @return True if descendant, false otherwise.
         */
        private boolean descendantOfStartWith(E node) {
            E parent = node.getParent();

            while (parent != null) {
                if (parent == startNode) {
                    return true;
                }

                parent = parent.getParent();
            }

            return false;
        }
    }
}
