package com.potato.study.leetcode.p1033;


import java.util.Arrays;

/**
 * 
 * @author liuzhao11
 * 
 * 	1033. Moving Stones Until Consecutive
 *  
 *        Three stones are on a number line at positions a, b, and c.

Each turn, you pick up a stone at an endpoint (ie., either the lowest or highest position stone), and move it to an unoccupied position between those endpoints.  Formally, let's say the stones are currently at positions x, y, z with x < y < z.  You pick up the stone at either position x or position z, and move that stone to an integer position k, with x < k < z and k != y.

The game ends when you cannot make any more moves, ie. the stones are in consecutive positions.

When the game ends, what is the minimum and maximum number of moves that you could have made?  Return the answer as an length 2 array: answer = [minimum_moves, maximum_moves]



Example 1:

Input: a = 1, b = 2, c = 5
Output: [1,2]
Explanation: Move the stone from 5 to 3, or move the stone from 5 to 4 to 3.
Example 2:

Input: a = 4, b = 3, c = 2
Output: [0,0]
Explanation: We cannot make any moves.
Example 3:

Input: a = 3, b = 5, c = 1
Output: [1,2]
Explanation: Move the stone from 1 to 4; or move the stone from 1 to 2 to 4.


Note:

1 <= a <= 100
1 <= b <= 100
1 <= c <= 100
a != b, b != c, c != a
 *         
 *         思路：
 *
 *              https://leetcode-cn.com/problems/moving-stones-until-consecutive/solution/xian-pai-xu-ran-hou-fen-qing-kuang-pan-duan-by-nua/
 *
 *
 *
 */
public class Solution {

    public int[] numMovesStones(int a, int b, int c) {

        int[] res = new int[2];
        int[] array = {a, b, c};
        Arrays.sort(array);
        //两数相差大于1，就直接挪到中间数旁边，需要一次；两数挨着，就不动
        res[0] = (array[1] - array[0] == 1 ? 0 : 1) + (array[2] - array[1] == 1 ? 0 : 1);
        //两数相差等于2，就直接把另一个数挪中间
        res[0] = (array[1] - array[0] == 2 || array[2] - array[1] == 2) ? 1 : res[0];
        //最大值就是两边距离
        res[1] = array[2] - array[0] - 2;

        return res;
    }
}
