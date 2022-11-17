package com.potato.study.leetcodecn.p00425.t001;

import org.junit.Assert;

import java.util.List;

/**
 * 425. 单词方块
 *
 *
 * 给定一个单词集合 words （没有重复），找出并返回其中所有的 单词方块 。 words 中的同一个单词可以被 多次 使用。你可以按 任意顺序 返回答案。

 一个单词序列形成了一个有效的 单词方块 的意思是指从第 k 行和第 k 列  0 <= k < max(numRows, numColumns) 来看都是相同的字符串。

 例如，单词序列 ["ball","area","lead","lady"] 形成了一个单词方块，因为每个单词从水平方向看和从竖直方向看都是相同的。
  

 示例 1：

 输入：words = ["area","lead","wall","lady","ball"]
 输出: [["ball","area","lead","lady"],
 ["wall","area","lead","lady"]]
 解释：
 输出包含两个单词方块，输出的顺序不重要，只需要保证每个单词方块内的单词顺序正确即可。
 示例 2：

 输入：words = ["abat","baba","atan","atal"]
 输出：[["baba","abat","baba","atal"],
 ["baba","abat","baba","atan"]]
 解释：
 输出包含两个单词方块，输出的顺序不重要，只需要保证每个单词方块内的单词顺序正确即可。
  

 提示:

 1 <= words.length <= 1000
 1 <= words[i].length <= 4
 words[i] 长度相同
 words[i] 只由小写英文字母组成
 words[i] 都 各不相同

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/word-squares
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public List<List<String>> wordSquares(String[] words) {
        return null;
    }
}
