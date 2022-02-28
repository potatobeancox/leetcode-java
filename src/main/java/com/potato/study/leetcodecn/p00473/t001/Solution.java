package com.potato.study.leetcodecn.p00473.t001;

import org.junit.Assert;

import java.lang.ref.SoftReference;
import java.util.Arrays;

/**
 * 473. 火柴拼正方形
 *
 * 还记得童话《卖火柴的小女孩》吗？现在，你知道小女孩有多少根火柴，请找出一种能使用所有火柴拼成一个正方形的方法。不能折断火柴，可以把火柴连接起来，并且每根火柴都要用到。

 输入为小女孩拥有火柴的数目，每根火柴用其长度表示。输出即为是否能用所有的火柴拼成正方形。

 示例 1:

 输入: [1,1,2,2,2]
 输出: true

 解释: 能拼成一个边长为2的正方形，每边两根火柴。
 示例 2:

 输入: [3,3,3,3,4]
 输出: false

 解释: 不能用所有火柴拼成一个正方形。
 注意:

 给定的火柴长度和在 0 到 10^9之间。
 火柴数组的长度不超过15。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/matchsticks-to-square
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public boolean makesquare(int[] matchsticks) {
        // 计算 sum
        int sum = 0;
        for (int stick : matchsticks) {
            sum += stick;
        }
        if (sum % 4 != 0) {
            return false;
        }
        // 遍历 matchsticks dfs 按照
        int target = sum / 4;
        boolean[] visit = new boolean[matchsticks.length];
        Arrays.sort(matchsticks);
        return makesquare(matchsticks, target, visit, 0, 0, 0);
    }



    /**
     *
     * @param matchsticks
     * @param target        每条边的长度
     * @param visit         true 为i位置 火柴已经被使用
     * @param count         当前边个数
     * @param current       当前凑到了边的长度
     * @param visitCount    当前已经用了 多少个火柴
     * @return
     */
    private boolean makesquare(int[] matchsticks, int target, boolean[] visit, int count, int current, int visitCount) {
        // 终止条件
        if (current > target) {
            // 当前边长度过长
            return false;
        }
        // 用的太多
        if (visitCount > matchsticks.length) {
            return false;
        }
        // 超多4个边
        if (count > 4) {
            return false;
        }
        // 判断是否全部 visit
        if (visitCount == matchsticks.length) {
            return count == 4;
        }
        for (int i = 0; i < matchsticks.length; i++) {
            // 访问过了 continue
            if (visit[i]) {
                continue;
            }
            // 当前 有一个边超级长
            if (matchsticks[i] > target) {
                return false;
            }
            int nextCurrent = current + matchsticks[i];
            if (nextCurrent > target) {
                continue;
            }
            // 用这个火柴
            visit[i] = true;
            boolean flag;
            if (nextCurrent == target) {
                flag = makesquare(matchsticks, target, visit, count + 1, 0, visitCount + 1);
            } else {
                flag = makesquare(matchsticks, target, visit, count, nextCurrent, visitCount + 1);
            }
            if (flag) {
                return true;
            }
            visit[i] = false;
        }
        return false;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                1,1,2,2,2
        };
        boolean makesquare = solution.makesquare(arr);
        System.out.println(makesquare);
        Assert.assertEquals(true, makesquare);



        arr = new int[] {
                3,3,3,3,4
        };
        makesquare = solution.makesquare(arr);
        System.out.println(makesquare);
        Assert.assertEquals(false, makesquare);



        arr = new int[] {
                5,5,5,5,16,4,4,4,4,4,3,3,3,3,4
        };
        makesquare = solution.makesquare(arr);
        System.out.println(makesquare);
        Assert.assertEquals(true, makesquare);
    }

}
