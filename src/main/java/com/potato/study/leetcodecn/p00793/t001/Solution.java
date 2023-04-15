package com.potato.study.leetcodecn.p00793.t001;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 793. 阶乘函数后 K 个零
 *
 * f(x) 是 x! 末尾是 0 的数量。回想一下 x! = 1 * 2 * 3 * ... * x，且 0! = 1 。

 例如， f(3) = 0 ，因为 3! = 6 的末尾没有 0 ；而 f(11) = 2 ，因为 11!= 39916800 末端有 2 个 0 。
 给定 k，找出返回能满足 f(x) = k 的非负整数 x 的数量。

  

 示例 1：

 输入：k = 0
 输出：5
 解释：0!, 1!, 2!, 3!, 和 4! 均符合 k = 0 的条件。
 示例 2：

 输入：k = 5
 输出：0
 解释：没有匹配到这样的 x!，符合 k = 5 的条件。
 示例 3:

 输入: k = 3
 输出: 5
  

 提示:

 0 <= k <= 109

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/preimage-size-of-factorial-zeroes-function
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int preimageSizeFZF(int k) {
        // 枚举左右边的数字 计算中间点数字的阶乘对应的 5的个数 是不是k个
        long left = 0;
        long right = 5L * k;
        long res = -1;
        while (left <= right) {
            long mid = (left + right) / 2;

            long zeroCount = getZeroCount(mid);
            if (zeroCount == k) {
                res = mid;
                break;
            } else if (zeroCount > k) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (res == -1) {
            return 0;
        }
        return 5;
    }

    /**
     * 计算target 阶乘中有多少个 0
     * @param target
     * @return
     */
    private long getZeroCount(long target) {
        long base = 5;
        long count = 0;
        while (target > 0) {
            long thisCount = target / base;
            count += thisCount;

            target /= base;
        }
        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.preimageSizeFZF(3);
        System.out.println(i);
        Assert.assertEquals(5, i);


        i = solution.preimageSizeFZF(5);
        System.out.println(i);
        Assert.assertEquals(0, i);


        i = solution.preimageSizeFZF(1000000000);
        System.out.println(i);
        Assert.assertEquals(5, i);
    }
}
