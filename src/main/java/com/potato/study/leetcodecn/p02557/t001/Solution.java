package com.potato.study.leetcodecn.p02557.t001;

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
        // 如果 n个数 总和 小于等于 maxSum 那么就是 n - banned 小于等于 n的数量


        // maxSum 求最高到几 （1+n）* n <= maxSum * 2
        return -1;
    }


}
