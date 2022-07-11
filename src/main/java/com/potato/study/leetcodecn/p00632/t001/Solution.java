package com.potato.study.leetcodecn.p00632.t001;


import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 632. 最小区间
 *
 * 你有 k 个 非递减排列 的整数列表。找到一个 最小 区间，使得 k 个列表中的每个列表至少有一个数包含在其中。
 *
 * 我们定义如果 b-a < d-c 或者在 b-a == d-c 时 a < c，则区间 [a,b] 比 [c,d] 小。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 * 输出：[20,24]
 * 解释：
 * 列表 1：[4, 10, 15, 24, 26]，24 在区间 [20,24] 中。
 * 列表 2：[0, 9, 12, 20]，20 在区间 [20,24] 中。
 * 列表 3：[5, 18, 22, 30]，22 在区间 [20,24] 中。
 * 示例 2：
 *
 * 输入：nums = [[1,2,3],[1,2,3],[1,2,3]]
 * 输出：[1,1]
 *  
 *
 * 提示：
 *
 * nums.length == k
 * 1 <= k <= 3500
 * 1 <= nums[i].length <= 50
 * -105 <= nums[i][j] <= 105
 * nums[i] 按非递减顺序排列
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/smallest-range-covering-elements-from-k-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode.cn/problems/smallest-range-covering-elements-from-k-lists/solution/632-zui-xiao-qu-jian-java-by-wa-pian-d-dlr8/
     * @param nums
     * @return
     */
    public int[] smallestRange(List<List<Integer>> nums) {
        // 用一个 小根堆 记录 每个 nums list 每个list中的最小值位置，每次去最最小的pop 并将之后的位置差进去，维护一个maxValue
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(nums.get(o1[0]).get(o1[1]), nums.get(o2[0]).get(o2[1]));
            }
        });
        // nums[i] 按非递减顺序排列 前提 放入第一个节点
        int maxValue = nums.get(0).get(0);
        for (int i = 0; i < nums.size(); i++) {
            int targetValue = nums.get(i).get(0);
            maxValue = Math.max(maxValue, targetValue);
            priorityQueue.add(new int[] {i, 0});
        }
        // 每次得到一个窗口 与之前的对比大小
        int[] smallestWindow = new int[] {
                nums.get(priorityQueue.peek()[0]).get(priorityQueue.peek()[1]),
                maxValue
        };

        int di = priorityQueue.peek()[0];
        int dj = priorityQueue.peek()[1] + 1;

        while (dj < nums.get(di).size()) {
            // 删除最小的点 放入之后的点
            int[] poll = priorityQueue.poll();
            if (poll[1] + 1 < nums.get(poll[0]).size()) {
                maxValue = Math.max(maxValue, nums.get(poll[0]).get(poll[1] + 1));
                int[] next = new int[] {
                        poll[0],
                        poll[1] + 1
                };
                priorityQueue.add(next);

                // 计算最小的点
                smallestWindow = getSmallestWindow(smallestWindow, new int[] {
                        nums.get(priorityQueue.peek()[0]).get(priorityQueue.peek()[1]),
                        maxValue
                });

            }

            di = poll[0];
            dj = poll[1] + 1;
        }
        return smallestWindow;
    }

    private int[] getSmallestWindow(int[] smallestWindow, int[] window) {
        int smallDiff = smallestWindow[1] - smallestWindow[0];
        int diff = window[1] - window[0];

        if (smallDiff > diff) {
            return window;
        } else if (smallDiff < diff) {
            return smallestWindow;
        }
        // 长度相同 按照 开始为位置
        if (smallestWindow[0] <= window[0]) {
            return smallestWindow;
        } else {
            return window;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]";
        List<List<Integer>> list = LeetcodeInputUtils.inputString2IntListTwoDimensional(input);
        int[] ints = solution.smallestRange(list);
        System.out.println(Arrays.toString(ints));
        Assert.assertArrayEquals(new int[] {
                20,24
        }, ints);
    }

}
