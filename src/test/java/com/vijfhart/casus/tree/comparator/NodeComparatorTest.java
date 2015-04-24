package com.vijfhart.casus.tree.comparator;

import com.vijfhart.casus.tree.node.NameNode;
import com.vijfhart.casus.tree.tree.NodeTree;
import com.vijfhart.casus.tree.tree.Tree;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class NodeComparatorTest {
    private NodeComparator<NameNode> comparator;

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

        comparator = new NodeComparator<>();
    }

    @Test
    public void testCompareLevel() throws Exception {

        // one is root
        assertNodeLarger(nodeB, nodeA);
        assertNodeSmaller(nodeA, nodeC);

        // same level, sorts alphabetically be default
        assertNodeSmaller(nodeB, nodeC);
        assertNodeLarger(nodeF, nodeE);

        // different level (non-root, one level difference)
        assertNodeSmaller(nodeB, nodeD);
        assertNodeLarger(nodeI, nodeE);
        assertNodeLarger(nodeF, nodeB);

        // different level (multiple levels difference)
        assertNodeSmaller(nodeB, nodeH);
        assertNodeLarger(nodeI, nodeB);
    }

    @Test
    public void testCompare() throws Exception {

        // 1. one is root
        assertNodeLarger(nodeB, nodeA);
        assertNodeSmaller(nodeA, nodeE);

        // 2. parent-child (direct, indirect)
        assertNodeSmaller(nodeB, nodeD);
        assertNodeLarger(nodeH, nodeB);

        // 3. same level, same parents (sort alphabetically)
        assertNodeSmaller(nodeD, nodeE);
        assertNodeLarger(nodeH, nodeG);

        // 4. same level, different parents (sort by parents alphabetically)
        assertNodeSmaller(nodeD, nodeF);
        assertNodeLarger(nodeF, nodeD);

        // 5. different levels (both sides)
        assertNodeSmaller(nodeG, nodeF);
        assertNodeLarger(nodeC, nodeI);

        // test with diffent sibling comparator (inversed alphabetically)
        comparator.setSiblingsComparator(new Comparator<NameNode>() {
            @Override
            public int compare(NameNode node1, NameNode node2) {
                return node2.toString().compareToIgnoreCase(node1.toString());
            }
        });

        // 1. one is root
        assertNodeLarger(nodeB, nodeA);
        assertNodeSmaller(nodeA, nodeE);

        // 2. parent-child (direct, indirect)
        assertNodeSmaller(nodeB, nodeD);
        assertNodeLarger(nodeH, nodeB);

        // 3. same level, same parents (sort alphabetically)
        assertNodeLarger(nodeD, nodeE);
        assertNodeSmaller(nodeH, nodeG);

        // 4. same level, different parents (sort by parents alphabetically)
        assertNodeLarger(nodeD, nodeF);
        assertNodeSmaller(nodeF, nodeD);

        // 5. different levels (both sides)
        assertNodeLarger(nodeG, nodeF);
        assertNodeSmaller(nodeC, nodeI);
    }

    @Test
    public void testCompareRootNodes() throws Exception {
        NameNode nodeA = new NameNode("A");
        NameNode nodeA2 = new NameNode("A");
        NameNode nodeB = new NameNode("B");
        NameNode nodeC = new NameNode("C");

        assertNodesEqual(nodeA, nodeA2);
        assertNodeLarger(nodeB, nodeA);
        assertNodeSmaller(nodeA, nodeC);
    }

    @Test
    public void testComparatorWithTree() throws Exception {
        NameNode nodeA = new NameNode("A");
        NameNode nodeB = new NameNode("B");
        NameNode nodeC = new NameNode("C");

        NameNode nodeD = new NameNode("D", nodeC);
        NameNode nodeE = new NameNode("E", nodeC);

        Tree<NameNode> tree = new NodeTree<>();

        tree.add(nodeB);
        tree.add(nodeC);
        tree.add(nodeD);
        tree.add(nodeA);
        tree.add(nodeE);

        NameNode[] nodes = new NameNode[5];
        int i = 0;
        for (NameNode node : tree) {
            nodes[i] = node;
            i++;
        }

        assertEquals(nodeA, nodes[0]);
        assertEquals(nodeB, nodes[1]);
        assertEquals(nodeC, nodes[2]);
        assertEquals(nodeD, nodes[3]);
        assertEquals(nodeE, nodes[4]);

        // now a different comparator for siblings (inversed alphabetically)
        tree.setSiblingsComparator(new Comparator<NameNode>() {
            @Override
            public int compare(NameNode node1, NameNode node2) {
                return node2.toString().compareToIgnoreCase(node1.toString());
            }
        });

        i = 0;
        for (NameNode node : tree) {
            nodes[i] = node;
            i++;
        }

        assertEquals(nodeC, nodes[0]);
        assertEquals(nodeE, nodes[1]);
        assertEquals(nodeD, nodes[2]);
        assertEquals(nodeB, nodes[3]);
        assertEquals(nodeA, nodes[4]);
    }

    private void assertNodeSmaller(NameNode node1, NameNode node2) {
        assertTrue(comparator.compare(node1, node2) < 0);
    }

    private void assertNodeLarger(NameNode node1, NameNode node2) {
        assertTrue(comparator.compare(node1, node2) > 0);
    }

    private void assertNodesEqual(NameNode node1, NameNode node2) {
        assertTrue(comparator.compare(node1, node2) == 0);
    }
}