package com.potato.study.leetcodecn.p00491.t001;

import java.util.*;

/**
 * 491. 递增子序列
 *
 * 给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中 至少有两个元素 。你可以按 任意顺序 返回答案。

 数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。

  

 示例 1：

 输入：nums = [4,6,7,7]
 输出：[[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
 示例 2：

 输入：nums = [4,4,3,2,1]
 输出：[[4,4]]
  

 提示：

 1 <= nums.length <= 15
 -100 <= nums[i] <= 100

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/increasing-subsequences
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        // 生成一个标记位置
        int n = nums.length;
        long limit = (1 << n);
        Set<List<Integer>> resultList = new HashSet<>();
        for (long i = 1; i < limit; i++) {
            // 按照标记位生成结果
            List<Integer> list = getAscendingSubsequence(nums, i);
            if (list.size() >= 2) {
                resultList.add(list);
            }
        }
        return new ArrayList<>(resultList);
    }

    /**
     * 按照i位置对nums 进行获取
     * @param nums
     * @param i
     * @return
     */
    private List<Integer> getAscendingSubsequence(int[] nums, long i) {
        List<Integer> result = new ArrayList<>();
        for (int j = 0; j < nums.length; j++) {
            long bit = i & 1;
            if (bit == 1) {
                if (result.size() >= 1 && nums[j] < result.get(result.size() - 1)) {
                    return new ArrayList<>();
                }
                result.add(nums[j]);
            }
            i >>>= 1;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                4,6,7,7
        };
        List<List<Integer>> subsequences = solution.findSubsequences(nums);
        System.out.println(subsequences);
    }


}
