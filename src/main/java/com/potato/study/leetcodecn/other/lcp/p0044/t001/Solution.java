package com.potato.study.leetcodecn.other.lcp.p0044.t001;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.junit.Assert;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * LCP 44. 开幕式焰火
 *
 * 「力扣挑战赛」开幕式开始了，空中绽放了一颗二叉树形的巨型焰火。
 * 给定一棵二叉树 root 代表焰火，节点值表示巨型焰火这一位置的颜色种类。请帮小扣计算巨型焰火有多少种不同的颜色。
 *
 * 示例 1：
 *
 * 输入：root = [1,3,2,1,null,2]
 *
 * 输出：3
 *
 * 解释：焰火中有 3 个不同的颜色，值分别为 1、2、3
 *
 * 示例 2：
 *
 * 输入：root = [3,3,3]
 *
 * 输出：1
 *
 * 解释：焰火中仅出现 1 个颜色，值为 3
 *
 * 提示：
 *
 * 1 <= 节点个数 <= 1000
 * 1 <= Node.val <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sZ59z6
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int numColor(TreeNode root) {
        if (null == root) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (poll.left != null) {
                queue.add(poll.left);
            }
            if (poll.right != null) {
                queue.add(poll.right);
            }
            set.add(poll.val);
        }
        return set.size();
    }



    public static void main(String[] args) {
//        Solution solution = new Solution();
//        String s = "AB";
//        int res = solution.calculate(s);
//        System.out.println(res);
//        Assert.assertEquals(4, res);
    }


}
