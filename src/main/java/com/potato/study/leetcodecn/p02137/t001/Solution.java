package com.potato.study.leetcodecn.p02137.t001;

/**
 * 2137. 通过倒水操作让所有的水桶所含水量相等
 *
 * 你有 n 个水桶，每个水桶中所含的水量用一个 下标从 0 开始 的数组 buckets 给出，第 i 个水桶中有 buckets[i] 升水。
 *
 * 你想让所有的水桶中所含的水量相同。你可以从一个水桶向其它任意一个水桶倒任意数量的水（可以不是整数）。但是，你每倒 k 升水，百分之 loss 的水会洒掉。
 *
 * 请返回经过倒水操作，所有水桶中的水量相同时，每个水桶中的 最大 水量。如果你的答案和标准答案的误差不超过 10-5，那么答案将被通过。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: buckets = [1,2,7], loss = 80
 * 输出: 2.00000
 * 解释: 从水桶 2 向水桶 0 倒 5 升水。
 * 5 * 80% = 4 升水会洒掉，水桶 0 只会获得 5 - 4 = 1 升水。
 * 此时所有的水桶中都含有 2 升水，所以返回 2。
 * 示例 2:
 *
 * 输入: buckets = [2,4,6], loss = 50
 * 输出: 3.50000
 * 解释: 从水桶 1 向水桶 0 倒 0.5 升水。
 * 0.5 * 50% = 0.25 升水会洒掉，水桶 0 只会获得 0.5 - 0.25 = 0.25 升水。
 * 此时, buckets = [2.25, 3.5, 6].
 *
 * 从水桶 2 向水桶 0 倒 2.5 升水。
 * 2.5 * 50% = 1.25 升水会洒掉，水桶 0 只会获得 2.5 - 1.25 = 1.25 升水。
 * 此时所有的水桶中都含有 3.5 升水，所以返回 3.5。
 * 示例 3:
 *
 * 输入: buckets = [3,3,3,3], loss = 40
 * 输出: 3.00000
 * 解释: 所有的水桶已经含有相同的水量。
 *  
 *
 * 提示:
 *
 * 1 <= buckets.length <= 105
 * 0 <= buckets[i] <= 105
 * 0 <= loss <= 99
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/pour-water-between-buckets-to-make-water-levels-equal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public double equalizeWater(int[] buckets, int loss) {
        // 二分法 求水最多能多少 使用 check 函数 确定
        double left = 0;
        double right = 1_00001;

        while (Math.abs(right - left) > 10e-10) {
            double mid = (left + right) / 2;
            boolean res = check(mid, buckets, loss);
            if (res) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private boolean check(double mid, int[] buckets, int loss) {
        double add = 0.0;
        double minus = 0.0;
        for (int bucket : buckets) {
            if (bucket > mid) {
                add += (bucket - mid);
            } else if (bucket < mid) {
                minus += (mid - bucket);
            }
        }
        // 算下损失
        add *= ((100 - loss) / 100.0);
        return add >= minus;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] buckets = new int[] {
            1,2,7
        };
        int loss = 80;
        double v = solution.equalizeWater(buckets, loss);
        System.out.println(v);

    }
}
