package com.yuxin.kvstorage;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

public class SkipList<T> {
    private SkipNode headNode;
    private int highLevel;
    private Random random;
    final int MAX_LEVEL = 32;

    public SkipList() {
        random = new Random();
        headNode = new SkipNode(Integer.MIN_VALUE, null);
        highLevel = 0;
    }

    public SkipNode search(int key) {
        SkipNode cur = headNode;
        while (cur != null) {
            if (cur.key == key) {
                return cur;
            } else if (cur.right == null) {
                cur = cur.down;
            } else if (cur.right.key > key) {
                cur = cur.down;
            } else {
                cur = cur.right;
            }
        }
        return null;
    }

    public void delete(int key) {
        SkipNode cur = headNode;
        while (cur != null) {
            if (cur.right == null) {
                cur = cur.down;
            } else if (cur.right.key == key) {
                cur.right = cur.right.right;
                cur = cur.down;
            } else if (cur.right.key > key) {
                cur = cur.down;
            } else {
                cur = cur.right;
            }
        }
    }

    public void add(SkipNode node) {
        int key = node.key;
        SkipNode findNode = search(key);

        // node with the same key exists
        if (findNode != null) {
            findNode.value = node.value;
            return;
        }

        Deque<SkipNode> stack = new LinkedList<SkipNode>();
        SkipNode cur = headNode;
        while (cur != null) {
            if (cur.right == null) {
                stack.push(cur);
                cur = cur.down;
            } else if (cur.right.key > key) {
                stack.push(cur);
                cur = cur.down;
            } else {
                cur = cur.right;
            }
        }

        int level = 1;
        SkipNode downNode = null;
        while (!stack.isEmpty()) {
            cur = stack.pop();
            SkipNode addNode = new SkipNode(node.key, node.value);
            addNode.down = downNode;
            downNode = addNode;
            if (cur.right == null) {
                cur.right = addNode;
            } else {
                addNode.right = cur.right;
                cur.right = addNode;
            }

            if (level > MAX_LEVEL || random.nextDouble() > 0.5) break;
            level++;
            if (level > highLevel) {
                highLevel = level;
                SkipNode newHeadNode = new SkipNode(Integer.MIN_VALUE, null);
                newHeadNode.down = headNode;
                headNode = newHeadNode;
                stack.push(headNode);
            }
        }
    }

    public void printList() {
        SkipNode curLevel = headNode;
        SkipNode bottomLevel = curLevel;
        while (bottomLevel.down != null) {
            bottomLevel = bottomLevel.down;
        }
        int index = 1;

        while (curLevel != null) {
            SkipNode enumCur = curLevel.right;
            SkipNode enumBottom = bottomLevel.right;
            System.out.printf("%-8s", "head->");
            while (enumCur != null && enumBottom != null) {
                if (enumCur.key == enumBottom.key) {
                    System.out.printf("%-5s", enumBottom.key + "->");
                    enumCur = enumCur.right;
                    enumBottom = enumBottom.right;
                } else {
                    System.out.printf("%-5s", "");
                    enumBottom = enumBottom.right;
                }
            }
            curLevel = curLevel.down;
            index++;
            System.out.println();
        }
    }
}

