package com.potato.study.leetcodecn.p00968.t001;

import com.potato.study.leetcode.domain.TreeNode;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 968. 监控二叉树
 *
 * 给定一个二叉树，我们在树的节点上安装摄像头。

 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。

 计算监控树的所有节点所需的最小摄像头数量。

  

 示例 1：



 输入：[0,0,null,0,0]
 输出：1
 解释：如图所示，一台摄像头足以监控所有节点。
 示例 2：



 输入：[0,0,null,0,null,0,null,null,0]
 输出：2
 解释：需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。

 提示：

 给定树的节点数的范围是 [1, 1000]。
 每个节点的值都是 0。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/binary-tree-cameras
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    private int count;

    /**
     * https://leetcode.cn/problems/binary-tree-cameras/
     * @param root
     * @return
     */
    public int minCameraCover(TreeNode root) {
        this.count = 0;
        // 贪心 后序遍历
        int rootStatus = postOrder(root);
        // 根节点需要在主函数中判断
        if (rootStatus == 0) {
            this.count++;
        }
        return this.count;
    }


    /**
     * 返回 root 放置状态
     * @param root
     * @return
     */
    private int postOrder(TreeNode root) {
        // 3种状态 0 当前没有摄像头覆盖， 1当前节点安置摄像头 2被别的摄像头覆盖了
        if (root == null) {
            return 2;
        }
        int leftStatus = postOrder(root.left);
        int rightStatus = postOrder(root.right);
        // 如果孩子中有任意一个 0 那么当前点必须为 1 （之后都是 没有 0的情况）
        if (leftStatus == 0 || rightStatus == 0) {
            // 别忘了最终计数
            this.count++;
            return 1;
        }
        // 如果孩子中 有一个 1 那么当前点可以返回2 当前点空也是 返回2  这样让叶子节点是 0
        if (leftStatus == 1 || rightStatus == 1) {
            return 2;
        }
        // 其他情况就是 0 说明孩子没有 0也没有 1
        return 0;
    }

}
