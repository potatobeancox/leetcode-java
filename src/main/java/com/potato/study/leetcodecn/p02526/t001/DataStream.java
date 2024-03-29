package com.potato.study.leetcodecn.p02526.t001;

import org.junit.Assert;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 2526. 找到数据流中的连续整数
 *
 * 给你一个整数数据流，请你实现一个数据结构，检查数据流中最后 k 个整数是否 等于 给定值 value 。
 *
 * 请你实现 DataStream 类：
 *
 * DataStream(int value, int k) 用两个整数 value 和 k 初始化一个空的整数数据流。
 * boolean consec(int num) 将 num 添加到整数数据流。如果后 k 个整数都等于 value ，返回 true ，否则返回 false 。如果少于 k 个整数，条件不满足，所以也返回 false 。
 *  
 *
 * 示例 1：
 *
 * 输入：
 * ["DataStream", "consec", "consec", "consec", "consec"]
 * [[4, 3], [4], [4], [4], [3]]
 * 输出：
 * [null, false, false, true, false]
 *
 * 解释：
 * DataStream dataStream = new DataStream(4, 3); // value = 4, k = 3
 * dataStream.consec(4); // 数据流中只有 1 个整数，所以返回 False 。
 * dataStream.consec(4); // 数据流中只有 2 个整数
 *                       // 由于 2 小于 k ，返回 False 。
 * dataStream.consec(4); // 数据流最后 3 个整数都等于 value， 所以返回 True 。
 * dataStream.consec(3); // 最后 k 个整数分别是 [4,4,3] 。
 *                       // 由于 3 不等于 value ，返回 False 。
 *  
 *
 * 提示：
 *
 * 1 <= value, num <= 109
 * 1 <= k <= 105
 * 至多调用 consec 次数为 105 次。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-consecutive-integers-from-a-data-stream
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class DataStream {

    private int k;
    private int lastDiffIndex;
    private int count;
    private int value;


    public DataStream(int value, int k) {
        // 双端队列 保存 k个
        this.lastDiffIndex = -1;
        this.k = k;
        this.count = 0;
        this.value = value;
    }

    public boolean consec(int num) {
        count++;
        if (num != value) {
            lastDiffIndex = count - 1;
        } else {
            int i = count - lastDiffIndex - 1;
            if (i >= k && count >= k) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        DataStream dataStream = new DataStream(1, 2);
        // [1],[2],[1],[1],[1]]
        boolean consec = dataStream.consec(1);
        System.out.println(consec);
        Assert.assertEquals(false, consec);
        consec = dataStream.consec(2);
        System.out.println(consec);
        Assert.assertEquals(false, consec);
        consec = dataStream.consec(1);
        System.out.println(consec);
        Assert.assertEquals(false, consec);
        consec = dataStream.consec(1);
        System.out.println(consec);
        Assert.assertEquals(true, consec);
        consec = dataStream.consec(1);
        System.out.println(consec);
        Assert.assertEquals(true, consec);
    }
}
