package com.potato.study.leetcodecn.other.swordoffer.p0037.p1.t001;


import java.util.ArrayList;
import java.util.List;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 剑指 Offer 37. 序列化二叉树
 *
 * 请实现两个函数，分别用来序列化和反序列化二叉树。
 *
 * 你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * 提示：输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 *
 *  
 *
 * 示例：
 *
 *
 * 输入：root = [1,2,3,null,null,4,5]
 * 输出：[1,2,3,null,null,4,5]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/xu-lie-hua-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        // 先根遍历 生成 list
        List<String> serializeList = new ArrayList<>();
        preorder(root, serializeList);
        // 将list 转成字符串
        StringBuilder builder = new StringBuilder();
        for (String value : serializeList) {
            builder.append(value).append("#");
        }
        if (builder.charAt(builder.length() - 1) == '#') {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }

    /**
     * 先序遍历 二叉树 将 节点值 写到list 中
     * @param root
     * @param serializeList
     */
    private void preorder(TreeNode root, List<String> serializeList) {
        if (root == null) {
            serializeList.add("null");
            return;
        }
        serializeList.add(String.valueOf(root.val));
        preorder(root.left, serializeList);
        preorder(root.right, serializeList);
    }

    private int index;
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // data 按照 # 进行分割
        String[] split = data.split("#");
        this.index = 0;
        // 先根便利 list 记录 index 生成 结果
        return preorderGenerate(split);
    }

    /**
     * 生成
     * @param split
     * @return
     */
    private TreeNode preorderGenerate(String[] split) {
        String nodeValue = split[index];
        index++;
        if ("null".equals(nodeValue)) {
            return null;
        }
        int val = Integer.parseInt(nodeValue);
        TreeNode treeNode = new TreeNode(val);
        treeNode.left = preorderGenerate(split);
        treeNode.right = preorderGenerate(split);
        return treeNode;
    }
}
