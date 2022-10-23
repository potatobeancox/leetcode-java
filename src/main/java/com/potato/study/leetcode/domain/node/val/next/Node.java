package com.potato.study.leetcode.domain.node.val.next;

public class Node {

    public int val;
    public Node next;

    public Node() {}

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }

//    @Override
//    public String toString() {
//        return "Node(" + val + ") -> " + (next == null ? "" : next.toString());
//    }

}
