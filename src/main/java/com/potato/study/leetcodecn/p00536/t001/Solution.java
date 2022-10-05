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
        return null;
    }
}
