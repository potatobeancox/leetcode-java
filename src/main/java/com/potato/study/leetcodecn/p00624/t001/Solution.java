package com.potato.study.leetcodecn.p00624.t001;


import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.List;

/**
 * 624. 数组列表中的最大距离
 *
 * 给定 m 个数组，每个数组都已经按照升序排好序了。现在你需要从两个不同的数组中选择两个整数（每个数组选一个）并且计算它们的距离。两个整数 a 和 b 之间的距离定义为它们差的绝对值 |a-b| 。你的任务就是去找到最大距离
 *
 * 示例 1：
 *
 * 输入：
 * [[1,2,3],
 *  [4,5],
 *  [1,2,3]]
 * 输出： 4
 * 解释：
 * 一种得到答案 4 的方法是从第一个数组或者第三个数组中选择 1，同时从第二个数组中选择 5 。
 *  
 *
 * 注意：
 *
 * 每个给定数组至少会有 1 个数字。列表中至少有两个非空数组。
 * 所有 m 个数组中的数字总数目在范围 [2, 10000] 内。
 * m 个数组中所有整数的范围在 [-10000, 10000] 内。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-distance-in-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/maximum-distance-in-arrays/solution/dong-tai-gui-hua-de-jian-hua-100-by-tsin-tyor/
     * @param arrays
     * @return
     */
    public int maxDistance(List<List<Integer>> arrays) {
        if (null == arrays || arrays.size() < 2) {
            return 0;
        }
        // 遍历 arrays 遍历到 每个 array 获取当前最大值和最小值
        int totalMaxDistance = 0;
        List<Integer> firstArray = arrays.get(0);
        // 维护一个历史的最大值和最小值 （当前 array 之前的）
        int min = firstArray.get(0);
        int max = firstArray.get(firstArray.size() -1);
        // 用这列的最大值最小值，减去之前的最小值和最大值，得到全局的最大距离
        for (int i = 1; i < arrays.size(); i++) {
            List<Integer> thisList = arrays.get(i);
            int thisMin = thisList.get(0);
            int thisMax = thisList.get(thisList.size() - 1);

            // 计算结果
            totalMaxDistance = Math.max(totalMaxDistance, thisMax - min);
            totalMaxDistance = Math.max(totalMaxDistance, max - thisMin);

            // 更新最大值和最小值
            max = Math.max(max, thisMax);
            min = Math.min(min, thisMin);
        }

        return totalMaxDistance;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[1,4],[0,5]]";
        List<List<Integer>> arrays = LeetcodeInputUtils.inputString2IntListTwoDimensional(input);
        int i = solution.maxDistance(arrays);
        System.out.println(i);
        Assert.assertEquals(4, i);
    }

}
