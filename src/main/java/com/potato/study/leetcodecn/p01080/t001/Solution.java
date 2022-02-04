package com.potato.study.leetcodecn.p01080.t001;

import com.potato.study.leetcode.domain.TreeNode;
import org.junit.Assert;

/**
 * 1080. 根到叶路径上的不足节点
 *
 * 给定一棵二叉树的根 root，请你考虑它所有 从根到叶的路径：从根到任何叶的路径。（所谓一个叶子节点，就是一个没有子节点的节点）

 假如通过节点 node 的每种可能的 “根-叶” 路径上值的总和全都小于给定的 limit，则该节点被称之为「不足节点」，需要被删除。

 请你删除所有不足节点，并返回生成的二叉树的根。

  

 示例 1：


 输入：root = [1,2,3,4,-99,-99,7,8,9,-99,-99,12,13,-99,14], limit = 1

 输出：[1,2,3,4,null,null,7,8,9,null,14]
 示例 2：


 输入：root = [5,4,8,11,null,17,4,7,1,null,null,5,3], limit = 22

 输出：[5,4,8,11,null,17,4,7,null,null,null,5]
 示例 3：


 输入：root = [5,-6,-6], limit = 0
 输出：[]
  

 提示：

 给定的树有 1 到 5000 个节点
 -10^5 <= node.val <= 10^5
 -10^9 <= limit <= 10^9

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/insufficient-nodes-in-root-to-leaf-paths
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public TreeNode sufficientSubset(TreeNode root, int limit) {

        return null;
    }
}
