package com.potato.study.leetcodecn.other.lcr.p0067.t001;

/**
 * 剑指 Offer II 067. 最大的异或
 *
 * 给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。

  

 示例 1：

 输入：nums = [3,10,5,25,2,8]
 输出：28
 解释：最大运算结果是 5 XOR 25 = 28.
 示例 2：

 输入：nums = [14,70,53,83,49,91,36,80,92,51,66,70]
 输出：127
  

 提示：

 1 <= nums.length <= 2 * 105
 0 <= nums[i] <= 231 - 1
  

 注意：本题与主站 421 题相同： https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array/

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/ms70jA
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 字典数 节点内部结构
     */
    class BinaryDicNode {
        public BinaryDicNode[] child;

        /**
         * 只有 01
         */
        public BinaryDicNode() {
            this.child = new BinaryDicNode[2];
        }
    }


    private BinaryDicNode root;

    /**
     * 字典数
     * @param nums
     * @return
     */
    public int findMaximumXOR(int[] nums) {
        // 遍历 nums 将每个节点 都放入字典里边
        this.root = new BinaryDicNode();
        for (int num : nums) {
            add(num);
        }
        // 遍历 nums 从 字典里边拿出 可能最大的值 并进行异或记录
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            int other = getMax(num);
            max = Math.max(max, (other ^ num));
        }
        return max;
    }

    /**
     * 找到最大的 如果存在不一样就找不一样 否则找一样
     * @param num
     * @return
     */
    private int getMax(int num) {
        BinaryDicNode current = this.root;
        int result = 0;
        for (int i = 30; i >= 0; i--) {
            int bit = 1 << i;
            int temp = num & bit;
            int targetIndex;
            result *= 2;
            if (temp == 0) {
                // 当前bit 是0 找1
                targetIndex = 1;
            } else {
                // 当前bit 是1 找0
                targetIndex = 0;
            }
            if (current == null) {
                continue;
            }
            // 当前 是空的 看看 是不是 能继续找 找到了
            if (current.child[targetIndex] != null) {
                current = current.child[targetIndex];
                result += targetIndex;
                continue;
            }
            // 当前是空的 看看 能不能找下
            if (current.child[1-targetIndex] != null) {
                current = current.child[1-targetIndex];
                result += (1-targetIndex);
                continue;
            }
            // 当前是空的，孩子也是空的
            current = null;
        }
        return result;
    }

    /**
     * 将num 放进树中
     * @param num
     */
    private void add(int num) {
        BinaryDicNode current = this.root;
        for (int i = 30; i >= 0; i--) {
            int bit = 1 << i;
            int temp = num & bit;
            int index;
            if (temp == 0) {
                // 当前bit 是0
                index = 0;
            } else {
                // 当前bit 是1
                index = 1;
            }
            // 创建节点
            if (current.child[index] == null) {
                current.child[index] = new BinaryDicNode();
            }
            current = current.child[index];
        }
    }
}
