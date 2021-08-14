package com.potato.study.leetcodecn.other.Interview.p0008.p0009;


import java.util.ArrayList;
import java.util.List;

/**
 * 面试题 08.09. 括号
 *
 * 括号。设计一种算法，打印n对括号的所有合法的（例如，开闭一一对应）组合。

 说明：解集不能包含重复的子集。

 例如，给出 n = 3，生成结果为：

 [
 "((()))",
 "(()())",
 "(())()",
 "()(())",
 "()()()"
 ]

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/bracket-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * 递归生成 结果 并放入结果集合
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        getEachParenthesis(result, 0, 0, "", n);
        return result;
    }



    /**
     *
     * @param result            最终结果集合
     * @param status            当前括号状态 1 为有一个没有匹配的左括号
     * @param usedCount         当前已经使用左括号的个数
     * @param currentResrult    当前结果
     * @param n                 总共需要多少个左括号
     */
    private void getEachParenthesis(List<String> result, int status, int usedCount, String currentResrult, int n) {
        // 终止条件 状态 == 0 平衡 且 左括号用完 直接将 当前结果入 结果集合 返回
        if (status == 0 && usedCount == n) {
            result.add(currentResrult);
            return;
        }
        // 如果 当前还可以加左括号 加了 递归
        if (usedCount < n) {
            getEachParenthesis(result, status+1, usedCount+1, currentResrult + "(", n);
        }
        // 如果 当前可以加右括号，加了 递归
        if (status > 0) {
            getEachParenthesis(result, status-1, usedCount, currentResrult + ")", n);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> list = solution.generateParenthesis(3);
        System.out.println(list);
    }

}
