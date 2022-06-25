package com.potato.study.leetcodecn.p01432.t001;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

/**
 * 1432. 改变一个整数能得到的最大差值
 *
 * 给你一个整数 num 。你可以对它进行如下步骤恰好 两次 ：
 *
 * 选择一个数字 x (0 <= x <= 9).
 * 选择另一个数字 y (0 <= y <= 9) 。数字 y 可以等于 x 。
 * 将 num 中所有出现 x 的数位都用 y 替换。
 * 得到的新的整数 不能 有前导 0 ，得到的新整数也 不能 是 0 。
 * 令两次对 num 的操作得到的结果分别为 a 和 b 。
 *
 * 请你返回 a 和 b 的 最大差值 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：num = 555
 * 输出：888
 * 解释：第一次选择 x = 5 且 y = 9 ，并把得到的新数字保存在 a 中。
 * 第二次选择 x = 5 且 y = 1 ，并把得到的新数字保存在 b 中。
 * 现在，我们有 a = 999 和 b = 111 ，最大差值为 888
 * 示例 2：
 *
 * 输入：num = 9
 * 输出：8
 * 解释：第一次选择 x = 9 且 y = 9 ，并把得到的新数字保存在 a 中。
 * 第二次选择 x = 9 且 y = 1 ，并把得到的新数字保存在 b 中。
 * 现在，我们有 a = 9 和 b = 1 ，最大差值为 8
 * 示例 3：
 *
 * 输入：num = 123456
 * 输出：820000
 * 示例 4：
 *
 * 输入：num = 10000
 * 输出：80000
 * 示例 5：
 *
 * 输入：num = 9288
 * 输出：8700
 *  
 *
 * 提示：
 *
 * 1 <= num <= 10^8
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/max-difference-you-can-get-from-changing-an-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 1432
    public int maxDiff(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        // 找到第一个 不是 9 的数字 改成 9
        int firstNot9Index = -1;
        int firstNot1Index = -1;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '1' && firstNot1Index == -1) {
                firstNot1Index = i;
            }

            if (chars[i] != '9' && firstNot9Index == -1) {
                firstNot9Index = i;
            }
        }
        // 找到第一个不是 1的数字 改成1
        int num9 = num;
        if (firstNot9Index != -1) {
            char[] clone = chars.clone();
            for (int i = 0; i < clone.length; i++) {
                if (clone[i] == chars[firstNot9Index]) {
                    clone[i] = '9';
                }
            }
            num9 = Integer.parseInt(String.valueOf(clone));
        }
        // 求差 可以选择 0
        int num1 = num;
        if (firstNot1Index != -1) {
            char[] clone = chars.clone();
            for (int i = 0; i < clone.length; i++) {
                if (clone[i] == chars[firstNot1Index]) {
                    if (firstNot1Index != 0) {
                        clone[i] = '0';
                    } else {
                        clone[i] = '1';
                    }
                }
            }
            num1 = Integer.parseInt(String.valueOf(clone));
        }
        return num9 - num1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.maxDiff(555);
        System.out.println(i);
        Assert.assertEquals(888, i);

        i= solution.maxDiff(123456);
        System.out.println(i);
        Assert.assertEquals(820000, i);
    }
}
