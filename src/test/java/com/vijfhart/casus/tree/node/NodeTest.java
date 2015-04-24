package com.vijfhart.casus.tree.node;

import org.junit.Test;

import static org.junit.Assert.*;

public class NodeTest {

    @Test
    public void testParent() throws Exception {
        SimpleNode parent1 = new SimpleNode();
        SimpleNode parent2 = new SimpleNode();
        SimpleNode child = new SimpleNode(parent1);

        assertEquals(child.getParent(), parent1);

        child.setParent(parent2);
        assertEquals(child.getParent(), parent2);
    }

    @Test
    public void testInvalidParent() throws Exception {
        SimpleNode level1 = new SimpleNode();
        SimpleNode level2 = new SimpleNode(level1);
        SimpleNode level3 = new SimpleNode(level2);

        try {
            level2.setParent(level3);
            fail("Should throw exception while setting current child as parent");
        } catch (Exception ignored) { }

        try {
            level3.setParent(level3);
            fail("Should throw exception while setting same instance as parent");
        } catch (Exception ignored) { }
    }

    @Test
    public void testLeaf() throws Exception {
        SimpleNode parent1 = new SimpleNode();
        SimpleNode parent2 = new SimpleNode();
        SimpleNode node = new SimpleNode();

        assertTrue(parent1.isLeaf());
        assertTrue(parent2.isLeaf());
        assertTrue(node.isLeaf());

        node.setParent(parent1);

        assertFalse(parent1.isLeaf());
        assertTrue(parent2.isLeaf());
        assertTrue(node.isLeaf());

        node.setParent(parent2);

        assertTrue(parent1.isLeaf());
        assertFalse(parent2.isLeaf());
        assertTrue(node.isLeaf());
    }
}