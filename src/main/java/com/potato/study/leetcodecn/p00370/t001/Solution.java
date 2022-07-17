package com.potato.study.leetcodecn.p00370.t001;

import org.junit.Assert;

/**
 * 370. 区间加法
 *
 * 假设你有一个长度为 n 的数组，初始情况下所有的数字均为 0，你将会被给出 k​​​​​​​ 个更新的操作。

 其中，每个操作会被表示为一个三元组：[startIndex, endIndex, inc]，你需要将子数组 A[startIndex ... endIndex]（包括 startIndex 和 endIndex）增加 inc。

 请你返回 k 次操作后的数组。

 示例:

 输入: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
 输出: [-2,0,3,5,3]
 解释:

 初始状态:
 [0,0,0,0,0]

 进行了操作 [1,3,2] 后的状态:
 [0,2,2,2,0]

 进行了操作 [2,4,3] 后的状态:
 [0,2,5,5,3]

 进行了操作 [0,2,-2] 后的状态:
 [-2,0,3,5,3]

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/range-addition
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int[] getModifiedArray(int length, int[][] updates) {
        // 查分数组
        int[] result = new int[length];
        // 遍历 设置
        for (int[] update : updates) {
            int startIndex = update[0];
            int endIndex = update[1] + 1;
            int value = update[2];

            result[startIndex] += value;
            if (endIndex < length) {
                result[endIndex] -= value;
            }
        }
        // 遍历查分数组 求 和
        for (int i = 1; i < length; i++) {
            result[i] += result[i-1];
        }
        return result;
    }


}
