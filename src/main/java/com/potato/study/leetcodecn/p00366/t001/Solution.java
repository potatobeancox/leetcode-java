package com.potato.study.leetcodecn.p00366.t001;

import com.potato.study.leetcode.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 366. 寻找二叉树的叶子节点
 *
 * 给你一棵二叉树，请按以下要求的顺序收集它的全部节点：

 依次从左到右，每次收集并删除所有的叶子节点
 重复如上过程直到整棵树为空
  

 示例:

 输入: [1,2,3,4,5]
  
           1
 / \
 2   3
 / \
 4   5

 输出: [[4,5,3],[2],[1]]
  

 解释:

 1. 删除叶子节点 [4,5,3] ，得到如下树结构：

 1
 /
 2
  

 2. 现在删去叶子节点 [2] ，得到如下树结构：

 1
  

 3. 现在删去叶子节点 [1] ，得到空树：

 []

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/find-leaves-of-binary-tree
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * dfs 一直往下找找到 叶子为止
     * https://leetcode.cn/problems/find-leaves-of-binary-tree/solution/366-xun-zhao-er-cha-shu-de-xie-zi-jie-dian-by-klb/
     * @param root
     * @return
     */
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> resultList = new ArrayList<>();
        // 每次都修改 root 直到删除最后一个点
        while (root != null) {
            // 存放每次找到的叶子的结果
            List<Integer> list = new ArrayList<>();
            root = dfs(root, list);
            resultList.add(list);
        }
        return resultList;
    }

    /**
     * 修改 当前点 并记录叶子
     * @param root
     * @param list
     * @return
     */
    private TreeNode dfs(TreeNode root, List<Integer> list) {
        // 如果当前点就是叶子 那么加入list
        if (root.left == null && root.right == null) {
            list.add(root.val);
            return null;
        }
        // 否则 往孩子中找 找过程中并修改 孩子的对应关系
        if (root.left != null) {
            root.left = dfs(root.left, list);
        }
        if (root.right != null) {
            root.right = dfs(root.right, list);
        }
        return root;
    }
}
