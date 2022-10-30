package com.potato.study.leetcodecn.p01196.t001;


import java.util.Arrays;
import java.util.concurrent.Semaphore;

/**
 * 1196. 最多可以买到的苹果数量
 *
 * 你有一些苹果和一个可以承载 5000 单位重量的篮子。
 *
 * 给定一个整数数组 weight ，其中 weight[i] 是第 i 个苹果的重量，返回 你可以放入篮子的最大苹果数量 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：weight = [100,200,150,1000]
 * 输出：4
 * 解释：所有 4 个苹果都可以装进去，因为它们的重量之和为 1450。
 * 示例 2：
 *
 * 输入：weight = [900,950,800,1000,700,800]
 * 输出：5
 * 解释：6 个苹果的总重量超过了 5000，所以我们只能从中任选 5 个。
 *  
 *
 * 提示：
 *
 * 1 <= weight.length <= 103
 * 1 <= weight[i] <= 103
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/how-many-apples-can-you-put-into-the-basket
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {
    public int maxNumberOfApples(int[] weight) {
        Arrays.sort(weight);
        int sum = 0;
        int count = 0;
        for (int i = 0; i < weight.length; i++) {
            sum += weight[i];
            if (sum <= 5000) {
                count++;
            } else {
                // 大于 5000
                return count;
            }
        }
        return count;
    }
}
