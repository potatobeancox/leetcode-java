package com.potato.study.leetcodecn.p00604.t001;

import java.util.ArrayList;
import java.util.List;

/**
 * 604. 迭代压缩字符串
 *
 * 设计并实现一个迭代压缩字符串的数据结构。给定的压缩字符串的形式是，每个字母后面紧跟一个正整数，表示该字母在原始未压缩字符串中出现的次数。

 设计一个数据结构，它支持如下两种操作： next 和 hasNext。

 next() - 如果原始字符串中仍有未压缩字符，则返回下一个字符，否则返回空格。
 hasNext() - 如果原始字符串中存在未压缩的的字母，则返回true，否则返回false。
  

 示例 1：

 输入：
 ["StringIterator", "next", "next", "next", "next", "next", "next", "hasNext", "next", "hasNext"]
 [["L1e2t1C1o1d1e1"], [], [], [], [], [], [], [], [], []]
 输出：
 [null, "L", "e", "e", "t", "C", "o", true, "d", true]

 解释：
 StringIterator stringIterator = new StringIterator("L1e2t1C1o1d1e1");
 stringIterator.next(); // 返回 "L"
 stringIterator.next(); // 返回 "e"
 stringIterator.next(); // 返回 "e"
 stringIterator.next(); // 返回 "t"
 stringIterator.next(); // 返回 "C"
 stringIterator.next(); // 返回 "o"
 stringIterator.hasNext(); // 返回 True
 stringIterator.next(); // 返回 "d"
 stringIterator.hasNext(); // 返回 True
  

 提示：

 1 <= compressedString.length <= 1000
 compressedString 由小写字母、大写字母和数字组成。
 在 compressedString 中，单个字符的重复次数在 [1,10 ^9] 范围内。
 next 和 hasNext 的操作数最多为 100 。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/design-compressed-string-iterator
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class StringIterator {

    private List<Integer> charIndexList;
    private List<Integer> charNumList;
    private int charIndex;
    private int charCount;
    private String compressedString;

    public StringIterator(String compressedString) {
        // list 存储 ch 坐标 index
        this.charIndexList = new ArrayList<>();
        // list 存储 每个 ch 对应的 个数
        this.charNumList = new ArrayList<>();
        // ch index
        this.charIndex = 0;
        // count 当前已经使用的个数
        this.charCount = 0;
        this.compressedString = compressedString;


        int lastCharIndex = -1;
        int count = 0;
        for (int i = 0; i < compressedString.length(); i++) {
            char ch = compressedString.charAt(i);
            if (Character.isLetter(ch)) {
                // 计算上个单词
                if (count > 0) {
                    charIndexList.add(lastCharIndex);
                    charNumList.add(count);
                    count = 0;
                }
                lastCharIndex = i;
            } else {
                count *= 10;
                count += (ch - '0');
            }
        }
        // 最后一个字母
        if (count > 0) {
            charIndexList.add(lastCharIndex);
            charNumList.add(count);
        }
    }

    public char next() {
        if (!hasNext()) {
            return ' ';
        }
        int thisChIndex = charIndexList.get(charIndex);
        // 获取字母本身
        char c = compressedString.charAt(thisChIndex);
        charCount++;
        int charNum = charNumList.get(charIndex);
        // 判断是否需要往后移动
        if (charCount == charNum) {
            charIndex++;
            charCount = 0;
        }
        return c;
    }

    public boolean hasNext() {
        return charIndex < charIndexList.size();
    }


    public static void main(String[] args) {
        StringIterator stringIterator = new StringIterator("L1e2t1C1o1d1e1");
        char next = stringIterator.next();
        System.out.println(next);

    }

}
