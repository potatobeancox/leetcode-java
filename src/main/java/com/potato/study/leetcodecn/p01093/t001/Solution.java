package com.potato.study.leetcodecn.p01093.t001;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 1093. 大样本统计
 *
 * 我们对 0 到 255 之间的整数进行采样，并将结果存储在数组 count 中：count[k] 就是整数 k 在样本中出现的次数。
 *
 * 计算以下统计数据:
 *
 * minimum ：样本中的最小元素。
 * maximum ：样品中的最大元素。
 * mean ：样本的平均值，计算为所有元素的总和除以元素总数。
 * median ：
 * 如果样本的元素个数是奇数，那么一旦样本排序后，中位数 median 就是中间的元素。
 * 如果样本中有偶数个元素，那么中位数median 就是样本排序后中间两个元素的平均值。
 * mode ：样本中出现次数最多的数字。保众数是 唯一 的。
 * 以浮点数数组的形式返回样本的统计信息 [minimum, maximum, mean, median, mode] 。与真实答案误差在 10-5 内的答案都可以通过。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：count = [0,1,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
 * 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
 * 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
 * 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
 * 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
 * 输出：[1.00000,3.00000,2.37500,2.50000,3.00000]
 * 解释：用count表示的样本为[1,2,2,2,3,3,3,3,3]。
 * 最小值和最大值分别为1和3。
 * 均值是(1+2+2+2+3+3+3+3) / 8 = 19 / 8 = 2.375。
 * 因为样本的大小是偶数，所以中位数是中间两个元素2和3的平均值，也就是2.5。
 * 众数为3，因为它在样本中出现的次数最多。
 * 示例 2：
 *
 * 输入：count = [0,4,3,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
 * 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
 * 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
 * 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
 * 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
 * 输出：[1.00000,4.00000,2.18182,2.00000,1.00000]
 * 解释：用count表示的样本为[1,1,1,1,2,2,3,3,3,4,4]。
 * 最小值为1，最大值为4。
 * 平均数是(1+1+1+1+2+2+2+3+3+4+4)/ 11 = 24 / 11 = 2.18181818…(为了显示，输出显示了整数2.18182)。
 * 因为样本的大小是奇数，所以中值是中间元素2。
 * 众数为1，因为它在样本中出现的次数最多。
 *  
 *
 * 提示：
 *
 * count.length == 256
 * 0 <= count[i] <= 109
 * 1 <= sum(count) <= 109
 *  count 的众数是 唯一 的
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/statistics-from-a-large-sample
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public double[] sampleStats(int[] count) {
        double minimum = -1;
        double maximum = -1;
        // 出现最多的数字
        double mode  = -1;

        double sum = 0;
        int num = 0;
        long[] sumCount = new long[count.length];
        for (int i = 0; i < 256; i++) {
            if (i == 0) {
                sumCount[i] = count[i];
            } else {
                sumCount[i] = sumCount[i-1] + count[i];
            }
            if (count[i] == 0) {
                continue;
            }
            // 有统计次数
            if (minimum == -1) {
                minimum = i;
            }
            // 最大值 每次更新
            maximum = i;
            // 出现次数最多的数字
            if (mode == -1 || count[(int)mode] < count[i]) {
                mode = i;
            }
            sum += 1.0 * i * count[i];
            num += count[i];
        }
        // 平均值
        double mean = sum / num;
        // 知道 num 找到 中位数 是第几个
        int target = (num + 1) / 2;
        int ans = getTargetIndex(count, sumCount, target);
        // 中位数
        double median = -1;
        if (ans != -1) {
            median = ans;
        }

        if (num % 2 == 0) {
            ans = getTargetIndex(count, sumCount, target+1);
            median += ans;
            median /= 2;
        }

        return new double[] {
                minimum, maximum, mean, median, mode
        };
    }

    private int getTargetIndex(int[] count, long[] sumCount, int target) {
        int left = 0;
        int right = count.length - 1;
        int ans = -1;
        while (left <= right) {
            int midIndex = (left + right) / 2;
            if (sumCount[midIndex] < target) {
                //  target 在后边
                left = midIndex + 1;
            } else {
                // sumCount[midIndex] >= target
                ans = midIndex;
                right = midIndex - 1;
            }
        }
        return ans;
    }
}
