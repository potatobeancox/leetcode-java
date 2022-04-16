package com.potato.study.leetcodecn.other.lcp.p0039.t001;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * LCP 39. 无人机方阵
 *
 * 在 「力扣挑战赛」 开幕式的压轴节目 「无人机方阵」中，每一架无人机展示一种灯光颜色。 无人机方阵通过两种操作进行颜色图案变换：

 调整无人机的位置布局
 切换无人机展示的灯光颜色
 给定两个大小均为 N*M 的二维数组 source 和 target 表示无人机方阵表演的两种颜色图案，由于无人机切换灯光颜色的耗能很大，请返回从 source 到 target 最少需要多少架无人机切换灯光颜色。

 注意： 调整无人机的位置布局时无人机的位置可以随意变动。

 示例 1：

 输入：source = [[1,3],[5,4]], target = [[3,1],[6,5]]

 输出：1

 解释：
 最佳方案为
 将 [0,1] 处的无人机移动至 [0,0] 处；
 将 [0,0] 处的无人机移动至 [0,1] 处；
 将 [1,0] 处的无人机移动至 [1,1] 处；
 将 [1,1] 处的无人机移动至 [1,0] 处，其灯光颜色切换为颜色编号为 6 的灯光；
 因此从source 到 target 所需要的最少灯光切换次数为 1。


 示例 2：

 输入：source = [[1,2,3],[3,4,5]], target = [[1,3,5],[2,3,4]]

 输出：0
 解释：
 仅需调整无人机的位置布局，便可完成图案切换。因此不需要无人机切换颜色

 提示：
 n == source.length == target.length
 m == source[i].length == target[i].length
 1 <= n, m <=100
 1 <= source[i][j], target[i][j] <=10^4

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/0jQkd0
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int minimumSwitchingTimes(int[][] source, int[][] target) {
        // 统计 source 中数量
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < source.length; i++) {
            for (int j = 0; j < source[0].length; j++) {
                countMap.put(source[i][j], countMap.getOrDefault(source[i][j], 0) + 1);
            }
        }
        // 然后使用 target减去
        int minTime = 0;
        for (int i = 0; i < target.length; i++) {
            for (int j = 0; j < target[0].length; j++) {
                int key = target[i][j];
                Integer count = countMap.getOrDefault(key, 0);
                if (count < 1) {
                    minTime++;
                }
                countMap.put(key, count - 1);
            }
        }
        return minTime;
    }


}
