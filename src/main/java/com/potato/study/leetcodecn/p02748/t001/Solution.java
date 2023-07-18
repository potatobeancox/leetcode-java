package com.potato.study.leetcodecn.p02748.t001;


import org.junit.Assert;

/**
 *
 * 2748. 美丽下标对的数目
 *
 * 给你一个下标从 0 开始的整数数组 nums 。如果下标对 i、j 满足 0 ≤ i < j < nums.length ，如果 nums[i] 的 第一个数字 和 nums[j] 的 最后一个数字 互质 ，则认为 nums[i] 和 nums[j] 是一组 美丽下标对 。
 *
 * 返回 nums 中 美丽下标对 的总数目。
 *
 * 对于两个整数 x 和 y ，如果不存在大于 1 的整数可以整除它们，则认为 x 和 y 互质 。换而言之，如果 gcd(x, y) == 1 ，则认为 x 和 y 互质，其中 gcd(x, y) 是 x 和 k 最大公因数 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [2,5,1,4]
 * 输出：5
 * 解释：nums 中共有 5 组美丽下标对：
 * i = 0 和 j = 1 ：nums[0] 的第一个数字是 2 ，nums[1] 的最后一个数字是 5 。2 和 5 互质，因此 gcd(2,5) == 1 。
 * i = 0 和 j = 2 ：nums[0] 的第一个数字是 2 ，nums[1] 的最后一个数字是 1 。2 和 5 互质，因此 gcd(2,1) == 1 。
 * i = 1 和 j = 2 ：nums[0] 的第一个数字是 5 ，nums[1] 的最后一个数字是 1 。2 和 5 互质，因此 gcd(5,1) == 1 。
 * i = 1 和 j = 3 ：nums[0] 的第一个数字是 5 ，nums[1] 的最后一个数字是 4 。2 和 5 互质，因此 gcd(5,4) == 1 。
 * i = 2 和 j = 3 ：nums[0] 的第一个数字是 1 ，nums[1] 的最后一个数字是 4 。2 和 5 互质，因此 gcd(1,4) == 1 。
 * 因此，返回 5 。
 * 示例 2：
 *
 * 输入：nums = [11,21,12]
 * 输出：2
 * 解释：共有 2 组美丽下标对：
 * i = 0 和 j = 1 ：nums[0] 的第一个数字是 1 ，nums[1] 的最后一个数字是 1 。gcd(1,1) == 1 。
 * i = 0 和 j = 2 ：nums[0] 的第一个数字是 1 ，nums[1] 的最后一个数字是 2 。gcd(1,2) == 1 。
 * 因此，返回 2 。
 *  
 *
 * 提示：
 *
 * 2 <= nums.length <= 100
 * 1 <= nums[i] <= 9999
 * nums[i] % 10 != 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-beautiful-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {





    public int countBeautifulPairs(int[] nums) {
        int pairCount = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                // 提取i的第一个 和j的最后一个
                int num1 = String.valueOf(nums[i]).charAt(0) - '0';
                String s = String.valueOf(nums[j]);
                int num2 = s.charAt(s.length() - 1) - '0';

                int gcd = gcd(num1, num2);
                if (gcd == 1) {
                    pairCount++;
                }
            }
        }
        return pairCount;
    }

    /**
     * 最大公约数 辗转相除
     * @param a
     * @param b
     * @return
     */
    private int gcd(int a, int b) {
        if (a % b == 0) {
            return b;
        }
        return gcd(b, a%b);
    }
}
