package com.potato.study.leetcodecn.p00420.t001;

/**
 * 420. 强密码检验器
 *
 *
 * 如果一个密码满足下述所有条件，则认为这个密码是强密码：
 由至少 6 个，至多 20 个字符组成。
 至少包含 一个小写 字母，一个大写 字母，和 一个数字 。
 同一字符 不能 连续出现三次 (比如 "...aaa..." 是不允许的, 但是 "...aa...a..." 如果满足其他条件也可以算是强密码)。
 给你一个字符串 password ，返回 将 password 修改到满足强密码条件需要的最少修改步数。如果 password 已经是强密码，则返回 0 。

 在一步修改操作中，你可以：

 插入一个字符到 password ，
 从 password 中删除一个字符，或
 用另一个字符来替换 password 中的某个字符。
  

 示例 1：

 输入：password = "a"
 输出：5
 示例 2：

 输入：password = "aA1"
 输出：3
 示例 3：

 输入：password = "1337C0d3"
 输出：0
  

 提示：

 1 <= password.length <= 50
 password 由字母、数字、点 '.' 或者感叹号 '!'

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/strong-password-checker
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/strong-password-checker/solution/by-livorth-u-dsz3/
     * https://leetcode.cn/problems/strong-password-checker/solution/by-livorth-u-dsz3/
     * @param password
     * @return
     */
    public int strongPasswordChecker(String password) {
        // 计算种类数量
        int lower = 0;
        int upper = 0;
        int digit = 0;
        char[] passwordArray = password.toCharArray();
        for (int i = 0; i < password.length(); i++) {
            char ch = passwordArray[i];
            if (Character.isLowerCase(ch)) {
                lower = 1;
            } else if (Character.isUpperCase(ch)) {
                upper = 1;
            } else if (Character.isDigit(ch)) {
                digit = 1;
            }
        }
        int len = password.length();
        if (len < 6) {
            // 小于 6的情况 值考虑添加 也就是 6-长度 max
            return Math.max(6 - len, 3 - lower - upper - digit);

        } else if (len <= 20) {
            // [6-20] 使用替换 每个超过 3连的数字 / 3 , 需要考虑种类
            int i = 0;
            int needReplaceTime = 0;
            while (i < len) {
                int j = i;
                while (j < len && passwordArray[j] == passwordArray[i]) {
                    j++;
                }
                // 计算长度 j不算 因为 j已经不相同了
                int currentLen = j - i;
                needReplaceTime += currentLen / 3;

                i = j;
            }
            return Math.max(needReplaceTime, 3 - lower - upper - digit);
        } else {
            // 20 大于 主要考虑 删除 再考虑替换 先删除 超3连 直到 到了 20 进行 替换
            int deleteCount = len - 20;
            // 替换次数
            int i = 0;
            int needReplaceTime = 0;
            int[] counts = new int[3];
            while (i < len) {
                int j = i;
                while (j < len && passwordArray[j] == passwordArray[i]) {
                    j++;
                }
                // 计算长度 j不算 因为 j已经不相同了
                int currentLen = j - i;
                if (currentLen >= 3) {
                    needReplaceTime += currentLen / 3;
                    // 计算每种结尾个数
                    counts[currentLen % 3]++;
                }
                i = j;
            }
            // 剩下的操作 可以替换删除
            int remind = deleteCount;
            for (int j = 0; j < 3; j++) {

                if (j == 2) {
                    counts[j] = needReplaceTime;
                }

                if (counts[j] != 0 && remind != 0) {
                    int times = Math.min(remind, counts[j] * (j + 1));
                    remind -= times;

                    needReplaceTime -= times / (j+1);
                }

            }
            // 需要至少被删除的才做
            return deleteCount + Math.max(3-digit-lower-upper, needReplaceTime);
        }
    }
}
