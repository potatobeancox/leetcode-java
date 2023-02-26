package com.potato.study.leetcodecn.p02345.t001;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 2345. 寻找可见山的数量
 *
 * 给定一个 下标从 0 开始 的二维整数数组 peaks，其中 peaks[i] = [xi, yi] 表示山 i 在坐标 (xi, yi) 处有一个峰值。山可以被描述为一个直角等腰三角形，它的底部沿着 x 轴，山峰处有一个直角。更正式地说，上山和下山的 梯度 分别为 1 和 -1。

 一座山如果它的顶峰不在另一座山 (包括其他山的边界) 之内，那么它被认为是 可见 的。

 返回可见山的数量。

  

 示例 1:


 输入: peaks = [[2,2],[6,3],[5,4]]
 输出: 2
 解释: 上面的图表显示了山脉。
 - 山 0 是可见的，因为它的峰值不在另一座山的里面或另一座山的边界。
 - 山 1 是不可见的，因为它的顶峰在山 2 的边界。
 - 山 2 是可见的，因为它的峰值不在另一座山的里面或另一座山的边界。
 有 2 座山是可见的。
 示例 2:


 输入: peaks = [[1,3],[1,3]]
 输出: 0
 解释: 上面的图表显示了这些山 (它们完全重叠)。
 两座山都看不见，因为它们的山峰在彼此里面。
  

 提示:

 1 <= peaks.length <= 105
 peaks[i].length == 2
 1 <= xi, yi <= 105


 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/finding-the-number-of-visible-mountains
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int visibleMountains(int[][] peaks) {
        // 遍历 peaks
        List<int[]> mountainList = new ArrayList<>();
        for (int[] peak : peaks) {
            int width = peak[0];
            int height = peak[1];

            int start = width - height;
            int end = width + height;
            // 根据 横坐标 加减 纵坐标得到 山的范围 将范围收集起来
            mountainList.add(new int[] {start, end});
        }
        // 对 上面收集的 list 进行排序 按照 横坐标 递增 区间递减排序
        Collections.sort(mountainList, (mountain1, mountain2) -> {
            // 先按照开始的位置
            int compare = Integer.compare(mountain1[0], mountain2[0]);
            if (compare != 0) {
                return compare;
            }
            // 按照距离降序排序
            return Integer.compare(mountain2[1] - mountain2[0], mountain1[1] - mountain1[0]);
        });
        // 遍历 每个 左边 判断 当前右边 是否已经在之前的范围里了 如果已经
        int lastRight = Integer.MIN_VALUE;
        int visibleCount = 0;
        for (int i = 0; i < mountainList.size(); i++) {
            int[] mountain = mountainList.get(i);
            // 左边已经是大于等于了 直接比较右边
            int thisMountainRight = mountain[1];
            // 如果当前 已经在 右边范围里了肯定看不见
            if (thisMountainRight <= lastRight) {
                // 已经被当上了
                continue;
            }
            // 更改下右边
            lastRight = thisMountainRight;
            // 否则能看到 看一下 是不是跟下一个是一样的区间 是的话 也还是不能用
            if (i != mountainList.size() - 1
                    && mountainList.get(i+1)[0] == mountain[0]
                    && mountainList.get(i+1)[1] == mountain[1]) {
                // 不是最后一个 切 后一个跟前一个一样 continue 掉
                continue;
            }
            // 没有重复的 可以奇数
            visibleCount++;
        }
        return visibleCount;
    }

}
