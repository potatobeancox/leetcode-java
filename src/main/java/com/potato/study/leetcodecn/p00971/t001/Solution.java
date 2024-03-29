package com.potato.study.leetcodecn.p00971.t001;

import com.potato.study.leetcode.domain.TreeNode;
import com.potato.study.leetcode.util.LeetcodeInputUtils;
import com.potato.study.leetcode.util.TreeNodeUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 971. 翻转二叉树以匹配先序遍历
 *
 * 给你一棵二叉树的根节点 root ，树中有 n 个节点，每个节点都有一个不同于其他节点且处于 1 到 n 之间的值。

 另给你一个由 n 个值组成的行程序列 voyage ，表示 预期 的二叉树 先序遍历 结果。

 通过交换节点的左右子树，可以 翻转 该二叉树中的任意节点。例，翻转节点 1 的效果如下：


 请翻转 最少 的树中节点，使二叉树的 先序遍历 与预期的遍历行程 voyage 相匹配 。 

 如果可以，则返回 翻转的 所有节点的值的列表。你可以按任何顺序返回答案。如果不能，则返回列表 [-1]。

  

 示例 1：


 输入：root = [1,2], voyage = [2,1]
 输出：[-1]
 解释：翻转节点无法令先序遍历匹配预期行程。
 示例 2：


 输入：root = [1,2,3], voyage = [1,3,2]
 输出：[1]
 解释：交换节点 2 和 3 来翻转节点 1 ，先序遍历可以匹配预期行程。
 示例 3：


 输入：root = [1,2,3], voyage = [1,2,3]
 输出：[]
 解释：先序遍历已经匹配预期行程，所以不需要翻转节点。
  

 提示：

 树中的节点数目为 n
 n == voyage.length
 1 <= n <= 100
 1 <= Node.val, voyage[i] <= n
 树中的所有值 互不相同
 voyage 中的所有值 互不相同

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/flip-binary-tree-to-match-preorder-traversal
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    private List<Integer> list;

    private int index;

    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        // 从 root 开始 dfs 记录 index
        this.list = new ArrayList<>();
        boolean isValid = dfs(root, voyage);
        if (!isValid) {
            List<Integer> objects = new ArrayList<>();
            objects.add(-1);
            return objects;
        }
        return list;
    }

    private boolean dfs(TreeNode root, int[] voyage) {
        // 先判断当前 root 是不是跟index位置 能重合 不能重合直接结束
        if (root == null) {
            return true;
        }
        if (root.val != voyage[index]) {
            return false;
        }
        index++;
        int restore = index;
        // 能重合 先正常判断左边和右边鱼 index 是否都ok
        if (dfs(root.left, voyage) && dfs(root.right, voyage)) {
            return true;
        }
        // 不是的话 重置 index 右 左判断一下
        index = restore;
        boolean res1 = dfs(root.right, voyage);
        boolean res2 = dfs(root.left, voyage);
        if (res1 && res2) {
            list.add(root.val);
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNodeUtil.generateTreeByArrayString("[1,2,3]");
        String input = "[1,3,2]";
        int[] voyage = LeetcodeInputUtils.inputString2IntArray(input);
        List<Integer> list = solution.flipMatchVoyage(root, voyage);
        System.out.println(list);
    }

}
