package com.potato.study.leetcodecn.p01526.t001;

import org.junit.Assert;

/**
 * 1526. 形成目标数组的子数组最少增加次数
 *
 * 给你一个整数数组 target 和一个数组 initial ，initial 数组与 target  数组有同样的维度，且一开始全部为 0 。

 请你返回从 initial 得到  target 的最少操作次数，每次操作需遵循以下规则：

 在 initial 中选择 任意 子数组，并将子数组中每个元素增加 1 。
 答案保证在 32 位有符号整数以内。

  

 示例 1：

 输入：target = [1,2,3,2,1]
 输出：3
 解释：我们需要至少 3 次操作从 intial 数组得到 target 数组。
 [0,0,0,0,0] 将下标为 0 到 4 的元素（包含二者）加 1 。
 [1,1,1,1,1] 将下标为 1 到 3 的元素（包含二者）加 1 。
 [1,2,2,2,1] 将下表为 2 的元素增加 1 。
 [1,2,3,2,1] 得到了目标数组。
 示例 2：

 输入：target = [3,1,1,2]
 输出：4
 解释：(initial)[0,0,0,0] -> [1,1,1,1] -> [1,1,1,2] -> [2,1,1,2] -> [3,1,1,2] (target) 。
 示例 3：

 输入：target = [3,1,5,4,2]
 输出：7
 解释：(initial)[0,0,0,0,0] -> [1,1,1,1,1] -> [2,1,1,1,1] -> [3,1,1,1,1]
 -> [3,1,2,2,2] -> [3,1,3,3,2] -> [3,1,4,4,2] -> [3,1,5,4,2] (target)。
 示例 4：

 输入：target = [1,1,1,1]
 输出：1
  

 提示：

 1 <= target.length <= 10^5
 1 <= target[i] <= 10^5

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int minNumberOperations(int[] target) {
        int total = target[0];
        if (target.length == 1) {
            return total;
        }
        // 求每两个位置之间的差值
        int[] diff = new int[target.length - 1];
        // 从第一个位置开会求 大于 0的值
        for (int i = 0; i < target.length - 1; i++) {
            diff[i] = target[i+1] - target[i];
        }
        // 累加大于 0
        for (int d : diff) {
            if (d <= 0) {
                continue;
            }
            total += d;
        }
        return total;
    }
}
