package com.potato.study.leetcodecn.other.swordoffer2.p0082.t001;

import java.util.*;

/**
 * 剑指 Offer II 082. 含有重复元素集合的组合
 *
 * 给定一个可能有重复数字的整数数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

 candidates 中的每个数字在每个组合中只能使用一次，解集不能包含重复的组合。 

  

 示例 1:

 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 输出:
 [
 [1,1,6],
 [1,2,5],
 [1,7],
 [2,6]
 ]
 示例 2:

 输入: candidates = [2,5,2,1,2], target = 5,
 输出:
 [
 [1,2,2],
 [5]
 ]
  

 提示:

 1 <= candidates.length <= 100
 1 <= candidates[i] <= 50
 1 <= target <= 30
  

 注意：本题与主站 40 题相同： https://leetcode-cn.com/problems/combination-sum-ii/

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/4sjJUc
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 对 candidates 进行排序 用一个list 记录当前选择了的数字 用一个 temp 记录 当前的sum值 注意剪枝
        List<List<Integer>> result = new ArrayList<>();
        long tempSum = 0;
        List<Integer> tempResult = new ArrayList<>();
        int index = 0;
        Arrays.sort(candidates);
        dfs(result, tempSum, tempResult, index, target, candidates);
        return result;
    }

    /**
     *
     * @param result
     * @param tempSum
     * @param tempResult
     * @param index 从index 开始遍历 依次选择 一个 往下dfs
     */
    private void dfs(List<List<Integer>> result, long tempSum, List<Integer> tempResult,
                     int index, int target, int[] candidates) {
        // 那这个结果看看能不能保留先看看 tempSum 是不是ok的
        if (tempSum == target) {
            result.add(new ArrayList<>(tempResult));
            return;
        }
        // 当前已经index 到了末尾，且没有达到目标
        if (index == candidates.length) {
            return;
        }
        // 剪枝
        if (tempSum > target) {
            return;
        }
        // 从index 开始往后 遍历 并选择
        for (int i = index; i < candidates.length; i++) {

            // 剪枝一样的
            if (i > index && candidates[i] == candidates[i-1]) {
                continue;
            }

            int value = candidates[i];
            // 选择这个点看看行不行
            tempResult.add(value);
            dfs(result, tempSum + value, tempResult, i + 1, target, candidates);
            tempResult.remove(tempResult.size() - 1);
        }
    }
}
