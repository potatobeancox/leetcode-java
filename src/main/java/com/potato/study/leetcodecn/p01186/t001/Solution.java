package com.potato.study.leetcodecn.p01186.t001;


import org.junit.Assert;

/**
 * 1186. 删除一次得到子数组最大和
 *
 *给你一个整数数组，返回它的某个 非空 子数组（连续元素）在执行一次可选的删除操作后，所能得到的最大元素总和。换句话说，你可以从原数组中选出一个子数组，并可以决定要不要从中删除一个元素（只能删一次哦），（删除后）子数组中至少应当有一个元素，然后该子数组（剩下）的元素总和是所有子数组之中最大的。

 注意，删除一个元素后，子数组 不能为空。

  

 示例 1：

 输入：arr = [1,-2,0,3]
 输出：4
 解释：我们可以选出 [1, -2, 0, 3]，然后删掉 -2，这样得到 [1, 0, 3]，和最大。
 示例 2：

 输入：arr = [1,-2,-2,3]
 输出：3
 解释：我们直接选出 [3]，这就是最大和。
 示例 3：

 输入：arr = [-1,-1,-1,-1]
 输出：-1
 解释：最后得到的子数组不能为空，所以我们不能选择 [-1] 并从中删去 -1 来得到 0。
 我们应该直接选择 [-1]，或者选择 [-1, -1] 再从中删去一个 -1。
  

 提示：

 1 <= arr.length <= 105
 -104 <= arr[i] <= 104

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/maximum-subarray-sum-with-one-deletion
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/swap-for-longest-repeated-character-substring/solution/hua-dong-chuang-kou-yi-ci-bian-li-by-tufeibaba/
     * @param arr
     * @return
     */
    public int maximumSum(int[] arr) {
        // dp0 i 没有删除过 dp1 删除过一次 最大
        int[] dp0 = new int[arr.length];
        int[] dp1 = new int[arr.length];
        // 遍历 arr 每个位置 找到小于 0的位置 状态转移 dp0 i= max（dp0 i-1， 0） + num[i],  dp1 i= max（dp0 i-1， 0） + num[i] 上一个位置 没删除 删除了 i
        int max = Integer.MIN_VALUE;
        dp0[0] = arr[0];
        max = Math.max(dp0[0], max);
        dp1[0] = 0;
        for (int i = 1; i < arr.length; i++) {
            // 之前从未删除过 判断下之前位置是否大于0
            dp0[i] = Math.max(0, dp0[i-1]) + arr[i];
            // 只删除一次 之前删除过一次 这次学乖了， 之前没有删除过
            dp1[i] = Math.max(dp1[i-1] + arr[i], dp0[i-1]);

            max = Math.max(max, dp0[i]);
            max = Math.max(max, dp1[i]);
        }
        return max;
    }

}

