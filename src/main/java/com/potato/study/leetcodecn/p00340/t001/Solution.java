package com.potato.study.leetcodecn.p00340.t001;


import java.util.HashSet;
import java.util.Set;

/**
 * 340. 至多包含 K 个不同字符的最长子串
 *
 * 给定一个字符串 s ，找出 至多 包含 k 个不同字符的最长子串 T。

  

 示例 1:

 输入: s = "eceba", k = 2
 输出: 3
 解释: 则 T 为 "ece"，所以长度为 3。
 示例 2:

 输入: s = "aa", k = 1
 输出: 2
 解释: 则 T 为 "aa"，所以长度为 2。
  

 提示：

 1 <= s.length <= 5 * 104
 0 <= k <= 50

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/longest-substring-with-at-most-k-distinct-characters
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // 数组记录 count 每个字母 窗口内部的
        int[] count = new int[256];
        Set<Integer> set = new HashSet<>();
        // set 记录当前有哪些字母 如果 发生count 到0 set remove
        int leftIndex = 0;
        // 遍历 s 获取结果 最长值
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i);
            count[index]++;
            set.add(index);

            // 是否需要移动左边
            while (set.size() > k) {
                int left = s.charAt(leftIndex);

                if (count[left] > 1) {
                    count[left]--;
                } else {
                    // 只有一个
                    count[left]--;
                    set.remove(left);
                }
                leftIndex++;
            }

            // 窗口大小比较
            max = Math.max(max, i-leftIndex + 1);
        }
        return max;
    }
}
