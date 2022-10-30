package com.potato.study.leetcodecn.p02364.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 2364. 统计坏数对的数目
 *
 * 给你一个下标从 0 开始的整数数组 nums 。如果 i < j 且 j - i != nums[j] - nums[i] ，那么我们称 (i, j) 是一个 坏数对 。

 请你返回 nums 中 坏数对 的总数目。

  

 示例 1：

 输入：nums = [4,1,3,3]
 输出：5
 解释：数对 (0, 1) 是坏数对，因为 1 - 0 != 1 - 4 。
 数对 (0, 2) 是坏数对，因为 2 - 0 != 3 - 4, 2 != -1 。
 数对 (0, 3) 是坏数对，因为 3 - 0 != 3 - 4, 3 != -1 。
 数对 (1, 2) 是坏数对，因为 2 - 1 != 3 - 1, 1 != 2 。
 数对 (2, 3) 是坏数对，因为 3 - 2 != 3 - 3, 1 != 0 。
 总共有 5 个坏数对，所以我们返回 5 。
 示例 2：

 输入：nums = [1,2,3,4,5]
 输出：0
 解释：没有坏数对。
  

 提示：

 1 <= nums.length <= 105
 1 <= nums[i] <= 109

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/count-number-of-bad-pairs
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public long countBadPairs(int[] nums) {
        // 变形算式 j - nums[j] != i - nums[i]
        long count = 0;
        // 过一遍 每次 j计算之前有多少个
        Map<Integer, Long> countMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int target = i - nums[i];
            long sameCount = 0;
            if (countMap.containsKey(target)) {
                sameCount = countMap.get(target);
            }
            // 计算
            count += (i - sameCount);
            // 当前添加
            countMap.put(target, countMap.getOrDefault(target, 0L) + 1);
        }
        return count;
    }


}