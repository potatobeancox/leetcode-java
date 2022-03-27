package com.potato.study.leetcodecn.p02202.t001;

import org.junit.Assert;

import java.lang.ref.SoftReference;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 2202. K 次操作后最大化顶端元素
 *
 * 给你一个下标从 0 开始的整数数组 nums ，它表示一个 栈 ，其中 nums[0] 是栈顶的元素。

 每一次操作中，你可以执行以下操作 之一 ：

 如果栈非空，那么 删除 栈顶端的元素。
 如果存在 1 个或者多个被删除的元素，你可以从它们中选择任何一个，添加 回栈顶，这个元素成为新的栈顶元素。
 同时给你一个整数 k ，它表示你总共需要执行操作的次数。

 请你返回 恰好 执行 k 次操作以后，栈顶元素的 最大值 。如果执行完 k 次操作以后，栈一定为空，请你返回 -1 。

  

 示例 1：

 输入：nums = [5,2,2,4,0,6], k = 4
 输出：5
 解释：
 4 次操作后，栈顶元素为 5 的方法之一为：
 - 第 1 次操作：删除栈顶元素 5 ，栈变为 [2,2,4,0,6] 。
 - 第 2 次操作：删除栈顶元素 2 ，栈变为 [2,4,0,6] 。
 - 第 3 次操作：删除栈顶元素 2 ，栈变为 [4,0,6] 。
 - 第 4 次操作：将 5 添加回栈顶，栈变为 [5,4,0,6] 。
 注意，这不是最后栈顶元素为 5 的唯一方式。但可以证明，4 次操作以后 5 是能得到的最大栈顶元素。
 示例 2：

 输入：nums = [2], k = 1
 输出：-1
 解释：
 第 1 次操作中，我们唯一的选择是将栈顶元素弹出栈。
 由于 1 次操作后无法得到一个非空的栈，所以我们返回 -1 。
  

 提示：

 1 <= nums.length <= 105
 0 <= nums[i], k <= 109

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/maximize-the-topmost-element-after-k-moves
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode-cn.com/problems/maximize-the-topmost-element-after-k-moves/solution/by-feilue-om3r/
     * @param nums
     * @param k
     * @return
     */
    public int maximumTop(int[] nums, int k) {
        if (nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            // k 是奇数 不可能存在值
            if (k % 2 == 1) {
                return -1;
            } else {
                return nums[0];
            }
        }
        int n = nums.length;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        // 如果 k大于 n 直接去 nums 中最大值即可
        if (k > n) {
            return Arrays.stream(nums).max().getAsInt();
        }
        // 如果 k 等于 n 获取k-1个 然后 找k-1中最大值
        for (int i = 0; i < k - 1; i++) {
            priorityQueue.add(nums[i]);
        }
        if (k == n) {
            return priorityQueue.peek();
        }
        // 如果 k小于 n 获取 k-1 最大值 返回 k-1最大值 和 当前 第k个 元素
        priorityQueue.add(nums[k]);
        return priorityQueue.peek();
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                68,76,53,73,85,87,58,24,48,59,38,80,38,65,90,38,45,22,3,28,11
        };
        int k = 1;
        int i = solution.maximumTop(nums, k);
        System.out.println(i);
        Assert.assertEquals(76, i);


        nums = new int[] {
                18
        };
        k = 3;
        i = solution.maximumTop(nums, k);
        System.out.println(i);
        Assert.assertEquals(-1, i);
    }

}
