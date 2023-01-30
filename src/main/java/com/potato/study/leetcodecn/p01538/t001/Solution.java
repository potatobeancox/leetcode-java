package com.potato.study.leetcodecn.p01538.t001;

/**
 * 1538. 找出隐藏数组中出现次数最多的元素
 *
 * 给定一个整数数组 nums，且 nums 中的所有整数都为 0 或 1。你不能直接访问这个数组，你需要使用 API ArrayReader ，该 API 含有下列成员函数：

 int query(int a, int b, int c, int d)：其中 0 <= a < b < c < d < ArrayReader.length() 。此函数查询以这四个参数为下标的元素并返回：
 4 : 当这四个元素相同（0 或 1）时。
 2 : 当其中三个元素的值等于 0 且一个元素等于 1 时，或当其中三个元素的值等于 1 且一个元素等于 0 时。
 0 : 当其中两个元素等于 0 且两个元素等于 1 时。
 int length()：返回数组的长度。
 你可以调用 query() 最多 2 * n 次，其中 n 等于 ArrayReader.length()。

 返回 nums 中出现次数最多的值的任意索引，若所有的值出现次数均相同，返回 -1。

  

 示例 1：

 输入: nums = [0,0,1,0,1,1,1,1]
 输出: 5
 解释: API 的调用情况如下：
 reader.length() // 返回 8，因为隐藏数组中有 8 个元素。
 reader.query(0,1,2,3) // 返回 2，查询元素 nums[0], nums[1], nums[2], nums[3] 间的比较。
 // 三个元素等于 0 且一个元素等于 1 或出现相反情况。
 reader.query(4,5,6,7) // 返回 4，因为 nums[4], nums[5], nums[6], nums[7] 有相同值。
 我们可以推断，最常出现的值在最后 4 个元素中。
 索引 2, 4, 6, 7 也是正确答案。
 示例 2:

 输入: nums = [0,0,1,1,0]
 输出: 0
 示例 3:

 输入: nums = [1,0,1,0,1,0,1,0]
 输出: -1
  

 提示:

 5 <= nums.length <= 10^5
 0 <= nums[i] <= 1
  

 进阶：要找到出现次数最多的元素，需要至少调用 query() 多少次？

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/guess-the-majority-in-a-hidden-array
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode.cn/problems/guess-the-majority-in-a-hidden-array/solution/cpython3java-1bian-li-on-by-hanxin_hanxi-o3as/
     * @param reader
     * @return
     */
    public int guessMajority(ArrayReader reader) {
        // 0123 获取多少判断
        int res3 = reader.query(0, 1, 2, 3);
        // 从 4开始到 n 看看 有多少个根3 一致的数字
        int n = reader.length();
        // 根3 一样的个数
        int count3 = 1;
        int otherCount = 0;
        int otherNum = 0;
        boolean is3Diff4 =  false;
        for (int i = 4; i < n; i++) {
            int res = reader.query(0, 1, 2, i);
            if (res == res3) {
                count3++;
            } else {
                // 如果不一样怎么办 计数么
                otherCount++;
                otherNum = i;
                if (i == 4) {
                    is3Diff4 = true;
                }
            }
        }
        // 统计 0123  0和4一样 处理0
        if (reader.query( 1, 2, 3, 4) == res3) {
            // 3 和4 不同 0和3不一样
            if (is3Diff4) {
                otherCount++;
            } else {
                // 34 一样
                count3++;
            }
        } else {
            if (is3Diff4) {
                count3++;
            } else {
                otherCount++;
            }
        }
        // 处理 1 0123   1和4 一样
        if (reader.query(0,  2, 3, 4) == res3) {
            // 3 和4 不同 1和3不一样
            if (is3Diff4) {
                otherCount++;
            } else {
                // 34 一样
                count3++;
            }
        } else {
            if (is3Diff4) {
                count3++;
            } else {
                otherCount++;
            }
        }
        // 处理 2
        if (reader.query(0, 1,  3, 4) == res3) {
            if (is3Diff4) {
                otherCount++;
            } else {
                // 34 一样
                count3++;
            }
        } else {
            if (is3Diff4) {
                count3++;
            } else {
                otherCount++;
            }
        }

        if (count3 > n/2) {
            return 3;
        } else {
            // 另一个根3不一样 那么不是 0就是 1
            if (otherCount > n / 2) {
                return otherNum;
            } else {
                return -1;
            }
        }
    }

}

interface ArrayReader {

    int query(int a, int b, int c, int d);
    int length();

}
