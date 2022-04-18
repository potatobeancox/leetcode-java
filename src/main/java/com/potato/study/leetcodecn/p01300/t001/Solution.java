package com.potato.study.leetcodecn.p01300.t001;


import com.potato.study.leetcode.domain.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;

/**
 * 1300. 转变数组后最接近目标值的数组和
 *
 * 给你一个整数数组 arr 和一个目标值 target ，请你返回一个整数 value ，使得将数组中所有大于 value 的值变成 value 后，数组的和最接近  target （最接近表示两者之差的绝对值最小）。

 如果有多种使得和最接近 target 的方案，请你返回这些整数中的最小值。

 请注意，答案不一定是 arr 中的数字。

  

 示例 1：

 输入：arr = [4,9,3], target = 10
 输出：3
 解释：当选择 value 为 3 时，数组会变成 [3, 3, 3]，和为 9 ，这是最接近 target 的方案。
 示例 2：

 输入：arr = [2,3,5], target = 10
 输出：5
 示例 3：

 输入：arr = [60864,25176,27249,21296,20204], target = 56803
 输出：11361
  

 提示：

 1 <= arr.length <= 10^4
 1 <= arr[i], target <= 10^5

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/sum-of-mutated-array-closest-to-target
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode-cn.com/problems/sum-of-mutated-array-closest-to-target/solution/zui-you-jie-fa-onlogn-by-java_lee/
     * @param arr
     * @param target
     * @return
     */
    public int findBestValue(int[] arr, int target) {
        // 排序 计算
        Arrays.sort(arr);
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            // 当前有多少 变成sum的
            int count = arr.length - i;
            // 计算差值
            int diff = sum + count * arr[i] - target;
            if (diff >= 0) {
                // 这块要四舍五入一下
                return arr[i] - (int)Math.round(1.0 * diff / count);
            }
            sum += arr[i];
        }
        // 没有结果 就使用最大值
        return arr[arr.length - 1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                4,9,3
        };
        int target = 10;
        int bestValue = solution.findBestValue(arr, target);
        System.out.println(bestValue);
        Assert.assertEquals(3, bestValue);


        arr = new int[] {
                2,3,5
        };
        target = 10;
        bestValue = solution.findBestValue(arr, target);
        System.out.println(bestValue);
        Assert.assertEquals(5, bestValue);

        arr = new int[] {
                60864,25176,27249,21296,20204
        };
        target = 56803;
        bestValue = solution.findBestValue(arr, target);
        System.out.println(bestValue);
        Assert.assertEquals(11361, bestValue);
    }
}
