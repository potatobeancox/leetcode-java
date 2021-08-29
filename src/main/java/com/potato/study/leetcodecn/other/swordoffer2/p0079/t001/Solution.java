package com.potato.study.leetcodecn.other.swordoffer2.p0079.t001;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer II 079. 所有子集
 *
 * 给定一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 示例 2：
 *
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 *  
 *
 * 注意：本题与主站 78 题相同： https://leetcode-cn.com/problems/subsets/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/TVdhkn
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        int bitLimit = (1 << n);
        List<List<Integer>> resultList = new ArrayList<>();
        for (int i = 0; i < bitLimit; i++) {
            resultList.add(getSubSetByBitStatus(i, nums));
        }
        return resultList;
    }

    /**
     *
     * @param
     * @return
     */
    private List<Integer> getSubSetByBitStatus(int status, int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int bit = (status & 1);
            if (bit == 1) {
                result.add(nums[i]);
            }
            status >>>= 1;
        }
        return result;
    }


}
