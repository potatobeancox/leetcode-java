package com.potato.study.leetcodecn.p00281.t001;


import java.util.ArrayList;
import java.util.List;

/**
 * 281. 锯齿迭代器
 *
 * 给出两个一维的向量，请你实现一个迭代器，交替返回它们中间的元素。

 示例:

 输入:
 v1 = [1,2]
 v2 = [3,4,5,6]

 输出: [1,3,2,4,5,6]

 解析: 通过连续调用 next 函数直到 hasNext 函数返回 false，
      next 函数返回值的次序应依次为: [1,3,2,4,5,6]。
 拓展：假如给你 k 个一维向量呢？你的代码在这种情况下的扩展性又会如何呢?

 拓展声明：
  “锯齿” 顺序对于 k > 2 的情况定义可能会有些歧义。所以，假如你觉得 “锯齿” 这个表述不妥，也可以认为这是一种 “循环”。例如：

 输入:
 [1,2,3]
 [4,5,6,7]
 [8,9]

 输出: [1,4,8,2,5,9,3,6,7].

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/zigzag-iterator
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class ZigzagIterator {

    private int index1;
    private int index2;

    private List<Integer> v1;
    private List<Integer> v2;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public int next() {
        // 如果当前v1到头了
        if (index1 == v1.size()) {
            return this.v2.get(index2++);
        }
        if (index2 == v2.size()) {
            return this.v1.get(index1++);
        }
        // 都没有到 最后 判定当前index1 是否大于index2
        if (index1 == index2) {
            return this.v1.get(index1++);
        } else {
            // index1 > index2
            return this.v2.get(index2++);
        }
    }

    public boolean hasNext() {
        return index1 < v1.size() || index2 < v2.size();
    }
}
