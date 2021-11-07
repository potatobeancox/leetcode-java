package com.potato.study.leetcodecn.p00677.t001;

import com.potato.study.leetcode.domain.TreeNode;
import com.potato.study.leetcodecn.p00402.t001.Solution;
import org.junit.Assert;

import java.util.logging.SocketHandler;

/**
 * 677. 键值映射
 *
 * 实现一个 MapSum 类，支持两个方法，insert 和 sum：

 MapSum() 初始化 MapSum 对象
 void insert(String key, int val) 插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。如果键 key 已经存在，那么原来的键值对将被替代成新的键值对。
 int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和。
  

 示例：

 输入：
 ["MapSum", "insert", "sum", "insert", "sum"]
 [[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
 输出：
 [null, null, 3, null, 5]

 解释：
 MapSum mapSum = new MapSum();
 mapSum.insert("apple", 3);
 mapSum.sum("ap");           // return 3 (apple = 3)
 mapSum.insert("app", 2);
 mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
  

 提示：

 1 <= key.length, prefix.length <= 50
 key 和 prefix 仅由小写英文字母组成
 1 <= val <= 1000
 最多调用 50 次 insert 和 sum

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/map-sum-pairs
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class MapSum {

    /**
     * 字典树 目录
     */
    private Node root;


    /**
     *
     */
    public MapSum() {
        this.root = new Node();
        root.nodes = new Node[26];
    }

    /**
     * 插入
     * @param key
     * @param val
     */
    public void insert(String key, int val) {
        Node node = this.root;
        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            int index = ch - 'a';
            if (node.nodes[index] == null) {
                node.nodes[index] = new Node();
            }
            node = node.nodes[index];
        }
        node.val = val;
    }

    /**
     * 求和
     * @param prefix
     * @return
     */
    public int sum(String prefix) {
        Node node = this.root;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            int index = ch - 'a';
            if (node.nodes[index] == null) {
                node.nodes[index] = new Node();
            }
            node = node.nodes[index];
        }
        return dfsSum(node);
    }

    private int dfsSum(Node node) {
        if (node == null) {
            return 0;
        }
        int total = node.val;
        for (int i = 0; i < 26; i++) {
            Node child = node.nodes[i];
            if (child == null) {
                continue;
            }
            total += dfsSum(child);
        }
        return total;
    }


    class Node {
        public Node[] nodes;
        public int val;

        public Node() {
            this.nodes = new Node[26];
        }
    }

    public static void main(String[] args) {
//        Solution solution = new Solution();
//        String s = solution.removeKdigits();
//        System.out.println(s);
//        Assert.assertEquals(, s);
    }
}
