package com.potato.study.leetcodecn.p00456.t001;

import org.junit.Assert;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * 456. 132 模式
 *
 * 给你一个整数数组 nums ，数组中共有 n 个整数。132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。

 如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。

  

 示例 1：

 输入：nums = [1,2,3,4]
 输出：false
 解释：序列中不存在 132 模式的子序列。
 示例 2：

 输入：nums = [3,1,4,2]
 输出：true
 解释：序列中有 1 个 132 模式的子序列： [1, 4, 2] 。
 示例 3：

 输入：nums = [-1,3,2,0]
 输出：true
 解释：序列中有 3 个 132 模式的的子序列：[-1, 3, 2]、[-1, 3, 0] 和 [-1, 2, 0] 。
  

 提示：

 n == nums.length
 1 <= n <= 2 * 105
 -109 <= nums[i] <= 109

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/132-pattern
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public boolean find132pattern(int[] nums) {
        // 枚举3 从左到右遍历 nums 使用leftMin 记录当前位置左边的最小值
        TreeMap<Integer, Integer> countMap = new TreeMap<>();
        for (int num : nums) {
            Integer count = countMap.getOrDefault(num, 0);
            count++;
            countMap.put(num, count);
        }
        // 再次遍历 当前位置使用 treeMap 记录 比3位置大1 的数字 如果有的话 返回 true 否则返回false
        int leftMin = nums[0];
        Integer count = countMap.get(leftMin);
        count--;
        if (count == 0) {
            countMap.remove(leftMin);
        } else {
            countMap.put(leftMin, count);
        }
        for (int i = 1; i < nums.length; i++) {
            // 处理当前i
            Integer cc = countMap.get(nums[i]);
            cc--;
            if (cc == 0) {
                countMap.remove(nums[i]);
            } else {
                countMap.put(nums[i], cc);
            }
            // 找到是是否满足条件
            if (leftMin < nums[i]) {
                Integer valueK = countMap.ceilingKey(leftMin + 1);
                if (valueK != null && valueK < nums[i]) {
                    return true;
                }
            } else {
                leftMin = nums[i];
            }
        }
        return false;
    }
}
