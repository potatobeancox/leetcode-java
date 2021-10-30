package com.potato.study.leetcodecn.p02032.t001;

import java.util.*;

/**
 *
 *
 * 2032. 至少在两个数组中出现的值
 *
 * 给你三个整数数组 nums1、nums2 和 nums3 ，请你构造并返回一个 不同 数组，且由 至少 在 两个 数组中出现的所有值组成。数组中的元素可以按 任意 顺序排列。
  

 示例 1：

 输入：nums1 = [1,1,3,2], nums2 = [2,3], nums3 = [3]
 输出：[3,2]
 解释：至少在两个数组中出现的所有值为：
 - 3 ，在全部三个数组中都出现过。
 - 2 ，在数组 nums1 和 nums2 中出现过。
 示例 2：

 输入：nums1 = [3,1], nums2 = [2,3], nums3 = [1,2]
 输出：[2,3,1]
 解释：至少在两个数组中出现的所有值为：
 - 2 ，在数组 nums2 和 nums3 中出现过。
 - 3 ，在数组 nums1 和 nums2 中出现过。
 - 1 ，在数组 nums1 和 nums3 中出现过。
 示例 3：

 输入：nums1 = [1,2,2], nums2 = [4,3,3], nums3 = [5]
 输出：[]
 解释：不存在至少在两个数组中出现的值。
  

 提示：

 1 <= nums1.length, nums2.length, nums3.length <= 100
 1 <= nums1[i], nums2[j], nums3[k] <= 100

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/two-out-of-three
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        Set<Integer> set1 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }
        Set<Integer> set2 = new HashSet<>();
        for (int num : nums2) {
            set2.add(num);
        }
        Set<Integer> set3 = new HashSet<>();
        for (int num: nums3) {
            set3.add(num);
        }
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : set1) {
            Integer count = countMap.getOrDefault(num, 0);
            count++;
            countMap.put(num, count);
        }
        for (int num : set2) {
            Integer count = countMap.getOrDefault(num, 0);
            count++;
            countMap.put(num, count);
        }
        for (int num : set3) {
            Integer count = countMap.getOrDefault(num, 0);
            count++;
            countMap.put(num, count);
        }
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() >= 2) {
                result.add(entry.getKey());
            }
        }
        return result;
    }
}
