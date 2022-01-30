package com.potato.study.leetcodecn.p02148.t001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2148. 元素计数
 *
 * 给你一个整数数组 nums ，统计并返回在 nums 中同时具有一个严格较小元素和一个严格较大元素的元素数目。

  

 示例 1：

 输入：nums = [11,7,2,15]
 输出：2
 解释：元素 7 ：严格较小元素是元素 2 ，严格较大元素是元素 11 。
 元素 11 ：严格较小元素是元素 7 ，严格较大元素是元素 15 。
 总计有 2 个元素都满足在 nums 中同时存在一个严格较小元素和一个严格较大元素。
 示例 2：

 输入：nums = [-3,3,3,90]
 输出：2
 解释：元素 3 ：严格较小元素是元素 -3 ，严格较大元素是元素 90 。
 由于有两个元素的值为 3 ，总计有 2 个元素都满足在 nums 中同时存在一个严格较小元素和一个严格较大元素。
  

 提示：

 1 <= nums.length <= 100
 -105 <= nums[i] <= 105

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/count-elements-with-strictly-smaller-and-greater-elements
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int countElements(int[] nums) {
        // 找到最大值 和最小值 统计个数
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            Integer count = countMap.getOrDefault(num, 0);
            count++;
            countMap.put(num, count);
            max = Math.max(max, num);
            min = Math.min(min, num);
        }

        if (max == min) {
            return nums.length - countMap.getOrDefault(min, 0);
        }
        return nums.length - countMap.getOrDefault(min, 0) - countMap.getOrDefault(max, 0);
    }
}
