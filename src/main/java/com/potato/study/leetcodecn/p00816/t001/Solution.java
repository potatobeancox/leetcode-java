package com.potato.study.leetcodecn.p00816.t001;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.potato.study.leetcode.domain.ListNode;

/**
 * 816. 模糊坐标
 *
 * 我们有一些二维坐标，如 "(1, 3)" 或 "(2, 0.5)"，然后我们移除所有逗号，小数点和空格，得到一个字符串S。返回所有可能的原始字符串到一个列表中。
 *
 * 原始的坐标表示法不会存在多余的零，所以不会出现类似于"00", "0.0", "0.00", "1.0", "001", "00.01"或一些其他更小的数来表示坐标。此外，一个小数点前至少存在一个数，所以也不会出现“.1”形式的数字。
 *
 * 最后返回的列表可以是任意顺序的。而且注意返回的两个数字中间（逗号之后）都有一个空格。
 *
 *  
 *
 * 示例 1:
 * 输入: "(123)"
 * 输出: ["(1, 23)", "(12, 3)", "(1.2, 3)", "(1, 2.3)"]
 * 示例 2:
 * 输入: "(00011)"
 * 输出:  ["(0.001, 1)", "(0, 0.011)"]
 * 解释:
 * 0.0, 00, 0001 或 00.01 是不被允许的。
 * 示例 3:
 * 输入: "(0123)"
 * 输出: ["(0, 123)", "(0, 12.3)", "(0, 1.23)", "(0.1, 23)", "(0.1, 2.3)", "(0.12, 3)"]
 * 示例 4:
 * 输入: "(100)"
 * 输出: [(10, 0)]
 * 解释:
 * 1.0 是不被允许的。
 *  
 *
 * 提示:
 *
 * 4 <= S.length <= 12.
 * S[0] = "(", S[S.length - 1] = ")", 且字符串 S 中的其他元素都是数字。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ambiguous-coordinates
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public List<String> ambiguousCoordinates(String s) {
        // 去掉 （）
        String substring = s.substring(1, s.length() - 1);
        // 首先枚举分割点
        List<String> resultList = new ArrayList<>();
        for (int i = 1; i < substring.length(); i++) {
            String left = substring.substring(0, i);
            String right = substring.substring(i);
            for (String leftString : getValidCoordinate(left)) {
                for (String rightString : getValidCoordinate(right)) {
                    resultList.add("(" + leftString + ", " + rightString + ")");
                }
            }
        }
        // 针对每个分割，往其中添加 . 形成坐标 返回
        return resultList;
    }

    private List<String> getValidCoordinate(String numString) {
        // 枚举每一个可以插入.的位置
        List<String> list = new ArrayList<>();
        for (int i = 1; i < numString.length(); i++) {
            String left = numString.substring(0, i);
            if (left.startsWith("0") && !"0".equals(left)) {
                continue;
            }
            String right = numString.substring(i);
            if (right.equals("0")) {
                continue;
            }
            if (right.endsWith("0")) {
                continue;
            }
            list.add(left + "." + right);
        }
        // 不分割
        if (!numString.startsWith("0") || "0".equals(numString)) {
            list.add(numString);
        }
        return list;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "(0123)";
        List<String> list = solution.ambiguousCoordinates(s);
        System.out.println(list);

        s = "(00011)";
        list = solution.ambiguousCoordinates(s);
        System.out.println(list);
    }
}
