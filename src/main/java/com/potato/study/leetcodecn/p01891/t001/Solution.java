package com.potato.study.leetcodecn.p01891.t001;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1891. 割绳子
 *
 * 给定一个整数数组 ribbons 和一个整数 k，数组每项 ribbons[i] 表示第 i 条绳子的长度。对于每条绳子，你可以将任意切割成一系列长度为正整数的部分，或者选择不进行切割。

 例如，如果给你一条长度为 4 的绳子，你可以：

 保持绳子的长度为 4 不变；
 切割成一条长度为 3 和一条长度为 1 的绳子；
 切割成两条长度为 2 的绳子；
 切割成一条长度为 2 和两条长度为 1 的绳子；
 切割成四条长度为 1 的绳子。
 你的任务是最终得到 k 条完全一样的绳子，他们的长度均为相同的正整数。如果绳子切割后有剩余，你可以直接舍弃掉多余的部分。

 对于这 k 根绳子，返回你能得到的绳子最大长度；如果你无法得到 k 根相同长度的绳子，返回 0。

  

 示例 1:

 输入: ribbons = [9,7,5], k = 3
 输出: 5
 解释:
 - 把第一条绳子切成两部分，一条长度为 5，一条长度为 4；
 - 把第二条绳子切成两部分，一条长度为 5，一条长度为 2；
 - 第三条绳子不进行切割；
 现在，你得到了 3 条长度为 5 的绳子。
 示例 2:

 输入: ribbons = [7,5,9], k = 4
 输出: 4
 解释:
 - 把第一条绳子切成两部分，一条长度为 4，一条长度为 3；
 - 把第二条绳子切成两部分，一条长度为 4，一条长度为 1；
 - 把第二条绳子切成三部分，一条长度为 4，一条长度为 4，还有一条长度为 1；
 现在，你得到了 4 条长度为 4 的绳子。
 示例 3:

 输入: ribbons = [5,7,9], k = 22
 输出: 0
 解释: 由于绳子长度需要为正整数，你无法得到 22 条长度相同的绳子。
  

 提示:

 1 <= ribbons.length <= 105
 1 <= ribbons[i] <= 105
 1 <= k <= 109


 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/cutting-ribbons
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int maxLength(int[] ribbons, int k) {
        // 找到最大的分割 ，让分割大于等于 当前值 大于 k的大小
        int left = 1;
        int right = 1;
        for (int ribbon : ribbons) {
            right = Math.max(right, ribbon);
        }
        // 二分查找
        int res = 0;
        while (left <= right) {
            int mid = (right + left) / 2;
            // 大于等于 k 看还有没有更大的
            if (check(mid, ribbons, k)) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

    /**
     * 检查 分成 target 个大小的绳子是否能分大于等于 k段
     * @param target
     * @param ribbons
     * @param k
     * @return
     */
    private boolean check(int target, int[] ribbons, int k) {
        int count = 0;
        for (int ribbon : ribbons) {
            count += (ribbon / target);
        }
        return count >= k;
    }


}