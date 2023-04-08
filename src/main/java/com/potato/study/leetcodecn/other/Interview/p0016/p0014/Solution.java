package com.potato.study.leetcodecn.other.Interview.p0016.p0014;


import java.util.HashMap;
import java.util.Map;

/**
 * 面试题 16.14. 最佳直线
 *
 * 给定一个二维平面及平面上的 N 个点列表Points，其中第i个点的坐标为Points[i]=[Xi,Yi]。请找出一条直线，其通过的点的数目最多。

 设穿过最多点的直线所穿过的全部点编号从小到大排序的列表为S，你仅需返回[S[0],S[1]]作为答案，若有多条直线穿过了相同数量的点，则选择S[0]值较小的直线返回，S[0]相同则选择S[1]值较小的直线返回。

 示例：

 输入： [[0,0],[1,1],[1,0],[2,0]]
 输出： [0,2]
 解释： 所求直线穿过的3个点的编号为[0,2,3]
 提示：

 2 <= len(Points) <= 300
 len(Points[i]) = 2

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/best-line-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int[] bestLine(int[][] points) {
        // 枚举开始的位置
        int n = points.length;
        int maxLineCount = 2;
        int[] res = new int[] {0, 1};
        for (int i = 0; i < n; i++) {
            int x1 = points[i][0];
            int y1 = points[i][1];

            for (int j = i+1; j < n; j++) {
                int x2 = points[j][0];
                int y2 = points[j][1];

                int currentCount = 2;

                for (int k = j+1; k < n; k++) {
                    int x3 = points[k][0];
                    int y3 = points[k][1];

                    // 三点共线判断
                    if ((y1 - y3) * (x1 - x2) == (y1 - y2) * (x1 - x3)) {
                        currentCount++;
                    }
                }

                if (currentCount > maxLineCount) {
                    maxLineCount = currentCount;

                    res[0] = i;
                    res[1] = j;
                }
            }
        }
        return res;
    }
}
