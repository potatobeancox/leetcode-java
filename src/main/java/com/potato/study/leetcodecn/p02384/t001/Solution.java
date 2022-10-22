package com.potato.study.leetcodecn.p02384.t001;


import com.potato.study.leetcode.domain.TreeNode;

import java.util.*;

/**
 * 2384. 最大回文数字
 *
 * 给你一个仅由数字（0 - 9）组成的字符串 num 。
 *
 * 请你找出能够使用 num 中数字形成的 最大回文 整数，并以字符串形式返回。该整数不含 前导零 。
 *
 * 注意：
 *
 * 你 无需 使用 num 中的所有数字，但你必须使用 至少 一个数字。
 * 数字可以重新排序。
 *  
 *
 * 示例 1：
 *
 * 输入：num = "444947137"
 * 输出："7449447"
 * 解释：
 * 从 "444947137" 中选用数字 "4449477"，可以形成回文整数 "7449447" 。
 * 可以证明 "7449447" 是能够形成的最大回文整数。
 * 示例 2：
 *
 * 输入：num = "00009"
 * 输出："9"
 * 解释：
 * 可以证明 "9" 能够形成的最大回文整数。
 * 注意返回的整数不应含前导零。
 *  
 *
 * 提示：
 *
 * 1 <= num.length <= 105
 * num 由数字（0 - 9）组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/largest-palindromic-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public String largestPalindromic(String num) {
        // 遍历 计数
        int[] count = new int[10];
        // 从大到小一次插入
        for (char ch : num.toCharArray()) {
            count[ch - '0']++;
        }
        // 插入最大的单个
        StringBuilder builder = new StringBuilder();
        for (int i = 9; i >= 0; i--) {
            if (i != 0) {
                while (count[i] > 1) {
                    builder.append(i);
                    count[i] -= 2;
                }
            } else {
                // 0
                while (builder.length() > 0 && count[i] > 1) {
                    builder.append(i);
                    count[i] -= 2;
                }
            }
        }
        String reverse = new StringBuilder(builder).reverse().toString();
        // 处理一个

        for (int i = 9; i >= 0; i--) {
            if (count[i] > 0) {
                builder.append(i);
                count[i]--;
                break;
            }
        }
        builder.append(reverse);

        return builder.toString();
    }

}
