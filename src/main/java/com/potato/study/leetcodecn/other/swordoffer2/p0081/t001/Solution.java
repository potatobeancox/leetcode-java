package com.potato.study.leetcodecn.other.swordoffer2.p0081.t001;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 剑指 Offer II 081. 允许重复选择元素的组合
 *
 * 给定一个无重复元素的正整数数组 candidates 和一个正整数 target ，找出 candidates 中所有可以使数字和为目标数 target 的唯一组合。
 *
 * candidates 中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是唯一的。 
 *
 * 对于给定的输入，保证和为 target 的唯一组合数少于 150 个。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: candidates = [2,3,6,7], target = 7
 * 输出: [[7],[2,2,3]]
 * 示例 2：
 *
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 * 示例 3：
 *
 * 输入: candidates = [2], target = 1
 * 输出: []
 * 示例 4：
 *
 * 输入: candidates = [1], target = 1
 * 输出: [[1]]
 * 示例 5：
 *
 * 输入: candidates = [1], target = 2
 * 输出: [[1,1]]
 *  
 *
 * 提示：
 *
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/Ygoe9J
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 还是回溯吧
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Set<List<Integer>> result = new HashSet<>();
        List<Integer> currentResult = new ArrayList<>();
        this.getCombination(candidates, target, 0, 0, result, currentResult);
        return new ArrayList<>(result);
    }


    /**
     *
     * @param candidates
     * @param target
     * @param index
     * @param currentSum
     * @param result
     * @param currentResult
     */
    private void getCombination(int[] candidates, int target, int index, int currentSum, Set<List<Integer>> result,
            List<Integer> currentResult) {
        if (currentSum == target) {
            result.add(new ArrayList<>(currentResult));
            return;
        }
        if (currentSum > target) {
            return;
        }
        if (index >= candidates.length) {
            return;
        }
        // 不用当前这个数字
        getCombination(candidates, target, index+1, currentSum, result, new ArrayList<>(currentResult));
        // 使用当前这个数字
        int num = candidates[index];
        int tmp = currentSum;
        ArrayList<Integer> integers = new ArrayList<>(currentResult);
        while (tmp + num <= target) {
            integers.add(num);
            getCombination(candidates, target, index, tmp + num, result, integers);
            tmp += num;
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] candidates = new int[] {2,3,6,7};
        int target = 7;
        List<List<Integer>> list = solution.combinationSum(candidates, target);
        System.out.println(list);
    }
}
