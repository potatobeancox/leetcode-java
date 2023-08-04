package com.potato.study.leetcodecn.other.lcr.p0066.t001;

/**
 * 剑指 Offer II 066. 单词之和
 *
 * 实现一个 MapSum 类，支持两个方法，insert 和 sum：
 *
 * MapSum() 初始化 MapSum 对象
 * void insert(String key, int val) 插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。如果键 key 已经存在，那么原来的键值对将被替代成新的键值对。
 * int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和。
 *  
 *
 * 示例：
 *
 * 输入：
 * inputs = ["MapSum", "insert", "sum", "insert", "sum"]
 * inputs = [[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
 * 输出：
 * [null, null, 3, null, 5]
 *
 * 解释：
 * MapSum mapSum = new MapSum();
 * mapSum.insert("apple", 3);
 * mapSum.sum("ap");           // return 3 (apple = 3)
 * mapSum.insert("app", 2);
 * mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
 *  
 *
 * 提示：
 *
 * 1 <= key.length, prefix.length <= 50
 * key 和 prefix 仅由小写英文字母组成
 * 1 <= val <= 1000
 * 最多调用 50 次 insert 和 sum
 *  
 *
 * 注意：本题与主站 677 题相同： https://leetcode-cn.com/problems/map-sum-pairs/
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/z1R5dt
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class MapSum {

    private DictionaryNode root;

    /** Initialize your data structure here. */
    public MapSum() {
        this.root = new DictionaryNode();
    }

    public void insert(String key, int val) {
        if (null == key) {
            return;
        }
        // 对key 的每个字母 找到对应数的节点 如果么有就创建 如果找到最终 设置value
        DictionaryNode current = this.root;
        for (char ch : key.toCharArray()) {
            int index = (ch - 'a');
            // 如果当前字母 还没有节点 直接设置
            if (current.next[index] == null) {
                current.next[index] = new DictionaryNode();
            }
            current = current.next[index];
        }
        // 找到最终节点
        current.isEnd = true;
        current.value = val;
    }

    /**
     * 对 prefix 找到 对应的最终节点 遍历这个节点获取其所有的值的和
     * @param prefix
     * @return
     */
    public int sum(String prefix) {
        if (null == prefix) {
            return -1;
        }
        DictionaryNode current = this.root;
        for (char ch : prefix.toCharArray()) {
            int index = (ch - 'a');
            // 如果当前字母 还没有节点 直接设置
            if (current.next[index] == null) {
                current.next[index] = new DictionaryNode();
            }
            current = current.next[index];
        }
        // 遍历 current 找到其中的值 递归找
        int result = getSum(current);
        return result;
    }

    private int getSum(DictionaryNode current) {
        if (null == current) {
            return 0;
        }
        int result = current.value;
        DictionaryNode[] next = current.next;
        for (DictionaryNode child : next) {
            result += getSum(child);
        }
        return result;
    }

    // 定义字典树节点
    class DictionaryNode {

        /**
         * 下一个节点
         */
        public DictionaryNode[] next;
        /**
         * 有可能没用
         */
        public boolean isEnd;

        /**
         * 当前前缀的value
         */
        public int value;

        public DictionaryNode() {
            this.next = new DictionaryNode[26];
        }
    }

}
