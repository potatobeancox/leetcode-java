package com.potato.study.leetcodecn.p01386.t001;


import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.*;

/**
 * 1386. 安排电影院座位
 *
 * 如上图所示，电影院的观影厅中有 n 行座位，行编号从 1 到 n ，且每一行内总共有 10 个座位，列编号从 1 到 10 。

 给你数组 reservedSeats ，包含所有已经被预约了的座位。比如说，researvedSeats[i]=[3,8] ，它表示第 3 行第 8 个座位被预约了。

 请你返回 最多能安排多少个 4 人家庭 。4 人家庭要占据 同一行内连续 的 4 个座位。隔着过道的座位（比方说 [3,3] 和 [3,4]）不是连续的座位，但是如果你可以将 4 人家庭拆成过道两边各坐 2 人，这样子是允许的。

  

 示例 1：



 输入：n = 3, reservedSeats = [[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]]
 输出：4
 解释：上图所示是最优的安排方案，总共可以安排 4 个家庭。蓝色的叉表示被预约的座位，橙色的连续座位表示一个 4 人家庭。
 示例 2：

 输入：n = 2, reservedSeats = [[2,1],[1,8],[2,6]]
 输出：2
 示例 3：

 输入：n = 4, reservedSeats = [[4,3],[1,4],[4,6],[1,7]]
 输出：4
  

 提示：

 1 <= n <= 10^9
 1 <= reservedSeats.length <= min(10*n, 10^4)
 reservedSeats[i].length == 2
 1 <= reservedSeats[i][0] <= n
 1 <= reservedSeats[i][1] <= 10
 所有 reservedSeats[i] 都是互不相同的。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/cinema-seat-allocation
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/cinema-seat-allocation/solution/java-hashmap-by-yuruiyin/
     * @param n
     * @param reservedSeats
     * @return
     */
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // 使用一个 map key 排的index value 是 list 占座的index 首先排序
        Map<Integer, List<Integer>> reservedSeatMap = new HashMap<>();
        // 遍历每个位置 计算中间空了多少个座位
        for (int i = 0; i < reservedSeats.length; i++) {
            int[] reservedSeat = reservedSeats[i];
            int line = reservedSeat[0];
            int seat = reservedSeat[1];

            List<Integer> seatList = reservedSeatMap.getOrDefault(line, new ArrayList<>());
            seatList.add(seat);

            reservedSeatMap.put(line, seatList);
        }
        // 基础运算 那些空的位置
        int blankLine = n - reservedSeatMap.size();
        int blankCount = blankLine * 2;
        // 遍历 求中间空的位置
        for (List<Integer> seatList : reservedSeatMap.values()) {
            Collections.sort(seatList);
            for (int j = 0; j < seatList.size(); j++) {
                int len;
                int lastFullSeat;
                if (j == 0) {
                    len = seatList.get(0) - 1;
                    lastFullSeat = seatList.get(0);
                } else {
                    len = seatList.get(j) - seatList.get(j-1) - 1;
                    lastFullSeat = seatList.get(j);
                }
                // 次数统计 中间 如果有过道 最多 一遍坐2个
                if (len >= 4) {
                    if (len != 4 && len != 8) {
                        blankCount += (len/4);
                        continue;
                    }
                    // 4 or 8 检查
                    if (len == 4 && (lastFullSeat == 8 || lastFullSeat == 6 || lastFullSeat == 10)) {
                        blankCount++;
                        continue;
                    }
                    if (len == 8) {
                        if (lastFullSeat == 10) {
                            blankCount += 2;
                        } else {
                            blankCount++;
                        }
                    }
                }
            }
            // 最后一个位置
            int lastLen = 10 - seatList.get(seatList.size() - 1) - 1;
            // 次数统计
            if (lastLen >= 4) {
                blankCount += (lastLen/4);
            }
        }
        return blankCount;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 3;
        int[][] reservedSeats = new int[][] {
                {1,2},
                {1,3},
                {1,8},
                {2,6},
                {3,1},
                {3,10}
        };
        int i = solution.maxNumberOfFamilies(n, reservedSeats);
        System.out.println(i);
        Assert.assertEquals(i, 4);



        n = 5;
        String input = "[[4,7],[4,1],[3,1],[5,9],[4,4],[3,7],[1,3],[5,5],[1,6],[1,8],[3,9],[2,9],[1,4],[1,9],[1,10]]";
        reservedSeats = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        i = solution.maxNumberOfFamilies(n, reservedSeats);
        System.out.println(i);
        Assert.assertEquals(i, 2);


//        2
//

        n = 2;
        input = "[[2,1],[1,8],[2,6]]";
        reservedSeats = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        i = solution.maxNumberOfFamilies(n, reservedSeats);
        System.out.println(i);
        Assert.assertEquals(i, 2);


    }

}
