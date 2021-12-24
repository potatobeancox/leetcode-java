package com.potato.study.leetcodecn.p02062.t001;

import org.junit.Assert;

/**
 * 2062. 统计字符串中的元音子字符串
 *
 * 子字符串 是字符串中的一个连续（非空）的字符序列。

 元音子字符串 是 仅 由元音（'a'、'e'、'i'、'o' 和 'u'）组成的一个子字符串，且必须包含 全部五种 元音。

 给你一个字符串 word ，统计并返回 word 中 元音子字符串的数目 。

  

 示例 1：

 输入：word = "aeiouu"
 输出：2
 解释：下面列出 word 中的元音子字符串（斜体加粗部分）：
 - "aeiouu"
 - "aeiouu"
 示例 2：

 输入：word = "unicornarihan"
 输出：0
 解释：word 中不含 5 种元音，所以也不会存在元音子字符串。
 示例 3：

 输入：word = "cuaieuouac"
 输出：7
 解释：下面列出 word 中的元音子字符串（斜体加粗部分）：
 - "cuaieuouac"
 - "cuaieuouac"
 - "cuaieuouac"
 - "cuaieuouac"
 - "cuaieuouac"
 - "cuaieuouac"
 - "cuaieuouac"
 示例 4：

 输入：word = "bbaeixoubb"
 输出：0
 解释：所有包含全部五种元音的子字符串都含有辅音，所以不存在元音子字符串。
  

 提示：

 1 <= word.length <= 100
 word 仅由小写英文字母组成

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/count-vowel-substrings-of-a-string
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int countVowelSubstrings(String word) {
        //枚举开始位置和结束位置
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            for (int j = i + 1; j <= word.length(); j++) {
                String substring = word.substring(i, j);
                if (hasAllVowel(substring)) {
//                    System.out.println(substring);
                    count++;
                }
            }
        }
        // 判断当前字符串是否满足 如果满足，后续所有位置 都可以计数
        return count;
    }

    /**
     * 是否包含所有 的原因字符
     * @param word
     * @return
     */
    private boolean hasAllVowel(String word) {
        if (word == null || word.length() < 5) {
            return false;
        }
        int status = 0b11111;
        int current = 0;
        char[] chars = word.toCharArray();
        for (char ch : chars) {
            if ('a' == ch) {
                current |= 1;
            } else if ('e' == ch) {
                current |= 2;
            } else if ('i' == ch) {
                current |= 4;
            } else if ('o' == ch) {
                current |= 8;
            } else if ('u' == ch){
                current |= 16;
            } else {
                return false;
            }
        }
        return current == status;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String word = "aeiouu";
        int i = solution.countVowelSubstrings(word);
        System.out.println(i);
        Assert.assertEquals(2, i);


        word = "cuaieuouac";
        i = solution.countVowelSubstrings(word);
        System.out.println(i);
        Assert.assertEquals(7, i);
    }


}
