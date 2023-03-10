package com.potato.study.leetcodecn.p00982.t001;

import org.junit.Assert;

import java.util.HashSet;
import java.util.Set;

/**
 * 982. 按位与为零的三元组
 *
 * 给你一个整数数组 nums ，返回其中 按位与三元组 的数目。
 *
 * 按位与三元组 是由下标 (i, j, k) 组成的三元组，并满足下述全部条件：
 *
 * 0 <= i < nums.length
 * 0 <= j < nums.length
 * 0 <= k < nums.length
 * nums[i] & nums[j] & nums[k] == 0 ，其中 & 表示按位与运算符。
 *  
 * 示例 1：
 *
 * 输入：nums = [2,1,3]
 * 输出：12
 * 解释：可以选出如下 i, j, k 三元组：
 * (i=0, j=0, k=1) : 2 & 2 & 1
 * (i=0, j=1, k=0) : 2 & 1 & 2
 * (i=0, j=1, k=1) : 2 & 1 & 1
 * (i=0, j=1, k=2) : 2 & 1 & 3
 * (i=0, j=2, k=1) : 2 & 3 & 1
 * (i=1, j=0, k=0) : 1 & 2 & 2
 * (i=1, j=0, k=1) : 1 & 2 & 1
 * (i=1, j=0, k=2) : 1 & 2 & 3
 * (i=1, j=1, k=0) : 1 & 1 & 2
 * (i=1, j=2, k=0) : 1 & 3 & 2
 * (i=2, j=0, k=1) : 3 & 2 & 1
 * (i=2, j=1, k=0) : 3 & 1 & 2
 * 示例 2：
 *
 * 输入：nums = [0,0,0]
 * 输出：27
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] < 216
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/triples-with-bitwise-and-equal-to-zero
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    // 982
    public int countTriplets(int[] nums) {
        // 计数器 16 位 相当于 用到了 32位
        int limit = (1 << 16);
        int[] count = new int[limit];
        // 双循环
        for (int n1 : nums) {
            for (int n2 : nums) {
                count[(n1 & n2)]++;
            }
        }
        // 遍历看看 没有有0的加上去
        int total = 0;
        for (int n : nums) {
            for (int i = 0; i < count.length; i++) {
                int state = n & i;
                if (state == 0) {
                    total += count[i];
                }
            }
        }
        return total;
    }

}
