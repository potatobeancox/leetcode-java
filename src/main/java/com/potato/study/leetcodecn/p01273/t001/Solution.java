package com.potato.study.leetcodecn.p01273.t001;

import java.util.ArrayList;
import java.util.List;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 1273. 删除树节点
 *
 * 给你一棵以节点 0 为根节点的树，定义如下：
 *
 * 节点的总数为 nodes 个；
 * 第 i 个节点的值为 value[i] ；
 * 第 i 个节点的父节点是 parent[i] 。
 * 请你删除节点值之和为 0 的每一棵子树。
 *
 * 在完成所有删除之后，返回树中剩余节点的数目。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：nodes = 7, parent = [-1,0,0,1,2,2,2], value = [1,-2,4,0,-2,-1,-1]
 * 输出：2
 * 示例 2：
 *
 * 输入：nodes = 7, parent = [-1,0,0,1,2,2,2], value = [1,-2,4,0,-2,-1,-2]
 * 输出：6
 * 示例 3：
 *
 * 输入：nodes = 5, parent = [-1,0,1,0,0], value = [-672,441,18,728,378]
 * 输出：5
 * 示例 4：
 *
 * 输入：nodes = 5, parent = [-1,0,0,1,1], value = [-686,-842,616,-739,-746]
 * 输出：5
 *  
 *
 * 提示：
 *
 * 1 <= nodes <= 10^4
 * parent.length == nodes
 * 0 <= parent[i] <= nodes - 1
 * parent[0] == -1 表示节点 0 是树的根。
 * value.length == nodes
 * -10^5 <= value[i] <= 10^5
 * 题目输入数据 保证 是一棵 有效的树 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/delete-tree-nodes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    private int deleteCount;

    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
        // 将 parent 转换成 list list 存当前作为 list。get i 节点的孩子们 index
        List<List<Integer>> childList = new ArrayList<>();
        for (int i = 0; i < nodes; i++) {
            childList.add(new ArrayList<>());
        }
        int startIndex = -1;
        for (int i = 0; i < parent.length; i++) {
            // 整个树的根
            if (parent[i] < 0) {
                startIndex = i;
                continue;
            }
            int p = parent[i];
            int child = i;
            childList.get(p).add(i);
        }
        // dfs
        this.deleteCount = 0;
        if (startIndex < 0) {
            return -1;
        }
        dfs(childList, value, startIndex);
        return nodes - this.deleteCount;
    }

    private void dfs(List<List<Integer>> childList, int[] value, int startIndex) {

    }

}
