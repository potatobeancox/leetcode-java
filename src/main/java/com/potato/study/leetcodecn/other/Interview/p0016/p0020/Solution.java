package com.potato.study.leetcodecn.other.Interview.p0016.p0020;


import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 面试题 16.20. T9键盘
 *
 *在老式手机上，用户通过数字键盘输入，手机将提供与这些数字相匹配的单词列表。每个数字映射到0至4个字母。给定一个数字序列，实现一个算法来返回匹配单词的列表。你会得到一张含有有效单词的列表。映射如下图所示：



 示例 1:

 输入: num = "8733", words = ["tree", "used"]
 输出: ["tree", "used"]
 示例 2:

 输入: num = "2", words = ["a", "b", "c", "d"]
 输出: ["a", "b", "c"]
 提示：

 num.length <= 1000
 words.length <= 500
 words[i].length == num.length
 num中不会出现 0, 1 这两个数字

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/t9-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public List<String> getValidT9Words(String num, String[] words) {
        Map<Character, Character> map = new HashMap<>();
        map.put('a', '2');
        map.put('b', '2');
        map.put('c', '2');
        map.put('d', '3');
        map.put('e', '3');
        map.put('f', '3');
        map.put('g', '4');
        map.put('h', '4');
        map.put('i', '4');
        map.put('j', '5');
        map.put('k', '5');
        map.put('l', '5');
        map.put('m', '6');
        map.put('n', '6');
        map.put('o', '6');
        map.put('p', '7');
        map.put('q', '7');
        map.put('r', '7');
        map.put('s', '7');
        map.put('t', '8');
        map.put('u', '8');
        map.put('v', '8');
        map.put('w', '9');
        map.put('x', '9');
        map.put('y', '9');
        map.put('z', '9');

        List<String> result = new ArrayList<>();
        for (String word : words) {

            StringBuilder builder = new StringBuilder();
            for (char ch : word.toCharArray()) {
                builder.append(map.get(ch));
            }

            if (builder.toString().equals(num)) {
                result.add(word);
            }
        }
        return result;
    }
}
