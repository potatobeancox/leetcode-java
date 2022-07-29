package com.potato.study.leetcodecn.p00431.t001;

import java.util.ArrayList;
import java.util.List;

import com.potato.study.leetcode.domain.TreeNode;
import com.potato.study.leetcode.domain.node.val.children.Node;

/**
 * 431. 将 N 叉树编码为二叉树
 *
 *
 * 设计一个算法，可以将 N 叉树编码为二叉树，并能将该二叉树解码为原 N 叉树。一个 N 叉树是指每个节点都有不超过 N 个孩子节点的有根树。类似地，一个二叉树是指每个节点都有不超过 2 个孩子节点的有根树。你的编码 /
 * 解码的算法的实现没有限制，你只需要保证一个 N 叉树可以编码为二叉树且该二叉树可以解码回原始 N 叉树即可。
 *
 * 例如，你可以将下面的 3-叉 树以该种方式编码：
 *
 *  
 *
 *
 *
 *  
 *
 * 注意，上面的方法仅仅是一个例子，可能可行也可能不可行。你没有必要遵循这种形式转化，你可以自己创造和实现不同的方法。
 *
 * 注意：
 *
 * N 的范围在 [1, 1000]
 * 不要使用类成员 / 全局变量 / 静态变量来存储状态。你的编码和解码算法应是无状态的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/encode-n-ary-tree-to-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Codec {


    // Encodes an n-ary tree to a binary tree.
    public TreeNode encode(Node root) {
        if (root == null) {
            return null;
        }
        // 递归处理 对当前节点 生成 TreeNode 并设置
        TreeNode treeRoot = new TreeNode(root.val);
        // 处理当前的孩子节点的逻辑 TreeNode 左边孩子是 孩子的第一个 其他的都是右孩子
        treeRoot.left = encodeChildren(root.children);
        return treeRoot;
    }

    /**
     *
     * @param children
     * @return
     */
    private TreeNode encodeChildren(List<Node> children) {
        // 通过 children 构建子树 返回 子树的根
        if (children == null || children.size() == 0) {
            return null;
        }
        // 哨兵节点
        TreeNode treeNode = new TreeNode(-1);
        TreeNode p = treeNode;
        // 对于每个 chilren 都要递归生成 treenode 再有孩子连接
        for (int i = 0; i < children.size(); i++) {
            Node nextChild = children.get(i);
            TreeNode encodeChild = this.encode(nextChild);
            p.right = encodeChild;
            p = p.right;
        }
        return treeNode.right;
    }

    // Decodes your binary tree to an n-ary tree.
    public Node decode(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 当前节点转换
        Node rootNode = new Node(root.val);
        // 生成 chilidren root 左孩子和 左边孩子的右边孩子
        rootNode.children = getTreeNodeChild(root.left);
        return rootNode;
    }

    private List<Node> getTreeNodeChild(TreeNode root) {
        List<Node> childrenList = new ArrayList<>();
        // root 左边孩子 每个孩子都要在搞一次 decode
        TreeNode p = root;
        while (p != null) {
            Node decode = decode(p);
            childrenList.add(decode);
            p = p.right;
        }
        return childrenList;
    }

}
