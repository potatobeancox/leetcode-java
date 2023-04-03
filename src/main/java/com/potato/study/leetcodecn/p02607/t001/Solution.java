package com.potato.study.leetcodecn.p02607.t001;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * 2607. 使子数组元素和相等
 *
 * 给你一个下标从 0 开始的整数数组 arr 和一个整数 k 。数组 arr 是一个循环数组。换句话说，数组中的最后一个元素的下一个元素是数组中的第一个元素，数组中第一个元素的前一个元素是数组中的最后一个元素。
 *
 * 你可以执行下述运算任意次：
 *
 * 选中 arr 中任意一个元素，并使其值加上 1 或减去 1 。
 * 执行运算使每个长度为 k 的 子数组 的元素总和都相等，返回所需要的最少运算次数。
 *
 * 子数组 是数组的一个连续部分。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：arr = [1,4,1,3], k = 2
 * 输出：1
 * 解释：在下标为 1 的元素那里执行一次运算，使其等于 3 。
 * 执行运算后，数组变为 [1,3,1,3] 。
 * - 0 处起始的子数组为 [1, 3] ，元素总和为 4
 * - 1 处起始的子数组为 [3, 1] ，元素总和为 4
 * - 2 处起始的子数组为 [1, 3] ，元素总和为 4
 * - 3 处起始的子数组为 [3, 1] ，元素总和为 4
 * 示例 2：
 *
 * 输入：arr = [2,5,5,7], k = 3
 * 输出：5
 * 解释：在下标为 0 的元素那里执行三次运算，使其等于 5 。在下标为 3 的元素那里执行两次运算，使其等于 5 。
 * 执行运算后，数组变为 [5,5,5,5] 。
 * - 0 处起始的子数组为 [5, 5, 5] ，元素总和为 15
 * - 1 处起始的子数组为 [5, 5, 5] ，元素总和为 15
 * - 2 处起始的子数组为 [5, 5, 5] ，元素总和为 15
 * - 3 处起始的子数组为 [5, 5, 5] ，元素总和为 15
 *  
 *
 * 提示：
 *
 * 1 <= k <= arr.length <= 105
 * 1 <= arr[i] <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/make-k-subarray-sums-equal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 2607
    public long makeSubKSumEqual(int[] arr, int k) {
        // 首先看下 最小的k 是多少 k和n的最小公约数
        int kk = gcd(arr.length, k);
        // 对于 小的周期 kk，周期内的每个元素位置开始遍历 分成kk组
        long total = 0;
        for (int i = 0; i < kk; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = i; j < arr.length; j+= kk) {
                list.add(arr[j]);
            }
            // 对于每种分组，找到中位数，都变成中间位置数 可以排序
            Collections.sort(list);
            int median = list.get(list.size() / 2);
            // 计算每个组 都变成中位数 要搞多少次
            for (int ele : list) {
                total += Math.abs((long) ele - median);
            }
        }
        return total;
    }

    private int gcd(int a, int b) {
        if (a % b == 0) {
            return b;
        }
        return gcd(b, a % b);
    }


}
