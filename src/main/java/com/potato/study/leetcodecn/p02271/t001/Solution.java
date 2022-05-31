package com.potato.study.leetcodecn.p02271.t001;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 2271. 毯子覆盖的最多白色砖块数
 *
 * 给你一个二维整数数组 tiles ，其中 tiles[i] = [li, ri] ，表示所有在 li <= j <= ri 之间的每个瓷砖位置 j 都被涂成了白色。

 同时给你一个整数 carpetLen ，表示可以放在 任何位置 的一块毯子。

 请你返回使用这块毯子，最多 可以盖住多少块瓷砖。

  

 示例 1：



 输入：tiles = [[1,5],[10,11],[12,18],[20,25],[30,32]], carpetLen = 10
 输出：9
 解释：将毯子从瓷砖 10 开始放置。
 总共覆盖 9 块瓷砖，所以返回 9 。
 注意可能有其他方案也可以覆盖 9 块瓷砖。
 可以看出，瓷砖无法覆盖超过 9 块瓷砖。
 示例 2：



 输入：tiles = [[10,11],[1,1]], carpetLen = 2
 输出：2
 解释：将毯子从瓷砖 10 开始放置。
 总共覆盖 2 块瓷砖，所以我们返回 2 。
  

 提示：

 1 <= tiles.length <= 5 * 104
 tiles[i].length == 2
 1 <= li <= ri <= 109
 1 <= carpetLen <= 109
 tiles 互相 不会重叠 。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/maximum-white-tiles-covered-by-a-carpet
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int maximumWhiteTiles(int[][] tiles, int carpetLen) {
        Arrays.sort(tiles, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });
        // 遍历 每个位置 往后找 carpetlen
        int maxLength = 0;
        for (int i = 0; i < tiles.length; i++) {
            // 最终找到的位置 exclude
            int totalEnd = tiles[i][0] + carpetLen;
            // 计算当前区间 覆盖度
            int currentLength = 0;
            boolean isFindAll = false;
            // 往下找
            for (int j =i; j < tiles.length; j++) {
                int start = tiles[j][0];
                if (totalEnd <= start) {
                    break;
                }
                if (totalEnd > tiles[j][1]) {
                    currentLength += (tiles[j][1] - tiles[j][0] + 1);
                    if (j == tiles.length - 1) {
                        isFindAll = true;
                    }
                } else {
                    // 这个区间覆盖不全
                    currentLength += (totalEnd - tiles[j][0]);
                    maxLength = Math.max(maxLength, currentLength);
                    break;
                }
            }
            maxLength = Math.max(maxLength, currentLength);
            if (isFindAll) {
                break;
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] tiles = new int[][]{{10,11},{1,1}};
        int carpetLen = 2;
        int i = solution.maximumWhiteTiles(tiles, carpetLen);
        System.out.println(i);
        Assert.assertEquals(2, i);
    }
}
