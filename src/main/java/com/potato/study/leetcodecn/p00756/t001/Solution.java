package com.potato.study.leetcodecn.p00756.t001;

import org.junit.Assert;

import java.util.*;

/**
 * 756. 金字塔转换矩阵
 *
 * 现在，我们用一些方块来堆砌一个金字塔。 每个方块用仅包含一个字母的字符串表示。

 使用三元组表示金字塔的堆砌规则如下：

 对于三元组 ABC ，C 为顶层方块，方块 A 、B 分别作为方块 C 下一层的的左、右子块。当且仅当 ABC 是被允许的三元组，我们才可以将其堆砌上。

 初始时，给定金字塔的基层 bottom，用一个字符串表示。一个允许的三元组列表 allowed，每个三元组用一个长度为 3 的字符串表示。

 如果可以由基层一直堆到塔尖就返回 true ，否则返回 false 。

  

 示例 1：

 输入：bottom = "BCD", allowed = ["BCG", "CDE", "GEA", "FFF"]
 输出：true
 解释：
 可以堆砌成这样的金字塔:
 A
 / \
 G   E
 / \ / \
 B   C   D

 因为符合 BCG、CDE 和 GEA 三种规则。
 示例 2：

 输入：bottom = "AABA", allowed = ["AAA", "AAB", "ABA", "ABB", "BAC"]
 输出：false
 解释：
 无法一直堆到塔尖。
 注意, 允许存在像 ABC 和 ABD 这样的三元组，其中 C != D。
  

 提示：

 bottom 的长度范围在 [2, 8]。
 allowed 的长度范围在[0, 200]。
 方块的标记字母范围为{'A', 'B', 'C', 'D', 'E', 'F', 'G'}。


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/pyramid-transition-matrix
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        // 将 allowed 准换成map
        Map<String, String> allowMap = new HashMap<>();
        for (String word : allowed) {
            String key = word.substring(0, 2);
            String value = allowMap.getOrDefault(key, "");
            value += word.substring(2);
            allowMap.put(key, value);
        }
        // dfs
        return canMakePyramid(bottom, "", allowMap, 0);
    }

    /**
     *
     * @param currentBottom     当前遍历的层
     * @param nextBottom        下一层
     * @param allowMap          可以组成的map
     * @param index             当前找到的index
     * @return
     */
    private boolean canMakePyramid(String currentBottom, String nextBottom, Map<String, String> allowMap, int index) {
        // 如果当前层只剩一个 字母了 就说明 这个已经找到了结果
        if (currentBottom.length() == 1) {
            return true;
        }
        // 如果 当前 找到了 最后一个 位置 index 这层就结束了，
        if (index == currentBottom.length() - 1) {
            // 当前字母 在上一个字母就已经被搞过了
            return canMakePyramid(nextBottom, "", allowMap, 0);
        }
        // 还没找到最后一个 字母， 选择两个 字母 fore 找 下一个字母 dfs 每次 失败都记录 seen （这个还没想好怎么做）
        char ch1 = currentBottom.charAt(index);
        char ch2 = currentBottom.charAt(index + 1);
        String key = "" + ch1 + ch2;
        String s = allowMap.get(key);
        if (null == s || s.length() == 0) {
            return false;
        }
        for (char ch3 : s.toCharArray()) {
            String tmpNextBottom = nextBottom + ch3;
            boolean result = canMakePyramid(currentBottom, tmpNextBottom, allowMap, index + 1);
            if (result) {
                return true;
            }
        }
        return false;
    }
}
