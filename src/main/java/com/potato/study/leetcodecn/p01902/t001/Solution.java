package com.potato.study.leetcodecn.p01902.t001;

import java.util.TreeMap;

/**
 * 1902. 给定二叉搜索树的插入顺序求深度
 *
 * 给定一个从 0 开始索引的整数类型数组 order ，其长度为 n，是从 1 到 n 的所有整数的一个排列，表示插入到一棵二叉搜索树的顺序。

 二叉搜索树的定义如下：

 一个节点的左子树只包含键值小于该节点键值的节点。
 一个节点的右子树只包含键值大于该节点键值的节点。
 左子树和右子树须均为二叉搜索树。
 该二叉搜索树的构造方式如下：

 order[0] 将成为该二叉搜索树的根。
 所有后续的元素均在维持二叉搜索树性质的前提下作为任何已存在节点的子节点插入。
 返回该二叉搜索树的深度。

 一棵二叉树的深度是从根节点到最远叶节点的最长路径所经节点的个数。

  

 示例 1:


 输入: order = [2,1,4,3]
 输出: 3
 解释: 该二叉搜索树的深度为 3，路径为 2->4->3。
 示例 2:


 输入: order = [2,1,3,4]
 输出: 3
 解释: 该二叉搜索树的深度为 3，路径为 2->3->4。
 示例 3:


 输入: order = [1,2,3,4]
 输出: 4
 解释: 该二叉搜索树的深度为 4，路径为 1->2->3->4。
  

 提示：

 n == order.length
 1 <= n <= 105
 order 是从 1 到 n 的整数的一个排列。


 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/depth-of-bst-given-insertion-order
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 1902
    public int maxDepthBST(int[] order) {
        // TreeMap key 是 节点的值 value 是深度
        TreeMap<Integer, Integer> depthMap = new TreeMap<>();
        depthMap.put(0, 0);
        depthMap.put(Integer.MAX_VALUE, 0);
        int maxDepth = 0;
        for (int i = 0; i < order.length; i++) {
            int node = order[i];
            Integer higherKey = depthMap.higherKey(node);
            Integer lowerKey = depthMap.lowerKey(node);
            // 分别找到 左右
            int dep = Math.max(depthMap.get(higherKey), depthMap.get(lowerKey));
            depthMap.put(node, dep + 1);
            maxDepth = Math.max(maxDepth, dep + 1);
        }
        return maxDepth;
    }

}
