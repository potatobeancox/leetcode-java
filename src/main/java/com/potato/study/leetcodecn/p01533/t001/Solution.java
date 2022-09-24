package com.potato.study.leetcodecn.p01533.t001;

import com.potato.study.leetcode.domain.TreeNode;
import org.junit.Assert;

/**
 * 1533. 找到最大整数的索引
 *
 * 我们有这样一个整数数组 arr ，除了一个最大的整数外，其他所有整数都相等。你不能直接访问该数组，你需要通过 API ArrayReader 来间接访问，这个 API 有以下成员函数：
 *
 * int compareSub(int l, int r, int x, int y)：其中 0 <= l, r, x, y < ArrayReader.length()， l <= r 且 x <= y。这个函数比较子数组 arr[l..r] 与子数组 arr[x..y] 的和。该函数返回：
 * 1 若 arr[l]+arr[l+1]+...+arr[r] > arr[x]+arr[x+1]+...+arr[y] 。
 * 0 若 arr[l]+arr[l+1]+...+arr[r] == arr[x]+arr[x+1]+...+arr[y] 。
 * -1 若 arr[l]+arr[l+1]+...+arr[r] < arr[x]+arr[x+1]+...+arr[y] 。
 * int length()：返回数组的长度。
 * 你最多可以调用函数 compareSub() 20 次。你可以认为这两个函数的时间复杂度都为 O(1) 。
 *
 * 返回 arr 中最大整数的索引。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: arr = [7,7,7,7,10,7,7,7]
 * 输出: 4
 * 解释: API 的调用如下：
 * reader.compareSub(0, 0, 1, 1) // 返回 0。比较子数组 (0, 0) 与子数组 (1, 1) （即比较 arr[0] 和 arr[1]）。
 * 因此我们知道 arr[0] 和 arr[1] 不包含最大元素。
 * reader.compareSub(2, 2, 3, 3) // 返回 0。我们可以排除 arr[2] 和 arr[3]。
 * reader.compareSub(4, 4, 5, 5) // 返回 1。因此，可以确定 arr[4] 是数组中最大的元素。
 * 注意，我们只调用了 3 次 compareSub，所以这个答案是有效的。
 * 示例 2:
 *
 * 输入: nums = [6,6,12]
 * 输出: 2
 *  
 *
 * 提示:
 *
 * 2 <= arr.length <= 5 * 10^5
 * 1 <= arr[i] <= 100
 * arr 中除一个最大元素外，其余所有元素都相等。
 *  
 *
 * 进阶
 *
 * 如果 arr 中有两个整数比其他数大呢？
 * 如果有一个数比其他数大，另一个数比其他数小呢？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-the-index-of-the-large-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int getIndex(ArrayReader reader) {
        // 其实就是二分法
        int length = reader.length();
        int left1 = 0;
        int left2 = length / 2 - 1;

        int mid = length / 2;
        int right1 = length / 2;
        if (length % 2 == 1) {
            right1 += 1;
        }
        int right2 = length - 1;

        // 比较 平均值
        while (left1 != right1 || left2 != right2) {
            int compare = reader.compareSub(left1, left2, right1, right2);
            if (compare > 0) {
                // 往左走
                if (left1 == left2) {
                    return left1;
                }
                // 不是缩小区间
                right2 = left2;
                int len = right2 - left1 + 1;
                left2 = left1 + len / 2 - 1;
                right1 = left1 + len / 2;

                if (len % 2 == 1) {
                    mid = right1;
                    right1++;
                }

            } else if (compare < 0){
                 // right
                if (right1 == right2) {
                    return right1;
                }
                left1 = right1;
                int len = right2 - left1 + 1;
                left2 = left1 + len / 2 - 1;
                right1 = left1 + len / 2;

                if (len % 2 == 1) {
                    mid = right1;
                    right1++;
                }

            } else {
                return mid;
            }
        }
        return left1;
    }
}

interface ArrayReader {
    // Compares the sum of arr[l..r] with the sum of arr[x..y]
    // return 1 if sum(arr[l..r]) > sum(arr[x..y])
    // return 0 if sum(arr[l..r]) == sum(arr[x..y])
    // return -1 if sum(arr[l..r]) < sum(arr[x..y])
    public int compareSub(int l, int r, int x, int y);

    // Returns the length of the array
    public int length();
}
