package com.potato.study.leetcodecn.p00421.t001;

import org.junit.Assert;

/**
 * 421. 数组中两个数的最大异或值
 *
 *
 * 给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
 *
 * 进阶：你可以在 O(n) 的时间解决这个问题吗？
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [3,10,5,25,2,8]
 * 输出：28
 * 解释：最大运算结果是 5 XOR 25 = 28.
 * 示例 2：
 *
 * 输入：nums = [0]
 * 输出：0
 * 示例 3：
 *
 * 输入：nums = [2,4]
 * 输出：6
 * 示例 4：
 *
 * 输入：nums = [8,10,2]
 * 输出：10
 * 示例 5：
 *
 * 输入：nums = [14,70,53,83,49,91,36,80,92,51,66,70]
 * 输出：127
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 2 * 104
 * 0 <= nums[i] <= 231 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    private NumberDic dic;

    /**
     * 暴力解法 tle
     * @param nums
     * @return
     */
    public int findMaximumXOR(int[] nums) {
        dic = new NumberDic();
        // 字典树 每个num 转换成32 位 插入字典树
        for (int num : nums) {
            insert(num);
        }
        // 重新遍历 找到 对应最大的值
        int max = 0;
        for (int num : nums) {
            int thisMax = findMax(num);
            max = Math.max(thisMax ^ num, max);
        }
        return max;
    }


    private void insert(int num) {
        String s = Integer.toBinaryString(num);
        StringBuilder builder = new StringBuilder();
        for (int i = s.length(); i < 32; i++) {
            builder.append("0");
        }
        builder.append(s);
        NumberDic p = dic;
        for (int i = 0; i < builder.length(); i++) {
            int index = builder.charAt(i) - '0';
            if (p.child[index] == null) {
                p.child[index] = new NumberDic();
            }
            p = p.child[index];
        }
        p.isEnd = true;
    }

    private int findMax(int num) {
        String s = Integer.toBinaryString(num);
        StringBuilder builder = new StringBuilder();
        for (int i = s.length(); i < 32; i++) {
            builder.append("0");
        }
        builder.append(s);

        NumberDic p = dic;
        int target = 0;
        for (int i = 0; i < builder.length(); i++) {
            int index = builder.charAt(i) - '0';
            index = 1-index;
            if (p.child[index] != null) {
                p = p.child[index];
                target *= 2;
                target += index;
            } else {
                p = p.child[1-index];
                target *= 2;
                target += (1-index);
            }
        }
        return target;
    }


    class NumberDic {
        public NumberDic[] child;
        public boolean isEnd;

        public NumberDic() {
            this.child = new NumberDic[2];
        }
    }

    public static void main(String[] args) {

    }
}
