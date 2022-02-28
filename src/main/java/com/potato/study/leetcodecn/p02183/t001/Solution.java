package com.potato.study.leetcodecn.p02183.t001;

import com.potato.study.leetcode.domain.ListNode;

/**
 * 2183. 统计可以被 K 整除的下标对数目
 *
 * 给你一个下标从 0 开始、长度为 n 的整数数组 nums 和一个整数 k ，返回满足下述条件的下标对 (i, j) 的数目：
 *
 * 0 <= i < j <= n - 1 且
 * nums[i] * nums[j] 能被 k 整除。
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,4,5], k = 2
 * 输出：7
 * 解释：
 * 共有 7 对下标的对应积可以被 2 整除：
 * (0, 1)、(0, 3)、(1, 2)、(1, 3)、(1, 4)、(2, 3) 和 (3, 4)
 * 它们的积分别是 2、4、6、8、10、12 和 20 。
 * 其他下标对，例如 (0, 2) 和 (2, 4) 的乘积分别是 3 和 15 ，都无法被 2 整除。
 * 示例 2：
 *
 * 输入：nums = [1,2,3,4], k = 5
 * 输出：0
 * 解释：不存在对应积可以被 5 整除的下标对。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i], k <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-array-pairs-divisible-by-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public long countPairs(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((long)nums[i] * nums[j] % k == 0) {
                    count++;
                }
            }
        }
        return count;
    }
}
