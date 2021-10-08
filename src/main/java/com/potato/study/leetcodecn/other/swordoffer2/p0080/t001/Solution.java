package com.potato.study.leetcodecn.other.swordoffer2.p0080.t001;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer II 080. 含有 k 个元素的组合
 *
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * 示例 2:
 *
 * 输入: n = 1, k = 1
 * 输出: [[1]]
 *  
 *
 * 提示:
 *
 * 1 <= n <= 20
 * 1 <= k <= n
 *  
 *
 * 注意：本题与主站 77 题相同： https://leetcode-cn.com/problems/combinations/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/uUsW3B
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 回溯吧
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentResult = new ArrayList<>();
        this.getCombine(n, k, 1, 0, result, currentResult);
        return result;
    }



    /**
     *
     * @param n
     * @param k
     * @param current       当前该用哪个字符串遍历
     * @param currentUsed   当前已经有了多少个 字符
     * @param result        结果
     */
    private void getCombine(int n, int k, int current, int currentUsed, List<List<Integer>> result,
            List<Integer> currentResult) {
        // 终止条件
        List<Integer> list = new ArrayList<>(currentResult);
        if (currentUsed == k) {
            result.add(list);
            return;
        }
        if (current > n) {
            return;
        }
        // 不用当前 current 往后走
        getCombine(n, k, current + 1, currentUsed, result, currentResult);
        // 选择当前 current
        list.add(current);
        getCombine(n, k, current + 1, currentUsed + 1, result, list);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> combine = solution.combine(4, 2);
        System.out.println(combine);
    }
}
