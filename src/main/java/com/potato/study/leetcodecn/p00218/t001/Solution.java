package com.potato.study.leetcodecn.p00218.t001;

import java.util.*;

/**
 * 218. 天际线问题
 *
 * 城市的天际线是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。给你所有建筑物的位置和高度，请返回由这些建筑物形成的 天际线 。

 每个建筑物的几何信息由数组 buildings 表示，其中三元组 buildings[i] = [lefti, righti, heighti] 表示：

 lefti 是第 i 座建筑物左边缘的 x 坐标。
 righti 是第 i 座建筑物右边缘的 x 坐标。
 heighti 是第 i 座建筑物的高度。
 天际线 应该表示为由 “关键点” 组成的列表，格式 [[x1,y1],[x2,y2],...] ，并按 x 坐标 进行 排序 。关键点是水平线段的左端点。列表中最后一个点是最右侧建筑物的终点，y 坐标始终为 0 ，仅用于标记天际线的终点。此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。

 注意：输出天际线中不得有连续的相同高度的水平线。例如 [...[2 3], [4 5], [7 5], [11 5], [12 7]...] 是不正确的答案；三条高度为 5 的线应该在最终输出中合并为一个：[...[2 3], [4 5], [12 7], ...]

  

 示例 1：


 输入：buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
 输出：[[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
 解释：
 图 A 显示输入的所有建筑物的位置和高度，
 图 B 显示由这些建筑物形成的天际线。图 B 中的红点表示输出列表中的关键点。
 示例 2：

 输入：buildings = [[0,2,3],[2,5,3]]
 输出：[[0,3],[5,0]]
  

 提示：

 1 <= buildings.length <= 104
 0 <= lefti < righti <= 231 - 1
 1 <= heighti <= 231 - 1
 buildings 按 lefti 非递减排序

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/the-skyline-problem
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode-cn.com/problems/the-skyline-problem/solution/gong-shui-san-xie-sao-miao-xian-suan-fa-0z6xc/
     * @param buildings
     * @return
     */
    public List<List<Integer>> getSkyline(int[][] buildings) {
        // 处理一下 buildings 用用一个 int[] 存x坐标和 高度 如果 左边 高度负数，否则整数
        List<int[]> list = new ArrayList<>();
        for (int[] build : buildings) {
            int left = build[0];
            int right = build[1];
            int h = build[2];

            list.add(new int[] {left, -1 * h});
            list.add(new int[] {right, h});
        }
        // 按照 坐标 递增排序，坐标相同 按照高度 升序排序
        Collections.sort(list, (o1, o2) -> {
            int compare = Integer.compare(o1[0], o2[0]);
            if (compare == 0) {
                return Integer.compare(o1[1], o2[1]);
            }
            return compare;
        });
        // 使用一个 优先级队列 大根堆记录当前存在的线的高度 使用 prev 记录之前的高度 初始插入0
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        int previous = 0;
        priorityQueue.add(previous);
        // 遍历数组，每次 将高度插入或者移除，并取出 当前高度 如果根prev 比发生了变化，那就生成这个点 x 高度 设置 prev
        List<List<Integer>> resultList = new ArrayList<>();
        for (int[] build : list) {
            int x = build[0];
            int h = build[1];
            // 左边点插入，右边点删除
            if (h < 0) {
                priorityQueue.add(-1 * h);
            } else {
                priorityQueue.remove(h);
            }
            // 判断是够改变 改变才能有边界
            Integer peek = priorityQueue.peek();
            if (peek != previous) {

                List<Integer> result = new ArrayList<>();
                result.add(x);
                result.add(peek);

                resultList.add(result);
                previous = peek;
            }
        }
        return resultList;
    }
}
