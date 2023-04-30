package com.potato.study.leetcodecn.p01224.t001;


import java.util.HashMap;
import java.util.Map;

/**
 * 1224. 最大相等频率
 *
 * 给你一个正整数数组 nums，请你帮忙从该数组中找出能满足下面要求的 最长 前缀，并返回该前缀的长度：

 从前缀中 恰好删除一个 元素后，剩下每个数字的出现次数都相同。
 如果删除这个元素后没有剩余元素存在，仍可认为每个数字都具有相同的出现次数（也就是 0 次）。

  

 示例 1：

 输入：nums = [2,2,1,1,5,3,3,5]
 输出：7
 解释：对于长度为 7 的子数组 [2,2,1,1,5,3,3]，如果我们从中删去 nums[4] = 5，就可以得到 [2,2,1,1,3,3]，里面每个数字都出现了两次。
 示例 2：

 输入：nums = [1,1,1,2,2,2,3,3,3,4,4,4,5]
 输出：13
  

 提示：

 2 <= nums.length <= 105
 1 <= nums[i] <= 105

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/maximum-equal-frequency
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/maximum-equal-frequency/solution/zui-da-xiang-deng-pin-lu-by-leetcode-sol-5y2m/
     * @param nums
     * @return
     */
    public int maxEqualFreq(int[] nums) {
        // count 记录 每个元素出现的个数
        Map<Integer, Integer> countMap = new HashMap<>();
        // freq 记录出现为 x的元素 有多少个
        Map<Integer, Integer> freqMap = new HashMap<>();
        // maxCount 当前元素中出现次数最多是多少
        int maxCount = 0;
        // 遍历 nums 通过 count 和 freq 记录出现了多少次
        int maxPrefixLen = 0;
        for (int i = 0; i < nums.length; i++) {
            int target = nums[i];

            Integer count = countMap.getOrDefault(target, 0);
            count++;
            countMap.put(target, count);

            // freqMap 修改
            Integer freq = freqMap.getOrDefault(count, 0);
            freq++;
            // 之前减去一下
            freqMap.put(count, freq);
            // 之前的不要了
            if (count > 1) {
                freqMap.put(count-1, freqMap.get(count-1) - 1);
            }
            maxCount = Math.max(maxCount, count);
            // 判断当前是否满足条件
            // 对于 当前出现次数判断是不是 按照一下三种情况 全部是出现1次 maxCount ，
            // 只有一个 出现 maxCount 次 其他全是出现 maxCount -1次
            // 或者只要一个是1次 其他都是maxCount 次
            if (maxCount == 1
                    || i+1 == maxCount + freqMap.getOrDefault(maxCount-1, 0) * (maxCount-1)
                    || i+1 == 1 + freqMap.getOrDefault(maxCount, 0) * maxCount) {
                maxPrefixLen = i+1;
            }

        }
        return maxPrefixLen;
    }
}
