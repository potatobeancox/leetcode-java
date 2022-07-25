package com.potato.study.leetcodecn.p01522.t001;

import java.util.Arrays;
import java.util.List;

import com.potato.study.leetcode.domain.node.val.children.Node;

/**
 * 1522. N 叉树的直径
 *
 * 给定一棵 N 叉树的根节点 root ，计算这棵树的直径长度。
 *
 * N 叉树的直径指的是树中任意两个节点间路径中 最长 路径的长度。这条路径可能经过根节点，也可能不经过根节点。
 *
 * （N 叉树的输入序列以层序遍历的形式给出，每组子节点用 null 分隔）
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：3
 * 解释：直径如图中红线所示。
 * 示例 2：
 *
 *
 *
 * 输入：root = [1,null,2,null,3,4,null,5,null,6]
 * 输出：4
 * 示例 3：
 *
 *
 *
 * 输入: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出: 7
 *  
 *
 * 提示：
 *
 * N 叉树的深度小于或等于 1000 。
 * 节点的总个数在 [0, 10^4] 间。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/diameter-of-n-ary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    private int diameter;

    public int diameter(Node root) {
        // 递归找每个孩子的高度
        int height = dfs(root);
        diameter = Math.max(height, diameter);
        return this.diameter;
    }

    private int dfs(Node root) {
        // 遍历孩子找到 最大的高度 计算和
        if (root == null) {
            return 0;
        }
        // 没有孩子 叶子节点
        if (root.children == null || root.children.size() == 0) {
            return 0;
        }
        List<Node> children = root.children;
        int[] height = new int[children.size()];
        int index = 0;
        for (Node child : children) {
            int childHeight = dfs(child);
            height[index++] = childHeight;
        }

        if (height.length == 1) {
            this.diameter = Math.max(diameter, height[0]);
            return height[0] + 1;
        }
        Arrays.sort(height);
        this.diameter = Math.max(diameter, height[height.length - 1] + height[height.length - 2] + 2);
        return height[height.length - 1] + 1;
    }
}
