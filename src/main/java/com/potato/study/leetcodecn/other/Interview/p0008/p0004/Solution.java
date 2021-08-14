package com.potato.study.leetcodecn.other.Interview.p0008.p0004;


import java.util.ArrayList;
import java.util.List;

/**
 * 面试题 08.04. 幂集
 *
 * 幂集。编写一种方法，返回某集合的所有子集。集合中不包含重复的元素。

 说明：解集不能包含重复的子集。

 示例:

 输入： nums = [1,2,3]
 输出：
 [
 [3],
   [1],
   [2],
   [1,2,3],
   [1,3],
   [2,3],
   [1,2],
   []
 ]

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/power-set-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * 使用 list 存之前的结果 空集合 放进去
     * 遍历 nums
     *  每一个 num fori 遍历 list 对于每个值 生成新结果并加入 num 插入结果集合
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        // 空集合 放进去
        resultList.add(new ArrayList<>());
        for (int num : nums) {
            int length = resultList.size();
            for (int i = 0; i < length; i++) {
                List<Integer> oldResult = resultList.get(i);
                // 对于每个值 生成新结果并加入 num
                List<Integer> currentResult = new ArrayList<>(oldResult);
                currentResult.add(num);
                // 插入结果集合
                resultList.add(currentResult);
            }
        }
        return resultList;
    }

}
