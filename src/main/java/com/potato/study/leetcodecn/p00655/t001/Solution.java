package com.potato.study.leetcodecn.p00655.t001;

import com.potato.study.leetcode.domain.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 655. 输出二叉树
 *
 * 在一个 m*n 的二维字符串数组中输出二叉树，并遵守以下规则：

 行数 m 应当等于给定二叉树的高度。
 列数 n 应当总是奇数。
 根节点的值（以字符串格式给出）应当放在可放置的第一行正中间。根节点所在的行与列会将剩余空间划分为两部分（左下部分和右下部分）。你应该将左子树输出在左下部分，右子树输出在右下部分。左下和右下部分应当有相同的大小。即使一个子树为空而另一个非空，你不需要为空的子树输出任何东西，但仍需要为另一个子树留出足够的空间。然而，如果两个子树都为空则不需要为它们留出任何空间。
 每个未使用的空间应包含一个空的字符串""。
 使用相同的规则输出子树。
 示例 1:

 输入:
 1
 /
 2
 输出:
 [["", "1", ""],
 ["2", "", ""]]
 示例 2:

 输入:
 1
 / \
 2   3
 \
 4
 输出:
 [["", "", "", "1", "", "", ""],
 ["", "2", "", "", "", "3", ""],
 ["", "", "4", "", "", "", ""]]
 示例 3:

 输入:
 1
 / \
 2   5
 /
 3
 /
 4
 输出:
 [["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
 ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
 ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
 ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]
 注意: 二叉树的高度在范围 [1, 10] 中。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/print-binary-tree
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode-cn.com/problems/print-binary-tree/solution/shu-chu-er-cha-shu-by-leetcode/
     * @param root
     * @return
     */
    public List<List<String>> printTree(TreeNode root) {
        // 递归生成 先根 每次根在中间 先看下有多少层
        int height = getHeight(root);
        // 设置 足够的 数组 每次往固定位置 放当前root的值
        int length = (1 << height) - 1;
        String[][] res = new String[height][length];
        for (int i = 0; i < height; i++) {
            Arrays.fill(res[i], "");
        }
        // 没费生成一层 递归
        getResult(root, res, 0, 0, length);

        // 数组列表
        List<List<String>> list = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            list.add(Arrays.asList(res[i]));
        }
        return list;
    }

    private void getResult(TreeNode root, String[][] res, int index, int left, int right) {
        if (root == null) {
            return;
        }
        String[] layer = res[index];
        int rootIndex = (left + right) / 2;
        layer[rootIndex] = String.valueOf(root.val);
        // 左边和右边
        getResult(root.left, res, index + 1, left, rootIndex);
        getResult(root.right, res, index + 1, rootIndex + 1, right);
    }

    /**
     * 获取这个树的高度
     * @param root
     * @return
     */
    private int getHeight(TreeNode root) {
        if (null == root) {
            return 0;
        }
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }


}
