package com.potato.study.leetcodecn.p00848.t001;


import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 848. 字母移位
 *
 * 有一个由小写字母组成的字符串 s，和一个长度相同的整数数组 shifts。
 *
 * 我们将字母表中的下一个字母称为原字母的 移位 shift() （由于字母表是环绕的， 'z' 将会变成 'a'）。
 *
 * 例如，shift('a') = 'b', shift('t') = 'u', 以及 shift('z') = 'a'。
 * 对于每个 shifts[i] = x ， 我们会将 s 中的前 i + 1 个字母移位 x 次。
 *
 * 返回 将所有这些移位都应用到 s 后最终得到的字符串 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "abc", shifts = [3,5,9]
 * 输出："rpl"
 * 解释：
 * 我们以 "abc" 开始。
 * 将 S 中的第 1 个字母移位 3 次后，我们得到 "dbc"。
 * 再将 S 中的前 2 个字母移位 5 次后，我们得到 "igc"。
 * 最后将 S 中的这 3 个字母移位 9 次后，我们得到答案 "rpl"。
 * 示例 2:
 *
 * 输入: s = "aaa", shifts = [1,2,3]
 * 输出: "gfd"
 *  
 *
 * 提示:
 *
 * 1 <= s.length <= 105
 * s 由小写英文字母组成
 * shifts.length == s.length
 * 0 <= shifts[i] <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shifting-letters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public String shiftingLetters(String s, int[] shifts) {
        char[] chars = s.toCharArray();
        // 先求和
        int sum = 0;
        for (int i = 0; i < shifts.length; i++) {
            sum += shifts[i];
            sum %= 26;
        }
        // 从后往前 移动 每次 减去 shift 位置的值
        for (int i = 0; i < shifts.length; i++) {
            chars[i] = (char) ((chars[i] - 'a' + sum) % 26 + 'a');
            sum -= shifts[i];
            sum = Math.floorMod(sum, 26);
        }
        return new String(chars);
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String str = "abc";
        int[] arr = new int[] {
                3,5,9
        };
        String s = solution.shiftingLetters(str, arr);
        System.out.println(s);
        Assert.assertEquals("rpl", s);
    }
}
