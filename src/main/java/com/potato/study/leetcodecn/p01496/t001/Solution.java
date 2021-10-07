package com.potato.study.leetcodecn.p01496.t001;

import org.junit.Assert;

import java.util.HashSet;
import java.util.Set;

/**
 * 1496. 判断路径是否相交
 *
 * 给你一个字符串 path，其中 path[i] 的值可以是 'N'、'S'、'E' 或者 'W'，分别表示向北、向南、向东、向西移动一个单位。

 机器人从二维平面上的原点 (0, 0) 处开始出发，按 path 所指示的路径行走。

 如果路径在任何位置上出现相交的情况，也就是走到之前已经走过的位置，请返回 True ；否则，返回 False 。

  

 示例 1：



 输入：path = "NES"
 输出：false
 解释：该路径没有在任何位置相交。
 示例 2：



 输入：path = "NESWW"
 输出：true
 解释：该路径经过原点两次。
  

 提示：

 1 <= path.length <= 10^4
 path 仅由 {'N', 'S', 'E', 'W} 中的字符组成

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/path-crossing
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param path
     * @return
     */
    public boolean isPathCrossing(String path) {
        // 用map 记录 某个点之前是否有被经过
        Set<String> visitedSet = new HashSet<>();
        visitedSet.add(getKey(0, 0));
        int x = 0;
        int y = 0;
        for (int i = 0; i < path.length(); i++) {
            char ch = path.charAt(i);
            if ('N' == ch) {
                x--;
            } else if ('S' == ch) {
                x++;
            } else if ('E' == ch) {
                y++;
            } else {
                // w
                y--;
            }

            String current = getKey(x, y);
            if (visitedSet.contains(current)) {
                return true;
            }
            visitedSet.add(current);
        }
        return false;
    }

    private String getKey(int x, int y) {
        return x + "_" + y;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String path = "NES";
        boolean pathCrossing = solution.isPathCrossing(path);
        System.out.println(pathCrossing);
        Assert.assertEquals(false, pathCrossing);

        path = "NESWW";
        pathCrossing = solution.isPathCrossing(path);
        System.out.println(pathCrossing);
        Assert.assertEquals(true, pathCrossing);
    }
}
