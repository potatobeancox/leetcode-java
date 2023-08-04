package com.potato.study.leetcodecn.other.lcr.p0084.t001;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer II 084. 含有重复元素集合的全排列
 *
 * 给定一个可包含重复数字的整数集合 nums ，按任意顺序 返回它所有不重复的全排列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 * 示例 2：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/7p8L0Z
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public List<List<Integer>> permuteUnique(int[] nums) {
        // 排序
        Arrays.sort(nums);
        // dfs 找每种可能
        List<List<Integer>> resultList = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        List<Integer> list = new ArrayList<>();
        dfs(resultList, nums, used, list);
        return resultList;
    }

    private void dfs(List<List<Integer>> resultList, int[] nums, boolean[] used, List<Integer> list) {
        if (list.size() == nums.length) {
            resultList.add(new ArrayList<>(list));
            return;
        }
        // 从0 开始 找下一个点
        for (int i = 0; i < nums.length; i++) {
            // 如果当前i 已经被使用
            if (used[i]) {
                continue;
            }
            // 如果 i 跟之前的一样 且之前的没有被使用 相当于 去重
            if (i > 0 && nums[i-1] == nums[i] && !used[i-1]) {
                continue;
            }
            // 使用这个点
            list.add(nums[i]);
            used[i] = true;
            // dfs
            dfs(resultList, nums, used, list);
            used[i] = false;
            list.remove(list.size() - 1);
        }
    }


}
