package com.potato.study.leetcodecn.other.lcr.p0011.t001;

import java.util.HashMap;
import java.util.Map;

/**
 * LCR 011. 连续数组
 *
 * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: nums = [0,1]
 * 输出: 2
 * 说明: [0, 1] 是具有相同数量 0 和 1 的最长连续子数组。
 * 示例 2：
 *
 * 输入: nums = [0,1,0]
 * 输出: 2
 * 说明: [0, 1] (或 [1, 0]) 是具有相同数量 0 和 1 的最长连续子数组。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * nums[i] 不是 0 就是 1
 *  
 *
 * 注意：本题与主站 525 题相同： https://leetcode-cn.com/problems/contiguous-array/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/A1NYOS
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     *
     * @param nums
     * @return
     */
    public int findMaxLength(int[] nums) {
        // 跟之前的那个一样 使用一个 state 记录 当前 0和1的数量 0++ 1-- 并记录第一个出现这个的位置
        Map<Integer, Integer> stateIndexMap = new HashMap<>();
        int state = 0;
        stateIndexMap.put(state, -1);
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                state--;
            } else {
                state++;
            }
            if (stateIndexMap.containsKey(state)) {
                Integer index = stateIndexMap.get(state);
                max = Math.max(max, i - index);
            } else {
                stateIndexMap.put(state, i);
            }
        }
        return max;
    }
}
