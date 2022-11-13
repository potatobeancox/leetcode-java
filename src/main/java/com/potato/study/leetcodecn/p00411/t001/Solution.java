package com.potato.study.leetcodecn.p00411.t001;


import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.*;

/**
 * 411. 最短独占单词缩写
 *
 * 通过将任意数量的 不相邻 子字符串替换为它们的长度，可以完成对字符串的 缩写 。 例如，像 "substitution" 这样的字符串可以缩写为（但不限于）：

 "s10n" ("s ubstitutio n")
 "sub4u4" ("sub stit u tion")
 "12" ("substitution")
 "su3i1u2on" ("su bst i t u ti on")
 "substitution" (不替换子字符串)
 注意："s55n" ("s ubsti tutio n") 不是 "substitution" 的有效缩写形式，因为它试图替换两个相邻的子字符串。

 缩写的 长度 是未被替换的字母数加上被替换的字符串数。例如，缩写 "s10n" 的长度为 3（2 个字母 + 1 个子字符串），而 "su3i1u2on" 的长度为 9（6 个字母 + 3 子字符串）。

 给你一个目标字符串 target 和一个字符串数组 dictionary 作为字典，为 target 找出并返回一个 最短 长度的缩写字符串，同时这个缩写字符串 不是 字典 dictionary 中其他字符串的缩写形式。如果有多个有效答案，可以返回其中任意一个。

  

 示例 1：

 输入：target = "apple", dictionary = ["blade"]
 输出："a4"
 解释："apple" 的最短缩写形式为 "5" ，但这也是 "blade" 的缩写形式之一。
 下一组最短缩写是 "a4" 和 "4e" ，其中 "4e" 也是 "blade" 的缩写形式之一，而 "a4" 不是。
 因此，返回 "a4" 。
 示例 2：

 输入：target = "apple", dictionary = ["blade","plain","amber"]
 输出："1p3"
 解释："5" 同时是 "apple" 和字典中所有单词的缩写形式。
 "a4" 同时是 "apple" 和 "amber" 的缩写形式。
 "4e" 同时是 "apple" 和 "blade" 的缩写形式。
 "1p3"、"2p2" 和 "3l1" 是 "apple" 的下一组最短缩写形式。
 因为它们不是字典中其他单词的缩写形式，返回其中任意一个都是正确的。
  

 提示：

 target.length == m
 dictionary.length == n
 1 <= m <= 21
 0 <= n <= 1000
 1 <= dictionary[i].length <= 100
 如果 n > 0 ，那么 log2(n) + m <= 21
 target 和 dictionary[i] 仅包含小写字符

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/minimum-unique-word-abbreviation
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 可以用位运算的背景
     *  1 <= m <= 21
     * @param target
     * @param dictionary
     * @return
     */
    public String minAbbreviation(String target, String[] dictionary) {
        // 1. 将 dictionary 中长度和 target长度不同的处理掉，因为不可能匹配上 返回list
        List<String> realDictionary = new ArrayList<>();
        for (String dic : dictionary) {
            if (dic.length() == target.length()) {
                realDictionary.add(dic);
            }
        }
        // 2. 生成 代表 target 缩写 的int数字，并且 返回长度 dfs 生成 字符保留为1 字符隐藏为0 ，长度要看有多少个保留字符 和多少段隐藏数字 组成
        List<Abbreviation> abbrList = new ArrayList<>();
        int abbrLen = 0;
        dfsGetBitList(abbrList, target, 0, abbrLen, false, 0);
        // 3. 将2 结果list 排序 按照 长度升序排列
        Collections.sort(abbrList, Comparator.comparingInt(o -> o.len));
        // 4. 处理 1的list 生成 数字 其中bit = 1表示 字母与target一直，否则不一致
        List<Integer> dicTargetDiffBitList = new ArrayList<>();
        for (String word : realDictionary) {
            // 与 target 比较生成
            int temp = 0;
            for (int i = 0; i < target.length(); i++) {
                if (target.charAt(i) == word.charAt(i)) {
                    temp |= (1 << i);
                }
            }
            dicTargetDiffBitList.add(temp);
        }
        // 5.对于 3的结果 与 4list 进行位运算匹配 & 结果还是 之前的缩写， 说明这个缩写 跟这个 dictionary的某个缩写一致 都不一致再返回
        for (Abbreviation abbreviation : abbrList) {
            // 遍历 dicTargetDiffBitList 如果都不是 才是要找到
            boolean isAllMis = true;
            for (Integer dicTargetDiff : dicTargetDiffBitList) {
                if ((abbreviation.bitNum & dicTargetDiff) == abbreviation.bitNum) {
                    isAllMis = false;
                    break;
                }
            }
            if (isAllMis) {
                return buildAbbreviationWord(target, abbreviation);
            }

        }
        return "";
    }

    /**
     * 根据省略的bit 生成字符串
     * @param target
     * @param abbreviation
     * @return
     */
    private String buildAbbreviationWord(String target, Abbreviation abbreviation) {
        int bitNum = abbreviation.bitNum;
        String s = Integer.toBinaryString(bitNum);
        StringBuilder builder = new StringBuilder();
        // 从后往前遍历 s 相当于 从前往后找 target 中单词需要 省略还是咋的
        int omitCount = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '1') {
                // 先处理下之前 的 omitCount
                if (omitCount > 0) {
                    builder.append(omitCount);
                    omitCount = 0;
                }
                builder.append(target.charAt(s.length() - 1 - i));
            } else {
                // 0 省略
                omitCount++;
            }
        }
        // 看看之前还有多少个字母
        omitCount += (target.length() - s.length());
        if (omitCount > 0) {
            builder.append(omitCount);
        }
        return builder.toString();
    }

    /**
     *
     * @param abbrList
     * @param target
     * @param index 当前处理到的位置
     * @param abbrLen
     * @param isInOmit 之前是否已经在省略了
     * @param currentBitNum 当前之前生成的数字
     */
    private void dfsGetBitList(List<Abbreviation> abbrList, String target, int index, int abbrLen, boolean isInOmit,
                               int currentBitNum) {
        // 如果当前已经处理到了 末尾 生成 Abbreviation 并返回
        if (index == target.length()) {
            Abbreviation abbreviation = new Abbreviation();
            abbreviation.word = target;
            abbreviation.bitNum = currentBitNum;
            abbreviation.len = abbrLen;

            abbrList.add(abbreviation);
            return;
        }
        // 如果不是 末尾，就有两种选择 1中是保留当前字符，2将其省略 需要看下之前是不是已经省略了
        // 保留当前 字符
        dfsGetBitList(abbrList, target, index + 1, abbrLen + 1, false, currentBitNum | (1 << index));
        // 将当前字符生路 需要判断长度生成
        int nextAbbrLen = abbrLen;
        if (!isInOmit) {
            // 之前就是在省略，本次不用增加长度
            nextAbbrLen++;
        }

        int nextOmitCount = 1;
        if (isInOmit) {
            nextOmitCount++;
        }
        dfsGetBitList(abbrList, target, index + 1, nextAbbrLen, true, currentBitNum);

    }


    // 记录 生成的数字和 原来的字符串 和长度
    class Abbreviation {
        public String word;
        // 字符保留为1 字符隐藏为0
        public int bitNum;
        // 变成缩写之后的缩写字符串长度
        public int len;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String target = "apple";
        String[] dic = LeetcodeInputUtils.inputString2StringArray("[\"blade\"]");
        String s = solution.minAbbreviation(target, dic);
        System.out.println(s);
        Assert.assertEquals("a4", s);
    }
}
