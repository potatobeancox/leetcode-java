package com.potato.study.leetcodecn.other.swordoffer.p0017.p1.t001;

import java.util.Arrays;

/**
 * 剑指 Offer 17. 打印从1到最大的n位数
 *
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。

 示例 1:

 输入: n = 1
 输出: [1,2,3,4,5,6,7,8,9]
  

 说明：

 用返回一个整数列表来代替打印
 n 为正整数

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     *
     * @param n
     * @return
     */
    public int[] printNumbers(int n) {
        // 生成 几个 9
        int total = 0;
        for (int i = 0; i < n; i++) {
            total *= 10;
            total += 9;
        }
        int[] res = new int[total];
        for (int i = 1; i <= total; i++) {
            res[i - 1] = i;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 1;
        int[] ints = solution.printNumbers(n);
        System.out.println(Arrays.toString(ints));
    }

}
