package com.potato.study.leetcodecn.p01903.t001;

import org.junit.Assert;

/**
 * 1903. 字符串中的最大奇数
 *
 * 给你一个字符串 num ，表示一个大整数。请你在字符串 num 的所有 非空子字符串 中找出 值最大的奇数 ，并以字符串形式返回。如果不存在奇数，则返回一个空字符串 "" 。
 *
 * 子字符串 是字符串中的一个连续的字符序列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：num = "52"
 * 输出："5"
 * 解释：非空子字符串仅有 "5"、"2" 和 "52" 。"5" 是其中唯一的奇数。
 * 示例 2：
 *
 * 输入：num = "4206"
 * 输出：""
 * 解释：在 "4206" 中不存在奇数。
 * 示例 3：
 *
 * 输入：num = "35427"
 * 输出："35427"
 * 解释："35427" 本身就是一个奇数。
 *  
 *
 * 提示：
 *
 * 1 <= num.length <= 105
 * num 仅由数字组成且不含前导零
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-odd-number-in-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 从后往前 找 num 找到 第一个 奇数 记录位置
     * @param num
     * @return
     */
    public String largestOddNumber(String num) {
        if (num == null) {
            return "";
        }
        int index = -1;
        for (int i = num.length() - 1; i >= 0; i--) {
            int target = num.charAt(i) - '0';
            if (target % 2 == 1) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return "";
        }
        return num.substring(0, index + 1);
    }

}
