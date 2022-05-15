package com.potato.study.leetcodecn.p02064.t001;

import org.junit.Assert;

/**
 * 2064. 分配给商店的最多商品的最小值
 *
 * 给你一个整数 n ，表示有 n 间零售商店。总共有 m 种产品，每种产品的数目用一个下标从 0 开始的整数数组 quantities 表示，其中 quantities[i] 表示第 i 种商品的数目。

 你需要将 所有商品 分配到零售商店，并遵守这些规则：

 一间商店 至多 只能有 一种商品 ，但一间商店拥有的商品数目可以为 任意 件。
 分配后，每间商店都会被分配一定数目的商品（可能为 0 件）。用 x 表示所有商店中分配商品数目的最大值，你希望 x 越小越好。也就是说，你想 最小化 分配给任意商店商品数目的 最大值 。
 请你返回最小的可能的 x 。

  

 示例 1：

 输入：n = 6, quantities = [11,6]
 输出：3
 解释： 一种最优方案为：
 - 11 件种类为 0 的商品被分配到前 4 间商店，分配数目分别为：2，3，3，3 。
 - 6 件种类为 1 的商品被分配到另外 2 间商店，分配数目分别为：3，3 。
 分配给所有商店的最大商品数目为 max(2, 3, 3, 3, 3, 3) = 3 。
 示例 2：

 输入：n = 7, quantities = [15,10,10]
 输出：5
 解释：一种最优方案为：
 - 15 件种类为 0 的商品被分配到前 3 间商店，分配数目为：5，5，5 。
 - 10 件种类为 1 的商品被分配到接下来 2 间商店，数目为：5，5 。
 - 10 件种类为 2 的商品被分配到最后 2 间商店，数目为：5，5 。
 分配给所有商店的最大商品数目为 max(5, 5, 5, 5, 5, 5, 5) = 5 。
 示例 3：

 输入：n = 1, quantities = [100000]
 输出：100000
 解释：唯一一种最优方案为：
 - 所有 100000 件商品 0 都分配到唯一的商店中。
 分配给所有商店的最大商品数目为 max(100000) = 100000 。
  

 提示：

 m == quantities.length
 1 <= m <= n <= 105
 1 <= quantities[i] <= 105

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/minimized-maximum-of-products-distributed-to-any-store
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 直接二分
     * @param n
     * @param quantities
     * @return
     */
    public int minimizedMaximum(int n, int[] quantities) {

        long left = 1;
        long right = 1_0000_1;
        long res = -1;

        long sum = 0;
        for (int i = 0; i < quantities.length; i++) {
            sum += quantities[i];
        }

        while (left <= right) {
            long mid = (left + right) / 2;
            // 按照最多 mid 分配 如果 只有一个 分配mid 还是不够分 mid 需要变小 可以分配 0 件
            if (sum < mid) {
                // mid 太大了
                right = mid - 1;
                continue;
            }
            long current = 0;
            for (int q: quantities) {
                current += q / mid;
                if (q % mid != 0) {
                    current++;
                }
            }
            // 全按 mid 分 还有剩余 说明 mid 选小了 变大
            if (n < current) {
                left = mid + 1;
                continue;
            }
            // 能分配 记录下 mid 看看 还能不能再小点
            res = mid;
            right = mid - 1;
        }
        return (int)res;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 100000;
        int[] quantities = new int[] {
            1
        };
        int i = solution.minimizedMaximum(n, quantities);
        System.out.println(i);
        Assert.assertEquals(1, i);
    }




}
