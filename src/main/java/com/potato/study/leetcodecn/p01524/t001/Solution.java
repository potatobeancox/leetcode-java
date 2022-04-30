package com.potato.study.leetcodecn.p01524.t001;

import org.junit.Assert;

/**
 * 1524. 和为奇数的子数组数目
 *
 * 给你一个整数数组 arr 。请你返回和为 奇数 的子数组数目。
 *
 * 由于答案可能会很大，请你将结果对 10^9 + 7 取余后返回。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：arr = [1,3,5]
 * 输出：4
 * 解释：所有的子数组为 [[1],[1,3],[1,3,5],[3],[3,5],[5]] 。
 * 所有子数组的和为 [1,4,9,3,8,5].
 * 奇数和包括 [1,9,3,5] ，所以答案为 4 。
 * 示例 2 ：
 *
 * 输入：arr = [2,4,6]
 * 输出：0
 * 解释：所有子数组为 [[2],[2,4],[2,4,6],[4],[4,6],[6]] 。
 * 所有子数组和为 [2,6,12,4,10,6] 。
 * 所有子数组和都是偶数，所以答案为 0 。
 * 示例 3：
 *
 * 输入：arr = [1,2,3,4,5,6,7]
 * 输出：16
 * 示例 4：
 *
 * 输入：arr = [100,100,99,99]
 * 输出：4
 * 示例 5：
 *
 * 输入：arr = [7]
 * 输出：1
 *  
 *
 * 提示：
 *
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i] <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-sub-arrays-with-odd-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int numOfSubarrays(int[] arr) {
        // 遍历 arr 记录 以当前位置 i 为最后一个位置的奇数子数组的长度 偶数子数组的长度 进行求和
        int oddCount = 0;
        int evenCount = 0;
        int total = 0;
        int mod = 1_000_000_000 + 7;
        for (int i = 0; i < arr.length; i++) {
            int lastOddCount = oddCount;
            int lastEvenCount = evenCount;
            if (arr[i] % 2 == 0) {
                if (lastOddCount > 0) {
                    oddCount = lastOddCount + 1;
                }
                evenCount = lastEvenCount + 1;
            } else {
                oddCount = lastEvenCount + 1;
                if (lastOddCount > 0) {
                    evenCount = lastOddCount + 1;
                }
            }
            total += oddCount;
            total %= mod;
        }
        return total;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                1,3,5
        };
        int i = solution.numOfSubarrays(arr);
        System.out.println(i);
        Assert.assertEquals(4, i);
    }
}
