package com.potato.study.leetcodecn.p02582.t001;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 2582. 递枕头

 n 个人站成一排，按从 1 到 n 编号。

 最初，排在队首的第一个人拿着一个枕头。每秒钟，拿着枕头的人会将枕头传递给队伍中的下一个人。一旦枕头到达队首或队尾，传递方向就会改变，队伍会继续沿相反方向传递枕头。

 例如，当枕头到达第 n 个人时，TA 会将枕头传递给第 n - 1 个人，然后传递给第 n - 2 个人，依此类推。
 给你两个正整数 n 和 time ，返回 time 秒后拿着枕头的人的编号。

  

 示例 1：

 输入：n = 4, time = 5
 输出：2
 解释：队伍中枕头的传递情况为：1 -> 2 -> 3 -> 4 -> 3 -> 2 。
 5 秒后，枕头传递到第 2 个人手中。
 示例 2：

 输入：n = 3, time = 2
 输出：3
 解释：队伍中枕头的传递情况为：1 -> 2 -> 3 。
 2 秒后，枕头传递到第 3 个人手中。
  

 提示：

 2 <= n <= 1000
 1 <= time <= 1000

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/pass-the-pillow
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 2582
    public int passThePillow(int n, int time) {
        // 计算当前 time /n 对应应该到了第几个循环
        int t = time / (n-1);
        // 如果是偶数就是 正向 如果是奇数就是逆向
        int remind = time % (n-1);
        if (t % 2 == 1) {
            return n - remind;
        } else {
            return remind + 1;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.passThePillow(3, 2);
        System.out.println(i);
        Assert.assertEquals(3, i);


        i = solution.passThePillow(4, 5);
        System.out.println(i);
        Assert.assertEquals(2, i);
    }




}
