package com.potato.study.leetcodecn.p00336.t001;

import java.util.*;

/**
 * 336. 回文对
 *
 * 给定一组 互不相同 的单词， 找出所有 不同 的索引对 (i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。

  

 示例 1：

 输入：words = ["abcd","dcba","lls","s","sssll"]
 输出：[[0,1],[1,0],[3,2],[2,4]]
 解释：可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
 示例 2：

 输入：words = ["bat","tab","cat"]
 输出：[[0,1],[1,0]]
 解释：可拼接成的回文串为 ["battab","tabbat"]
 示例 3：

 输入：words = ["a",""]
 输出：[[0,1],[1,0]]
  
 提示：

 1 <= words.length <= 5000
 0 <= words[i].length <= 300
 words[i] 由小写英文字母组成

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/palindrome-pairs
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/palindrome-pairs/solution/hui-wen-dui-by-leetcode-solution/
     * @param words
     * @return
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        // 将 words 翻转存入 map 中 key 是翻转之后的 结果 value 是原来的index 方便进行查找
        Map<String, Integer> reverseWordIndexMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            StringBuilder builder = new StringBuilder(words[i]);
            reverseWordIndexMap.put(builder.reverse().toString(), i);
        }
        // 遍历 words 对于每个 sub 长度 再翻转里边找到两边每个位置 的index 作为结果 生成 最终的组合
        List<List<Integer>> result = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            // 枚举分割长度 左边
            int length = words[i].length();
            if (length == 0) {
                continue;
            }
            // 左边长度
            for (int j = 0; j <= length; j++) {
                String leftSubStr = words[i].substring(0, j);
                String rightSubStr = words[i].substring(j);
                // 左边存在 找到 左边对应的串 拼在右边
                if (isPalindrome(rightSubStr)
                        && reverseWordIndexMap.containsKey(leftSubStr)) {
                    Integer k = reverseWordIndexMap.get(leftSubStr);
                    if (i != k && !set.contains(i + "_" + k)) {
                        List<Integer> list = new ArrayList<>();
                        list.add(i);
                        list.add(k);

                        result.add(list);
                        set.add(i + "_" + k);
                    }

                }
                // 右边存在 找到 右边对应的串 拼在左边
                if (isPalindrome(leftSubStr)
                        && reverseWordIndexMap.containsKey(rightSubStr)) {
                    Integer k = reverseWordIndexMap.get(rightSubStr);

                    if (i != k && !set.contains(k + "_" + i)) {
                        List<Integer> list = new ArrayList<>();
                        list.add(k);
                        list.add(i);

                        result.add(list);
                        set.add(k + "_" + i);
                    }
                }
            }
        }
        return result;
    }

    /**
     * 判断是否回文
     * @param word
     * @return
     */
    private boolean isPalindrome(String word) {
        if (null == word || word.length() == 0) {
            return true;
        }
        int left = 0;
        int right = word.length() - 1;
        char[] chars = word.toCharArray();
        while (left < right) {
            if (chars[left] != chars[right]) {
                return false;
            }

            left++;
            right--;
        }
        return true;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] words = new String[] {
                "abcd","dcba","lls","s","sssll"
        };
        List<List<Integer>> lists = solution.palindromePairs(words);
        System.out.println(lists);
    }



}
