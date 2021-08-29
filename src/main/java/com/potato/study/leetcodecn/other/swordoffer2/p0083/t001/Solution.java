package com.potato.study.leetcodecn.other.swordoffer2.p0083.t001;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 剑指 Offer II 083. 没有重复元素集合的全排列
 *
 * 给定一个不含重复数字的整数数组 nums ，返回其 所有可能的全排列 。可以 按任意顺序 返回答案。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 示例 2：
 *
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 示例 3：
 *
 * 输入：nums = [1]
 * 输出：[[1]]
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/VvJkup
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * dfs 回溯
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> totalResult = new ArrayList<>();
        List<Integer> currentResult = new ArrayList<>();
        boolean[] visit = new boolean[nums.length];
        dfsGetResult(nums, totalResult, currentResult, visit);
        return totalResult;
    }

    private void dfsGetResult (int[] nums, List<List<Integer>> totalResult, List<Integer> currentResult,
            boolean[] visit) {
        if (currentResult.size() == nums.length) {
            totalResult.add(new ArrayList<>(currentResult));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visit[i]) {
                continue;
            }
            List<Integer> newResult = new ArrayList<>(currentResult);
            newResult.add(nums[i]);
            visit[i] = true;
            dfsGetResult(nums, totalResult, newResult, visit);
            visit[i] = false;
        }
    }
}
