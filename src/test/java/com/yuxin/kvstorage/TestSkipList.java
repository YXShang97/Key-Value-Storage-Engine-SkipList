package com.yuxin.kvstorage;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestSkipList{
    SkipList<Integer> list = new SkipList<Integer>();

    @Test
    public void testAddSearch() {
        SkipNode addNode = new SkipNode<>(1, 100);
        list.add(addNode);
        assertEquals(list.search(1).value, addNode.value);
//        assertSame(list.search(1), addNode);
    }

    @Test
    public void testDeleteSearch() {
        list.add(new SkipNode<>(2, 200));
        list.add(new SkipNode<>(3, 300));
        list.delete(2);
        assertNull(list.search(2));
    }
}