package com.potato.study.leetcodecn.other.swordoffer2.p0085.t001;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer II 085. 生成匹配的括号
 *
 * 正整数 n 代表生成括号的对数，请设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：["()"]
 *  
 *
 * 提示：
 *
 * 1 <= n <= 8
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/IDBivT
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        this.getParenthesis(result, "", n, 0, 0);
        return result;
    }


    /**
     *
     * @param result  总结果
     * @param current 当前字符串
     * @param n     能用左括号
     * @param state 当前状态
     * @param used  已经使用的左括号数
     */
    private void getParenthesis(List<String> result, String current, int n, int state, int used) {
        // 终止条件
        if (used == n && state == 0) {
            result.add(current);
            return;
        }
        // 先添加一个 （  递归生成
        if (used < n) {
            getParenthesis(result, current + "(", n, state + 1, used + 1);
        }
        // 添加一个 ） 递归生成
        if (state > 0) {
            getParenthesis(result, current + ")", n, state - 1, used);
        }
    }

    public static void main(String[] args) {

    }
}
