package com.vijfhart.casus.tree.node;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public class WrapperNodeTest {

    private StringWrapperNode node;

    @Before
    public void setUp() throws Exception {
        node = new StringWrapperNode("Bb");
    }

    @Test
    public void testGetObject() throws Exception {
        assertEquals(node.getObject(), "Bb");
    }

    @Test
    public void testCompareTo() throws Exception {
        assertEquals(1, node.compareTo(new StringWrapperNode("Aa")));
        assertEquals(0, node.compareTo(new StringWrapperNode("Bb")));
        assertEquals(-1, node.compareTo(new StringWrapperNode("Cc")));

        // with parent
        assertEquals(-1, node.compareTo(new StringWrapperNode("Bb", node)));
        assertEquals(-1, node.compareTo(new StringWrapperNode("Aa", node)));
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("Bb", node.toString());
    }
}