package com.potato.study.leetcodecn.p02015.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

import java.util.*;

/**
 * 2015. 每段建筑物的平均高度
 *
 * 一条完全笔直的街道由一条数字线表示。街道上有建筑物，由二维整数阵列 buildings 表示，其中 buildings[i] = [starti, endi, heighti]。这意味着在 半封闭的位置[starti，endi] 有一座高度为 heighti 的建筑。
 * 你想用 最少 数量的非重叠 部分 来 描述 街道上建筑物的高度。街道可以用2D整数数组 street 来表示，其中 street[j] = [leftj, rightj, averagej] 描述了道路的 半封闭区域 [leftj, rightj) ，该段中建筑物的 平均 高度为 averagej 。
 *
 * 例如，如果 buildings = [[1,5,2],[3,10,4]] ， street = [[1,3,2],[3,5,3],[5,10,4]] 可以表示街道，因为：
 * 从 1 到 3 ，只有第一栋建筑的平均高度为 2 / 1 = 2 。
 * 从 3 到 5 ，第一和第二栋建筑的平均高度均为 （2+4） / 2 = 3 。
 * 从 5 到 10 ，只有第二栋建筑的平均高度为 4 / 1 = 4 。
 * 给定 buildings ，返回如上所述的二维整数矩阵 street （ 不包括 街道上没有建筑物的任何区域）。您可以按 任何顺序 返回数组。
 * n 个元素的 平均值 是 n 个元素除以 n 的 总和 （整数除法）。
 * 半闭合段 [a, b) 是点 a 和 b 之间的数字线的截面，包括 点 a ，不包括 点 b 。
 *
 *  
 *
 * 示例1：
 *
 *
 * 输入: buildings = [[1,4,2],[3,9,4]]
 * 输出: [[1,3,2],[3,4,3],[4,9,4]]
 * 解释:
 * 从 1 到 3 ，只有第一栋建筑的平均高度为 2 / 1 = 2。
 * 从 3 到 4 ，第一和第二栋建筑的平均高度均为（2+4）/ 2 = 3。
 * 从 4 到 9 ，只有第二栋建筑的平均高度为 4 / 1 = 4。
 * 示例 2:
 *
 * 输入: buildings = [[1,3,2],[2,5,3],[2,8,3]]
 * 输出: [[1,3,2],[3,8,3]]
 * 解释:
 * 从 1 到 2 ，只有第一栋建筑的平均高度为 2 / 1 = 2。
 * 从 2 到 3 ，这三座建筑的平均高度均为 （2+3+3） / 3 = 2。
 * 从 3 到 5 ，第二和第三栋楼都在那里，平均高度为 （3+3） / 2 = 3。
 * 从 5 到 8 ，只有最后一栋建筑的平均高度为 3 / 1 = 3。
 * 从 1 到 3 的平均高度是相同的，所以我们可以把它们分成一个部分。
 * 从 3 到 8 的平均高度是相同的，所以我们可以把它们分成一个部分。
 * 示例 3:
 *
 * 输入: buildings = [[1,2,1],[5,6,1]]
 * 输出: [[1,2,1],[5,6,1]]
 * 解释:
 * 从 1 到 2 ，只有第一栋建筑的平均高度为 1 / 1 = 1。
 * 从 2 到 5 ，没有建筑物，因此不包括在输出中。
 * 从 5 到 6 ，只有第二栋建筑的平均高度为 1 / 1 = 1。
 * 我们无法将这些部分组合在一起，因为没有建筑的空白空间将这些部分隔开。
 *  
 *
 * 提示:
 *
 * 1 <= buildings.length <= 105
 * buildings[i].length == 3
 * 0 <= starti < endi <= 108
 * 1 <= heighti <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/average-height-of-buildings-in-each-segment
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 查分数据的方式 记录每个区间的 sum 和 个数
     * 测试用例说明 还需要对相邻的连续区间进行压缩
     * @param buildings
     * @return
     */
    public int[][] averageHeightOfBuildings(int[][] buildings) {
        // map 计算 位置 index 个数 sum 总高度 treemap
        Map<Integer, int[]> indexInfoMap = new TreeMap<>();
        // 遍历 buildings 更新 map info[0] = 总高度变化量 info[1] 数量变化量
        for (int[] info : buildings) {
            //  buildings[i] = [starti, endi, heighti]
            int start = info[0];
            int end = info[1];
            int height = info[2];

            int[] startInfo = indexInfoMap.getOrDefault(start, new int[]{0, 0});
            startInfo[0] += height;
            startInfo[1] += 1;
            indexInfoMap.put(start, startInfo);

            int[] endInfo = indexInfoMap.getOrDefault(end, new int[]{0, 0});
            endInfo[0] -= height;
            endInfo[1] -= 1;
            indexInfoMap.put(end, endInfo);
        }
        int lastEndIndex = -1;
        List<int[]> resultList = new ArrayList<>();
        // 遍历 map 生成结果
        int totalHeight = 0;
        int totalCount = 0;
        for (Map.Entry<Integer, int[]> entry : indexInfoMap.entrySet()) {
            // 当前是开始位置
            int index = entry.getKey();
            if (lastEndIndex != -1 && totalCount > 0) {
                // 不是第一个位置 生成区间
                int[] intervalInfo = new int[3];
                intervalInfo[0] = lastEndIndex;
                intervalInfo[1] = index;
                // 平均高度
                intervalInfo[2] = totalHeight / totalCount;

                resultList.add(intervalInfo);
            }
            lastEndIndex = index;
            int[] info = entry.getValue();
            totalHeight += info[0];
            totalCount += info[1];
        }
        // 对了list 进行压缩
        List<int[]> newResultList = new ArrayList<>();
        for (int[] oriResult : resultList) {
            // 如果 当前 oriResult 和 newResultList 最后一个区间可以连接的话 更改最后一个区间
            int[] ints = null;
            if (!newResultList.isEmpty()) {
                ints = newResultList.get(newResultList.size() - 1);
            }
            if (ints != null && ints[2] == oriResult[2] && ints[1] == oriResult[0]) {
                ints[1] = oriResult[1];
            } else {
                // 否则直接插入 newResultList
                newResultList.add(oriResult);
            }
        }
        // 转换成数组
        int[][] result = new int[newResultList.size()][];
        int index = 0;
        for (int[] res : newResultList) {
            result[index++] = res;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[1,4,2],[3,9,4]]";
        int[][] buildings = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        int[][] ints = solution.averageHeightOfBuildings(buildings);
        // [[1,3,2],[3,4,3],[4,9,4]]
        System.out.println(Arrays.deepToString(ints));


        input = "[[1,3,2],[2,5,3],[2,8,3]]";
        buildings = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        ints = solution.averageHeightOfBuildings(buildings);
        // [[1,2,2],[2,3,2],[3,5,3],[5,8,3]]
        // [[1,3,2],[3,8,3]]
        System.out.println(Arrays.deepToString(ints));


        input = "[[1,2,1],[5,6,1]]";
        buildings = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        ints = solution.averageHeightOfBuildings(buildings);
        // [[1,2,2],[2,3,2],[3,5,3],[5,8,3]]
        // [[1,3,2],[3,8,3]]
        System.out.println(Arrays.deepToString(ints));

    }

}

