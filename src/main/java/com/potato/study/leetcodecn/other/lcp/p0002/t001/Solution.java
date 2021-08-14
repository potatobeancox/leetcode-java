package com.potato.study.leetcodecn.other.lcp.p0002.t001;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 3. 无重复字符的最长子串
 *
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

  

 示例 1:

 输入: s = "abcabcbb"
 输出: 3
 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 示例 2:

 输入: s = "bbbbb"
 输出: 1
 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 示例 3:

 输入: s = "pwwkew"
 输出: 3
 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 示例 4:

 输入: s = ""
 输出: 0
  

 提示：

 0 <= s.length <= 5 * 104
 s 由英文字母、数字、符号和空格组成

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {
    /**
     * 第一个 字母 找到当前最大的窗口 ，
     * 接下来 每次平移 找到窗口内的 重复数字 如果有的话，没有的话就继续添加窗口长度
     *
     * 设置一个窗口
     * map char lastIndex
     * 每次找到一个字母时，从map 中找是否存在 index ，
     *      如果不存在 map 方， 更新最大长度
     *      如果存在 map 中 找到那个index 更新，并重新遍历map 清楚 之前index 前的信息
     *
     * 如何遍历 map 并删除 元素
     * https://blog.csdn.net/wangmaohong0717/article/details/79286842
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        int maxLength = 0;
        Map<Character, Integer> charLastIndexMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!charLastIndexMap.containsKey(c)) {
                charLastIndexMap.put(c, i);
                maxLength = Math.max(maxLength, charLastIndexMap.size());
            } else {
                int lastIndex = charLastIndexMap.get(c);
                // 更新 目前的map
                charLastIndexMap.put(c, i);
                Iterator<Map.Entry<Character, Integer>> it = charLastIndexMap.entrySet().iterator();
                while(it.hasNext()){
                    Map.Entry<Character, Integer> entry = it.next();
                    if (entry.getValue() <= lastIndex) {
                        //使用迭代器的remove()方法删除元素
                        it.remove();
                    }
                }
                maxLength = Math.max(maxLength, charLastIndexMap.size());
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "abcabcbb";
        int len = solution.lengthOfLongestSubstring(s);
        System.out.println(len);
        Assert.assertEquals(3, len);

        s = "bbbbb";
        len = solution.lengthOfLongestSubstring(s);
        System.out.println(len);
        Assert.assertEquals(1, len);

        s = "pwwkew";
        len = solution.lengthOfLongestSubstring(s);
        System.out.println(len);
        Assert.assertEquals(3, len);

        s = "";
        len = solution.lengthOfLongestSubstring(s);
        System.out.println(len);
        Assert.assertEquals(0, len);
    }


}
