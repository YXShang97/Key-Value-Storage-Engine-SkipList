package com.yuxin.kvstorage;

public class SkipListTest {
    public static void main(String[] args) {
        SkipList<Integer> list=new SkipList<Integer>();
        for(int i=1;i<20;i++) {
            list.add(new SkipNode(i,666));
        }
        list.printList();
        list.delete(4);
        list.delete(8);
        list.printList();
    }
}