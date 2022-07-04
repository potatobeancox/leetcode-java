package com.potato.study.leetcodecn.p02325.t001;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

/**
 * 2325. 解密消息
 *
 * 给你字符串 key 和 message ，分别表示一个加密密钥和一段加密消息。解密 message 的步骤如下：
 *
 * 使用 key 中 26 个英文小写字母第一次出现的顺序作为替换表中的字母 顺序 。
 * 将替换表与普通英文字母表对齐，形成对照表。
 * 按照对照表 替换 message 中的每个字母。
 * 空格 ' ' 保持不变。
 * 例如，key = "happy boy"（实际的加密密钥会包含字母表中每个字母 至少一次），据此，可以得到部分对照表（'h' -> 'a'、'a' -> 'b'、'p' -> 'c'、'y' -> 'd'、'b' ->
 * 'e'、'o' -> 'f'）。
 * 返回解密后的消息。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：key = "the quick brown fox jumps over the lazy dog", message = "vkbs bs t suepuv"
 * 输出："this is a secret"
 * 解释：对照表如上图所示。
 * 提取 "the quick brown fox jumps over the lazy dog" 中每个字母的首次出现可以得到替换表。
 * 示例 2：
 *
 *
 *
 * 输入：key = "eljuxhpwnyrdgtqkviszcfmabo", message = "zwx hnfx lqantp mnoeius ycgk vcnjrdb"
 * 输出："the five boxing wizards jump quickly"
 * 解释：对照表如上图所示。
 * 提取 "eljuxhpwnyrdgtqkviszcfmabo" 中每个字母的首次出现可以得到替换表。
 *  
 *
 * 提示：
 *
 * 26 <= key.length <= 2000
 * key 由小写英文字母及 ' ' 组成
 * key 包含英文字母表中每个字符（'a' 到 'z'）至少一次
 * 1 <= message.length <= 2000
 * message 由小写英文字母和 ' ' 组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/decode-the-message
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public String decodeMessage(String key, String message) {
        // 遍历 key 按照顺序 生成 替换的map
        Map<Character, Character> map = new HashMap<>();
        int index = 0;
        for (char ch : key.toCharArray()) {
            if (' ' == ch) {
                continue;
            }
            if (!map.containsKey(ch)) {
                map.put(ch, (char) (index + 'a'));
                index++;
            }
        }
        // 遍历 message 进行替换
        StringBuilder builder = new StringBuilder();
        for (char ch : message.toCharArray()) {
            if (Character.isAlphabetic(ch)) {
                builder.append(map.getOrDefault(ch, ch));
            } else {
                builder.append(ch);
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String key = "the quick brown fox jumps over the lazy dog";
        String message = "vkbs bs t suepuv";
        String s = solution.decodeMessage(key, message);
        System.out.println(s);
        Assert.assertEquals("this is a secret", s);
    }

}
