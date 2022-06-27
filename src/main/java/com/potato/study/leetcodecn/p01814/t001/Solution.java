package com.potato.study.leetcodecn.p01814.t001;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

/**
 * 1814. 统计一个数组中好对子的数目
 *
 * 给你一个数组 nums ，数组中只包含非负整数。定义 rev(x) 的值为将整数 x 各个数字位反转得到的结果。比方说 rev(123) = 321 ， rev(120) = 21 。我们称满足下面条件的下标对 (i, j)
 * 是 好的 ：
 *
 * 0 <= i < j < nums.length
 * nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
 * 请你返回好下标对的数目。由于结果可能会很大，请将结果对 109 + 7 取余 后返回。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [42,11,1,97]
 * 输出：2
 * 解释：两个坐标对为：
 *  - (0,3)：42 + rev(97) = 42 + 79 = 121, 97 + rev(42) = 97 + 24 = 121 。
 *  - (1,2)：11 + rev(1) = 11 + 1 = 12, 1 + rev(11) = 1 + 11 = 12 。
 * 示例 2：
 *
 * 输入：nums = [13,10,35,24,76]
 * 输出：4
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 109
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/count-nice-pairs-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 1814
    public int countNicePairs(int[] nums) {
        // nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])   == > nums[i] - rev(nums[i]) == nums[j] - rev(nums[j]) // 个数
        Map<Long, Long> countMap = new HashMap<>();
        for (int num : nums) {
            long current = num;
            current -= rec(num);

            long count = countMap.getOrDefault(current, 0L);
            count++;
            countMap.put(current, count);
        }
        // 遍历 countmap 获取数量
        int pairCount = 0;
        int mod = 1_000_000_000 + 7;
        for (Map.Entry<Long, Long> entry : countMap.entrySet()) {
            long times = entry.getValue();
            if (times > 1) {
                pairCount += times * (times - 1) / 2;
                pairCount %= mod;
            }
        }
        return pairCount % mod;
    }

    private long rec(int num) {
        String numStr = String.valueOf(num);
        StringBuilder builder = new StringBuilder(numStr);
        long l = Long.parseLong(builder.reverse().toString());
        return l;
    }

}
