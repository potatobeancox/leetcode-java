package com.potato.study.leetcodecn.p02764.t001;


import java.util.List;

/**
 *
 * 2764. 数组是否表示某二叉树的前序遍历
 *
 * 给定一个以 0 为起始索引的整数 二维数组 nodes ，你的任务是确定给定的数组是否表示某个 二叉 树的 前序 遍历。
 *
 * 对于每个索引 i ，nodes[i] = [id, parentId] ，其中 id 是索引 i 处节点的 id，parentId 是其在树中的父节点 id（如果该节点没有父节点，则 parentId = -1 ）。
 *
 * 如果给定的数组表示某个树的前序遍历，则返回 true ，否则返回 false 。
 *
 * 注意：树的 前序 遍历是一种递归的遍历方式，它首先访问当前节点，然后对左子节点进行前序遍历，最后对右子节点进行前序遍历。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nodes = [[0,-1],[1,0],[2,0],[3,2],[4,2]]
 * 输出：true
 * 解释：给定的 nodes 数组可以构成下面图片中的树。
 * 我们可以验证这是树的前序遍历，首先访问节点 0，然后对右子节点进行前序遍历，即 [1] ，然后对左子节点进行前序遍历，即 [2,3,4] 。
 *
 *
 * 示例 2：
 *
 * 输入：nodes = [[0,-1],[1,0],[2,0],[3,1],[4,1]]
 * 输出：false
 * 解释：给定的 nodes 数组可以构成下面图片中的树。
 * 对于前序遍历，首先访问节点 0，然后对右子节点进行前序遍历，即 [1,3,4]，但是我们可以看到在给定的顺序中，2 位于 1 和 3 之间，因此它不是树的前序遍历。
 *
 *
 *  
 *
 * 提示：
 *
 * 1 <= nodes.length <= 105
 * nodes[i].length == 2
 * 0 <= nodes[i][0] <= 105
 * -1 <= nodes[i][1] <= 105
 * 生成的输入保证 nodes 可以组成二叉树。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/is-array-a-preorder-of-some-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    private int index;


    public boolean isPreorder(List<List<Integer>> nodes) {
        // 判断 nodes 对应是否是合法先序列遍历 记录父亲结点
        this.index = 0;
        return isPreorder(nodes, -1);
    }

    /**
     *
     * @param nodes
     * @param parentId 本次验证点的父亲应该是哪个结点
     * @return
     */
    private boolean isPreorder(List<List<Integer>> nodes, int parentId) {
        // nodes[i] = [id, parentId]
        if (nodes.size() <= this.index) {
            // 所有点都呗检测过 那就是 合法的
            return true;
        }
        List<Integer> currentNode = nodes.get(this.index);
        Integer pid = currentNode.get(1);
        if (pid != parentId) {
            return false;
        }
        int thisId = currentNode.get(0);
        // i+1 就是左孩子
        this.index++;
//        return isPreorder(nodes, thisId) && isPreorder(nodes,hi , thisId);
        return false;
    }


}
