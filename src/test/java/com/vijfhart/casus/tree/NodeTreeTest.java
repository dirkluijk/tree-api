package com.vijfhart.casus.tree;

import org.junit.Before;
import org.junit.Test;

public class NodeTreeTest {

    private NodeTree<NameNode> tree;
    private NameNode node2b;
    private NameNode node2a;
    private NameNode node1;

    @Before
    public void setUp() throws Exception {

        // Op volgorde aanmaken
        node1 = new NameNode("Lorem");

        node2a = new NameNode("Ipsum", node1);
        node2b = new NameNode("Dolor", node1);

        NameNode node3a = new NameNode("Sid", node2a);
        NameNode node3b = new NameNode("Amed", node2a);
        NameNode node3c = new NameNode("Consectetur", node2b);

        // maak tree
        tree = new NodeTree();

        // willekeurige volgorde toevoegen:
        tree.add(node3b);
        tree.add(node1);
        tree.add(node2b);
        tree.add(node3c);
        tree.add(node2a);
        tree.add(node3a);
    }

    @Test
    public void testLevelWithIterator() throws Exception {
        TreeIterator treeIterator = tree.treeIterator();

        while (treeIterator.hasNext()) {
            Object next = treeIterator.next();
            System.out.printf("Node %s with level %s%n", next, treeIterator.level());
        }
    }

    @Test
    public void testIteratorWithStartWith() throws Exception {

        TreeIterator treeIterator = tree.treeIterator();

        treeIterator.startWith(node2a);

        while (treeIterator.hasNext()) {
            Object next = treeIterator.next();
            System.out.printf("Node %s with level %s%n", next, treeIterator.level());
        }

    }
}