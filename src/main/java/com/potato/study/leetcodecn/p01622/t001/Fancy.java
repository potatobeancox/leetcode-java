package com.potato.study.leetcodecn.p01622.t001;

import java.util.ArrayList;
import java.util.List;

/**
 * 1622. 奇妙序列
 *
 * 请你实现三个 API append，addAll 和 multAll 来实现奇妙序列。
 *
 * 请实现 Fancy 类 ：
 *
 * Fancy() 初始化一个空序列对象。
 * void append(val) 将整数 val 添加在序列末尾。
 * void addAll(inc) 将所有序列中的现有数值都增加 inc 。
 * void multAll(m) 将序列中的所有现有数值都乘以整数 m 。
 * int getIndex(idx) 得到下标为 idx 处的数值（下标从 0 开始），并将结果对 109 + 7 取余。如果下标大于等于序列的长度，请返回 -1 。
 *  
 *
 * 示例：
 *
 * 输入：
 * ["Fancy", "append", "addAll", "append", "multAll", "getIndex", "addAll", "append", "multAll", "getIndex",
 * "getIndex", "getIndex"]
 * [[], [2], [3], [7], [2], [0], [3], [10], [2], [0], [1], [2]]
 * 输出：
 * [null, null, null, null, null, 10, null, null, null, 26, 34, 20]
 *
 * 解释：
 * Fancy fancy = new Fancy();
 * fancy.append(2);   // 奇妙序列：[2]
 * fancy.addAll(3);   // 奇妙序列：[2+3] -> [5]
 * fancy.append(7);   // 奇妙序列：[5, 7]
 * fancy.multAll(2);  // 奇妙序列：[5*2, 7*2] -> [10, 14]
 * fancy.getIndex(0); // 返回 10
 * fancy.addAll(3);   // 奇妙序列：[10+3, 14+3] -> [13, 17]
 * fancy.append(10);  // 奇妙序列：[13, 17, 10]
 * fancy.multAll(2);  // 奇妙序列：[13*2, 17*2, 10*2] -> [26, 34, 20]
 * fancy.getIndex(0); // 返回 26
 * fancy.getIndex(1); // 返回 34
 * fancy.getIndex(2); // 返回 20
 *  
 *
 * 提示：
 *
 * 1 <= val, inc, m <= 100
 * 0 <= idx <= 105
 * 总共最多会有 105 次对 append，addAll，multAll 和 getIndex 的调用。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fancy-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Fancy {
    // https://leetcode-cn.com/problems/fancy-sequence/solution/1622qi-miao-xu-lie-xi-shu-he-bing-cheng-z6oe2/
    private int multiCoefficient;
    private int plusCoefficient;
    private List<Integer> element;
    private int mod;

    // 初始化
    public Fancy() {
        this.plusCoefficient = 0;
        this.multiCoefficient = 1;
        this.element = new ArrayList<>();
        this.mod = 1_000_000_000 + 7;
    }

    // 往list 中添加数据
    public void append(int val) {
        // 计算原来的数字
        int baseElement = (((val - plusCoefficient)) / (multiCoefficient)) % mod;
        element.add(baseElement);
    }

    public void addAll(int inc) {
        this.plusCoefficient += inc;
    }

    public void multAll(int m) {
        this.multiCoefficient = ((multiCoefficient) *  (m)) % mod;
        this.plusCoefficient = ((plusCoefficient) *  (m)) % mod;;
    }

    public int getIndex(int idx) {
        // get 时进行变换
        if (idx >= element.size()) {
            return -1;
        }
        Integer value = element.get(idx);
        return (((value) * (multiCoefficient))% mod + plusCoefficient % mod) % mod;
    }
}
