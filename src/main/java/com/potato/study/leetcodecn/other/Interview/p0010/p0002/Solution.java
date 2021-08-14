package com.potato.study.leetcodecn.other.Interview.p0010.p0002;


import java.util.*;

/**
 * 面试题 10.02. 变位词组
 *
 * 编写一种方法，对字符串数组进行排序，将所有变位词组合在一起。变位词是指字母相同，但排列不同的字符串。

 注意：本题相对原题稍作修改

 示例:

 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 输出:
 [
 ["ate","eat","tea"],
 ["nat","tan"],
 ["bat"]
 ]
 说明：

 所有输入均为小写字母。
 不考虑答案输出的顺序。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/group-anagrams-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String word : strs) {
            String key = getKey(word);
            List<String> resultList = map.getOrDefault(key, new ArrayList<>());
            resultList.add(word);
            map.put(key, resultList);
        }
        // generate result
        List<List<String>> list = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry: map.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }


    private String getKey(String word) {
        int[] count = new int[26];
        for (char ch : word.toCharArray()) {
            count[ch-'a']++;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            builder.append(count[i]).append("-");
        }
        return builder.toString();
    }

//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int n = 3;
//        int res = solution.waysToStep(n);
//        System.out.println(res);
//        Assert.assertEquals(4, res);
//
//        n = 5;
//        res = solution.waysToStep(n);
//        System.out.println(res);
//        Assert.assertEquals(13, res);
//    }
}
