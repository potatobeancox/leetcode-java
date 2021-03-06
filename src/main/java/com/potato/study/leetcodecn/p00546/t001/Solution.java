package com.potato.study.leetcodecn.p00546.t001;


import java.util.HashMap;
import java.util.Map;

/**
 * 546. 移除盒子
 *
 * 给出一些不同颜色的盒子，盒子的颜色由数字表示，即不同的数字表示不同的颜色。

 你将经过若干轮操作去去掉盒子，直到所有的盒子都去掉为止。每一轮你可以移除具有相同颜色的连续 k 个盒子（k >= 1），这样一轮之后你将得到 k * k 个积分。

 当你将所有盒子都去掉之后，求你能获得的最大积分和。

  

 示例 1：

 输入：boxes = [1,3,2,2,2,3,4,3,1]
 输出：23
 解释：
 [1, 3, 2, 2, 2, 3, 4, 3, 1]
 ----> [1, 3, 3, 4, 3, 1] (3*3=9 分)
 ----> [1, 3, 3, 3, 1] (1*1=1 分)
 ----> [1, 1] (3*3=9 分)
 ----> [] (2*2=4 分)
 示例 2：

 输入：boxes = [1,1,1]
 输出：9
 示例 3：

 输入：boxes = [1]
 输出：1
  

 提示：

 1 <= boxes.length <= 100
 1 <= boxes[i] <= 100

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/remove-boxes
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int removeBoxes(int[] boxes) {

        return -1;
    }
}
