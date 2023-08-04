package com.potato.study.leetcodecn.other.lcr.p0003.t001;

/**
 * 剑指 Offer II 003. 前 n 个数字二进制中 1 的个数
 *
 * 给定一个非负整数 n ，请计算 0 到 n 之间的每个数字的二进制表示中 1 的个数，并输出一个数组。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: n = 2
 * 输出: [0,1,1]
 * 解释:
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 示例 2:
 *
 * 输入: n = 5
 * 输出: [0,1,1,2,1,2]
 * 解释:
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 3 --> 11
 * 4 --> 100
 * 5 --> 101
 *  
 *
 * 说明 :
 *
 * 0 <= n <= 105
 *  
 *
 * 进阶:
 *
 * 给出时间复杂度为 O(n*sizeof(integer)) 的解答非常容易。但你可以在线性时间 O(n) 内用一趟扫描做到吗？
 * 要求算法的空间复杂度为 O(n) 。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount ）来执行此操作。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/w3tCBm
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {
    /**
     *
     * @param n
     * @return
     */
    public int[] countBits(int n) {
        int[] count = new int[n+1];
        for (int i = 0; i <= n; i++) {
            count[i] = getCountBits(i);
        }
        return count;
    }

    /**
     *
     * @param i
     * @return
     */
    private int getCountBits(int i) {
        int count = 0;
        while (i > 0) {
            int bit = i & 1;
            if (bit == 1) {
                count++;
            }
            i /= 2;
        }
        return count;
    }
}
