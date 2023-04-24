package com.potato.study.leetcodecn.p02081.t001;

import org.junit.Assert;

/**
 * 2081. k 镜像数字的和
 *
 * 一个 k 镜像数字 指的是一个在十进制和 k 进制下从前往后读和从后往前读都一样的 没有前导 0 的 正 整数。

 比方说，9 是一个 2 镜像数字。9 在十进制下为 9 ，二进制下为 1001 ，两者从前往后读和从后往前读都一样。
 相反地，4 不是一个 2 镜像数字。4 在二进制下为 100 ，从前往后和从后往前读不相同。
 给你进制 k 和一个数字 n ，请你返回 k 镜像数字中 最小 的 n 个数 之和 。

  

 示例 1：

 输入：k = 2, n = 5
 输出：25
 解释：
 最小的 5 个 2 镜像数字和它们的二进制表示如下：
 十进制       二进制
 1          1
 3          11
 5          101
 7          111
 9          1001
 它们的和为 1 + 3 + 5 + 7 + 9 = 25 。
 示例 2：

 输入：k = 3, n = 7
 输出：499
 解释：
 7 个最小的 3 镜像数字和它们的三进制表示如下：
 十进制       三进制
 1          1
 2          2
 4          11
 8          22
 121        11111
 151        12121
 212        21212
 它们的和为 1 + 2 + 4 + 8 + 121 + 151 + 212 = 499 。
 示例 3：

 输入：k = 7, n = 17
 输出：20379000
 解释：17 个最小的 7 镜像数字分别为：
 1, 2, 3, 4, 5, 6, 8, 121, 171, 242, 292, 16561, 65656, 2137312, 4602064, 6597956, 6958596
  

 提示：

 2 <= k <= 9
 1 <= n <= 30

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/sum-of-k-mirror-numbers
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public long kMirror(int k, int n) {
        // 从1开始查找 10进制中的最小对称数
        int count = 0;
        long target = 1;
        long sum = 0;
        while (count < n) {
            // 当前target 是不是 对称的
            String s = Long.toString(target, k);
            // 找到之后 进行k进制转换 每次都判断 这个是不是 对称的 是的
            if (isMirror(s)) {
                sum += target;
                count++;
            }
            target = getNextHexMirror(target);
        }
        return sum;
    }

    private long getNextHexMirror(long target) {
        String s = String.valueOf(target);
        String substring = s.substring(0, (s.length() + 1) / 2);
        long l = Long.parseLong(substring);
        l++;
        // 判断是否进位
        boolean isProcess = substring.length() != String.valueOf(l).length();
        StringBuilder builder = new StringBuilder();
        if (isProcess) {
            int len = s.length() + 1;
            for (int i = 0; i < len; i++) {
                if (i == 0 || i == len - 1) {
                    builder.append("1");
                } else {
                    builder.append("0");
                }
            }
        } else {
            // 没有进位
            builder.append(l);
            // 后几位
            String ss = String.valueOf(l);
            for (int i = ss.length() - 1; i >= 0; i--) {
                if (s.length() % 2 == 1 && i == ss.length() - 1) {
                    // 如果是 奇数个让出来一个
                    continue;
                }
                builder.append(ss.charAt(i));
            }

        }
        return Long.parseLong(builder.toString());
    }

    /**
     * s 是不是对称的
     * @param s
     * @return
     */
    private boolean isMirror(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        long l = solution.kMirror(2, 5);
        System.out.println(l);
        Assert.assertEquals(25 , l);


        l = solution.kMirror(3, 7);
        System.out.println(l);
        Assert.assertEquals(499 , l);
    }
}
