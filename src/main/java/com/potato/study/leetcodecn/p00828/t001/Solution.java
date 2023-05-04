package com.potato.study.leetcodecn.p00828.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 828. 统计子串中的唯一字符
 *
 * 我们定义了一个函数 countUniqueChars(s) 来统计字符串 s 中的唯一字符，并返回唯一字符的个数。
 *
 * 例如：s = "LEETCODE" ，则其中 "L", "T","C","O","D" 都是唯一字符，因为它们只出现一次，所以 countUniqueChars(s) = 5 。
 *
 * 本题将会给你一个字符串 s ，我们需要返回 countUniqueChars(t) 的总和，其中 t 是 s 的子字符串。输入用例保证返回值为 32 位整数。
 *
 * 注意，某些子字符串可能是重复的，但你统计时也必须算上这些重复的子字符串（也就是说，你必须统计 s 的所有子字符串中的唯一字符）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: s = "ABC"
 * 输出: 10
 * 解释: 所有可能的子串为："A","B","C","AB","BC" 和 "ABC"。
 *      其中，每一个子串都由独特字符构成。
 *      所以其长度总和为：1 + 1 + 1 + 2 + 2 + 3 = 10
 * 示例 2：
 *
 * 输入: s = "ABA"
 * 输出: 8
 * 解释: 除了 countUniqueChars("ABA") = 1 之外，其余与示例 1 相同。
 * 示例 3：
 *
 * 输入：s = "LEETCODE"
 * 输出：92
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 10^5
 * s 只包含大写英文字符
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/count-unique-characters-of-all-substrings-of-a-given-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    // 828
    public int uniqueLetterString(String s) {
        // 遍历 s 使用一个 map 统计每个字母出现的次数
        Map<Character, List<Integer>> appearMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            List<Integer> list = appearMap.getOrDefault(ch, new ArrayList<>());
            if (list.size() == 0) {
                list.add(-1);
            }
            list.add(i);

            appearMap.put(ch, list);
        }
        // 遍历 出现过的每种字母计算大于等于 3的的情况
        int count = 0;
        for (char key : appearMap.keySet()) {
            List<Integer> list = appearMap.get(key);
            list.add(s.length());
            for (int i = 1; i < list.size() - 1; i++) {
                count += (list.get(i) - list.get(i-1)) * (list.get(i+1) - list.get(i));
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int abc = solution.uniqueLetterString("abc");
        System.out.println(abc);
        Assert.assertEquals(10, abc);
    }

}
