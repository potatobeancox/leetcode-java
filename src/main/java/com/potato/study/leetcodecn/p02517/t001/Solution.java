package com.potato.study.leetcodecn.p02517.t001;

import java.util.Arrays;

/**
 * 2517. 礼盒的最大甜蜜度
 *
 * 给你一个正整数数组 price ，其中 price[i] 表示第 i 类糖果的价格，另给你一个正整数 k 。
 *
 * 商店组合 k 类 不同 糖果打包成礼盒出售。礼盒的 甜蜜度 是礼盒中任意两种糖果 价格 绝对差的最小值。
 *
 * 返回礼盒的 最大 甜蜜度。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：price = [13,5,1,8,21,2], k = 3
 * 输出：8
 * 解释：选出价格分别为 [13,5,21] 的三类糖果。
 * 礼盒的甜蜜度为 min(|13 - 5|, |13 - 21|, |5 - 21|) = min(8, 8, 16) = 8 。
 * 可以证明能够取得的最大甜蜜度就是 8 。
 * 示例 2：
 *
 * 输入：price = [1,3,1], k = 2
 * 输出：2
 * 解释：选出价格分别为 [1,3] 的两类糖果。
 * 礼盒的甜蜜度为 min(|1 - 3|) = min(2) = 2 。
 * 可以证明能够取得的最大甜蜜度就是 2 。
 * 示例 3：
 *
 * 输入：price = [7,7,7,7], k = 2
 * 输出：0
 * 解释：从现有的糖果中任选两类糖果，甜蜜度都会是 0 。
 *  
 *
 * 提示：
 *
 * 1 <= price.length <= 105
 * 1 <= price[i] <= 109
 * 2 <= k <= price.length
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-tastiness-of-candy-basket
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 2517
    public int maximumTastiness(int[] price, int k) {
        // 对 price 进行排序 找到 left = 0  right = 两个最大值的差值
        Arrays.sort(price);
        // 二分发 对 k进行查找 找一下 当前是否满足 k 如果满足 k往大的方向移动
        int left = 0;
        int right = price[price.length - 1] - price[0];
        int max = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (checkValid(price, mid, k)) {
                max = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return max;
    }

    /**
     * 是否可以从 price 中找到 k个元素，使得，俩俩元素的差大于等于 mid
     * @param price
     * @param mid
     * @param k
     * @return
     */
    private boolean checkValid(int[] price, int mid, int k) {
        // 第一个糖果是一定要的，因为只要后面有要的 获取第一个会更合适
        int count = 1;
        int pre = price[0];
        for (int i = 1; i < price.length; i++) {
            if (price[i] >= pre + mid) {
                count++;
            }
            if (count >= k) {
                return true;
            }
        }
        return false;
    }

}
