package com.potato.study.leetcodecn.p02195.t001;

import org.junit.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 2195. 向数组中追加 K 个整数
 *
 * 给你一个整数数组 nums 和一个整数 k 。请你向 nums 中追加 k 个 未 出现在 nums 中的、互不相同 的 正 整数，并使结果数组的元素和 最小 。

 返回追加到 nums 中的 k 个整数之和。

  

 示例 1：

 输入：nums = [1,4,25,10,25], k = 2
 输出：5
 解释：在该解法中，向数组中追加的两个互不相同且未出现的正整数是 2 和 3 。
 nums 最终元素和为 1 + 4 + 25 + 10 + 25 + 2 + 3 = 70 ，这是所有情况中的最小值。
 所以追加到数组中的两个整数之和是 2 + 3 = 5 ，所以返回 5 。
 示例 2：

 输入：nums = [5,6], k = 6
 输出：25
 解释：在该解法中，向数组中追加的两个互不相同且未出现的正整数是 1 、2 、3 、4 、7 和 8 。
 nums 最终元素和为 5 + 6 + 1 + 2 + 3 + 4 + 7 + 8 = 36 ，这是所有情况中的最小值。
 所以追加到数组中的两个整数之和是 1 + 2 + 3 + 4 + 7 + 8 = 25 ，所以返回 25 。
  

 提示：

 1 <= nums.length <= 105
 1 <= nums[i], k <= 109

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/append-k-integers-with-minimal-sum
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public long minimalKSum(int[] nums, int k) {
        // 1 排序
        Arrays.sort(nums);
        // 2 遍历 每个位置 找 k 可能的数字 最后返回和
        int start = 1;
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (k == 0) {
                break;
            }
            if (nums[i] > start) {
                // num i不算
                int n = nums[i] - start;
                if (n > k) {
                    n = k;
                }
//                for (int j = 0; j < n; j++) {
//                    System.out.println(start + j);
//                }
                sum += ((long)start + start + n - 1) * n / 2;
                k -= n;
            }
            start = nums[i] + 1;
        }

        if (k != 0) {
            sum += ((long)start + start + k - 1) * k / 2;
        }

        return sum;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
         5,6
        };
        int k = 6;
        long l = solution.minimalKSum(nums, k);
        System.out.println(l);
        Assert.assertEquals(25, l);


        nums = new int[] {
                17,18,19,19,21,22,25,29,32,33,35,40,44,47,
                96,99,61,84,88,60,86,52,50,94,98,72,100,84
        };
        k = 35;
        l = solution.minimalKSum(nums, k);
        System.out.println(l);
        Assert.assertEquals(794, l);
    }

}
