package com.potato.study.leetcodecn.p00327.t001;


import org.junit.Assert;

/**
 * 327. 区间和的个数
 *
 * 给你一个整数数组 nums 以及两个整数 lower 和 upper 。求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的 区间和的个数 。

 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。

  

 示例 1：
 输入：nums = [-2,5,-1], lower = -2, upper = 2
 输出：3
 解释：存在三个区间：[0,0]、[2,2] 和 [0,2] ，对应的区间和分别是：-2 、-1 、2 。
 示例 2：

 输入：nums = [0], lower = 0, upper = 0
 输出：1
  

 提示：

 1 <= nums.length <= 105
 -231 <= nums[i] <= 231 - 1
 -105 <= lower <= upper <= 105
 题目数据保证答案是一个 32 位 的整数

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/count-of-range-sum
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    private int lower;
    private int upper;


    /**
     * https://leetcode.cn/problems/count-of-range-sum/solution/qu-jian-he-de-ge-shu-by-leetcode-solution/
     * @param nums
     * @param lower
     * @param upper
     * @return
     */
    public int countRangeSum(int[] nums, int lower, int upper) {
        // 求数组的前缀和
        int n = nums.length;
        // 处理 0的情况 因为可以是整个数组
        long[] prefixSum = new long[n+1];
        for (int i = 1; i < prefixSum.length; i++) {
            prefixSum[i] = prefixSum[i-1] + nums[i-1];
        }
        // 分治 归并排序
        this.lower = lower;
        this.upper = upper;
        int left = 0;
        int right = n;
        // 每次归并合并时 进行计算 对于每一一个左边的位置 找到右边的开始和结束位置 减去左边位置的和 为  [lower, upper]  的 计算个数
        return mergeAndSort(prefixSum, left, right);
    }

    /**
     *
     * @param prefixSum
     * @param left
     * @param right
     */
    private int mergeAndSort(long[] prefixSum, int left, int right) {
        if (left == right) {
            return 0;
        }
        // 使用mid 分割
        int mid = (left + right) / 2;
        int leftRes = mergeAndSort(prefixSum, left, mid);
        int rightRes = mergeAndSort(prefixSum, mid + 1, right);

        // 业务逻辑 这里 左边和右边有序 枚举左边每个位置作为开始位置
        int total = leftRes + rightRes;
        int start = mid + 1;
        int end = mid + 1;
        for (int i = left; i <= mid; i++) {
            //  在右边找到窗口
            while (start <= right && prefixSum[start] - prefixSum[i] < lower) {
                start++;
            }
            while (end <= right && prefixSum[end] - prefixSum[i] <= upper) {
                end++;
            }

            if (end >= start) {
                total += (end - start);
            }

        }
        // 归并逻辑
        merge(prefixSum, left, right, mid);
        return total;

    }

    /**
     * 归并 【left，mid】， 【mid+1, right】
     * 并对 left 到 right 排序
     * @param prefixSum
     * @param left
     * @param right
     * @param mid
     */
    private void merge(long[] prefixSum, int left, int right, int mid) {
        // 先将 left 到 right copy出来
        long[] temp = new long[right - left + 1];
        for (int i = left; i <= right; i++) {
            temp[i - left] = prefixSum[i];
        }

        int i = left;
        int j = mid + 1;
        // 枚举 left 和 right 每个位置
        for (int k = left; k <= right; k++) {
            if (i > mid) {
                // i 消耗完了
                prefixSum[k] = temp[j-left];
                j++;
            } else if (j > right) {
                // j 消耗完了
                prefixSum[k] = temp[i-left];
                i++;
            } else {
                // ij 都没有消耗完
                if (temp[i-left] <= temp[j-left]) {
                    prefixSum[k] = temp[i-left];
                    i++;
                } else {
                    prefixSum[k] = temp[j-left];
                    j++;
                }

            }
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{-2,5,-1};
        int lower = -2;
        int upper = 2;
        int i = solution.countRangeSum(nums, lower, upper);
        System.out.println(i);
        Assert.assertEquals(3, i);


        nums = new int[]{0};
        lower = 0;
        upper = 0;
        i = solution.countRangeSum(nums, lower, upper);
        System.out.println(i);
        Assert.assertEquals(1, i);
    }
}
