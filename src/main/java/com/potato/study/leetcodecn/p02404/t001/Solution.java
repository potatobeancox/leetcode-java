package com.potato.study.leetcodecn.p02404.t001;

import java.util.HashMap;
import java.util.Map;

/**
 * 2404. 出现最频繁的偶数元素
 *
 * 给你一个整数数组 nums ，返回出现最频繁的偶数元素。

 如果存在多个满足条件的元素，只需要返回 最小 的一个。如果不存在这样的元素，返回 -1 。

  

 示例 1：

 输入：nums = [0,1,2,2,4,4,1]
 输出：2
 解释：
 数组中的偶数元素为 0、2 和 4 ，在这些元素中，2 和 4 出现次数最多。
 返回最小的那个，即返回 2 。
 示例 2：

 输入：nums = [4,4,4,9,2,4]
 输出：4
 解释：4 是出现最频繁的偶数元素。
 示例 3：

 输入：nums = [29,47,21,41,13,37,25,7]
 输出：-1
 解释：不存在偶数元素。
  

 提示：

 1 <= nums.length <= 2000
 0 <= nums[i] <= 105

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/most-frequent-even-element
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int mostFrequentEven(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            if (num % 2 == 1) {
                continue;
            }
            int count = countMap.getOrDefault(num, 0);
            count++;
            countMap.put(num, count);
        }
        // 找到最大的
        int maxKey = -1;
        for (Map.Entry<Integer, Integer> enrty : countMap.entrySet()) {
            if (maxKey == -1) {
                maxKey = enrty.getKey();
                continue;
            }
            if (countMap.get(maxKey) > enrty.getValue()
                    || (countMap.get(maxKey) == enrty.getValue() && maxKey <= enrty.getKey())) {
                continue;
            }
            maxKey = enrty.getKey();
        }
        return maxKey;
    }

}
