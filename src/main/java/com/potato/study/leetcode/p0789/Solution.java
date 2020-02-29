package com.potato.study.leetcode.p0789;

import org.junit.Assert;

/**
 * 
 * @author liuzhao11
 * 
 * 	789. Escape The Ghosts
 *  
 *        You are playing a simplified Pacman game. You start at the point (0, 0), and your destination is (target[0], target[1]). There are several ghosts on the map, the i-th ghost starts at (ghosts[i][0], ghosts[i][1]).

Each turn, you and all ghosts simultaneously *may* move in one of 4 cardinal directions: north, east, west, or south, going from the previous point to a new point 1 unit of distance away.

You escape if and only if you can reach the target before any ghost reaches you (for any given moves the ghosts may take.)  If you reach any square (including the target) at the same time as a ghost, it doesn't count as an escape.

Return True if and only if it is possible to escape.

Example 1:
Input:
ghosts = [[1, 0], [0, 3]]
target = [0, 1]
Output: true
Explanation:
You can directly reach the destination (0, 1) at time 1, while the ghosts located at (1, 0) or (0, 3) have no way to catch up with you.
Example 2:
Input:
ghosts = [[1, 0]]
target = [2, 0]
Output: false
Explanation:
You need to reach the destination (2, 0), but the ghost at (1, 0) lies between you and the destination.
Example 3:
Input:
ghosts = [[2, 0]]
target = [1, 0]
Output: false
Explanation:
The ghost can reach the target at the same time as you.
Note:

All points have coordinates with absolute value <= 10000.
The number of ghosts will not exceed 100.


 *
 *
 *   解题思路：
 *
 *   https://blog.csdn.net/weixin_30387339/article/details/96620230
 *
 *
 *
 * 
 */
public class Solution {

    public boolean escapeGhosts(int[][] ghosts, int[] target) {

        // 小人距离
        int distanceOfPeople = Math.abs(target[0]) + Math.abs(target[1]);
        // 如果鬼距离 比小人距离短 返回false 否则 true

        for (int[] ghost : ghosts) {
            int dis = Math.abs(target[0] - ghost[0]) + Math.abs(target[1] - ghost[1]);
            if (dis <= distanceOfPeople) {
                return false;
            }
        }

        return true;
    }



	
	public static void main(String[] args) {
		Solution solution = new Solution();

        int[][] ghosts = {{1, 0}, {0, 3}};
        int[] target = {0, 1};

        boolean res = solution.escapeGhosts(ghosts, target);
        System.out.println(res);
        Assert.assertEquals(true, res);


        ghosts = new int[][]{{1, 0}};
        target = new int[]{2, 0};
        res = solution.escapeGhosts(ghosts, target);
        System.out.println(res);
        Assert.assertEquals(false, res);

        ghosts = new int[][]{{2, 0}};
        target = new int[]{1, 0};
        res = solution.escapeGhosts(ghosts, target);
        System.out.println(res);
        Assert.assertEquals(false, res);
    }
}
