package com.potato.study.leetcodecn.other.Interview.p0016.p0019;


import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;


/**
 * 面试题 16.19. 水域大小
 *
 * 你有一个用于表示一片土地的整数矩阵land，该矩阵中每个点的值代表对应地点的海拔高度。若值为0
 * 则表示水域。由垂直、水平或对角连接的水域为池塘。池塘的大小是指相连接的水域的个数。编写一个方法来计算矩阵中所有池塘的大小，返回值需要从小到大排序。
 *
 * 示例：
 *
 * 输入：
 * [
 *   [0,2,1,0],
 *   [0,1,0,1],
 *   [1,1,0,1],
 *   [0,1,0,1]
 * ]
 * 输出： [1,2,4]
 * 提示：
 *
 * 0 < len(land) <= 1000
 * 0 < len(land[i]) <= 1000
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/pond-sizes-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     *
     * @param land
     * @return
     */
    public int[] pondSizes(int[][] land) {
        // 遍历 land 从 0 的位置 开始 往其他方向找 找到了 将 0 置换成 -1 bfs
        PriorityQueue<Integer> area = new PriorityQueue<>();
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[0].length; j++) {
                if (land[i][j] != 0) {
                    continue;
                }
                int areaEach = bfs(land, i, j);
                area.add(areaEach);
            }
        }
        int[] array = new int[area.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = area.poll();
        }
        return array;
    }

    /**
     * 从 ij位置开始查找
     * @param land
     * @param i
     * @param j
     * @return
     */
    private int bfs(int[][] land, int i, int j) {
        int count = 0;
        Queue<int[]> pos = new LinkedList<>();
        pos.add(new int[] {i, j});
        int[][] dir = new int[][] {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1},
                {1, 1},
                {1, -1},
                {-1, 1},
                {-1, -1}
        };
        land[i][j] = -1;
        while (!pos.isEmpty()) {
            int[] poll = pos.poll();
            count++;
            for (int k = 0; k < 8; k++) {
                int di = poll[0] + dir[k][0];
                int dj = poll[1] + dir[k][1];
                // 坐标合法性
                if (di < 0 || dj < 0
                        || di >= land.length
                        || dj >= land[0].length) {
                    continue;
                }
                if (land[di][dj] != 0) {
                    continue;
                }
                // 加入队列 更改 land
                land[di][dj] = -1;
                pos.add(new int[]{
                        di, dj
                });
            }
        }
        return count;
    }
}
