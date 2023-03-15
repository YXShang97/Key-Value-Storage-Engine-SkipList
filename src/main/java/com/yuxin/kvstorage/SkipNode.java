package com.yuxin.kvstorage;

public class SkipNode<T> {
    int key;
    T value;
    SkipNode right;
    SkipNode down;

    public SkipNode(int key, T value) {
        this.key = key;
        this.value = value;
    }
}

