package com.potato.study.leetcodecn.p02096.t001;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.potato.study.leetcode.domain.TreeNode;
import com.potato.study.leetcode.util.LeetcodeInputUtils;
import com.potato.study.leetcode.util.TreeNodeUtil;

/**
 * 2096. 从二叉树一个节点到另一个节点每一步的方向
 *
 * 给你一棵 二叉树 的根节点 root ，这棵二叉树总共有 n 个节点。每个节点的值为 1 到 n 中的一个整数，且互不相同。给你一个整数 startValue ，表示起点节点 s 的值，和另一个不同的整数 destValue ，表示终点节点 t 的值。

 请找到从节点 s 到节点 t 的 最短路径 ，并以字符串的形式返回每一步的方向。每一步用 大写 字母 'L' ，'R' 和 'U' 分别表示一种方向：

 'L' 表示从一个节点前往它的 左孩子 节点。
 'R' 表示从一个节点前往它的 右孩子 节点。
 'U' 表示从一个节点前往它的 父 节点。
 请你返回从 s 到 t 最短路径 每一步的方向。

  

 示例 1：



 输入：root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
 输出："UURL"
 解释：最短路径为：3 → 1 → 5 → 2 → 6 。
 示例 2：



 输入：root = [2,1], startValue = 2, destValue = 1
 输出："L"
 解释：最短路径为：2 → 1 。
  

 提示：

 树中节点数目为 n 。
 2 <= n <= 105
 1 <= Node.val <= n
 树中所有节点的值 互不相同 。
 1 <= startValue, destValue <= n
 startValue != destValue

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    private List<Character> leftResult;
    private List<Character> rightResult;

    public String getDirections(TreeNode root, int startValue, int destValue) {
        // 找到 startValue 和 destValue 的共同祖先
        TreeNode commonRoot = getCommonRoot(root, startValue, destValue);
        // 从共同祖先开始 dfs 找到 startValue 并生成路径
        this.leftResult = new ArrayList<>();
        dfsFromStart(commonRoot, startValue, new ArrayList<>());
        // 从共同祖先开始 dfs 找到 destValue 并生成路径
        this.rightResult = new ArrayList<>();
        dfsFromDest(commonRoot, destValue, new ArrayList<>());
        StringBuilder builder = new StringBuilder();
        for (Character ch : leftResult) {
            builder.append(ch);
        }
        for (Character ch : rightResult) {
            builder.append(ch);
        }
        return builder.toString();
    }

    private void dfsFromStart(TreeNode commonRoot, int startValue, List<Character> temp) {
        if (this.leftResult.size() > 0) {
            return;
        }
        if (commonRoot == null) {
            return;
        }
        // 当前 tmp 就是结果
        if (commonRoot.val == startValue) {
            this.leftResult = new ArrayList<>(temp);
            return;
        }
        // 往两个孩子找
//        List<Character> nextTemp = new ArrayList<>(temp);
        temp.add('U');
        dfsFromStart(commonRoot.left, startValue, temp);
        temp.remove(temp.size() - 1);
        temp.add('U');
        dfsFromStart(commonRoot.right, startValue, temp);
        temp.remove(temp.size() - 1);
    }

    private void dfsFromDest(TreeNode commonRoot, int destValue, List<Character> temp) {
        if (this.rightResult.size() > 0) {
            return;
        }
        if (commonRoot == null) {
            return;
        }
        // 当前 tmp 就是结果
        if (commonRoot.val == destValue) {
            this.rightResult = new ArrayList<>(temp);
            return;
        }
        // 往两个孩子找
//        List<Character> nextTemp = new ArrayList<>(temp);
        temp.add('L');
        dfsFromDest(commonRoot.left, destValue, temp);
        temp.remove(temp.size() - 1);
        temp.add('R');
        dfsFromDest(commonRoot.right, destValue, temp);
        temp.remove(temp.size() - 1);
    }



    private TreeNode getCommonRoot(TreeNode root, int startValue, int destValue) {
        if (root == null) {
            return null;
        }
        if (root.val == startValue || root.val == destValue) {
            return root;
        }
        TreeNode leftCommonRoot = getCommonRoot(root.left, startValue, destValue);
        TreeNode rightCommonRoot = getCommonRoot(root.right, startValue, destValue);
        // 分别在两侧找到 说明这个点就是 当前的点
        if (leftCommonRoot != null && rightCommonRoot != null) {
            return root;
        }
        if (leftCommonRoot != null) {
            return leftCommonRoot;
        }
        if (rightCommonRoot != null) {
            return rightCommonRoot;
        }
        //全没找到
        return null;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String str = "[5,1,2,3,null,6,4]";
        TreeNode root = TreeNodeUtil.generateTreeByArrayString(str);
        int startValue = 3;
        int destValue = 6;
        String directions = solution.getDirections(root, startValue, destValue);
        System.out.println(directions);
        Assert.assertEquals("UURL", directions);


        str = "[3,1,2]";
        root = TreeNodeUtil.generateTreeByArrayString(str);
        startValue = 2;
        destValue = 1;
        directions = solution.getDirections(root, startValue, destValue);
        System.out.println(directions);
        Assert.assertEquals("UL", directions);

    }




}
