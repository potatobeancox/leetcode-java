package com.potato.study.leetcodecn.p01490.t001;

import com.potato.study.leetcode.domain.duplicate.name.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * 1490. 克隆 N 叉树
 *
 * 给定一棵 N 叉树的根节点 root ，返回该树的深拷贝（克隆）。

 N 叉树的每个节点都包含一个值（ int ）和子节点的列表（ List[Node] ）。

 class Node {
 public int val;
 public List<Node> children;
 }
 N 叉树的输入序列用层序遍历表示，每组子节点用 null 分隔（见示例）。

  

 示例 1：



 输入：root = [1,null,3,2,4,null,5,6]
 输出：[1,null,3,2,4,null,5,6]
 示例 2：



 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 输出：[1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
  

 提示：

 给定的 N 叉树的深度小于或等于 1000。
 节点的总个数在 [0, 10^4] 之间
  

 进阶：你的解决方案可以适用于克隆图问题吗？

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/clone-n-ary-tree
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public Node cloneTree(Node root) {
        if (root == null) {
            return null;
        }
        Node newNode = new Node(root.val);
        if (root.children == null) {
            newNode.children = null;
            return newNode;
        }
        List<Node> nodeList = new ArrayList<>();
        for (Node child : root.children) {
            Node newChild = cloneTree(child);
            nodeList.add(newChild);
        }
        newNode.children = nodeList;
        return newNode;
    }
}
