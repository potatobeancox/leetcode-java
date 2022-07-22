package com.potato.study.leetcode.domain.node.val.left.right.random;

public class NodeCopy {

    public int val;
    public NodeCopy left;
    public NodeCopy right;
    public NodeCopy random;
    public NodeCopy() {}
    public NodeCopy(int val) { this.val = val; }
    public NodeCopy(int val, NodeCopy left, NodeCopy right, NodeCopy random) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.random = random;
    }

}
