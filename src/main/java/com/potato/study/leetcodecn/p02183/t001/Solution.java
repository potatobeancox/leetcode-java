package com.potato.study.leetcodecn.p02183.t001;

import com.potato.study.leetcode.domain.ListNode;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

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
        // 存储 当前 最大公约数
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            if (num % k == 0) {
                count++;
            } else {
                int gcd = gcd(num, k);
                countMap.put(gcd, countMap.getOrDefault(gcd, 0 ) + 1);
            }
        }
        long totalCount = 0;
        // 都取自可以整除k的数组
        totalCount += ((long)count * (count - 1) / 2);
        totalCount += (long)count * (nums.length - count);
        // 各自的约数 可以被整除的
        long tempCount = 0;
        for (int key1 : countMap.keySet()) {
            for (int key2 : countMap.keySet()) {
                if (key1 * key2 % k != 0) {
                    continue;
                }
                // 能整除
                if (key1 == key2) {
                    tempCount += (long)countMap.get(key1) * (countMap.get(key1) - 1);
                } else {
                    tempCount += (long)countMap.get(key1) * countMap.get(key2);
                }
            }
        }
        return totalCount + tempCount / 2;
    }


    /**
     * 辗转相除 求最大公约数
     * @param a
     * @param b
     * @return
     */
    private int gcd(int a, int b) {
        // 被除数可以整除除数 返回除数
        if (a % b == 0) {
            return b;
        }
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{1,2,3,4,5};
        int k = 2;
        long l = solution.countPairs(nums, k);
        System.out.println(l);
        Assert.assertEquals(7, l);


        nums = new int[]{
                8,10,2,5,9,6,3,8,2
        };
        k = 6;
        l = solution.countPairs(nums, k);
        System.out.println(l);
        Assert.assertEquals(18, l);
    }
}
