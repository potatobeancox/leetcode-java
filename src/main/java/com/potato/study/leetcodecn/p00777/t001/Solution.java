package com.potato.study.leetcodecn.p00777.t001;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

/**
 * 777. 在LR字符串中交换相邻字符
 *
 * 在一个由 'L' , 'R' 和 'X' 三个字符组成的字符串（例如"RXXLRXRXL"）中进行移动操作。一次移动操作指用一个"LX"替换一个"XL"，或者用一个"XR"替换一个"RX"。现给定起始字符串start
 * 和结束字符串end，请编写代码，当且仅当存在一系列移动操作使得start可以转换成end时， 返回True。
 *
 *  
 *
 * 示例 :
 *
 * 输入: start = "RXXLRXRXL", end = "XRLXXRRLX"
 * 输出: True
 * 解释:
 * 我们可以通过以下几步将start转换成end:
 * RXXLRXRXL ->
 * XRXLRXRXL ->
 * XRLXRXRXL ->
 * XRLXXRRXL ->
 * XRLXXRRLX
 *  
 *
 * 提示：
 *
 * 1 <= len(start) = len(end) <= 10000。
 * start和end中的字符串仅限于'L', 'R'和'X'。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/swap-adjacent-in-lr-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public boolean canTransform(String start, String end) {
        // 去掉 X 是否相同
        if (!start.replace("X", "").equals(end.replace("X", ""))) {
            return false;
        }

        List<Integer> startLeftIndexList = new ArrayList<>();
        List<Integer> endLeftIndexList = new ArrayList<>();

        List<Integer> startRightIndexList = new ArrayList<>();
        List<Integer> endRightIndexList = new ArrayList<>();

        for (int i = 0; i < start.length(); i++) {
            if (start.charAt(i) == 'L') {
                startLeftIndexList.add(i);
            } else if (start.charAt(i) == 'R') {
                startRightIndexList.add(i);
            }
        }

        for (int i = 0; i < end.length(); i++) {
            if (end.charAt(i) == 'L') {
                endLeftIndexList.add(i);
            } else if (end.charAt(i) == 'R') {
                endRightIndexList.add(i);
            }
        }
        // 对于每个 L 判断 是否 出现start 出现 位置是否先于 end
        for (int i = 0; i < startLeftIndexList.size(); i++) {
            if (startLeftIndexList.get(i) < endLeftIndexList.get(i)) {
                return false;
            }
        }

        // 对于每个 R 判断 是否 出现start 出现 位置是否晚于 end
        for (int i = 0; i < startRightIndexList.size(); i++) {
            if (startRightIndexList.get(i) > endRightIndexList.get(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String start = "RXXLRXRXL";
        String end = "XRLXXRRLX";
        boolean b = solution.canTransform(start, end);
        System.out.println(b);
        Assert.assertEquals(true, b);
    }

}
