package com.potato.study.leetcode.domain.node.val.left.right.ch;

/**
 * @author liuzhao03 <liuzhao03@kuaishou.com>
 * Created on 2022-07-25
 */
public class Node {

    public char val;
    public Node left;
    public Node right;
    public Node() {this.val = ' ';}
    public Node(char val) { this.val = val; }
    public Node(char val, Node left, Node right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }

}
