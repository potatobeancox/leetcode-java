package com.potato.study.leetcodecn.p00536.t001;


import com.potato.study.leetcode.domain.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 536. 从字符串生成二叉树
 *
 * 你需要用一个包括括号和整数的字符串构建一棵二叉树。

 输入的字符串代表一棵二叉树。它包括整数和随后的 0 、1 或 2 对括号。整数代表根的值，一对括号内表示同样结构的子树。

 若存在子结点，则从左子结点开始构建。

  

 示例 1:


 输入： s = "4(2(3)(1))(6(5))"
 输出： [4,2,6,3,1,5]
 示例 2:

 输入： s = "4(2(3)(1))(6(5)(7))"
 输出： [4,2,6,3,1,5,7]
 示例 3:

 输入： s = "-4(2(3)(1))(6(5)(7))"
 输出： [-4,2,6,3,1,5,7]
  

 提示：

 0 <= s.length <= 3 * 104
 输入字符串中只包含 '(', ')', '-' 和 '0' ~ '9' 
 空树由 "" 而非"()"表示。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/construct-binary-tree-from-string
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {
    public TreeNode str2tree(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        char[] chars = s.toCharArray();
        int index = 0;
        // 解析出来最开始的富豪和数字 找到第一个（ 位置
        while (index < s.length()
                && (chars[index] == '-' || Character.isDigit(chars[index]))) {
            index++;
        }
        String numStr = s.substring(0, index);
        int num = Integer.parseInt(numStr);
        TreeNode treeNode = new TreeNode();
        treeNode.val = num;
        // 没有 （
        if (index >= s.length()) {
            return treeNode;
        }
        // 如果有 （ 位置 找到）  位置
        int start = index;
        int status = 0;
        int end = -1;
        while (index < s.length()) {
            if (chars[index] == '(') {
                status++;
            } else if (chars[index] == ')') {
                status--;
            }
            if (status == 0) {
                end = index;
                break;
            }
            index++;
        }
        if (end == -1) {
            throw new RuntimeException("输入自字符串只有（ 没有 ）");
        }
        index++;
        treeNode.left = str2tree(s.substring(start + 1, end));
        if (index >= s.length()) {
            return treeNode;
        }
        start = index;
        end = -1;
        while (index < s.length()) {
            if (chars[index] == '(') {
                status++;
            } else if (chars[index] == ')') {
                status--;
            }
            if (status == 0) {
                end = index;
                break;
            }
            index++;
        }
        if (end == -1) {
            throw new RuntimeException("输入自字符串只有第二个（ 没有 ）");
        }
        index++;
        treeNode.right = str2tree(s.substring(start + 1, end));
        // 如果还有 （） 找到 根据每个位置 subtring 递归生成
        return treeNode;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode treeNode = solution.str2tree("4(2(3)(1))(6(5))");
        System.out.println(treeNode);
    }
}
