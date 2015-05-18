package com.vijfhart.casus.tree.node;

/**
 * A Wrapper class to create a Tree-node out of any Comparable object.
 * A WrapperNode is a subclass of AbstractNode.
 * It keeps a reference to the contained object.
 *
 * @param <T> The newly created node type
 * @param <E> The type of the wrapped object
 *
 * @author Marko Draisma
 */
public abstract class WrapperNode<T extends WrapperNode<T, E>, E extends Comparable<E>> extends AbstractNode<T> {

    private E object;

    /**
     * Constructor to create the root node of a tree.
     *
     * @param object The wrapped object of this node.
     */
    public WrapperNode(E object) {
        this.object = object;
    }

    /**
     * Constructor to create a node as a child of the given parent node.
     *
     * @param object: the wrapped object of this node.
     * @param parent: the parent node.
     */
    public WrapperNode(E object, T parent) {
        this.object = object;
        setParent(parent);
    }

    /**
     * Retrieves the wrapped object.
     *
     * @return The wrapped object.
     */
    public E getObject() {
        return object;
    }

    /**
     * Overridden version of compareTo to implement Comparable.
     * This method is implemented in the same way as in AbstractNode,
     * with the following added behavior:
     * sibblings are ordered by using the compareTo method of the wrapped object.
     */
    @Override
    public int compareTo(T other) {
        if (this.getParent() == other.getParent()) {
            return object.compareTo(other.getObject());
        }

        return super.compareTo(other);
    }

    @Override
    public String toString() {
        return object.toString();
    }
}
