package com.potato.study.leetcodecn.p01005.t001;

import org.junit.Assert;

import java.util.PriorityQueue;

/**
 * 1005. K 次取反后最大化的数组和
 *
 * 给定一个整数数组 A，我们只能用以下方法修改该数组：我们选择某个索引 i 并将 A[i] 替换为 -A[i]，然后总共重复这个过程 K 次。（我们可以多次选择同一个索引 i。）

 以这种方式修改数组后，返回数组可能的最大和。

  

 示例 1：

 输入：A = [4,2,3], K = 1
 输出：5
 解释：选择索引 (1,) ，然后 A 变为 [4,-2,3]。
 示例 2：

 输入：A = [3,-1,0,2], K = 3
 输出：6
 解释：选择索引 (1, 2, 2) ，然后 A 变为 [3,1,0,2]。
 示例 3：

 输入：A = [2,-3,-1,5,-4], K = 2
 输出：13
 解释：选择索引 (1, 4) ，然后 A 变为 [2,3,-1,5,4]。
  

 提示：

 1 <= A.length <= 10000
 1 <= K <= 10000
 -100 <= A[i] <= 100

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/maximize-sum-of-array-after-k-negations
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 统计负数的数量
     * 看能否将所有负数都转换成正数，不行的话 挑大的转换
     * @param nums
     * @param k
     * @return
     */
    public int largestSumAfterKNegations(int[] nums, int k) {
        int negCount = 0;
        int min = Integer.MAX_VALUE;
        long sum = 0;
        long sumPositive = 0;
        PriorityQueue<Integer> integerPriority = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        for (int num : nums) {
            if (num < 0) {
                negCount++;
                min = Math.min(min, -1 * num);
                sum -= num;
                integerPriority.add(-1 * num);
            } else {
                min = Math.min(min, num);
                sum +=num;
                sumPositive += num;
            }
        }
        if (k >= negCount) {
            int remind = k - negCount;
            if (remind == 0) {
                return (int) sum;
            }
            if (remind % 2 == 0) {
                return (int) sum;
            }
            return (int)(sum - 2 * min);
        } else {
            // 优先级队列 找到小的
            for (int i = 0; i < k; i++) {
                sumPositive += integerPriority.poll();
            }
            while (!integerPriority.isEmpty()) {
                sumPositive -= integerPriority.poll();
            }
            return (int)sumPositive;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                4,2,3
        };
        int k = 1;
        int i = solution.largestSumAfterKNegations(arr, k);
        System.out.println(i);
        Assert.assertEquals(5, i);

        arr = new int[] {
                3,-1,0,2
        };
        k = 3;
        i = solution.largestSumAfterKNegations(arr, k);
        System.out.println(i);
        Assert.assertEquals(6, i);


        arr = new int[] {
                -5,5,6,1,4,8,-8,7,-5
        };
        k = 1;
        i = solution.largestSumAfterKNegations(arr, k);
        System.out.println(i);
        Assert.assertEquals(29, i);
    }
}
