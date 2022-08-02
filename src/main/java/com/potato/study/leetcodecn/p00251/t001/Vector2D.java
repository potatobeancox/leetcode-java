package com.potato.study.leetcodecn.p00251.t001;

import java.util.Map;
import java.util.TreeMap;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 251. 展开二维向量
 *
 * 请设计并实现一个能够展开二维向量的迭代器。该迭代器需要支持 next 和 hasNext 两种操作。
 *
 *  
 *
 * 示例：
 *
 * Vector2D iterator = new Vector2D([[1,2],[3],[4]]);
 *
 * iterator.next(); // 返回 1
 * iterator.next(); // 返回 2
 * iterator.next(); // 返回 3
 * iterator.hasNext(); // 返回 true
 * iterator.hasNext(); // 返回 true
 * iterator.next(); // 返回 4
 * iterator.hasNext(); // 返回 false
 *  
 *
 * 注意：
 *
 * 请记得 重置 在 Vector2D 中声明的类变量（静态变量），因为类变量会 在多个测试用例中保持不变，影响判题准确。请 查阅 这里。
 * 你可以假定 next() 的调用总是合法的，即当 next() 被调用时，二维向量总是存在至少一个后续元素。
 *  
 *
 * 进阶：尝试在代码中仅使用 C++ 提供的迭代器 或 Java 提供的迭代器。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/flatten-2d-vector
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Vector2D {

    private int i;
    private int j;
    private int[][] vec;

    public Vector2D(int[][] vec) {
        this.vec = vec;
        this.i = 0;
        while (i < vec.length && vec[i].length == 0) {
            i++;
        }
        this.j = 0;
    }

    public int next() {
        int val = vec[this.i][j];
        j++;
        if (j >= vec[i].length) {
            j = 0;
            i++;
            while (i < vec.length && vec[i].length == 0) {
                i++;
            }
        }
        return val;
    }

    public boolean hasNext() {
        return i < vec.length && j < vec[i].length;
    }

    public static void main(String[] args) {
        String input = "[[1,2],[3],[4]]";
        int[][] vec = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        Vector2D vector2D = new Vector2D(vec);

        vector2D.next();
        vector2D.next();
        System.out.println(vector2D.next());// 3

    }
}
