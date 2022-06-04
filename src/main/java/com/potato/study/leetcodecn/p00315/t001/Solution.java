package com.potato.study.leetcodecn.p00315.t001;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 315. 计算右侧小于当前元素的个数
 *
 * 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。

  

 示例：

 输入：nums = [5,2,6,1]
 输出：[2,1,1,0]
 解释：
 5 的右侧有 2 个更小的元素 (2 和 1)
 2 的右侧仅有 1 个更小的元素 (1)
 6 的右侧有 1 个更小的元素 (1)
 1 的右侧有 0 个更小的元素
  

 提示：

 0 <= nums.length <= 10^5
 -10^4 <= nums[i] <= 10^4

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode.cn/problems/count-of-smaller-numbers-after-self/solution/gui-bing-pai-xu-suo-yin-shu-zu-python-dai-ma-java-/
     * @param nums
     * @return
     */
    public List<Integer> countSmaller(int[] nums) {
        // 归并排序
        int n = nums.length;
        // 存index
        int[] indexArray = new int[n];
        for (int i = 0; i < n; i++) {
            indexArray[i] = i;
        }
        // 存最终结果
        int[] count = new int[n];
        // 将处理之前的数组 缓存下来 节省空间
        int[] tempIndexArray = new int[indexArray.length];

        // 对某个区间进行归并
        mergeAndSort(nums, indexArray, 0, n-1, count, tempIndexArray);
        List<Integer> result = new ArrayList<>();
        // 处理结果
        for (int i = 0; i < n; i++) {
            result.add(count[i]);
        }
        return result;

    }

    /**
     * 归并排序 left 到 right 的位置
     * @param nums
     * @param indexArray
     * @param left
     * @param right
     * @param count
     */
    private void mergeAndSort(int[] nums, int[] indexArray, int left, int right, int[] count, int[] tempIndexArray) {
        // 已经有序
        if (left == right) {
            return;
        }
        // 往内部 递归归并
        int mid = (left + right) / 2;
        mergeAndSort(nums, indexArray, left, mid, count, tempIndexArray);
        mergeAndSort(nums, indexArray, mid+1, right, count, tempIndexArray);

        // 如果索引数据 有序 说明 不需要进一步合并 因为 到这 本身 两个部分已经排序完毕
        if (nums[indexArray[mid]] <= nums[indexArray[mid+1]]) {
            return;
        }

        // 合并归并结果
        merge(nums, indexArray, left, mid, right, count, tempIndexArray);
    }

    /**
     * 合并 [left, mid], [mid+1, right] 排序 并 找到 小于 每个位置 的 值
     * @param nums
     * @param indexArray
     * @param left
     * @param mid
     * @param right
     * @param count
     */
    private void merge(int[] nums, int[] indexArray, int left, int mid, int right, int[] count, int[] tempIndexArray) {
        for (int i = left; i <= right; i++) {
            tempIndexArray[i] = indexArray[i];
        }
        // 两个数组开始
        int i = left;
        int j = mid + 1;

        // 枚举每个 位置 找最小的放上面
        for (int k = left; k <= right; k++) {
            if (i > mid) {
                // 第一个数组 已经用完
                indexArray[k] = tempIndexArray[j];
                j++;
            } else if (j > right) {
                // 第二个数组已经用完
                indexArray[k] = tempIndexArray[i];
                // i 应该在 j前面用 当时 j先用完了 说明 j之前的都比当前i小
                count[tempIndexArray[i]] += (right - mid);
                i++;
            } else {
                // 两个数组 都还有 找最小的 保证顺序
                if (nums[tempIndexArray[i]] <= nums[tempIndexArray[j]]) {
                    // 第二个数组已经用完
                    indexArray[k] = tempIndexArray[i];
                    // i 应该在 j前面用 当时 j先用完了 说明 j之前的都比当前i小
                    count[tempIndexArray[i]] += (j-1 - mid);
                    i++;
                } else {
                    //  nums[tempIndexArray[i]] > nums[tempIndexArray[j]]
                    indexArray[k] = tempIndexArray[j];
                    j++;
                }
            }
        }
    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        int[] arr = new int[] {
                5,2,6,1
        };
        // 2,1,1,0
        List<Integer> list = solution.countSmaller(arr);
        System.out.println(list);


        arr = new int[] {
                -1
        };
        // 0
        list = solution.countSmaller(arr);
        System.out.println(list);


        arr = new int[] {
                -1 , -1
        };
        // 0, 0
        list = solution.countSmaller(arr);
        System.out.println(list);
    }
}
