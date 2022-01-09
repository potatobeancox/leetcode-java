package com.potato.study.leetcodecn.p00449.t001;

import com.google.common.base.Strings;
import com.potato.study.leetcode.domain.TreeNode;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 449. 序列化和反序列化二叉搜索树
 *
 * 序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，或通过网络连接链路传输，以便稍后在同一个或另一个计算机环境中重建。

 设计一个算法来序列化和反序列化 二叉搜索树 。 对序列化/反序列化算法的工作方式没有限制。 您只需确保二叉搜索树可以序列化为字符串，并且可以将该字符串反序列化为最初的二叉搜索树。

 编码的字符串应尽可能紧凑。

  

 示例 1：

 输入：root = [2,1,3]
 输出：[2,1,3]
 示例 2：

 输入：root = []
 输出：[]
  

 提示：

 树中节点数范围是 [0, 104]
 0 <= Node.val <= 104
 题目数据 保证 输入的树是一棵二叉搜索树。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/serialize-and-deserialize-bst
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (null == root) {
            return "#";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(root.val)
                .append(",")
                .append(serialize(root.left))
                .append(",")
                .append(serialize(root.right));
        return builder.toString();
    }


    private int index;
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] split = data.split(",");
        this.index = 0;
        return deserializeEach(split);
    }



    /**
     * 生成
     * @param split
     * @return
     */
    private TreeNode deserializeEach(String[] split) {
        if (index >= split.length) {
            return null;
        }
        if ("".equals(split[index]) ) {
            index++;
            return null;
        }
        if ("#".equals(split[index])) {
            index++;
            return null;
        }

        int num = Integer.parseInt(split[index]);
        TreeNode node = new TreeNode(num);
        index++;
        node.left = deserializeEach(split);
        node.right = deserializeEach(split);

        return node;
    }


    public static void main(String[] args) {
        Codec codec = new Codec();

        TreeNode node = new TreeNode(2);
        node.left = new TreeNode(1);
        node.right = new TreeNode(3);

        String serialize = codec.serialize(node);
        System.out.println(serialize);
    }
}
