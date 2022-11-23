package com.potato.study.leetcodecn.p02476.t001;

import com.potato.study.leetcode.domain.TreeNode;

import java.util.*;

/**
 * 2476. 二叉搜索树最近节点查询
 *
 * 给你一个 二叉搜索树 的根节点 root ，和一个由正整数组成、长度为 n 的数组 queries 。
 *
 * 请你找出一个长度为 n 的 二维 答案数组 answer ，其中 answer[i] = [mini, maxi] ：
 *
 * mini 是树中小于等于 queries[i] 的 最大值 。如果不存在这样的值，则使用 -1 代替。
 * maxi 是树中大于等于 queries[i] 的 最小值 。如果不存在这样的值，则使用 -1 代替。
 * 返回数组 answer 。
 *
 *  
 *
 * 示例 1 ：
 *
 *
 *
 * 输入：root = [6,2,13,1,4,9,15,null,null,null,null,null,null,14], queries = [2,5,16]
 * 输出：[[2,2],[4,6],[15,-1]]
 * 解释：按下面的描述找出并返回查询的答案：
 * - 树中小于等于 2 的最大值是 2 ，且大于等于 2 的最小值也是 2 。所以第一个查询的答案是 [2,2] 。
 * - 树中小于等于 5 的最大值是 4 ，且大于等于 5 的最小值是 6 。所以第二个查询的答案是 [4,6] 。
 * - 树中小于等于 16 的最大值是 15 ，且大于等于 16 的最小值不存在。所以第三个查询的答案是 [15,-1] 。
 * 示例 2 ：
 *
 *
 *
 * 输入：root = [4,null,9], queries = [3]
 * 输出：[[-1,4]]
 * 解释：树中不存在小于等于 3 的最大值，且大于等于 3 的最小值是 4 。所以查询的答案是 [-1,4] 。
 *  
 *
 * 提示：
 *
 * 树中节点的数目在范围 [2, 105] 内
 * 1 <= Node.val <= 106
 * n == queries.length
 * 1 <= n <= 105
 * 1 <= queries[i] <= 106
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/closest-nodes-queries-in-a-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        build(root, treeSet);
        // 耍流氓办法 将 root 遍历一遍放入 treeSet中 查询
        List<List<Integer>> result = new ArrayList<>();
        for (int query : queries) {
            List<Integer> list = new ArrayList<>();
            // 找到是树中小于等于 queries[i] 的 最大值
            Integer floor = treeSet.floor(query);
            if (floor == null) {
                list.add(-1);
            } else {
                list.add(floor);
            }
            // 是树中大于等于 queries[i] 的 最小值
            Integer ceiling = treeSet.ceiling(query);
            if (ceiling == null) {
                list.add(-1);
            } else {
                list.add(ceiling);
            }
            result.add(list);
        }
        return result;
    }

    private void build(TreeNode root, TreeSet<Integer> treeSet) {
        if (root == null) {
            return;
        }
        treeSet.add(root.val);
        build(root.left, treeSet);
        build(root.right, treeSet);
    }
}
