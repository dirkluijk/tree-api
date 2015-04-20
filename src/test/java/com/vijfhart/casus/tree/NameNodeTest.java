package com.vijfhart.casus.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class NameNodeTest {
    @Test
    public void testComparing() throws Exception {

        // Op volgorde aanmaken
        NameNode node1 = new NameNode("Lorem");

        NameNode node2a = new NameNode("Ipsum", node1);
        NameNode node2b = new NameNode("Dolor", node1);

        NameNode node3a = new NameNode("Sid", node2a);
        NameNode node3b = new NameNode("Amed", node2a);
        NameNode node3c = new NameNode("Consectetur", node2b);

        // maak lijst en sorteer op level
        List<NameNode> list = new ArrayList<NameNode>();

        // willekeurige volgorde toevoegen:
        list.add(node3b);
        list.add(node1);
        list.add(node2b);
        list.add(node3c);
        list.add(node2a);
        list.add(node3a);

        // sorteer
        Collections.sort(list);

        assertEquals(node1, list.get(0));
        assertTrue(list.get(1) == node2a || list.get(2) == node2a);
        assertTrue(list.get(1) == node2b || list.get(2) == node2b);
        assertTrue(list.get(3) == node3a || list.get(4) == node3a || list.get(5) == node3a);
        assertTrue(list.get(3) == node3b || list.get(4) == node3b || list.get(5) == node3b);
        assertTrue(list.get(3) == node3c || list.get(4) == node3c || list.get(5) == node3c);

        for (NameNode node : list) {
            System.out.println(node);
        }
    }
}