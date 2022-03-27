package com.potato.study.leetcodecn.p02193.t001;

import java.util.ArrayList;
import java.util.List;

/**
 * 2193. 得到回文串的最少操作次数
 *
 * 给你一个只包含小写英文字母的字符串 s 。

 每一次 操作 ，你可以选择 s 中两个 相邻 的字符，并将它们交换。

 请你返回将 s 变成回文串的 最少操作次数 。

 注意 ，输入数据会确保 s 一定能变成一个回文串。

  

 示例 1：

 输入：s = "aabb"
 输出：2
 解释：
 我们可以将 s 变成 2 个回文串，"abba" 和 "baab" 。
 - 我们可以通过 2 次操作得到 "abba" ："aabb" -> "abab" -> "abba" 。
 - 我们可以通过 2 次操作得到 "baab" ："aabb" -> "abab" -> "baab" 。
 因此，得到回文串的最少总操作次数为 2 。
 示例 2：

 输入：s = "letelt"
 输出：2
 解释：
 通过 2 次操作从 s 能得到回文串 "lettel" 。
 其中一种方法是："letelt" -> "letetl" -> "lettel" 。
 其他回文串比方说 "tleelt" 也可以通过 2 次操作得到。
 可以证明少于 2 次操作，无法得到回文串。
  

 提示：

 1 <= s.length <= 2000
 s 只包含小写英文字母。
 s 可以通过有限次操作得到一个回文串。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/minimum-number-of-moves-to-make-palindrome
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int minMovesToMakePalindrome(String s) {

        return -1;
    }

}
