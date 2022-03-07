package com.potato.study.leetcodecn.p00473.t001;

import org.junit.Assert;

import java.lang.ref.SoftReference;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

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


    /**
     * https://leetcode-cn.com/problems/matchsticks-to-square/solution/huo-chai-pin-zheng-fang-xing-by-leetcode/
     * @param matchsticks
     * @return
     */
    public boolean makesquare(int[] matchsticks) {
        // 计算一下每个边 需要达到的大小 matchsticks 排序 倒序
        Arrays.sort(matchsticks);
        int left = 0;
        int right = matchsticks.length - 1;
        while (left < right) {
            int tmp = matchsticks[left];
            matchsticks[left] = matchsticks[right];
            matchsticks[right] = tmp;

            left++;
            right--;
        }
        int sum = 0;
        for (int num : matchsticks) {
            sum += num;
        }
        if (sum % 4 != 0) {
            return false;
        }
        if (matchsticks.length < 4) {
            return false;
        }
        // 使用 一个数组 存储当前每个边 凑齐的长度
        int[] edge = new int[4];
        int index = 0;
        int eachEdgeLength = sum / 4;
        return dfs(matchsticks, edge, index, eachEdgeLength);
    }

    private boolean dfs(int[] matchsticks, int[] edge, int index, int eachEdgeLength) {
        // 终止条件
        if (index == matchsticks.length) {
            return edge[0] == edge[1]
                    && edge[1] == edge[2]
                    && edge[2] == edge[3];
        }
        // 找到任意一个放
        for (int i = 0; i < 4; i++) {
            if (edge[i] + matchsticks[index] > eachEdgeLength) {
                continue;
            }
            edge[i] += matchsticks[index];
            boolean flag = dfs(matchsticks, edge, index + 1, eachEdgeLength);
            if (flag) {
                return true;
            }
            edge[i] -= matchsticks[index];
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



//        arr = new int[] {
//                5,5,5,5,16,4,4,4,4,4,3,3,3,3,4
//        };
//        makesquare = solution.makesquare(arr);
//        System.out.println(makesquare);
//        Assert.assertEquals(true, makesquare);
    }

}
