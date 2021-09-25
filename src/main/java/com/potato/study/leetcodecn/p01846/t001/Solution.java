package com.potato.study.leetcodecn.p01846.t001;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import org.junit.Assert;

/**
 * 1846. 减小和重新排列数组后的最大元素
 *
 * 给你一个正整数数组 arr 。请你对 arr 执行一些操作（也可以不进行任何操作），使得数组满足以下条件：
 *
 * arr 中 第一个 元素必须为 1 。
 * 任意相邻两个元素的差的绝对值 小于等于 1 ，也就是说，对于任意的 1 <= i < arr.length （数组下标从 0 开始），都满足 abs(arr[i] - arr[i - 1]) <= 1 。abs(x)
 *  为 x 的绝对值。
 * 你可以执行以下 2 种操作任意次：
 *
 * 减小 arr 中任意元素的值，使其变为一个 更小的正整数 。
 * 重新排列 arr 中的元素，你可以以任意顺序重新排列。
 * 请你返回执行以上操作后，在满足前文所述的条件下，arr 中可能的 最大值 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：arr = [2,2,1,2,1]
 * 输出：2
 * 解释：
 * 我们可以重新排列 arr 得到 [1,2,2,2,1] ，该数组满足所有条件。
 * arr 中最大元素为 2 。
 * 示例 2：
 *
 * 输入：arr = [100,1,1000]
 * 输出：3
 * 解释：
 * 一个可行的方案如下：
 * 1. 重新排列 arr 得到 [1,100,1000] 。
 * 2. 将第二个元素减小为 2 。
 * 3. 将第三个元素减小为 3 。
 * 现在 arr = [1,2,3] ，满足所有条件。
 * arr 中最大元素为 3 。
 * 示例 3：
 *
 * 输入：arr = [1,2,3,4,5]
 * 输出：5
 * 解释：数组已经满足所有条件，最大元素为 5 。
 *  
 *
 * 提示：
 *
 * 1 <= arr.length <= 105
 * 1 <= arr[i] <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-element-after-decreasing-and-rearranging
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        if (null == arr || arr.length == 0) {
            return -1;
        }
        // 1. 统计值出现次数 用一个数组统计 超过 arr len 按照len统计
        int[] times = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= arr.length) {
                times[arr[i]]++;
            } else {
                times[arr.length]++;
            }
        }
        // 2. 从1 找出现次数 并使用 miss 统计 之前有多少个 数字是占用了之后的资源
        int missCount = 0;
        int total = 0;
        for (int i = 1; i <= arr.length; i++) {
            // 3. 如果 当前出现次数 times = 0 那么需要miss 一下 ，
            int currentTime = times[i];
            if (currentTime > 0) {
                if (currentTime >= missCount) {
                    currentTime -= missCount;
                    missCount = 0;
                } else {
                    missCount -= currentTime;
                    currentTime = 0;
                }
            }

            if (currentTime == 0) {
                missCount++;
                total++;
            } else {
                total += currentTime;
            }
            // 当前是不是已经判断完了

            if (total == arr.length) {
                return i;
            }

        }
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                2,2,1,2,1
        };
        int i = solution.maximumElementAfterDecrementingAndRearranging(arr);
        System.out.println(i);
        Assert.assertEquals(2, i);

        arr = new int[] {
                100,1,1000
        };
        i = solution.maximumElementAfterDecrementingAndRearranging(arr);
        System.out.println(i);
        Assert.assertEquals(3, i);
    }
}
