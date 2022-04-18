package com.potato.study.leetcodecn.p01657.t001;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 1657. 确定两个字符串是否接近
 *
 * 如果可以使用以下操作从一个字符串得到另一个字符串，则认为两个字符串 接近 ：

 操作 1：交换任意两个 现有 字符。
 例如，abcde -> aecdb
 操作 2：将一个 现有 字符的每次出现转换为另一个 现有 字符，并对另一个字符执行相同的操作。
 例如，aacabb -> bbcbaa（所有 a 转化为 b ，而所有的 b 转换为 a ）
 你可以根据需要对任意一个字符串多次使用这两种操作。

 给你两个字符串，word1 和 word2 。如果 word1 和 word2 接近 ，就返回 true ；否则，返回 false 。

  

 示例 1：

 输入：word1 = "abc", word2 = "bca"
 输出：true
 解释：2 次操作从 word1 获得 word2 。
 执行操作 1："abc" -> "acb"
 执行操作 1："acb" -> "bca"
 示例 2：

 输入：word1 = "a", word2 = "aa"
 输出：false
 解释：不管执行多少次操作，都无法从 word1 得到 word2 ，反之亦然。
 示例 3：

 输入：word1 = "cabbba", word2 = "abbccc"
 输出：true
 解释：3 次操作从 word1 获得 word2 。
 执行操作 1："cabbba" -> "caabbb"
 执行操作 2："caabbb" -> "baaccc"
 执行操作 2："baaccc" -> "abbccc"
 示例 4：

 输入：word1 = "cabbba", word2 = "aabbss"
 输出：false
 解释：不管执行多少次操作，都无法从 word1 得到 word2 ，反之亦然。
  

 提示：

 1 <= word1.length, word2.length <= 105
 word1 和 word2 仅包含小写英文字母

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/determine-if-two-strings-are-close
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode-cn.com/problems/determine-if-two-strings-are-close/solution/que-ding-liang-ge-zi-fu-chuan-shi-fou-xi-lrs9/
     * 出现的次数必须相同
     * @param word1
     * @param word2
     * @return
     */
    public boolean closeStrings(String word1, String word2) {
        // 统计数量 统计2的时候 考虑下每个字符必须是出现过的
        int[] count1 = new int[26];
        for (char ch : word1.toCharArray()) {
            count1[ch - 'a']++;
        }
        int[] count2 = new int[26];
        for (char ch : word2.toCharArray()) {
            // 出现了 之前之前没有的字母
            if (count1[ch - 'a'] == 0) {
                return false;
            }
            count2[ch - 'a']++;
        }
        // 排序 count 如果 遍历 如果次数不同 不是
        Arrays.sort(count1);
        Arrays.sort(count2);
        for (int i = 0; i < 26; i++) {
            if (count1[i] != count2[i]) {
                return false;
            }
        }
        return true;
    }
}
