package com.potato.study.leetcodecn.p01564.t001;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 1564. 把箱子放进仓库里 I
 *
 * 给定两个正整数数组 boxes 和 warehouse ，分别包含单位宽度的箱子的高度，以及仓库中 n 个房间各自的高度。仓库的房间分别从 0 到 n - 1 自左向右编号， warehouse[i] （索引从 0 开始）是第 i 个房间的高度。

 箱子放进仓库时遵循下列规则：

 箱子不可叠放。
 你可以重新调整箱子的顺序。
 箱子只能从左向右推进仓库中。
 如果仓库中某房间的高度小于某箱子的高度，则这个箱子和之后的箱子都会停在这个房间的前面。
 你最多可以在仓库中放进多少个箱子？

  

 示例 1：



 输入：boxes = [4,3,4,1], warehouse = [5,3,3,4,1]
 输出：3
 解释：

 我们可以先把高度为 1 的箱子放入 4 号房间，然后再把高度为 3 的箱子放入 1 号、 2 号或 3 号房间，最后再把高度为 4 的箱子放入 0 号房间。
 我们不可能把所有 4 个箱子全部放进仓库里。
 示例 2：



 输入：boxes = [1,2,2,3,4], warehouse = [3,4,1,2]
 输出：3
 解释：

 我们注意到，不可能把高度为 4 的箱子放入仓库中，因为它不能通过高度为 3 的房间。
 而且，对于最后两个房间 2 号和 3 号来说，只有高度为 1 的箱子可以放进去。
 我们最多可以放进 3 个箱子，如上图所示。黄色的箱子也可以放入 2 号房间。
 交换橙色和绿色箱子的位置，或是将这两个箱子与红色箱子交换位置，也是可以的。
 示例 3：

 输入：boxes = [1,2,3], warehouse = [1,2,3,4]
 输出：1
 解释：由于第一个房间的高度为 1，我们只能放进高度为 1 的箱子。
  

 提示：

 n == warehouse.length
 1 <= boxes.length, warehouse.length <= 10^5
 1 <= boxes[i], warehouse[i] <= 10^9

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/put-boxes-into-the-warehouse-i
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int maxBoxesInWarehouse(int[] boxes, int[] warehouse) {
        // 从右往左遍历 warehouse 依次放入双端队列里边，如果当前值大于等于栈顶 直接放
        Deque<Integer> containDeque = new LinkedList<>();
        for (int i = warehouse.length - 1; i >= 0; i--) {
            if (containDeque.isEmpty() || containDeque.peekLast() <= warehouse[i]) {
                containDeque.addLast(warehouse[i]);
            } else {
                // 小于的话 依次出栈 直到空或者当期值大于等于栈顶 入栈计数个单位
                int count = 0;
                while (!containDeque.isEmpty() && containDeque.peekLast() > warehouse[i]) {
                    containDeque.pollLast();
                    count++;
                }
                if (count > 0) {
                    for (int j = 0; j <= count; j++) {
                        containDeque.addLast(warehouse[i]);
                    }
                }
            }
        }
        // 遍历 boxes 依次匹配
        Arrays.sort(boxes);
        // 对 boxes 升序排序 按照 顺序依次选择方块 containDeque box 小于等于的就使用 box大了 就往后移动 containDeque
        int boxIndex = 0;
        int containCount = 0;
        while (!containDeque.isEmpty() && boxIndex < boxes.length) {
            int containLimit = containDeque.pollFirst();
            if (containLimit >= boxes[boxIndex]) {
                boxIndex++;
                containCount++;
            }
        }
        return containCount;
    }
}
