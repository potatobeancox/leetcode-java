package com.potato.study.leetcodecn.p01228.t001;


/**
 * 1228. 等差数列中缺失的数字
 *
 * 在某个数组 arr 中，值符合等差数列的数值规律：在 0 <= i < arr.length - 1 的前提下，arr[i+1] - arr[i] 的值都相等。
 *
 * 我们会从该数组中删除一个 既不是第一个 也 不是最后一个的值，得到一个新的数组  arr。
 *
 * 给你这个缺值的数组 arr，返回 被删除的那个数 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：arr = [5,7,11,13]
 * 输出：9
 * 解释：原来的数组是 [5,7,9,11,13]。
 * 示例 2：
 *
 * 输入：arr = [15,13,12]
 * 输出：14
 * 解释：原来的数组是 [15,14,13,12]。
 *  
 *
 * 提示：
 *
 * 3 <= arr.length <= 1000
 * 0 <= arr[i] <= 105
 * 给定的数组 保证 是一个有效的数组。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/missing-number-in-arithmetic-progression
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int missingNumber(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        // 求公差
        int d = (arr[arr.length - 1] - arr[0]) / arr.length;
        boolean isAllSame = true;
        // 便利arr i  i+1 求差
        for (int i = arr.length - 1; i > 0; i--) {
            if (arr[i] - arr[i-1] != d) {
                isAllSame = false;
                return arr[i] - d;
            }
        }

        if (isAllSame) {
            return arr[0];
        }
        return -1;
    }
}
