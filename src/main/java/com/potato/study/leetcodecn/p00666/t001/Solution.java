package com.potato.study.leetcodecn.p00666.t001;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 666. 路径总和 IV
 *
 * 对于一棵深度小于 5 的树，可以用一组三位十进制整数来表示。对于每个整数：

 百位上的数字表示这个节点的深度 d，1 <= d <= 4。
 十位上的数字表示这个节点在当前层所在的位置 P， 1 <= p <= 8。位置编号与一棵满二叉树的位置编号相同。
 个位上的数字表示这个节点的权值 v，0 <= v <= 9。
 给定一个包含三位整数的 升序 数组 nums ，表示一棵深度小于 5 的二叉树，请你返回 从根到所有叶子结点的路径之和 。

 保证 给定的数组表示一个有效的连接二叉树。

  

 示例 1：



 输入: nums = [113, 215, 221]
 输出: 12
 解释: 列表所表示的树如上所示。
 路径和 = (3 + 5) + (3 + 1) = 12.
 示例 2：



 输入: nums = [113, 221]
 输出: 4
 解释: 列表所表示的树如上所示。
 路径和 = (3 + 1) = 4.
  

 提示:

 1 <= nums.length <= 15
 110 <= nums[i] <= 489
 nums 表示深度小于 5 的有效二叉树

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/path-sum-iv
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    private long total;
    private Map<Integer, Integer> map;

    public int pathSum(int[] nums) {
        this.total = 0;
        // 将 nums 放到map 中备用 value是权值 key 是层+ index
        this.map = new HashMap<>();
        for (int num : nums) {
            int key = num / 10;
            int val = num % 10;
            map.put(key, val);
        }
        // dfs 生成 从根节点开始（第一个节点）每次记录 当前的sum
        dfs(nums[0]/10, 0);
        return (int) total;
    }

    private void dfs(int root, int current) {
        // 如果已经到了没有的情况 说明已经走过了 直接返回
        if (!map.containsKey(root)) {
            return;
        }
        int sum = current + map.get(root);
        // 计算最有孩子
        int level = root / 10;
        int index = root % 10;

        int childLevel = level + 1;
        int leftChildIndex = (index * 2) - 1;
        int rightChildIndex = leftChildIndex + 1;

        int leftChild = childLevel * 10 + leftChildIndex;
        int rightChild = childLevel * 10 + rightChildIndex;

        // 没有左右孩子了 加上去
        if (!map.containsKey(leftChild) && !map.containsKey(rightChild)) {
            this.total += sum;
        } else {
            // 至少有一个孩子
            dfs(leftChild, sum);
            dfs(rightChild, sum);
        }
    }
}
