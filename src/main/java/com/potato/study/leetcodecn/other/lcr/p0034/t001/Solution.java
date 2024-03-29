package com.potato.study.leetcodecn.other.lcr.p0034.t001;

import java.util.*;

import org.junit.Assert;

/**
 * LCR 034. 验证外星语词典
 *
 * 某种外星语也使用英文小写字母，但可能顺序 order 不同。字母表的顺序（order）是一些小写字母的排列。
 *
 * 给定一组用外星语书写的单词 words，以及其字母表的顺序 order，只有当给定的单词在这种外星语中按字典序排列时，返回 true；否则，返回 false。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 * 输出：true
 * 解释：在该语言的字母表中，'h' 位于 'l' 之前，所以单词序列是按字典序排列的。
 * 示例 2：
 *
 * 输入：words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 * 输出：false
 * 解释：在该语言的字母表中，'d' 位于 'l' 之后，那么 words[0] > words[1]，因此单词序列不是按字典序排列的。
 * 示例 3：
 *
 * 输入：words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 * 输出：false
 * 解释：当前三个字符 "app" 匹配时，第二个字符串相对短一些，然后根据词典编纂规则 "apple" > "app"，因为 'l' > '∅'，其中 '∅' 是空白字符，定义为比任何其他字符都小（更多信息）。
 *  
 *
 * 提示：
 *
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 20
 * order.length == 26
 * 在 words[i] 和 order 中的所有字符都是英文小写字母。
 *  
 *
 * 注意：本题与主站 953 题相同： https://leetcode-cn.com/problems/verifying-an-alien-dictionary/
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/lwyVBB
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param words
     * @param order
     * @return
     */
    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> indexMap = new HashMap<>();
        char[] chars = order.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            indexMap.put(chars[i], i);
        }
        String[] clone = words.clone();
        Arrays.sort(clone, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int idx1 = 0;
                int idx2 = 0;

                while (idx1 < o1.length() && idx2 < o2.length()) {
                    int num1 = indexMap.get(o1.charAt(idx1));
                    int num2 = indexMap.get(o2.charAt(idx2));

                    if (num1 == num2) {
                        idx1++;
                        idx2++;
                        continue;
                    }

                    return Integer.compare(num1, num2);
                }

                if (idx1 == o1.length()) {
                    return -1;
                } else if (idx2 == o2.length()) {
                    return 1;
                }
                return 0;
            }
        });

        for (int i = 0; i < words.length; i++) {
            if (!clone[i].equals(words[i])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] words = new String[]{"hello","leetcode"};
        String order = "hlabcdefgijkmnopqrstuvwxyz";
        boolean alienSorted = solution.isAlienSorted(words, order);
        System.out.println(alienSorted);
        Assert.assertEquals(true, alienSorted);


        words = new String[]{"word","world","row"};
        order = "worldabcefghijkmnpqstuvxyz";
        alienSorted = solution.isAlienSorted(words, order);
        System.out.println(alienSorted);
        Assert.assertEquals(false, alienSorted);


        words = new String[]{"apple","app"};
        order = "abcdefghijklmnopqrstuvwxyz";
        alienSorted = solution.isAlienSorted(words, order);
        System.out.println(alienSorted);
        Assert.assertEquals(false, alienSorted);
    }
}
