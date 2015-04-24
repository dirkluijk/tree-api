package com.vijfhart.casus.tree.iterator;

import com.vijfhart.casus.tree.node.NameNode;
import com.vijfhart.casus.tree.node.NodeString;
import com.vijfhart.casus.tree.tree.NodeTree;
import com.vijfhart.casus.tree.tree.Tree;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TreeIteratorTest {

    private TreeIterator<NameNode> iterator;

    private NameNode nodeA;
    private NameNode nodeB;
    private NameNode nodeC;
    private NameNode nodeD;
    private NameNode nodeE;

    @Before
    public void setUp() throws Exception {
        Tree<NameNode> tree = new NodeTree<>();

        nodeA = new NameNode("A");
        nodeB = new NameNode("B", nodeA);
        nodeC = new NameNode("C", nodeA);
        nodeD = new NameNode("D", nodeB);
        nodeE = new NameNode("E", nodeB);

        tree.add(nodeA);
        tree.add(nodeB);
        tree.add(nodeC);
        tree.add(nodeD);
        tree.add(nodeE);

        iterator = tree.iterator();
    }

    @Test
    public void testLevelAndPath() throws Exception {

        boolean hasA = false,
                hasB = false,
                hasC = false,
                hasD = false,
                hasE = false;

        int count = 0;

        NodeString<NameNode> lowerName = new NodeString<NameNode>() {
            @Override
            public String get(NameNode node) {
                return node.toString().toLowerCase();
            }
        };

        while (iterator.hasNext()) {
            // test level
            NameNode current = iterator.next();
            count++;

            if (current == nodeA) {
                hasA = true;
                assertEquals(0, iterator.level());
                assertEquals("/A", iterator.path());
                assertEquals("|a", iterator.path(lowerName, "|"));
            } else if (current == nodeB) {
                hasB = true;
                assertEquals(1, iterator.level());
                assertEquals("/A/B", iterator.path());
                assertEquals("|a|b", iterator.path(lowerName, "|"));
            } else if (current == nodeC) {
                hasC = true;
                assertEquals(1, iterator.level());
                assertEquals("/A/C", iterator.path());
                assertEquals("|a|c", iterator.path(lowerName, "|"));
            } else if (current == nodeD) {
                hasD = true;
                assertEquals(2, iterator.level());
                assertEquals("/A/B/D", iterator.path());
                assertEquals("|a|b|d", iterator.path(lowerName, "|"));
            } else if (current == nodeE) {
                hasE = true;
                assertEquals(2, iterator.level());
                assertEquals("/A/B/E", iterator.path());
                assertEquals("|a|b|e", iterator.path(lowerName, "|"));
            }
        }

        assertTrue(hasA);
        assertTrue(hasB);
        assertTrue(hasC);
        assertTrue(hasD);
        assertTrue(hasE);

        assertEquals(5, count);
    }

    @Test(expected = RuntimeException.class)
    public void testStartWithAndException() throws Exception {
        iterator.next();
        iterator.startWith(nodeB);
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveWithException() throws Exception {
        iterator.remove();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRemoveNonLeaf() throws Exception {
        iterator.next();
        iterator.remove();
    }

    @Test
    public void testValidRemove() throws Exception {
        while (iterator.hasNext()) {
            iterator.next();
        }

        // last one is definitely a leaf
        iterator.remove();
    }
}