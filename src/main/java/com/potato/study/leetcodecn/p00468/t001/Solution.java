package com.potato.study.leetcodecn.p00468.t001;


import org.junit.Assert;

/**
 * 468. 验证IP地址
 *
 * 给定一个字符串 queryIP。如果是有效的 IPv4 地址，返回 "IPv4" ；如果是有效的 IPv6 地址，返回 "IPv6" ；如果不是上述类型的 IP 地址，返回 "Neither" 。
 *
 * 有效的IPv4地址 是 “x1.x2.x3.x4” 形式的IP地址。 其中 0 <= xi <= 255 且 xi 不能包含 前导零。例如: “192.168.1.1” 、 “192.168.1.0” 为有效IPv4地址，
 * “192.168.01.1” 为无效IPv4地址; “192.168.1.00” 、 “192.168@1.1” 为无效IPv4地址。
 *
 * 一个有效的IPv6地址 是一个格式为“x1:x2:x3:x4:x5:x6:x7:x8” 的IP地址，其中:
 *
 * 1 <= xi.length <= 4
 * xi 是一个 十六进制字符串 ，可以包含数字、小写英文字母( 'a' 到 'f' )和大写英文字母( 'A' 到 'F' )。
 * 在 xi 中允许前导零。
 * 例如 "2001:0db8:85a3:0000:0000:8a2e:0370:7334" 和 "2001:db8:85a3:0:0:8A2E:0370:7334" 是有效的 IPv6 地址，而
 * "2001:0db8:85a3::8A2E:037j:7334" 和 "02001:0db8:85a3:0000:0000:8a2e:0370:7334" 是无效的 IPv6 地址。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：queryIP = "172.16.254.1"
 * 输出："IPv4"
 * 解释：有效的 IPv4 地址，返回 "IPv4"
 * 示例 2：
 *
 * 输入：queryIP = "2001:0db8:85a3:0:0:8A2E:0370:7334"
 * 输出："IPv6"
 * 解释：有效的 IPv6 地址，返回 "IPv6"
 * 示例 3：
 *
 * 输入：queryIP = "256.256.256.256"
 * 输出："Neither"
 * 解释：既不是 IPv4 地址，又不是 IPv6 地址
 *  
 *
 * 提示：
 *
 * queryIP 仅由英文字母，数字，字符 '.' 和 ':' 组成。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-ip-address
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public String validIPAddress(String queryIP) {
        if (queryIP.contains(":")) {
            // 如果最后一个字符是 ：
            if (queryIP.charAt(queryIP.length() - 1) == ':') {
                return "Neither";
            }
            if (queryIP.charAt(0) == ':') {
                return "Neither";
            }
            // ipv6
            String[] split = queryIP.split(":");
            // partnum
            if (split.length != 8) {
                return "Neither";
            }
            for (int i = 0; i < split.length; i++) {
                // split[i]  每个 部分长度 在 1-4 和之间
                if (split[i].length() < 1 || split[i].length() > 4) {
                    return "Neither";
                }
                // 不能出现其他字母
                for (int j = 0; j < split[i].length(); j++) {
                    char ch = split[i].charAt(j);
                    if (Character.isDigit(ch)) {
                        continue;
                    }
                    if (!Character.isAlphabetic(ch)) {
                        return "Neither";
                    }
                    // 字母
                    if ('a' <= ch && ch <= 'f') {
                        continue;
                    }
                    if ('A' <= ch && ch <= 'F') {
                        continue;
                    }
                    return "Neither";
                }
            }

            return "IPv6";
        } else if (queryIP.contains(".")) {
            if (queryIP.charAt(queryIP.length() - 1) == '.') {
                return "Neither";
            }
            if (queryIP.charAt(0) == '.') {
                return "Neither";
            }
            // ipv4
            String[] split = queryIP.split("\\.");
            // 4个 part
            if (split.length != 4) {
                return "Neither";
            }
            // 先导0
            for (int i = 0; i < split.length; i++) {
                if (split[i].length() == 0) {
                    return "Neither";
                }
                try {
                    int partNum = Integer.parseInt(split[i]);
                    // 大小
                    if (partNum < 0 || partNum >= 256) {
                        return "Neither";
                    }
                    // 先导0
                    if (split[i].length() != String.valueOf(partNum).length()) {
                        return "Neither";
                    }
                } catch (Exception e) {
                    // part 里边有其他字符
                    return "Neither";
                }
            }
            return "IPv4";
        } else {
            return "Neither";
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String ip = "172.16.254.1";
        String s = solution.validIPAddress(ip);
        System.out.println(s);
        Assert.assertEquals("IPv4", s);


        ip = "2001:0db8:85a3:0:0:8A2E:0370:7334:";
        s = solution.validIPAddress(ip);
        System.out.println(s);
        Assert.assertEquals("Neither", s);


        ip = "1.1.1.1.";
        s = solution.validIPAddress(ip);
        System.out.println(s);
        Assert.assertEquals("Neither", s);
    }
}
