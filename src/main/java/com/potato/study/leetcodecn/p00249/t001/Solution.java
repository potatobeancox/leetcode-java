package com.potato.study.leetcodecn.p00249.t001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 249. 移位字符串分组
 *
 * 给定一个字符串，对该字符串可以进行 “移位” 的操作，也就是将字符串中每个字母都变为其在字母表中后续的字母，比如："abc" -> "bcd"。这样，我们可以持续进行 “移位” 操作，从而生成如下移位序列：

 "abc" -> "bcd" -> ... -> "xyz"
 给定一个包含仅小写字母字符串的列表，将该列表中所有满足 “移位” 操作规律的组合进行分组并返回。

  

 示例：

 输入：["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"]
 输出：
 [
 ["abc","bcd","xyz"],
 ["az","ba"],
 ["acef"],
 ["a","z"]
 ]
 解释：可以认为字母表首尾相接，所以 'z' 的后续为 'a'，所以 ["az","ba"] 也满足 “移位” 操作规律。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/group-shifted-strings
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> listMap = new HashMap<>();
        for (String str : strings) {
            // 换key
            String key = getKey(str);
            List<String> orDefault = listMap.getOrDefault(key, new ArrayList<>());
            orDefault.add(str);
            listMap.put(key, orDefault);
        }
        List<List<String>> resultList = new ArrayList<>(listMap.values());
        return resultList;
    }

    /**
     *
     * @param str
     * @return
     */
    private String getKey(String str) {
        char[] chars = str.toCharArray();
        // 计算第一个字母与 a的差
        int diff = chars[0] - 'a';
        chars[0] = 'a';
        for (int i = 1; i < chars.length; i++) {
            int charDiff = (chars[i] - 'a' - diff + 26) % 26;
            chars[i] = (char) (charDiff + 'a');
        }
        return new String(chars);
    }


}
