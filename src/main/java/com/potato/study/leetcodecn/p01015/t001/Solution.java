package com.potato.study.leetcodecn.p01015.t001;

import org.junit.Assert;

/**
 * 1015. 可被 K 整除的最小整数
 *
 * 给定正整数 k ，你需要找出可以被 k 整除的、仅包含数字 1 的最 小 正整数 n 的长度。

 返回 n 的长度。如果不存在这样的 n ，就返回-1。

 注意： n 不符合 64 位带符号整数。

  

 示例 1：

 输入：k = 1
 输出：1
 解释：最小的答案是 n = 1，其长度为 1。
 示例 2：

 输入：k = 2
 输出：-1
 解释：不存在可被 2 整除的正整数 n 。
 示例 3：

 输入：k = 3
 输出：3
 解释：最小的答案是 n = 111，其长度为 3。
  

 提示：

 1 <= k <= 105

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/smallest-integer-divisible-by-k
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/smallest-integer-divisible-by-k/solution/javajie-fa-yi-ji-zheng-ming-de-si-lu-by-jiangzk/
     * @param k
     * @return
     */
    public int smallestRepunitDivByK(int k) {
        // 如果 k 是 2 or 5 gg 整除
        if (k % 2 == 0 || k % 5 == 0) {
            return -1;
        }

        long temp = 1;
        int len = 1;
        // 每次不能整除 先 % k 再 * 10 + 1i 次计算
        while (temp % k != 0) {
            temp %= k;
            temp = temp * 10 + 1;
            len++;
        }
        return len;
    }
}
