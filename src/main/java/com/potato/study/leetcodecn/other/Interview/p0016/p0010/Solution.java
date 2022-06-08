package com.potato.study.leetcodecn.other.Interview.p0016.p0010;


import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * 面试题 16.10. 生存人数
 *
 * 给定 N 个人的出生年份和死亡年份，第 i 个人的出生年份为 birth[i]，死亡年份为 death[i]，实现一个方法以计算生存人数最多的年份。

 你可以假设所有人都出生于 1900 年至 2000 年（含 1900 和 2000 ）之间。如果一个人在某一年的任意时期处于生存状态，那么他应该被纳入那一年的统计中。例如，生于 1908 年、死于 1909 年的人应当被列入 1908 年和 1909 年的计数。

 如果有多个年份生存人数相同且均为最大值，输出其中最小的年份。

  

 示例：

 输入：
 birth = {1900, 1901, 1950}
 death = {1948, 1951, 2000}
 输出： 1901
  

 提示：

 0 < birth.length == death.length <= 10000
 birth[i] <= death[i]

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/living-people-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int maxAliveYear(int[] birth, int[] death) {
        // 记录两个端点 记录过程汇总人口变化的最大值
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < birth.length; i++) {
            int birthday = birth[i];
            int deadDay = death[i] + 1;

            treeMap.put(birthday, treeMap.getOrDefault(birthday, 0) + 1);
            treeMap.put(deadDay, treeMap.getOrDefault(deadDay, 0) - 1);
        }

        // 遍历map
        int max = 0;
        int maxYear = 0;
        int current = 0;
        for (Map.Entry<Integer, Integer> entry: treeMap.entrySet()) {
            current += entry.getValue();
            if (current > max) {
                max = current;
                maxYear = entry.getKey();
            }
        }
        return maxYear;
    }
}
