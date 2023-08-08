package com.potato.study.leetcodecn.p00483.t001;

import org.junit.Assert;

/**
 * 483. 最小好进制
 *
 * 以字符串的形式给出 n , 以字符串的形式返回 n 的最小 好进制  。

 如果 n 的  k(k>=2) 进制数的所有数位全为1，则称 k(k>=2) 是 n 的一个 好进制 。

  

 示例 1：

 输入：n = "13"
 输出："3"
 解释：13 的 3 进制是 111。
 示例 2：

 输入：n = "4681"
 输出："8"
 解释：4681 的 8 进制是 11111。
 示例 3：

 输入：n = "1000000000000000000"
 输出："999999999999999999"
 解释：1000000000000000000 的 999999999999999999 进制是 11。
  

 提示：

 n 的取值范围是 [3, 1018]
 n 没有前导 0

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/smallest-good-base
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/smallest-good-base/solution/gong-shui-san-xie-xiang-jie-ru-he-fen-xi-r94g/
     * @param n
     * @return
     */
    public String smallestGoodBase(String n) {
        long num = Long.parseLong(n);
        // 能枚举最多有多少位置 log n2 最少有3个位111， 因为已经兜底 n-1
        int max = (int) (Math.log(num) / Math.log(2) + 1);
        // 从大往小枚举每一个 长度len ，二分法找到是不是存在一个 bit 是的 len位置是可以全都是1的
        for (long i = max; i >= 3; i--) {
            // 二分法查找
            long left = 2;
            long right = num-2;
            long target = right;
            while (left <= right) {
                long mid = (left + right) / 2;
                int compare = checkCompare(mid, i, num);
                if (compare >= 0) {
                    // 当前大了 说明 底数还应该小
                    target = mid;
                    right = mid - 1;
                } else {
                    // 底数小了 应该往大了搞
                    left = mid + 1;
                }
            }
            // 判断以 target 作为进制 有 i个位置的1 结果与 num的大小关系
            if (checkCompare(target, i, num) == 0) {
                return String.valueOf(target);
            }
        }
        // 如果 n 的  k(k>=2) 进制数的所有数位全为1，则称 k(k>=2) 是 n 的一个 好进制 。
        return String.valueOf(num - 1);
    }

    /**
     * 判断以 target 作为进制 有 bitLen个位置的1 结果与 num的大小关系
     * @param target
     * @param bitLen
     * @param num
     * @return 等于num 返回0 小于返回-1 大于返回1
     */
    private int checkCompare(long target, long bitLen, long num) {
        long result = 0;
        for (int i = 0; i < bitLen; i++) {
            result = result * target + 1;
        }
        return Long.compare(result, num);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = solution.smallestGoodBase("2251799813685247");
        System.out.println(s);
        Assert.assertEquals("2", s);
    }


}
