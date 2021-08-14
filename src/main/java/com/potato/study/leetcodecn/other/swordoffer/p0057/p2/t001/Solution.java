package com.potato.study.leetcodecn.other.swordoffer.p0057.p2.t001;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 剑指 Offer 57 - II. 和为s的连续正数序列
 *
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。

 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。

  

 示例 1：

 输入：target = 9
 输出：[[2,3,4],[4,5]]
 示例 2：

 输入：target = 15
 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
  

 限制：

 1 <= target <= 10^5

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 1. 求前缀和
     * 2. 过一遍前缀和 n2 求两个前缀和的查是不是target
     * @param target
     * @return
     */
    public int[][] findContinuousSequence(int target) {
        int[] prefix = new int[target+1];
        prefix[1] = 1;
        // 和 sum 对应加到的数
        Map<Integer, Integer> sumIndexMap = new HashMap<>();
        sumIndexMap.put(prefix[1], 1);
        List<int[]> result = new ArrayList<>();
        for (int i = 2; i < target; i++) {
            prefix[i] = prefix[i-1] + i;
            if (sumIndexMap.containsKey(prefix[i] - target)) {
                int[] res = new int[i - sumIndexMap.get(prefix[i] - target)];
                int index = 0;
                for (int j = sumIndexMap.get(prefix[i] - target) + 1; j <= i; j++) {
                    res[index++] = j;
                }
                result.add(res);
            } else if (prefix[i] == target) {
                int[] res = new int[i];
                for (int j = 1; j <= i; j++) {
                    res[j-1] = j;
                }
                result.add(res);
            }
            sumIndexMap.put(prefix[i], i);
        }
        // list -> arr
        int[][] resultArr = new int[result.size()][];
        for (int i = 0; i < result.size(); i++) {
            resultArr[i] = result.get(i);
        }
        return resultArr;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int target = 9;
        int[][] continuousSequence = solution.findContinuousSequence(target);
        System.out.println(Arrays.deepToString(continuousSequence));

        target = 15;
        continuousSequence = solution.findContinuousSequence(target);
        System.out.println(Arrays.deepToString(continuousSequence));
    }

}
