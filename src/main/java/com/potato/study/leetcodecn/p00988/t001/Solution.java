package com.potato.study.leetcodecn.p00988.t001;

import com.potato.study.leetcode.domain.TreeNode;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * 988. 从叶结点开始的最小字符串
 *
 * 给定一颗根结点为 root 的二叉树，树中的每一个结点都有一个从 0 到 25 的值，分别代表字母 'a' 到 'z'：值 0 代表 'a'，值 1 代表 'b'，依此类推。

 找出按字典序最小的字符串，该字符串从这棵树的一个叶结点开始，到根结点结束。

 （小贴士：字符串中任何较短的前缀在字典序上都是较小的：例如，在字典序上 "ab" 比 "aba" 要小。叶结点是指没有子结点的结点。）

  

 示例 1：



 输入：[0,1,2,3,4,3,4]
 输出："dba"
 示例 2：



 输入：[25,1,3,1,3,0,2]
 输出："adz"
 示例 3：



 输入：[2,2,1,null,1,0,null,0]
 输出："abc"
  

 提示：

 给定树的结点数介于 1 和 8500 之间。
 树中的每个结点都有一个介于 0 和 25 之间的值。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/smallest-string-starting-from-leaf
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    private String smallestWord;

    public String smallestFromLeaf(TreeNode root) {
        if (null == root) {
            return "";
        }
        // dfs 找到 最后翻转之后 比较然后返回
        dfs(root, "");
        return smallestWord;
    }

    private void dfs(TreeNode root, String current) {

        StringBuilder builder = new StringBuilder(current);
        builder.append((char)(root.val + 'a'));

        if (root.left == null && root.right == null) {
            String word = builder.reverse().toString();
            if (smallestWord == null) {
                smallestWord = word;
                return;
            }
            int i = smallestWord.compareTo(word);
            if (i > 0) {
                smallestWord = word;
            }
            return;
        }
        if (root.left != null) {
            dfs(root.left, builder.toString());
        }
        if (root.right != null) {
            dfs(root.right, builder.toString());
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        TreeNode root = new TreeNode(21);
        String s = solution.smallestFromLeaf(root);
        System.out.println(s);
        Assert.assertEquals("v", s);
    }


}
