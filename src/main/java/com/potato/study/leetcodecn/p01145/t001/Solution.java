package com.potato.study.leetcodecn.p01145.t001;


import com.potato.study.leetcode.domain.TreeNode;
import org.junit.Assert;

/**
 * 1145. 二叉树着色游戏
 *
 * 有两位极客玩家参与了一场「二叉树着色」的游戏。游戏中，给出二叉树的根节点 root，树上总共有 n 个节点，且 n 为奇数，其中每个节点上的值从 1 到 n 各不相同。

  

 游戏从「一号」玩家开始（「一号」玩家为红色，「二号」玩家为蓝色），最开始时，

 「一号」玩家从 [1, n] 中取一个值 x（1 <= x <= n）；

 「二号」玩家也从 [1, n] 中取一个值 y（1 <= y <= n）且 y != x。

 「一号」玩家给值为 x 的节点染上红色，而「二号」玩家给值为 y 的节点染上蓝色。

  

 之后两位玩家轮流进行操作，每一回合，玩家选择一个他之前涂好颜色的节点，将所选节点一个 未着色 的邻节点（即左右子节点、或父节点）进行染色。

 如果当前玩家无法找到这样的节点来染色时，他的回合就会被跳过。

 若两个玩家都没有可以染色的节点时，游戏结束。着色节点最多的那位玩家获得胜利 ✌️。

  

 现在，假设你是「二号」玩家，根据所给出的输入，假如存在一个 y 值可以确保你赢得这场游戏，则返回 true；若无法获胜，就请返回 false。

  

 示例：



 输入：root = [1,2,3,4,5,6,7,8,9,10,11], n = 11, x = 3
 输出：True
 解释：第二个玩家可以选择值为 2 的节点。
  

 提示：

 二叉树的根节点为 root，树上由 n 个节点，节点上的值从 1 到 n 各不相同。
 n 为奇数。
 1 <= x <= n <= 100

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/binary-tree-coloring-game
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        // 找到 x 点位置 计算 x的左子树的节点 个数 计算右孩子的节点个数
        TreeNode node = findNode(root, x);
        int leftCount = getCount(node.left);
        int limit = n / 2;
        if (n % 2 == 1) {
            limit++;
        }
        if (leftCount >= limit) {
            return true;
        }
        int rightCount = getCount(node.right);
        if (rightCount >= limit) {
            return true;
        }
        return n - leftCount - rightCount - 1 >= limit;
    }

    private int getCount(TreeNode node) {
        if (null == node) {
            return 0;
        }
        return getCount(node.left) + getCount(node.right) + 1;
    }

    private TreeNode findNode(TreeNode root, int x) {
        if (root == null) {
            return null;
        }
        if (root.val == x) {
            return root;
        }
        TreeNode left = findNode(root.left, x);
        if (left != null) {
            return left;
        }
        TreeNode right = findNode(root.right, x);
        return right;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        int n = 3;
        int x = 2;
        boolean b = solution.btreeGameWinningMove(root, n, x);
        System.out.println(b);
        Assert.assertEquals(true, b);
    }
}
