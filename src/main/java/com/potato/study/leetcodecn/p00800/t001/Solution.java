package com.potato.study.leetcodecn.p00800.t001;

/**
 * 800. 相似 RGB 颜色
 *
 * GB 颜色 "#AABBCC" 可以简写成 "#ABC" 。
 *
 * 例如，"#15c" 其实是 "#1155cc" 的简写。
 * 现在，假如我们分别定义两个颜色 "#ABCDEF" 和 "#UVWXYZ"，则他们的相似度可以通过这个表达式 -(AB - UV)^2 - (CD - WX)^2 - (EF - YZ)^2 来计算。
 *
 * 那么给你一个按 "#ABCDEF" 形式定义的字符串 color 表示 RGB 颜色，请你以字符串形式，返回一个与它相似度最大且可以简写的颜色。（比如，可以表示成类似 "#XYZ" 的形式）
 *
 * 任何 具有相同的（最大）相似度的答案都会被视为正确答案。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：color = "#09f166"
 * 输出："#11ee66"
 * 解释：
 * 因为相似度计算得出 -(0x09 - 0x11)^2 -(0xf1 - 0xee)^2 - (0x66 - 0x66)^2 = -64 -9 -0 = -73
 * 这已经是所有可以简写的颜色中最相似的了
 * 示例 2：
 *
 * 输入：color = "#4e3fe1"
 * 输出："#5544dd"
 *  
 *
 * 提示：
 *
 * color.length == 7
 * color[0] == '#'
 * 对于任何 i > 0，color[i] 都是一个在范围 ['0', 'f'] 内的 16 进制数
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/similar-rgb-color
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public String similarRGB(String color) {
        String charstring = color.substring(1, 2);
        String numString = color.substring(1, 3);
        StringBuilder builder = new StringBuilder("#");
        int potentialNum = getBestPotentialNum(charstring, numString);
        if (0 != potentialNum) {
            builder.append(Integer.toHexString(potentialNum));
        } else {
            builder.append("00");
        }


        charstring = color.substring(3, 4);
        numString = color.substring(3, 5);
        potentialNum = getBestPotentialNum(charstring, numString);
        if (0 != potentialNum) {
            builder.append(Integer.toHexString(potentialNum));
        } else {
            builder.append("00");
        }


        charstring = color.substring(5, 6);
        numString = color.substring(5, 7);
        potentialNum = getBestPotentialNum(charstring, numString);
        if (0 != potentialNum) {
            builder.append(Integer.toHexString(potentialNum));
        } else {
            builder.append("00");
        }

        return builder.toString();
    }

    private int getBestPotentialNum(String charstring, String numString) {
        int originalCharNum = Integer.parseInt(charstring, 16);
        int potentialNum = getPotentialNum(originalCharNum);
        int originalNum = Integer.parseInt(numString, 16);
        // 当前数字 差值
        int diff = (potentialNum - originalNum) * (potentialNum - originalNum);
        if (originalCharNum == 0) {
            int nextNum = getPotentialNum(originalCharNum + 1);
            if (diff > (nextNum - originalNum) * (nextNum - originalNum)) {
                diff = (nextNum - originalNum) * (nextNum - originalNum);
                potentialNum = nextNum;
            }
        } else if (originalCharNum == 15) {
            int nextNum = getPotentialNum(originalCharNum - 1);
            if (diff > (nextNum - originalNum) * (nextNum - originalNum)) {
                diff = (nextNum - originalNum) * (nextNum - originalNum);
                potentialNum = nextNum;
            }
        } else {
            // 3种
            int nextNum = getPotentialNum(originalCharNum + 1);
            if (diff > (nextNum - originalNum) * (nextNum - originalNum)) {
                diff = (nextNum - originalNum) * (nextNum - originalNum);
                potentialNum = nextNum;
            }
            // -1
            nextNum = getPotentialNum(originalCharNum - 1);
            if (diff > (nextNum - originalNum) * (nextNum - originalNum)) {
                diff = (nextNum - originalNum) * (nextNum - originalNum);
                potentialNum = nextNum;
            }
        }
        return potentialNum;
    }


    private int getPotentialNum(int charNum) {
        return 16 * charNum + charNum;
    }
}
