package com.potato.study.leetcodecn.p02587.t001;

import com.potato.study.leetcode.domain.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 2587. 重排数组以得到最大前缀分数

 给你一个下标从 0 开始的整数数组 nums 。你可以将 nums 中的元素按 任意顺序 重排（包括给定顺序）。

 令 prefix 为一个数组，它包含了 nums 重新排列后的前缀和。换句话说，prefix[i] 是 nums 重新排列后下标从 0 到 i 的元素之和。nums 的 分数 是 prefix 数组中正整数的个数。

 返回可以得到的最大分数。

  

 示例 1：

 输入：nums = [2,-1,0,1,-3,3,-3]
 输出：6
 解释：数组重排为 nums = [2,3,1,-1,-3,0,-3] 。
 prefix = [2,5,6,5,2,2,-1] ，分数为 6 。
 可以证明 6 是能够得到的最大分数。
 示例 2：

 输入：nums = [-2,-3,0]
 输出：0
 解释：不管怎么重排数组得到的分数都是 0 。
  

 提示：

 1 <= nums.length <= 105
 -106 <= nums[i] <= 106

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/rearrange-array-to-maximize-prefix-score
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 2587
    public int maxScore(int[] nums) {
        int score = 0;
        long sum = 0;
        // 记录 负数 将负数按照 从小达到排序 用sum计算 和
        List<Integer> negativeList = new ArrayList<>();
        for (int num : nums) {
            if (num >= 0) {
                sum += num;
                score++;
            } else {
                negativeList.add(num);
            }
        }
        // 如果所有非负数相加等于0 说明都是0 那么总的前缀长度就大于0
        if (sum == 0) {
            return 0;
        }
        Collections.sort(negativeList);
        for (int neg : negativeList) {
            sum += neg;
            if (sum > 0) {
                score++;
            }
        }
        return score;
    }




}
