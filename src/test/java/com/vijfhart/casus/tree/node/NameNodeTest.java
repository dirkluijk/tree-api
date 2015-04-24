package com.vijfhart.casus.tree.node;

import org.junit.Test;

import static org.junit.Assert.*;

public class NameNodeTest {
    @Test
    public void testNameNode() throws Exception {
        final String name = "Foo";

        NameNode nameNode = new NameNode(name);
        assertEquals(name, nameNode.toString());
    }
}