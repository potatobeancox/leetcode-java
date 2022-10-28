package com.potato.study.leetcodecn.p02381.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

/**
 * 2381. 字母移位 II
 *
 * 给你一个小写英文字母组成的字符串 s 和一个二维整数数组 shifts ，其中 shifts[i] = [starti, endi, directioni] 。对于每个 i ，将 s 中从下标 starti 到下标 endi （两者都包含）所有字符都进行移位运算，如果 directioni = 1 将字符向后移位，如果 directioni = 0 将字符向前移位。
 *
 * 将一个字符 向后 移位的意思是将这个字符用字母表中 下一个 字母替换（字母表视为环绕的，所以 'z' 变成 'a'）。类似的，将一个字符 向前 移位的意思是将这个字符用字母表中 前一个 字母替换（字母表是环绕的，所以 'a' 变成 'z' ）。
 *
 * 请你返回对 s 进行所有移位操作以后得到的最终字符串。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "abc", shifts = [[0,1,0],[1,2,1],[0,2,1]]
 * 输出："ace"
 * 解释：首先，将下标从 0 到 1 的字母向前移位，得到 s = "zac" 。
 * 然后，将下标从 1 到 2 的字母向后移位，得到 s = "zbd" 。
 * 最后，将下标从 0 到 2 的字符向后移位，得到 s = "ace" 。
 * 示例 2:
 *
 * 输入：s = "dztz", shifts = [[0,0,0],[1,1,1]]
 * 输出："catz"
 * 解释：首先，将下标从 0 到 0 的字母向前移位，得到 s = "cztz" 。
 * 最后，将下标从 1 到 1 的字符向后移位，得到 s = "catz" 。
 *  
 *
 * 提示：
 *
 * 1 <= s.length, shifts.length <= 5 * 104
 * shifts[i].length == 3
 * 0 <= starti <= endi < s.length
 * 0 <= directioni <= 1
 * s 只包含小写英文字母。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shifting-letters-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public String shiftingLetters(String s, int[][] shifts) {
        // 生成 数组 记录每个位置要移动多少
        int[] diff = new int[s.length()];
        // 遍历 shifts 对于两个端点 记录差值 0向前 1向后
        for (int[] shift : shifts) {
            int from = shift[0];
            int to = shift[1] + 1;
            int d = shift[2] == 0 ? -1 : 1;
            diff[from] += d;
            if (to < s.length()) {
                diff[to] -= d;
            }
        }
        // 遍历查分数组 生成前缀数组
        int[] prefix = new int[s.length()];
        // 根据前缀数组 对 s 进行调整
        prefix[0] = diff[0];
        for (int i = 1; i < s.length(); i++) {
            prefix[i] = prefix[i-1] + diff[i];
        }
        // 遍历 s
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int temp = chars[i] - 'a' + prefix[i];
            while (temp < 0) {
                temp += 26;
            }
            temp %= 26;
            chars[i] = (char) ('a' + temp);
        }
        return new String(chars);
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "abc";
        String input = "[[0,1,0],[1,2,1],[0,2,1]]";
        int[][] shifts = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        String tar = solution.shiftingLetters(s, shifts);
        System.out.println(tar);
        Assert.assertEquals("ace", tar);
    }

}
