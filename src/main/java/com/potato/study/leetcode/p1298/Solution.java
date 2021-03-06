package com.potato.study.leetcode.p1298;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 
 * @author liuzhao11
 * 
 * 	1298. Maximum Candies You Can Get from Boxes
 *  
 *
Given n boxes, each box is given in the format [status, candies, keys, containedBoxes] where:

status[i]: an integer which is 1 if box[i] is open and 0 if box[i] is closed.
candies[i]: an integer representing the number of candies in box[i].
keys[i]: an array contains the indices of the boxes you can open with the key in box[i].
containedBoxes[i]: an array contains the indices of the boxes found in box[i].
You will start with some boxes given in initialBoxes array. You can take all the candies in any open box and you can use the keys in it to open new boxes and you also can use the boxes you find in it.

Return the maximum number of candies you can get following the rules above.



Example 1:

Input: status = [1,0,1,0], candies = [7,5,4,100], keys = [[],[],[1],[]], containedBoxes = [[1,2],[3],[],[]], initialBoxes = [0]
Output: 16
Explanation: You will be initially given box 0. You will find 7 candies in it and boxes 1 and 2. Box 1 is closed and you don't have a key for it so you will open box 2. You will find 4 candies and a key to box 1 in box 2.
In box 1, you will find 5 candies and box 3 but you will not find a key to box 3 so box 3 will remain closed.
Total number of candies collected = 7 + 4 + 5 = 16 candy.
Example 2:

Input: status = [1,0,0,0,0,0], candies = [1,1,1,1,1,1], keys = [[1,2,3,4,5],[],[],[],[],[]], containedBoxes = [[1,2,3,4,5],[],[],[],[],[]], initialBoxes = [0]
Output: 6
Explanation: You have initially box 0. Opening it you can find boxes 1,2,3,4 and 5 and their keys. The total number of candies will be 6.
Example 3:

Input: status = [1,1,1], candies = [100,1,100], keys = [[],[0,2],[]], containedBoxes = [[],[],[]], initialBoxes = [1]
Output: 1
Example 4:

Input: status = [1], candies = [100], keys = [[]], containedBoxes = [[]], initialBoxes = []
Output: 0
Example 5:

Input: status = [1,1,1], candies = [2,3,2], keys = [[],[],[]], containedBoxes = [[],[],[]], initialBoxes = [2,1,0]
Output: 7


Constraints:

1 <= status.length <= 1000
status.length == candies.length == keys.length == containedBoxes.length == n
status[i] is 0 or 1.
1 <= candies[i] <= 1000
0 <= keys[i].length <= status.length
0 <= keys[i][j] < status.length
All values in keys[i] are unique.
0 <= containedBoxes[i].length <= status.length
0 <= containedBoxes[i][j] < status.length
All values in containedBoxes[i] are unique.
Each box is contained in one box at most.
0 <= initialBoxes.length <= status.length
0 <= initialBoxes[i] < status.length
 *         
 *         ?????????
 *
 *
 *          ?????????????????? ??????????????????????????? ?????? ???????????????????????????
 *
 *          https://leetcode-cn.com/problems/maximum-candies-you-can-get-from-boxes/solution/qing-xi-jian-dan-bfs-mo-ni-by-hlxing/
 *
 *
 *

 *
 */
public class Solution {

    /**
     *
     * @param status    1 if box[i] is open and 0 if box[i] is closed.
     * @param candies   box[i] ?????????????????????
     * @param keys      keys[i]   box[i] ?????????????????? ??????
     * @param containedBoxes      box[i] ????????????????????????
     * @param initialBoxes        ??????????????? ??????
     * @return
     */
    public int maxCandies(int[] status, int[] candies, int[][] keys,
                          int[][] containedBoxes, int[] initialBoxes) {
        // ????????? initialBoxes  ?????? visited ????????????????????????????????? ?????? queue?????? ???????????????????????? ??????????????????key
        Queue<Integer> canOpenBoxIndexQueue = new LinkedList<>();
        // ???????????? queue visited = true
        boolean[] visited = new boolean[status.length];
        Set<Integer> hasBoxIndexSet = new HashSet<>();
        Set<Integer> hasKeyIndexSet = new HashSet<>();
        // ????????????????????????????????????????????????????????? or ?????????????????????
        for (int initBoxIndex : initialBoxes) {
            hasBoxIndexSet.add(initBoxIndex);
            // ???????????????
            if (status[initBoxIndex] == 1) {
                canOpenBoxIndexQueue.add(initBoxIndex);
                visited[initBoxIndex] = true;
            }
        }
        int total = 0;
        // queue ?????? ??????????????? ???????????????????????? ???????????????????????????????????????????????? ????????? visited = true
        while (!canOpenBoxIndexQueue.isEmpty()) {
            Integer boxIndx = canOpenBoxIndexQueue.poll();
            // ??????
            total += candies[boxIndx];
            // key
            for (int hasKeyBoxIndex : keys[boxIndx]) {
                hasKeyIndexSet.add(hasKeyBoxIndex);
                if (!visited[hasKeyBoxIndex] && hasBoxIndexSet.contains(hasKeyBoxIndex)) {
                    canOpenBoxIndexQueue.add(hasKeyBoxIndex);
                    visited[hasKeyBoxIndex] = true;
                }
            }

            // box
            for (int box : containedBoxes[boxIndx]) {
                hasBoxIndexSet.add(box);
                if (!visited[box] && (hasKeyIndexSet.contains(box) || status[box] == 1)) {
                    canOpenBoxIndexQueue.add(box);
                    visited[box] = true;
                }
            }
        }
        // ??????????????????????????????
        return total;
    }
	
	public static void main(String[] args) {
		Solution solution = new Solution();


    }
}
