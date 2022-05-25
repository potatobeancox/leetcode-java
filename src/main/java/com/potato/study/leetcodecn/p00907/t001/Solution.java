package com.potato.study.leetcodecn.p00907.t001;

import java.util.Arrays;
import java.util.Stack;

import org.junit.Assert;

/**
 * 907. 子数组的最小值之和
 *
 * 给定一个整数数组 arr，找到 min(b) 的总和，其中 b 的范围为 arr 的每个（连续）子数组。

 由于答案可能很大，因此 返回答案模 10^9 + 7 。

  

 示例 1：

 输入：arr = [3,1,2,4]
 输出：17
 解释：
 子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
 最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。
 示例 2：

 输入：arr = [11,81,94,43,3]
 输出：444
  

 提示：

 1 <= arr.length <= 3 * 104
 1 <= arr[i] <= 3 * 104

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/sum-of-subarray-minimums
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode.cn/problems/sum-of-subarray-minimums/solution/zi-shu-zu-de-zui-xiao-zhi-zhi-he-by-leetcode/
     * @param arr
     * @return
     */
    public int sumSubarrayMins(int[] arr) {
        // 单调栈，找到left 第一个小于 i的数字
        int n = arr.length;
        int[] left = new int[n];
        Arrays.fill(left, -1);
        // 如果栈顶 大于等于 nums stack pop
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                left[i] = stack.peek();
            }
            stack.push(i);
        }
        // 找到 right 第一个小于 i的数字
        stack.clear();
        int[] right = new int[n];
        Arrays.fill(right, n);
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                right[i] = stack.peek();
            }
            stack.push(i);
        }
        // 对于每个位置 找到 左边到i的距离 和右边到 i的距离 i的值
        int mod = 1_000_000_000 + 7;
        long res = 0;
        for (int i = 0; i < n; i++) {
            res += (long)(i - left[i]) * (right[i] - i) % mod * arr[i] % mod;
            res %= mod;
        }
        return (int)res;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                3,1,2,4
        };
        int i = solution.sumSubarrayMins(arr);
        System.out.println(i);
        Assert.assertEquals(17, i);


        arr = new int[] {
                11,81,94,43,3
        };
        i = solution.sumSubarrayMins(arr);
        System.out.println(i);
        Assert.assertEquals(444, i);


        arr = new int[] {
                71,55,82,55
        };
        i = solution.sumSubarrayMins(arr);
        System.out.println(i);
        Assert.assertEquals(593, i);

    }


}
