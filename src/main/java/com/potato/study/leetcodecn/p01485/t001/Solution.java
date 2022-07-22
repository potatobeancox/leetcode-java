package com.potato.study.leetcodecn.p01485.t001;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.potato.study.leetcode.domain.node.val.left.right.random.Node;
import com.potato.study.leetcode.domain.node.val.left.right.random.NodeCopy;

/**
 * 1485. 克隆含随机指针的二叉树
 *
 * 给你一个二叉树，树中每个节点都含有一个附加的随机指针，该指针可以指向树中的任何节点或者指向空（null）。
 *
 * 请返回该树的 深拷贝 。
 *
 * 该树的输入/输出形式与普通二叉树相同，每个节点都用 [val, random_index] 表示：
 *
 * val：表示 Node.val 的整数
 * random_index：随机指针指向的节点（在输入的树数组中）的下标；如果未指向任何节点，则为 null 。
 * 该树以 Node 类的形式给出，而你需要以 NodeCopy 类的形式返回克隆得到的树。NodeCopy 类和Node 类定义一致。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [[1,null],null,[4,3],[7,0]]
 * 输出：[[1,null],null,[4,3],[7,0]]
 * 解释：初始二叉树为 [1,null,4,7] 。
 * 节点 1 的随机指针指向 null，所以表示为 [1, null] 。
 * 节点 4 的随机指针指向 7，所以表示为 [4, 3] 其中 3 是树数组中节点 7 对应的下标。
 * 节点 7 的随机指针指向 1，所以表示为 [7, 0] 其中 0 是树数组中节点 1 对应的下标。
 * 示例 2：
 *
 *
 *
 * 输入：root = [[1,4],null,[1,0],null,[1,5],[1,5]]
 * 输出：[[1,4],null,[1,0],null,[1,5],[1,5]]
 * 解释：节点的随机指针可以指向它自身。
 * 示例 3：
 *
 *
 *
 * 输入：root = [[1,6],[2,5],[3,4],[4,3],[5,2],[6,1],[7,0]]
 * 输出：[[1,6],[2,5],[3,4],[4,3],[5,2],[6,1],[7,0]]
 *  
 *
 * 提示：
 *
 * tree 中节点数目范围是 [0, 1000]
 * 每个节点的值的范围是 [1, 10^6]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/clone-binary-tree-with-random-pointer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {







    public NodeCopy copyRandomBinaryTree(Node root) {
        if (root == null) {
            return null;
        }
        // 层序便利 先将 节点建立出来
        Map<Node, NodeCopy> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            // 新建
            NodeCopy nodeCopy = new NodeCopy(poll.val);
            map.put(poll, nodeCopy);
            // 孩子
            if (poll.left != null) {
                queue.add(poll.left);
            }
            if (poll.right != null) {
                queue.add(poll.right);
            }
        }
        // 再次层序便利 连接不同节点之间的链
        queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            // 连接
            NodeCopy nodeCopy = map.get(poll);
            if (poll.left != null) {
                NodeCopy left = map.get(poll.left);
                nodeCopy.left = left;
                queue.add(poll.left);
            }
            if (poll.right != null) {
                NodeCopy right = map.get(poll.right);
                nodeCopy.right = right;
                queue.add(poll.right);
            }
            if (poll.random != null) {
                NodeCopy random = map.get(poll.random);
                nodeCopy.random = random;
            }
        }
        return map.get(root);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println("test");
    }
}
