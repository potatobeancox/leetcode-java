package com.potato.study.leetcodecn.other.swordoffer2.p0050.t001;

import com.potato.study.leetcode.domain.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 剑指 Offer II 050. 向下的路径节点之和
 *
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。

 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。

  

 示例 1：



 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 输出：3
 解释：和等于 8 的路径有 3 条，如图所示。
 示例 2：

 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 输出：3
  

 提示:

 二叉树的节点个数的范围是 [0,1000]
 -109 <= Node.val <= 109 
 -1000 <= targetSum <= 1000 
  

 注意：本题与主站 437 题相同：https://leetcode-cn.com/problems/path-sum-iii/

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/6eUYwP
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/6eUYwP/solution/xiang-xia-de-lu-jing-jie-dian-zhi-he-by-a1iyy/
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        // dfs 以每个点作为开始点或者作为途中的点 计算
        int res = rootSum(root, targetSum);
        // 或者孩子的
        res += pathSum(root.left, targetSum);
        res += pathSum(root.right, targetSum);
        return res;
    }


    /**
     * 开始遍历的节点
     * @param root  以root作为起点的路径
     * @param targetSum 要找的目标值
     * @return
     */
    private int rootSum(TreeNode root, long targetSum) {
        if (root == null) {
            return 0;
        }
        // 如果当前val 满足结果 当前节点自己就是一个结果
        int result = 0;
        if (targetSum == root.val) {
            result++;
        }
        result += rootSum(root.left, targetSum - root.val);
        result += rootSum(root.right, targetSum - root.val);
        return result;
    }


}
