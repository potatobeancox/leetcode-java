package com.potato.study.leetcodecn.p00996.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.*;

/**
 * 996. 正方形数组的数目
 *
 * 给定一个非负整数数组 A，如果该数组每对相邻元素之和是一个完全平方数，则称这一数组为正方形数组。
 *
 * 返回 A 的正方形排列的数目。两个排列 A1 和 A2 不同的充要条件是存在某个索引 i，使得 A1[i] != A2[i]。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[1,17,8]
 * 输出：2
 * 解释：
 * [1,8,17] 和 [17,8,1] 都是有效的排列。
 * 示例 2：
 *
 * 输入：[2,2,2]
 * 输出：1
 *  
 *
 * 提示：
 *
 * 1 <= A.length <= 12
 * 0 <= A[i] <= 1e9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-squareful-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int numSquarefulPerms(int[] nums) {
        // 统计 nums 每个数字 出现的次数 作为 dfs每次判断是否使用的依据
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        // 通过上面 数量map 的key 生成 任意点对应平方数链接的 矩阵 表示可以使用的点
        Map<Integer, Set<Integer>> connMap = new HashMap<>();
        for (int key1 : countMap.keySet()) {
            for (int key2 : countMap.keySet()) {
                int sqrt = (int) Math.sqrt(key1 + key2);
                if (sqrt * sqrt != key1 + key2) {
                    continue;
                }
                Set<Integer> set1 = connMap.getOrDefault(key1, new HashSet<>());
                set1.add(key2);
                connMap.put(key1, set1);

                Set<Integer> set2 = connMap.getOrDefault(key2, new HashSet<>());
                set2.add(key1);
                connMap.put(key2, set2);
            }
        }
        // dfs 当前没有使用的 map 当前的点 ，还需要多少个点 从每个店开始找
        int res = 0;
        // 开始节点必须不同
        for (int num : countMap.keySet()) {
            countMap.put(num, countMap.get(num) - 1);
            // 已经使用一个了
            res += dfs(countMap, nums.length-1, num, connMap);
            countMap.put(num, countMap.get(num) + 1);
        }
        return res;
    }

    private int dfs(Map<Integer, Integer> countMap, int needCount, int current, Map<Integer, Set<Integer>> connMap) {
        // 终止条件 当前需要的数量是 0
        if (needCount <= 0) {
            return 1;
        }
        // 找到当前对应的节点
        Set<Integer> nextSet = connMap.get(current);
        if (null == nextSet) {
            // 找不到后续节点 无法生成有效的点
            return 0;
        }
        int total = 0;
        for (int next : nextSet) {
            // 没有了
            if (countMap.getOrDefault(next, 0) <= 0) {
                continue;
            }
            // 使用一个 count 并递归找情况
            countMap.put(next, countMap.get(next) - 1);
            total += dfs(countMap, needCount-1, next, connMap);
            countMap.put(next, countMap.get(next) + 1);
        }
        return total;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = LeetcodeInputUtils.inputString2IntArray("[1,17,8]");
        int i = solution.numSquarefulPerms(nums);
        System.out.println(i);
        Assert.assertEquals(2, i);


        nums = LeetcodeInputUtils.inputString2IntArray("[2,2,2]");
        i = solution.numSquarefulPerms(nums);
        System.out.println(i);
        Assert.assertEquals(1, i);
    }
}
