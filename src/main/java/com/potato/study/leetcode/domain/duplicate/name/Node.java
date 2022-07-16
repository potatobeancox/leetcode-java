package com.potato.study.leetcode.domain.duplicate.name;

import java.util.ArrayList;
import java.util.List;

/**
 * 多叉树 的node
 * n叉树 节点
 */
public class Node {

    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
    }

    public Node(int val) {
        this.val = val;
        this.children = new ArrayList<>();
    }


}