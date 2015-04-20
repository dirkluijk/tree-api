package com.vijfhart.casus.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public class NodeTree<E extends Node<E>> implements Tree<E> {
    private List<E> nodeList = new ArrayList<>();

    @Override
    public void add(E node) {
        nodeList.add(node);
    }

    @Override
    public TreeIterator<E> treeIterator() {

        return new TreeIterator<E>() {
            private Iterator<E> iterator;
            private E current;
            private E startNode;

            {
                Collections.sort(nodeList);
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

            /**
             * Checks whether passed node is a descendent of our startNode
             *
             * @param node The node which parents will be checked
             * @return True if descendant, false otherwise.
             */
            private boolean descendantOfStartWith(E node) {
                E parent = node.getParent();

                if (parent == null) {
                    return false;
                }

                while (parent != null) {
                    if (parent == startNode) {
                        return true;
                    }

                    parent = parent.getParent();
                }

                return false;
            }

            @Override
            public boolean isLeaf() {
                // todo
                return false;
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
                    throw new UnsupportedOperationException("Current object is not a leaf, thus cannot be removed.");
                }

                iterator.remove();
            }
        };
    }

    @Override
    public Iterator<E> iterator() {
        return nodeList.iterator();
    }
}
