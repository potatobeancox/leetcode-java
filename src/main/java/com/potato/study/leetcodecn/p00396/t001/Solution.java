package com.potato.study.leetcodecn.p00396.t001;

import org.junit.Assert;

/**
 * 396. 旋转函数
 *
 * 给定一个长度为 n 的整数数组 A 。

 假设 Bk 是数组 A 顺时针旋转 k 个位置后的数组，我们定义 A 的“旋转函数” F 为：

 F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1]。

 计算F(0), F(1), ..., F(n-1)中的最大值。

 注意:
 可以认为 n 的值小于 105。

 示例:

 A = [4, 3, 2, 6]

 F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
 F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
 F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
 F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26

 所以 F(0), F(1), F(2), F(3) 中的最大值是 F(3) = 26 。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/rotate-function
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    /**
     * https://leetcode-cn.com/problems/rotate-function/solution/wa-pian-396-xuan-zhuan-han-shu-java-zhon-rbz4/
     * @param arr
     * @return
     */
    public int maxRotateFunction(int[] arr) {
        int length = arr.length;
        // 翻转的次数
        int sum = 0;
        int currentNum = 0;
        for (int i = 0; i < length; i++) {
            sum += arr[i];
            currentNum += arr[i] * i;
        }
        int max = currentNum;
        // 从之后每一个位置进行旋转获取 最大值
        for (int i = length - 2; i >= 0; i--) {
            currentNum = currentNum + sum - arr[i+1] * length;
            // 加上sum - 最大位置 * length + 1；
            max = Math.max(max, currentNum);
        }
        return max;
    }
}
