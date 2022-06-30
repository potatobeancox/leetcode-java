package com.potato.study.leetcodecn.p01737.t001;

import org.junit.Assert;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1737. 满足三条件之一需改变的最少字符数
 *
 * 给你两个字符串 a 和 b ，二者均由小写字母组成。一步操作中，你可以将 a 或 b 中的 任一字符 改变为 任一小写字母 。

 操作的最终目标是满足下列三个条件 之一 ：

 a 中的 每个字母 在字母表中 严格小于 b 中的 每个字母 。
 b 中的 每个字母 在字母表中 严格小于 a 中的 每个字母 。
 a 和 b 都 由 同一个 字母组成。
 返回达成目标所需的 最少 操作数。

  

 示例 1：

 输入：a = "aba", b = "caa"
 输出：2
 解释：满足每个条件的最佳方案分别是：
 1) 将 b 变为 "ccc"，2 次操作，满足 a 中的每个字母都小于 b 中的每个字母；
 2) 将 a 变为 "bbb" 并将 b 变为 "aaa"，3 次操作，满足 b 中的每个字母都小于 a 中的每个字母；
 3) 将 a 变为 "aaa" 并将 b 变为 "aaa"，2 次操作，满足 a 和 b 由同一个字母组成。
 最佳的方案只需要 2 次操作（满足条件 1 或者条件 3）。
 示例 2：

 输入：a = "dabadd", b = "cda"
 输出：3
 解释：满足条件 1 的最佳方案是将 b 变为 "eee" 。
  

 提示：

 1 <= a.length, b.length <= 105
 a 和 b 只由小写字母组成

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/change-minimum-characters-to-satisfy-one-of-three-conditions
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 1737
     * @param a
     * @param b
     * @return
     */
    public int minCharacters(String a, String b) {
        // 遍历 a 和 b 统计 每个字母出现次数
        int[] count1 = new int[26];
        int[] count2 = new int[26];
        for (char ch : a.toCharArray()) {
            count1[ch -'a']++;
        }
        for (char ch : b.toCharArray()) {
            count2[ch -'a']++;
        }
        // 遍历 次数 ab 都是 同一个字母 计算最小的替换 个数
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            min = Math.min(min, a.length() - count1[i] + b.length() - count2[i]);
        }
        // 如果 a 字母 严格 小于 b 枚举 计算 ai 小于  小于等于 i的个数
        for (int i = 1; i < 26; i++) {
            // b 的小于等于 a的字母 券都改了
            int editCount = 0;
            for (int j = i + 1; j < 26; j++) {
                editCount += count1[j];
            }
            for (int j = 0; j <= i; j++) {
                editCount += count2[j];
            }
            min = Math.min(min, editCount);
        }
        // 同理 如果 b 严格 小于 a 枚举 a中 小的 改了
        for (int i = 1; i < 26; i++) {
            // b 的小于等于 a的字母 券都改了
            int editCount = 0;
            for (int j = i + 1; j < 26; j++) {
                editCount += count2[j];
            }
            for (int j = 0; j <= i; j++) {
                editCount += count1[j];
            }
            min = Math.min(min, editCount);
        }
        return min;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String a = "aba";
        String b = "caa";
        int i = solution.minCharacters(a, b);
        System.out.println(i);
        Assert.assertEquals(2, i);

        a = "a";
        b = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
        i = solution.minCharacters(a, b);
        System.out.println(i);
        Assert.assertEquals(2, i);
    }

}
