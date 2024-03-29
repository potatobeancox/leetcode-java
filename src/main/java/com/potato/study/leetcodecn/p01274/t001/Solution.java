package com.potato.study.leetcodecn.p01274.t001;


import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

/**
 * 1274. 矩形内船只的数目
 *
 * (此题是 交互式问题 )

 在用笛卡尔坐标系表示的二维海平面上，有一些船。每一艘船都在一个整数点上，且每一个整数点最多只有 1 艘船。

 有一个函数 Sea.hasShips(topRight, bottomLeft) ，输入参数为右上角和左下角两个点的坐标，当且仅当这两个点所表示的矩形区域（包含边界）内至少有一艘船时，这个函数才返回 true ，否则返回 false 。

 给你矩形的右上角 topRight 和左下角 bottomLeft 的坐标，请你返回此矩形内船只的数目。题目保证矩形内 至多只有 10 艘船。

 调用函数 hasShips 超过400次 的提交将被判为 错误答案（Wrong Answer） 。同时，任何尝试绕过评测系统的行为都将被取消比赛资格。

  

 示例 1：



 输入：
 ships = [[1,1],[2,2],[3,3],[5,5]], topRight = [4,4], bottomLeft = [0,0]
 输出：3
 解释：在 [0,0] 到 [4,4] 的范围内总共有 3 艘船。
 示例 2:

 输入：ans = [[1,1],[2,2],[3,3]], topRight = [1000,1000], bottomLeft = [0,0]
 输出：3
  

 提示：

 ships 数组只用于评测系统内部初始化。你必须“蒙着眼睛”解决这个问题。你无法得知 ships 的信息，所以只能通过调用 hasShips 接口来求解。
 0 <= bottomLeft[0] <= topRight[0] <= 1000
 0 <= bottomLeft[1] <= topRight[1] <= 1000
 topRight != bottomLeft

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/number-of-ships-in-a-rectangle
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int countShips(Sea sea, int[] topRight, int[] bottomLeft) {
        // 终止条件
        if (topRight[0] == bottomLeft[0] && topRight[1] == bottomLeft[1]) {
            boolean b = sea.hasShips(topRight, bottomLeft);
            if (b) {
                return 1;
            } else {
                return 0;
            }
        }
        boolean b = sea.hasShips(topRight, bottomLeft);
        if (!b) {
            return 0;
        }
        int[] bottomLeft1;
        int[] topRight1;

        int[] bottomLeft2;
        int[] topRight2;

        if (bottomLeft[0] == topRight[0]) {
            bottomLeft1 = new int[] {bottomLeft[0], bottomLeft[1]};
            topRight1 = new int[] {topRight[0], (bottomLeft[1] + topRight[1]) / 2};

            bottomLeft2 = new int[] {bottomLeft[0], (bottomLeft[1] + topRight[1]) / 2 + 1};
            topRight2 = new int[] {topRight[0], topRight[1]};
        } else {
            // bottomLeft[1] == topRight[1]
            bottomLeft1 = new int[] {bottomLeft[0], bottomLeft[1]};
            topRight1 = new int[] {(bottomLeft[0] + topRight[0]) / 2, topRight[1]};

            bottomLeft2 = new int[] {(bottomLeft[0] + topRight[0]) / 2 + 1, bottomLeft[1]};
            topRight2 = new int[] {topRight[0], topRight[1]};
        }
        return countShips(sea, topRight1, bottomLeft1) + countShips(sea, topRight2, bottomLeft2);
    }
}

class Sea {
      public boolean hasShips(int[] topRight, int[] bottomLeft) {
          return false;
      }
}
