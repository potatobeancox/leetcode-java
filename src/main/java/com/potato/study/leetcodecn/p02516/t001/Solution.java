package com.potato.study.leetcodecn.p02516.t001;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 2516. 每种字符至少取 K 个
 *
 * 给你一个由字符 'a'、'b'、'c' 组成的字符串 s 和一个非负整数 k 。每分钟，你可以选择取走 s 最左侧 还是 最右侧 的那个字符。
 *
 * 你必须取走每种字符 至少 k 个，返回需要的 最少 分钟数；如果无法取到，则返回 -1 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "aabaaaacaabc", k = 2
 * 输出：8
 * 解释：
 * 从 s 的左侧取三个字符，现在共取到两个字符 'a' 、一个字符 'b' 。
 * 从 s 的右侧取五个字符，现在共取到四个字符 'a' 、两个字符 'b' 和两个字符 'c' 。
 * 共需要 3 + 5 = 8 分钟。
 * 可以证明需要的最少分钟数是 8 。
 * 示例 2：
 *
 * 输入：s = "a", k = 1
 * 输出：-1
 * 解释：无法取到一个字符 'b' 或者 'c'，所以返回 -1 。
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 105
 * s 仅由字母 'a'、'b'、'c' 组成
 * 0 <= k <= s.length
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/take-k-of-each-character-from-left-and-right
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int takeCharacters(String s, int k) {
        // 统计窗口 内部总的计数 如果窗口 最大的窗口大小
        Map<Character, Integer> countMap = new HashMap<>();
        for (char ch : s.toCharArray()) {
            countMap.put(ch, countMap.getOrDefault(ch, 0) + 1);
        }
        int left = 0;
        Map<Character, Integer> windowCountMap = new HashMap<>();
        // 看看每种字母 剩下的是不是大于 k
        int maxLen = -1;
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            windowCountMap.put(ch, windowCountMap.getOrDefault(ch, 0) + 1);
            while (left <= right
                    && (countMap.getOrDefault('a', 0) - windowCountMap.getOrDefault('a', 0) < k
                    || countMap.getOrDefault('b', 0) - windowCountMap.getOrDefault('b', 0) < k
                    || countMap.getOrDefault('c', 0) - windowCountMap.getOrDefault('c', 0) < k)) {
                windowCountMap.put(s.charAt(left), windowCountMap.getOrDefault(s.charAt(left), 0) - 1);
                left++;
            }
            // 是不是ok
            if (countMap.getOrDefault('a', 0) - windowCountMap.getOrDefault('a', 0) >= k
                    && countMap.getOrDefault('b', 0) - windowCountMap.getOrDefault('b', 0) >= k
                    && countMap.getOrDefault('c', 0) - windowCountMap.getOrDefault('c', 0) >= k) {
                maxLen = Math.max(maxLen, (right - left + 1));
            }
        }
        if (maxLen == -1) {
            return -1;
        }
        return s.length() - maxLen;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "aabaaaacaabc";
        int k = 2;
        int i = solution.takeCharacters(s, k);
        System.out.println(i);
        Assert.assertEquals(8, i);


        solution = new Solution();
        s = "a";
        k = 1;
        i = solution.takeCharacters(s, k);
        System.out.println(i);
        Assert.assertEquals(-1, i);
    }

}
