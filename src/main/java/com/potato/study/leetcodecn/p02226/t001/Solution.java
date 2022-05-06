package com.potato.study.leetcodecn.p02226.t001;

import org.junit.Assert;

import java.util.*;

/**
 * 2226. 每个小孩最多能分到多少糖果
 *
 * 给你一个 下标从 0 开始 的整数数组 candies 。数组中的每个元素表示大小为 candies[i] 的一堆糖果。你可以将每堆糖果分成任意数量的 子堆 ，但 无法 再将两堆合并到一起。

 另给你一个整数 k 。你需要将这些糖果分配给 k 个小孩，使每个小孩分到 相同 数量的糖果。每个小孩可以拿走 至多一堆 糖果，有些糖果可能会不被分配。

 返回每个小孩可以拿走的 最大糖果数目 。

  

 示例 1：

 输入：candies = [5,8,6], k = 3
 输出：5
 解释：可以将 candies[1] 分成大小分别为 5 和 3 的两堆，然后把 candies[2] 分成大小分别为 5 和 1 的两堆。现在就有五堆大小分别为 5、5、3、5 和 1 的糖果。可以把 3 堆大小为 5 的糖果分给 3 个小孩。可以证明无法让每个小孩得到超过 5 颗糖果。
 示例 2：

 输入：candies = [2,5], k = 11
 输出：0
 解释：总共有 11 个小孩，但只有 7 颗糖果，但如果要分配糖果的话，必须保证每个小孩至少能得到 1 颗糖果。因此，最后每个小孩都没有得到糖果，答案是 0 。
  

 提示：

 1 <= candies.length <= 105
 1 <= candies[i] <= 107
 1 <= k <= 1012

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/maximum-candies-allocated-to-k-children
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * 二分法 最小left = 1 最大 等于  10e7
     * @param candies
     * @param k
     * @return
     */
    public int maximumCandies(int[] candies, long k) {
        long left = 1;
        long right = 1_000_000_1;
        int res = 0;
        while (left <= right) {
            // 每次分多少
            long mid = (left + right) / 2;
            // 能分多少堆
            long count = getCount(candies, mid);

            if (count >= k) {
                // 多分点
                res = (int) mid;
                left = mid + 1;
            } else {
                // count < k 少分点
                right = mid - 1;
            }
        }
        return res;
    }

    /**
     *
     * @param candies
     * @param mid 每堆分多少个
     * @return
     */
    private long getCount(int[] candies, long mid) {
        long count = 0;
        for (int candy : candies) {
            count += candy / mid;
        }
        return count;
    }


    /**
     * [5,8,6]
     3
     * @param args
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] candies = new int[]{
                5,8,6
        };
        long k = 3;
        int i = solution.maximumCandies(candies, k);
        System.out.println(i);
        Assert.assertEquals(5, i);
    }
}
