package com.vijfhart.casus.tree.comparator;

import com.vijfhart.casus.tree.node.NameNode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NodeLevelComparatorTest {
    private NodeLevelComparator<NameNode> comparator;

    private NameNode nodeA;
    private NameNode nodeB;
    private NameNode nodeC;
    private NameNode nodeD;
    private NameNode nodeE;
    private NameNode nodeF;
    private NameNode nodeG;
    private NameNode nodeH;
    private NameNode nodeI;

    @Before
    public void setUp() throws Exception {
        nodeA = new NameNode("A");

        nodeB = new NameNode("B", nodeA);
        nodeC = new NameNode("C", nodeA);

        nodeD = new NameNode("D", nodeB);
        nodeE = new NameNode("E", nodeB);
        nodeF = new NameNode("F", nodeC);

        nodeG = new NameNode("G", nodeD);
        nodeH = new NameNode("H", nodeD);
        nodeI = new NameNode("I", nodeE);

        /*
         *         A
         *        / \
         *       B   C
         *      / \   \
         *     D   E   F
         *    / \   \
         *   G  H   I
         */

        comparator = new NodeLevelComparator<>();
    }

    @Test
    public void testCompareLevel() throws Exception {

        // one is root
        assertLevelLarger(nodeB, nodeA);
        assertLevelSmaller(nodeA, nodeC);

        // same level (non-root)
        assertLevelsEqual(nodeB, nodeC);
        assertLevelsEqual(nodeF, nodeE);

        // different level (non-root, one level difference)
        assertLevelSmaller(nodeB, nodeD);
        assertLevelLarger(nodeI, nodeE);
        assertLevelLarger(nodeF, nodeB);

        // different level (multiple levels difference)
        assertLevelSmaller(nodeB, nodeH);
        assertLevelLarger(nodeI, nodeB);
    }

    private void assertLevelSmaller(NameNode node1, NameNode node2) {
        assertTrue(comparator.compare(node1, node2) < 0);
    }

    private void assertLevelLarger(NameNode node1, NameNode node2) {
        assertTrue(comparator.compare(node1, node2) > 0);
    }

    private void assertLevelsEqual(NameNode node1, NameNode node2) {
        assertTrue(comparator.compare(node1, node2) == 0);
    }
}