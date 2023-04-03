package com.potato.study.leetcodecn.other.Interview.p0010.p0010;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 面试题 10.10. 数字流的秩
 *
 * 假设你正在读取一串整数。每隔一段时间，你希望能找出数字 x 的秩(小于或等于 x 的值的个数)。请实现数据结构和算法来支持这些操作，也就是说：
 *
 * 实现 track(int x) 方法，每读入一个数字都会调用该方法；
 *
 * 实现 getRankOfNumber(int x) 方法，返回小于或等于 x 的值的个数。
 *
 * 注意：本题相对原题稍作改动
 *
 * 示例:
 *
 * 输入:
 * ["StreamRank", "getRankOfNumber", "track", "getRankOfNumber"]
 * [[], [1], [0], [0]]
 * 输出:
 * [null,0,null,1]
 * 提示：
 *
 * x <= 50000
 * track 和 getRankOfNumber 方法的调用次数均不超过 2000 次
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/rank-from-stream-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class StreamRank {

    private List<Integer> list;

    public StreamRank() {
        this.list = new ArrayList<>();
    }


    /**
     * 将x 按照顺序 track
     * @param x
     */
    public void track(int x) {
        // 小于等于 x的数的个数
        int rankOfNumber = getRankOfNumber(x);
        // x 放在 rankOfNumber 位置
        list.add(rankOfNumber, x);
    }


    /**
     * 小于等于 x的个数
     * @param x
     * @return
     */
    public int getRankOfNumber(int x) {
        if (list.size() == 0) {
            return 0;
        }
        int left = 0;
        int right = list.size() - 1;
        // 最后一个小于等于 的节点位置
        int res = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (list.get(mid) <= x) {
                res = mid;
                left = mid + 1;
            } else {
                // 当前终点 大于 x
                right = mid - 1;
            }
        }
        return res + 1;
    }
}
