package com.potato.study.leetcodecn.p02313.t001;

import com.potato.study.leetcode.domain.TreeNode;
import org.junit.Assert;

/**
 * 2313. 二叉树中得到结果所需的最少翻转次数
 *
 * 给定二叉树的根 root，具有以下属性:
 *
 * 叶节点 的值为 0 或 1，分别表示 false 和 true。
 * 非叶节点的值为 2、3、4、5，分别表示布尔运算 OR, AND, XOR, NOT。
 * 您还将得到一个布尔型 result，这是 root 节点的期望 评价 结果。
 *
 * 对节点的评价计算如下:
 *
 * 如果节点是叶节点，则评价是节点的 值，即 true 或 false.
 * 否则, 将其值的布尔运算应用于子节点的 评价，该节点的 评价 即为布尔运算后的结果。
 * 在一个操作中，您可以 翻转 一个叶节点，这将导致一个 false 节点变为 true 节点，一个 true 节点变为 false 节点。
 *
 * 返回需要执行的最小操作数，以使 root 的评价得到 result。可以证明，总有办法达到 result。
 *
 * 叶节点 是没有子节点的节点。
 *
 * 注意: NOT 节点只有左孩子或只有右孩子，但其他非叶节点同时拥有左孩子和右孩子。
 *
 *  
 *
 * 示例 1:
 *
 *
 * 输入: root = [3,5,4,2,null,1,1,1,0], result = true
 * 输出: 2
 * 解释:
 * 可以证明，至少需要翻转 2 个节点才能使树的 root 评价为 true。上面的图显示了实现这一目标的一种方法。
 * 示例 2:
 *
 * 输入: root = [0], result = false
 * 输出: 0
 * 解释:
 * 树的 root 的评价已经为 false，所以 0 个节点必须翻转。
 *  
 *
 * 提示:
 *
 * 树中的节点数在 [1, 105] 范围内。
 * 0 <= Node.val <= 5
 * OR, AND, XOR 节点有 2 个子节点。
 * NOT 只有一个 1 子节点。
 * 叶节点的值为 0 或 1.
 * 非叶节点的值为2, 3, 4, 5.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-flips-in-binary-tree-to-get-result
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {




    // 2313
    public int minimumFlips(TreeNode root, boolean result) {
        // 使用一个 数组 0标识 false 的次数 1标识 true 的次数
        int[] flipCount = getMinimumFlips(root);
        if (result) {
            // 成功
            return flipCount[1];
        } else {
            return flipCount[0];
        }
    }

    /**
     *
     * @param root
     * @return
     */
    private int[] getMinimumFlips(TreeNode root) {
        // 终止条件 null or 叶子 判断
        if (null == root) {
            return new int[] {0, 0};
        }
        // 不是 叶子节点的情况下 找到两个孩子的结果 并根据当前的值进行计算
        // 叶节点 的值为 0 或 1，分别表示 false 和 true。
        // 非叶节点的值为 2、3、4、5，分别表示布尔运算 OR, AND, XOR, NOT。
        if (root.val == 0) {
            // 使用一个 数组 0标识 false 的次数 1标识 true 的次数
            return new int[] {0, 1};
        } else if (root.val == 1) {
            // true
            return new int[] {1, 0};
        } else {
            // 中间节点 先获取孩子的结果
            int[] leftResult = getMinimumFlips(root.left);
            int[] rightResult = getMinimumFlips(root.right);

            int op = root.val;
            if (op == 2) {
                // or false
                return new int[] {
                        leftResult[0] + rightResult[0],
                        Math.min(leftResult[0] + rightResult[1], Math.min(leftResult[1] + rightResult[0], leftResult[1] + rightResult[1]))
                };
            } else if (op == 3) {
                // AND false 任意一个 是false 就是 false 都成功就是成功
                return new int[] {
                        Math.min(leftResult[0] + rightResult[0], Math.min(leftResult[1] + rightResult[0], leftResult[0] + rightResult[1])),
                        leftResult[1] + rightResult[1]
                };
            } else if (op == 4) {
                // XOR false 相同 true 不相同
                return new int[] {
                        Math.min(leftResult[1] + rightResult[1], leftResult[0] + rightResult[0]),
                        Math.min(leftResult[1] + rightResult[0], leftResult[0] + rightResult[1])
                };
            } else {
                // NOT 取反
                return new int[] {leftResult[1] + rightResult[1], leftResult[0] + rightResult[0]};
            }

        }
    }

}
