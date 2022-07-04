package com.potato.study.leetcodecn.p02326.t001;

import com.potato.study.leetcode.domain.ListNode;
import com.potato.study.leetcode.util.LeetcodeInputUtils;
import com.potato.study.leetcode.util.ListNodeUtil;

import java.util.Arrays;

/**
 * 2326. 螺旋矩阵 IV
 *
 * 给你两个整数：m 和 n ，表示矩阵的维数。
 *
 * 另给你一个整数链表的头节点 head 。
 *
 * 请你生成一个大小为 m x n 的螺旋矩阵，矩阵包含链表中的所有整数。链表中的整数从矩阵 左上角 开始、顺时针 按 螺旋 顺序填充。如果还存在剩余的空格，则用 -1 填充。
 *
 * 返回生成的矩阵。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：m = 3, n = 5, head = [3,0,2,6,8,1,7,9,4,2,5,5,0]
 * 输出：[[3,0,2,6,8],[5,0,-1,-1,1],[5,2,4,9,7]]
 * 解释：上图展示了链表中的整数在矩阵中是如何排布的。
 * 注意，矩阵中剩下的空格用 -1 填充。
 * 示例 2：
 *
 *
 * 输入：m = 1, n = 4, head = [0,1,2]
 * 输出：[[0,1,2,-1]]
 * 解释：上图展示了链表中的整数在矩阵中是如何从左到右排布的。
 * 注意，矩阵中剩下的空格用 -1 填充。
 *  
 *
 * 提示：
 *
 * 1 <= m, n <= 105
 * 1 <= m * n <= 105
 * 链表中节点数目在范围 [1, m * n] 内
 * 0 <= Node.val <= 1000
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/spiral-matrix-iv
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] result = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(result[i], -1);
        }
        int[][] direction = new int[][] {
                {0, 1}, // right
                {1, 0},
                {0, -1},
                {-1, 0}
        };
        int dirIndex = 0;
        int i = 0;
        int j = 0;
        ListNode p = head;
        while (p != null && result[i][j] == -1) {
            // 右边
            while (j < n && result[i][j] == -1 && p != null) {
                result[i][j] = p.val;
                p = p.next;
                j++;
            }
            if (p == null) {
                break;
            }
            // 下
            j--;
            while (i < m && result[i][j] == -1 && p != null) {
                result[i][j] = p.val;
                p = p.next;
                i++;
            }
            if (p == null) {
                break;
            }
            // 左
            i--;
            while (j >= 0 && result[i][j] == -1 && p != null) {
                result[i][j] = p.val;
                p = p.next;
                j--;
            }
            if (p == null) {
                break;
            }
            // 上
            j++;
            while (i >= 0 && result[i][j] == -1 && p != null) {
                result[i][j] = p.val;
                p = p.next;
                i--;
            }
            if (p == null) {
                break;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int m = 10;
        int n = 1;
        String input = "[8,24,5,21,10,11,11,12,6,17]";
        ListNode head = ListNodeUtil.arrayStringToListNode(input);
        int[][] ints = solution.spiralMatrix(m, n, head);
        System.out.println(Arrays.deepToString(ints));
    }

}
