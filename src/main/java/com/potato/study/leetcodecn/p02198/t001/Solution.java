package com.potato.study.leetcodecn.p02198.t001;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 2198. Number of Single Divisor Triplets
 *
 * You are given a 0-indexed array of positive integers nums. A triplet of three distinct indices (i, j, k) is called a single divisor triplet of nums if nums[i] + nums[j] + nums[k] is divisible by exactly one of nums[i], nums[j], or nums[k].

 Return the number of single divisor triplets of nums.
  

 Example 1:

 Input: nums = [4,6,7,3,2]
 Output: 12
 Explanation:
 The triplets (0, 3, 4), (0, 4, 3), (3, 0, 4), (3, 4, 0), (4, 0, 3), and (4, 3, 0) have the values of [4, 3, 2] (or a permutation of [4, 3, 2]).
 4 + 3 + 2 = 9 which is only divisible by 3, so all such triplets are single divisor triplets.
 The triplets (0, 2, 3), (0, 3, 2), (2, 0, 3), (2, 3, 0), (3, 0, 2), and (3, 2, 0) have the values of [4, 7, 3] (or a permutation of [4, 7, 3]).
 4 + 7 + 3 = 14 which is only divisible by 7, so all such triplets are single divisor triplets.
 There are 12 single divisor triplets in total.
 Example 2:

 Input: nums = [1,2,2]
 Output: 6
 Explanation:
 The triplets (0, 1, 2), (0, 2, 1), (1, 0, 2), (1, 2, 0), (2, 0, 1), and (2, 1, 0) have the values of [1, 2, 2] (or a permutation of [1, 2, 2]).
 1 + 2 + 2 = 5 which is only divisible by 1, so all such triplets are single divisor triplets.
 There are 6 single divisor triplets in total.
 Example 3:

 Input: nums = [1,1,1]
 Output: 0
 Explanation:
 There are no single divisor triplets.
 Note that (0, 1, 2) is not a single divisor triplet because nums[0] + nums[1] + nums[2] = 3 and 3 is divisible by nums[0], nums[1], and nums[2].
  

 Constraints:

 3 <= nums.length <= 105
 1 <= nums[i] <= 100

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/number-of-single-divisor-triplets
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/number-of-single-divisor-triplets/solution/ji-shu-by-newhar-oaeg/
     * @param nums
     * @return
     */
    public long singleDivisorTriplet(int[] nums) {
        // 1 <= nums[i] <= 100 统计个数
        int[] count = new int[101];
        for (int num : nums) {
            count[num]++;
        }
        // 遍历判断
        long total = 0;
        for (int i = 1; i <= 100; i++) {
            for (int j = i ; j <= 100; j++) {
                for (int k = j; k <= 100; k++) {
                    int target = i + j + k;
                    int status = 0;
                    if (target % i == 0) {
                        status++;
                    }
                    if (target % j == 0) {
                        status++;
                    }
                    if (target % k == 0) {
                        status++;
                    }
                    if (status != 1) {
                        continue;
                    }
                    // 计算次数
                    if (i != j && j != k) {
                        // a33 *
                        total += 6L * count[i] * count[j] * count[k];
                    } else if (i == j) {
                        total += 6L * count[i] * (count[i] - 1) / 2 * count[k];
                    } else if (j == k) {
                        // a33 *
                        total += 6L * count[i] * count[j] * (count[j] - 1) / 2;
                    } else {
                        // 都相等
                        // 也不可能有这个状态
                        continue;
                    }

                }
            }
        }
        return total;
    }

}
