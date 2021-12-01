package com.potato.study.leetcodecn.p01850.t001;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * 1850. 邻位交换的最小次数
 *
 * 给你一个表示大整数的字符串 num ，和一个整数 k 。

 如果某个整数是 num 中各位数字的一个 排列 且它的 值大于 num ，则称这个整数为 妙数 。可能存在很多妙数，但是只需要关注 值最小 的那些。

 例如，num = "5489355142" ：
 第 1 个最小妙数是 "5489355214"
 第 2 个最小妙数是 "5489355241"
 第 3 个最小妙数是 "5489355412"
 第 4 个最小妙数是 "5489355421"
 返回要得到第 k 个 最小妙数 需要对 num 执行的 相邻位数字交换的最小次数 。

 测试用例是按存在第 k 个最小妙数而生成的。

  

 示例 1：

 输入：num = "5489355142", k = 4
 输出：2
 解释：第 4 个最小妙数是 "5489355421" ，要想得到这个数字：
 - 交换下标 7 和下标 8 对应的位："5489355142" -> "5489355412"
 - 交换下标 8 和下标 9 对应的位："5489355412" -> "5489355421"
 示例 2：

 输入：num = "11112", k = 4
 输出：4
 解释：第 4 个最小妙数是 "21111" ，要想得到这个数字：
 - 交换下标 3 和下标 4 对应的位："11112" -> "11121"
 - 交换下标 2 和下标 3 对应的位："11121" -> "11211"
 - 交换下标 1 和下标 2 对应的位："11211" -> "12111"
 - 交换下标 0 和下标 1 对应的位："12111" -> "21111"
 示例 3：

 输入：num = "00123", k = 1
 输出：1
 解释：第 1 个最小妙数是 "00132" ，要想得到这个数字：
 - 交换下标 3 和下标 4 对应的位："00123" -> "00132"
  

 提示：

 2 <= num.length <= 1000
 1 <= k <= 1000
 num 仅由数字组成

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/minimum-adjacent-swaps-to-reach-the-kth-smallest-number
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode-cn.com/problems/minimum-adjacent-swaps-to-reach-the-kth-smallest-number/solution/lin-wei-jiao-huan-de-zui-xiao-ci-shu-by-xerp9/
     * @param num
     * @param k
     * @return
     */
    public int getMinSwaps(String num, int k) {
        // 找 第k个比 num大的数字
        String target = num;
        for (int i = 0; i < k; i++) {
            target = getNextNum(target);
        }
        // 从后往前交换 直到交换到
        int step = 0;
        char[] chars1 = num.toCharArray();
        char[] chars2 = target.toCharArray();
        for (int i = num.length() - 1; i >= 0; i--) {
            if (chars1[i] == chars2[i]) {
                continue;
            }
            int j = i - 1;
            while (j >= 1 && chars1[j] != chars2[i]) {
                swap(j, j-1, chars1);
                step++;
                j--;
            }
        }
        return step;
    }

    private String getNextNum(String num) {
        // 从后往前 找到 第一个 升序位置 严格 记录 第一个位置 找比这个位置大的最小的数字，放在当前位置，然后去其余位置排序
        int n = num.length();
        int index = -1;
        char[] chars = num.toCharArray();
        for (int i = n-2; i >= 0; i--) {
            if (chars[i] < chars[i + 1]) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return "";
        }
        int minIndex = index + 1;
        for (int i = index + 2; i < num.length(); i++) {
            if (chars[i] > chars[index] && chars[i] < chars[minIndex]) {
                minIndex = i;
            }
        }
        // 最小的 和 index 交换
        swap(minIndex, index, chars);
        // 对 index + 1 之后进行排序
        Arrays.sort(chars, index + 1, num.length());
        return new String(chars);
    }

    private void swap(int index1, int index2, char[] chars) {
        char tmp = chars[index1];
        chars[index1] = chars[index2];
        chars[index2] = tmp;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String num = "5489355142";
        int k = 4;
        int minSwaps = solution.getMinSwaps(num, k);
        System.out.println(minSwaps);
        Assert.assertEquals(2, minSwaps);
    }
}
