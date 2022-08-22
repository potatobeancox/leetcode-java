package com.potato.study.leetcodecn.p00356.t001;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 356. 直线镜像
 *
 * 在一个二维平面空间中，给你 n 个点的坐标。问，是否能找出一条平行于 y 轴的直线，让这些点关于这条直线成镜像排布？
 *
 * 注意：题目数据中可能有重复的点。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：points = [[1,1],[-1,1]]
 * 输出：true
 * 解释：可以找出 x = 0 这条线。
 * 示例 2：
 *
 *
 * 输入：points = [[1,1],[-1,-1]]
 * 输出：false
 * 解释：无法找出这样一条线。
 *  
 *
 * 提示：
 *
 * n == points.length
 * 1 <= n <= 10^4
 * -10^8 <= points[i][j] <= 10^8
 *  
 *
 * 进阶：你能找到比 O(n2) 更优的解法吗?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/line-reflection
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public boolean isReflected(int[][] points) {
        // 遍历 按照 y 进行统计 list map
        Map<Integer, Set<Integer>> y2xListMap = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            int x = points[i][0];
            int y = points[i][1];

            Set<Integer> list = y2xListMap.getOrDefault(y, new HashSet<>());
            list.add(x);

            y2xListMap.put(y, list);
        }
        // 遍历 map 如果 len 为奇数 返回false 偶数的话 left + right / 2 看看是不是内层也这样
        boolean needFillFirst = true;
        double target = Integer.MAX_VALUE / 2.0;

        for (Set<Integer> set : y2xListMap.values()) {
            List<Integer> list = new ArrayList<>(set);
            Collections.sort(list);
            int left = 0;
            int right = set.size() - 1;
            while (left <= right) {
                double current = (list.get(left) + list.get(right)) / 2.0;
                if (needFillFirst) {
                    target = current;
                    needFillFirst = false;
                } else {
                    if (current != target) {
                        return false;
                    }
                }
                left++;
                right--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        // [[1,1],[-1,1]]
        int[][] points = new int[][] {
                {1,1},
                {-1,1}
        };
        boolean reflected = solution.isReflected(points);
        System.out.println(reflected);
        Assert.assertEquals(true, reflected);

        points = new int[][] {
                {0,0}
        };
        reflected = solution.isReflected(points);
        System.out.println(reflected);
        Assert.assertEquals(true, reflected);

        points = new int[][] {
                {1,1},
                {-1,-1}
        };
        reflected = solution.isReflected(points);
        System.out.println(reflected);
        Assert.assertEquals(false, reflected);


        points = new int[][] {
                {0,0}
        };
        reflected = solution.isReflected(points);
        System.out.println(reflected);
        Assert.assertEquals(true, reflected);

        String input = "[[-16,1],[16,1],[16,1]]";
        points = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        reflected = solution.isReflected(points);
        System.out.println(reflected);
        Assert.assertEquals(true, reflected);


        input = "[[0,0],[1,0],[3,0]]";
        points = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        reflected = solution.isReflected(points);
        System.out.println(reflected);
        Assert.assertEquals(false, reflected);

    }
}
