package com.potato.study.leetcodecn.p02107.t001;

/**
 * 2107. 分享 K 个糖果后独特口味的数量
 *
 * 您将获得一个 从0开始的 整数数组 candies ，其中 `candies[i]`表示第 i 个糖果的味道。你妈妈想让你和你妹妹分享这些糖果，给她 k 个 连续 的糖果，但你想保留尽可能多的糖果口味。
 在与妹妹分享后，返回 最多 可保留的 独特 口味的糖果。

  

 示例 1：

 输入: candies = [1,2,2,3,4,3], k = 3
 输出: 3
 解释:
 将[1,3]（含[2,2,3]）范围内的糖果加入[2,2,3]口味。
 你可以吃各种口味的糖果[1,4,3]。
 有3种独特的口味，所以返回3。
 示例 2:

 输入: candies = [2,2,2,2,3,3], k = 2
 输出: 2
 解释:
 在[3,4]范围内（含[2,3]）的糖果中加入[2,3]口味。
 你可以吃各种口味的糖果[2,2,2,3]。
 有两种独特的口味，所以返回2。
 请注意，你也可以分享口味为[2,2]的糖果，吃口味为[2,2,3,3]的糖果。
 示例 3:

 输入: candies = [2,4,5], k = 0
 输出: 3
 解释:
 你不必给任何糖果。
 你可以吃各种口味的糖果[2,4,5]。
 有3种独特的口味，所以返回3。
  

 提示:

 1 <= candies.length <= 105
 1 <= candies[i] <= 105
 0 <= k <= candies.length

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/number-of-unique-flavors-after-sharing-k-candies
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int shareCandies(int[] candies, int k) {

        return -1;
    }
}
