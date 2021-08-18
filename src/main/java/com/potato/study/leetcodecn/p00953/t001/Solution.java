package com.potato.study.leetcodecn.p00953.t001;

import org.junit.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 953. 验证外星语词典
 *
 * 某种外星语也使用英文小写字母，但可能顺序 order 不同。字母表的顺序（order）是一些小写字母的排列。

 给定一组用外星语书写的单词 words，以及其字母表的顺序 order，只有当给定的单词在这种外星语中按字典序排列时，返回 true；否则，返回 false。

  

 示例 1：

 输入：words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 输出：true
 解释：在该语言的字母表中，'h' 位于 'l' 之前，所以单词序列是按字典序排列的。
 示例 2：

 输入：words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 输出：false
 解释：在该语言的字母表中，'d' 位于 'l' 之后，那么 words[0] > words[1]，因此单词序列不是按字典序排列的。
 示例 3：

 输入：words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 输出：false
 解释：当前三个字符 "app" 匹配时，第二个字符串相对短一些，然后根据词典编纂规则 "apple" > "app"，因为 'l' > '∅'，其中 '∅' 是空白字符，定义为比任何其他字符都小（更多信息）。
  

 提示：

 1 <= words.length <= 100
 1 <= words[i].length <= 20
 order.length == 26
 在 words[i] 和 order 中的所有字符都是英文小写字母。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/verifying-an-alien-dictionary
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> charIndexMap = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            charIndexMap.put(order.charAt(i), i);
        }
        String[] newWord = Arrays.copyOf(words, words.length);
        Arrays.sort(newWord, (a, b) -> {
            int index1 = 0;
            int index2 = 0;
            while (index1 < a.length() && index2 < b.length()) {
                int pri1 = charIndexMap.get(a.charAt(index1));
                int pri2 = charIndexMap.get(b.charAt(index2));

                int compare = Integer.compare(pri1, pri2);

                if (compare != 0) {
                    return compare;
                }

                index1++;
                index2++;
            }

            if (index1 == a.length() && index2 == b.length()) {
                return 0;
            }

            if (index1 < a.length()) {
                return 1;
            } else if (index2 < b.length()) {
                return -1;
            }

            return 0;
        });


        for (int i = 0; i < words.length; i++) {
            if (!words[i].equals(newWord[i])) {
                return false;
            }
        }
        return true;
    }

//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        String[] grid = new String[] {
//                " /",
//                "/ "
//        };
//        int i = solution.regionsBySlashes(grid);
//        System.out.println(i);
//        Assert.assertEquals(2, i);
//
//
//        grid = new String[] {
//               " /",
//               "  "
//        };
//        i = solution.regionsBySlashes(grid);
//        System.out.println(i);
//        Assert.assertEquals(1, i);
//    }
}
