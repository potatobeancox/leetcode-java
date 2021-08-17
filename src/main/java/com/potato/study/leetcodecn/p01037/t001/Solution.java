package com.potato.study.leetcodecn.p01037.t001;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 1037. 有效的回旋镖
 *
 * 回旋镖定义为一组三个点，这些点各不相同且不在一条直线上。

 给出平面上三个点组成的列表，判断这些点是否可以构成回旋镖。

  

 示例 1：

 输入：[[1,1],[2,3],[3,2]]
 输出：true
 示例 2：

 输入：[[1,1],[2,2],[3,3]]
 输出：false
  

 提示：

 points.length == 3
 points[i].length == 2
 0 <= points[i][j] <= 100

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/valid-boomerang
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public boolean isBoomerang(int[][] points) {
        // 判断是否相同
        int tmp1 = 1000 * points[0][0] + points[0][1];
        int tmp2 = 1000 * points[1][0] + points[1][1];
        int tmp3 = 1000 * points[2][0] + points[2][1];
        if (tmp1 == tmp2 || tmp1 == tmp3 || tmp2 == tmp3) {
            return false;
        }
        // 判断是否在一条直线剩
        return  (points[0][1] - points[1][1]) * (points[0][0] - points[2][0])
                != (points[0][1] - points[2][1]) * (points[0][0] - points[1][0]);
    }
}
