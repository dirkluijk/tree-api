package com.vijfhart.casus.tree.tree;

import com.vijfhart.casus.tree.node.NameNode;
import com.vijfhart.casus.tree.node.NodeDouble;
import com.vijfhart.casus.tree.node.NodeInt;
import com.vijfhart.casus.tree.node.NumberNode;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class NodeTreeTest {

    @Test
    public void testAdd() throws Exception {
        NodeTree<NameNode> tree = new NodeTree<>();

        NameNode node1 = new NameNode("A");
        NameNode node2 = new NameNode("B", node1);

        tree.add(node1);
        tree.add(node2);

        boolean hasNode1 = false;
        boolean hasNode2 = false;

        for (NameNode node : tree) {
            if (node == node1) {
                hasNode1 = true;
            }
            if (node == node2) {
                hasNode2 = true;
            }
        }

        assertTrue(hasNode1);
        assertTrue(hasNode2);
    }

    @Test
    public void testDescendantsOfWithCount() throws Exception {
        Tree<NameNode> tree = new NodeTree<>();

        NameNode node1 = new NameNode("A");
        NameNode node2 = new NameNode("B", node1);
        NameNode node3 = new NameNode("C", node2);
        NameNode node4 = new NameNode("D", node2);

        tree.add(node1);
        tree.add(node2);
        tree.add(node3);
        tree.add(node4);

        Tree<NameNode> descendantsTree = tree.descendantsOf(node2);

        boolean hasNode1 = false, hasNode2 = false, hasNode3 = false, hasNode4 = false;

        for (NameNode node : descendantsTree) {
            if (node == node1) {
                hasNode1 = true;
            }
            if (node == node2) {
                hasNode2 = true;
            }
            if (node == node3) {
                hasNode3 = true;
            }
            if (node == node4) {
                hasNode4 = true;
            }
        }

        assertFalse(hasNode1);
        assertFalse(hasNode2);
        assertTrue(hasNode3);
        assertTrue(hasNode4);

        assertEquals(3, tree.descendantsCount(node1));
        assertEquals(2, tree.descendantsCount(node2));
        assertEquals(0, tree.descendantsCount(node3));
    }

    @Test
    public void testStrangeNodeInTree() throws Exception {
        NameNode strangeNode = new NameNode("A");

        NodeTree<NameNode> tree = new NodeTree<>();

        try {
            tree.descendantsOf(strangeNode);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException ignored) { }

        try {
            tree.descendantsCount(strangeNode);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException ignored) { }

    }

    @Test
    public void testDescendentsSumAndAvg() throws Exception {

        NumberNode node1 = new NumberNode();
        NumberNode node2 = new NumberNode(node1);
        NumberNode node3a = new NumberNode(node2);
        NumberNode node3b = new NumberNode(node2);

        Tree<NumberNode> tree = new NodeTree<>();

        tree.add(node1);
        tree.add(node2);
        tree.add(node3a);
        tree.add(node3b);

        NodeDouble<NumberNode> nodeDouble = new NodeDouble<NumberNode>() {
            @Override
            public double get(NumberNode node) {
                return node.getDoubleValue();
            }
        };

        NodeInt<NumberNode> nodeInt = new NodeInt<NumberNode>() {
            @Override
            public int get(NumberNode node) {
                return node.getIntValue();
            }
        };

        assertEquals(0, tree.descendantsSum(node2, nodeInt));
        assertEquals(0.0, tree.descendantsSum(node2, nodeDouble), 0.001);
        assertEquals(0, tree.descendantsAvg(node2, nodeInt), 0.001);
        assertEquals(0.0, tree.descendantsAvg(node2, nodeDouble), 0.001);

        node3a.setIntValue(10);
        node3b.setIntValue(2);

        node3a.setDoubleValue(2.5);
        node3b.setDoubleValue(3.7);

        assertEquals(12, tree.descendantsSum(node2, nodeInt));
        assertEquals(6.2, tree.descendantsSum(node2, nodeDouble), 0.001);
        assertEquals(6.0, tree.descendantsAvg(node2, nodeInt), 0.001);
        assertEquals(3.1, tree.descendantsAvg(node2, nodeDouble), 0.001);
    }
}