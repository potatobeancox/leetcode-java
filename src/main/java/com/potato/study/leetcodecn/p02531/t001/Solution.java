package com.potato.study.leetcodecn.p02531.t001;


import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 2531. 使字符串总不同字符的数目相等
 *
 * 给你两个下标从 0 开始的字符串 word1 和 word2 。
 *
 * 一次 移动 由以下两个步骤组成：
 *
 * 选中两个下标 i 和 j ，分别满足 0 <= i < word1.length 和 0 <= j < word2.length ，
 * 交换 word1[i] 和 word2[j] 。
 * 如果可以通过 恰好一次 移动，使 word1 和 word2 中不同字符的数目相等，则返回 true ；否则，返回 false 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：word1 = "ac", word2 = "b"
 * 输出：false
 * 解释：交换任何一组下标都会导致第一个字符串中有 2 个不同的字符，而在第二个字符串中只有 1 个不同字符。
 * 示例 2：
 *
 * 输入：word1 = "abcc", word2 = "aab"
 * 输出：true
 * 解释：交换第一个字符串的下标 2 和第二个字符串的下标 0 。之后得到 word1 = "abac" 和 word2 = "cab" ，各有 3 个不同字符。
 * 示例 3：
 *
 * 输入：word1 = "abcde", word2 = "fghij"
 * 输出：true
 * 解释：无论交换哪一组下标，两个字符串中都会有 5 个不同字符。
 *  
 *
 * 提示：
 *
 * 1 <= word1.length, word2.length <= 105
 * word1 和 word2 仅由小写英文字母组成。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/make-number-of-distinct-characters-equal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public boolean isItPossible(String word1, String word2) {
        // 分别对 word1 word2 进行计数 然后遍历两个计数数组
        Map<Character, Integer> count1Map = new HashMap<>();
        Map<Character, Integer> count2Map = new HashMap<>();
        for (char ch1 : word1.toCharArray()) {
            count1Map.put(ch1, count1Map.getOrDefault(ch1, 0) + 1);
        }
        for (char ch2 : word2.toCharArray()){
            count2Map.put(ch2, count2Map.getOrDefault(ch2, 0) + 1);
        }
        // 如果当前两个key 相同 且 种类相同，可以交互
        for (Map.Entry<Character, Integer> entry1 : count1Map.entrySet()) {
            for (Map.Entry<Character, Integer> entry2 : count2Map.entrySet()) {
                char key1 = entry1.getKey();
                char key2 = entry2.getKey();
                // 两个key 相同
                if (key1 == key2) {
                    if (count1Map.size() == count2Map.size()) {
                        return true;
                    } else {
                        continue;
                    }
                }
                // 不相同
                int size1 = count1Map.size();
                int size2 = count2Map.size();

                int key1Count = entry1.getValue();
                int key2Count = entry2.getValue();
                // 如果key1Co
                if (key1Count == 1) {
                    size1--;
                }
                if (key2Count == 1) {
                    size2--;
                }
                if (!count2Map.containsKey(key1)) {
                    size2++;
                }
                if (!count1Map.containsKey(key2)) {
                    size1++;
                }
                if (size1 == size2) {
                    return true;
                }
            }
        }
        // 如果不同，看看key1 在自己的个数 count11 在对方的个数 count12  key2 在自己个数 count22 key2在对方个数 count21
        // 如果 key1 和key2 都只在自己有
        return false;
    }

    // "ab"
    // "abcc" // false
    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean itPossible = solution.isItPossible("ab", "abcc");
        System.out.println(itPossible);
        Assert.assertEquals(false, itPossible);


        itPossible = solution.isItPossible("aa", "ab");
        System.out.println(itPossible);
        Assert.assertEquals(false, itPossible);
    }



}
