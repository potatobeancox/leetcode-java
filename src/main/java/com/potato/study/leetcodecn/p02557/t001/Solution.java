package com.potato.study.leetcodecn.p02557.t001;

import java.util.Arrays;

/**
 * 2557. 从一个范围内选择最多整数 II
 *
 * 给你一个整数数组 banned 和两个整数 n 和 maxSum 。你需要按照以下规则选择一些整数：

 被选择整数的范围是 [1, n] 。
 每个整数 至多 选择 一次 。
 被选择整数不能在数组 banned 中。
 被选择整数的和不超过 maxSum 。
 请你返回按照上述规则 最多 可以选择的整数数目。

  

 示例 1：

 输入：banned = [1,4,6], n = 6, maxSum = 4
 输出：1
 解释：你可以选择整数 2 或 3 。
 2 和 3 在范围 [1, 6] 内，且它们都不在 banned 中，它们中的任何一个都没有超过 maxSum 。
 示例 2：

 输入：banned = [4,3,5,6], n = 7, maxSum = 18
 输出：3
 解释：你可以选择整数 1, 2 和 7 。
 它们都在范围 [1, 7] 中，且都没出现在 banned 中，它们的和是 10 ，没有超过 maxSum 。
  

 提示：

 1 <= banned.length <= 105
 1 <= banned[i] <= n <= 109
 1 <= maxSum <= 1015

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/maximum-number-of-integers-to-choose-from-a-range-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 2557
    public int maxCount(int[] banned, int n, long maxSum) {
        // 二分法 最多选择 mid 个数字，最小sum是多少 按照是否小于 maxSum计算
        int left = 1;
        // 统计有多少个 banned 小于等于n
        int belowCount = 0;
        for (int ban : banned) {
            if (ban <= n) {
                belowCount++;
            }
        }
        Arrays.sort(banned);
        int right = n - belowCount;
        int res = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int sum = getSumOfCount(banned, mid, n);
            if (sum <= maxSum) {
                res = mid;
                left = mid + 1;
            } else {
                // 小一点
                right = mid - 1;
            }
        }
        return res;
    }

    /**
     * banned 已经排序，在n以内找到count 看看 和是多少
     * [1-n]
     * 题目能够保证 count 一定能找完
     * @param banned
     * @param count
     * @param n
     * @return
     */
    private int getSumOfCount(int[] banned, int count, int n) {
        int prevBan = 0;
        int banIndex = 0;
        int selectCount = 0;
        long sum = 0;
        while (banIndex <= banned.length && selectCount < count) {
            int ban;
            if (banIndex != banned.length) {
                ban = banned[banIndex];
            } else {
                ban = n+1;
            }
            banIndex++;
            // 可以使用的数字
            int left = prevBan + 1;
            int right = ban - 1;
            if (left > right) {
                break;
            }
            int len = right - left + 1;
            if (selectCount + len > count) {
                len = count - selectCount;
                right = left + len - 1;
            }
            selectCount += len;
            sum += (left + right) * (len) / 2;
            prevBan = ban;
        }
        return (int) sum;
    }


}
