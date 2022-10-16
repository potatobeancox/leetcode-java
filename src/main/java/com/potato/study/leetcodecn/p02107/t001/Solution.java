package com.potato.study.leetcodecn.p02107.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

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
        // 先前k个给妹妹 后n-k个给自己
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = k; i < candies.length; i++) {
            int count = countMap.getOrDefault(candies[i], 0);
            count++;
            countMap.put(candies[i], count);
        }
        int max = countMap.size();
        // 滑动
        for (int i = k; i < candies.length; i++) {
            // 删除 i
            int removeCandy = candies[i];
            Integer removeCount = countMap.get(removeCandy);
            if (removeCount == 1) {
                countMap.remove(removeCandy);
            } else {
                countMap.put(removeCandy, removeCount - 1);
            }

            // 添加 第i-k
            Integer count = countMap.getOrDefault(candies[i-k], 0);
            count++;
            countMap.put(candies[i-k], count);

            // max compute
            max = Math.max(max, countMap.size());
        }
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] candies = LeetcodeInputUtils.inputString2IntArray("[2,1,5,5,4]");
        int k = 1;
        int i = solution.shareCandies(candies, k);
        System.out.println(i);
        Assert.assertEquals(4, i);
    }
}
