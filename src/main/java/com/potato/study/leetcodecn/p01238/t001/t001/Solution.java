package com.potato.study.leetcodecn.p01238.t001.t001;


import java.util.ArrayList;
import java.util.List;

/**
 * 1238. 循环码排列
 *
 * 给你两个整数 n 和 start。你的任务是返回任意 (0,1,2,,...,2^n-1) 的排列 p，并且满足：

 p[0] = start
 p[i] 和 p[i+1] 的二进制表示形式只有一位不同
 p[0] 和 p[2^n -1] 的二进制表示形式也只有一位不同
  

 示例 1：

 输入：n = 2, start = 3
 输出：[3,2,0,1]
 解释：这个排列的二进制表示是 (11,10,00,01)
 所有的相邻元素都有一位是不同的，另一个有效的排列是 [3,1,0,2]
 示例 2：

 输出：n = 3, start = 2
 输出：[2,6,7,5,4,0,1,3]
 解释：这个排列的二进制表示是 (010,110,111,101,100,000,001,011)
  

 提示：

 1 <= n <= 16
 0 <= start < 2^n

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/circular-permutation-in-binary-representation
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode-cn.com/problems/circular-permutation-in-binary-representation/
     * 格雷码 转换方法
     * @param n
     * @param start
     * @return
     */
    public List<Integer> circularPermutation(int n, int start) {
        // 1 生成 n位 从 0 - n-1  的二进制字符串边在最开始增加0
        List<String> binaryStringList = new ArrayList<>();
        for (int i = 0; i < (1<<n); i++) {
            int tmp = i;
            StringBuilder numBuiler = new StringBuilder();
            for (int j = 0; j < n; j++) {
                numBuiler.append(tmp & 1);
                tmp >>>= 1;
            }
            binaryStringList.add(numBuiler.reverse().toString());
        }
        // 2 对每个位置的生成格雷码， 相邻位置 相同为 0 不相同为 1
        List<String> grayCodeList = new ArrayList<>();
        for (int i = 0; i < binaryStringList.size(); i++) {
            grayCodeList.add(getGrayCode(binaryStringList.get(i)));
        }
        // 3. 从 start位置开始遍历 找一圈 生成结果集
        List<Integer> list = new ArrayList<>();

        // 找到 startIndex
        int startIndex = -1;
        for (int i = 0; i < (1<<n); i++) {
            if (start == Integer.valueOf(grayCodeList.get(i), 2)) {
                startIndex = i;
            }
        }

        for (int i = 0; i < (1<<n); i++) {
            int index = (startIndex + i) % (1<<n);
            list.add(Integer.valueOf(grayCodeList.get(index), 2));
        }
        return list;
    }

    /**
     * 生成graycode
     * @param word
     * @return
     */
    private String getGrayCode(String word) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            if (i == 0) {
                if (word.charAt(i) == '0') {
                    builder.append("0");
                } else {
                    builder.append("1");
                }
            } else {
                if (word.charAt(i) == word.charAt(i-1)) {
                    builder.append("0");
                } else {
                    builder.append("1");
                }
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 2;
        int start = 3;
        List<Integer> list = solution.circularPermutation(n, start);
        System.out.println(list);
    }


}

