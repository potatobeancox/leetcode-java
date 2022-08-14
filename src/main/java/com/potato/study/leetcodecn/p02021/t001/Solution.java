package com.potato.study.leetcodecn.p02021.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.Map;
import java.util.TreeMap;

/**
 * 2021. 街上最亮的位置
 *
 * 一条街上有很多的路灯，路灯的坐标由数组 lights 的形式给出。 每个 lights[i] = [positioni, rangei] 代表坐标为 positioni 的路灯照亮的范围为 [positioni - rangei, positioni + rangei] （包括顶点）。

 位置 p 的亮度由能够照到 p的路灯的数量来决定的。

 给出 lights, 返回最亮的位置 。如果有很多，返回坐标最小的。

  

 示例 1:


 输入: lights = [[-3,2],[1,2],[3,3]]
 输出: -1
 解释:
 第一个路灯照亮的范围是[(-3) - 2, (-3) + 2] = [-5, -1].
 第二个路灯照亮的范围是 [1 - 2, 1 + 2] = [-1, 3].
 第三个路灯照亮的范围是 [3 - 3, 3 + 3] = [0, 6].

 坐标-1被第一个和第二个路灯照亮，亮度为2
 坐标0，1，2都被第二个和第三个路灯照亮，亮度为2.
 对于以上坐标，-1最小，所以返回-1
 示例 2：

 输入: lights = [[1,0],[0,1]]
 输出: 1
 示例 3：

 输入: lights = [[1,2]]
 输出: -1
  

 提示:

 1 <= lights.length <= 105
 lights[i].length == 2
 -108 <= positioni <= 108
 0 <= rangei <= 108

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/brightest-position-on-street
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int brightestPosition(int[][] lights) {
        TreeMap<Integer, Integer> positionStatusMap = new TreeMap<>();
        for (int[] light : lights) {
            int start = light[0] - light[1];
            int end = light[0] + light[1] + 1;

            int startStatus = positionStatusMap.getOrDefault(start, 0);
            startStatus++;
            positionStatusMap.put(start, startStatus);

            int endStatus = positionStatusMap.getOrDefault(end, 0);
            endStatus--;
            positionStatusMap.put(end, endStatus);
        }
        int maxStatus = Integer.MIN_VALUE;
        int maxIndex = -1;
        int currentStatus = 0;
        for (Map.Entry<Integer, Integer> entry : positionStatusMap.entrySet()) {
            int index = entry.getKey();
            int status = entry.getValue();
            currentStatus += status;

            if (currentStatus > maxStatus) {
                maxIndex = index;
                maxStatus = currentStatus;
            }
        }
        return maxIndex;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[-3,2],[1,2],[3,3]]";
        int[][] lights = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        int i = solution.brightestPosition(lights);
        System.out.println(i);
        Assert.assertEquals(-1, i);
    }

}

