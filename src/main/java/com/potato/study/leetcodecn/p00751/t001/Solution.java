package com.potato.study.leetcodecn.p00751.t001;

import java.util.ArrayList;
import java.util.List;

/**
 * 751. IP 到 CIDR
 *
 * IP地址 是一个格式化的 32位 无符号整数，每组 8位 被打印为一个十进制数字和，点字符 '.' 起到了分组的作用。
 *
 * 例如，二进制数 00001111 10001000 11111111 01101011 ( 为清晰起见增加了空格)被格式化为IP地址将是 “15.136.255.107” 。
 * CIDR块 是一种用来表示一组特定IP地址的格式。字符串形式，由基础IP地址、斜杠和前缀长度 k 组成。它所覆盖的地址是所有IP地址的 前 k 位 与基础IP地址相同的IP地址。
 *
 * 例如， “123.45.67.89/20” 是一个前缀长度为 20 的 CIDR块。任何二进制表示形式匹配 01111011 00101101 0100xxxx xxxxxxxx 的IP地址，其中 x 可以是 0 或 1 ，都在CIDR块覆盖的集合中。
 * 给你一个起始IP地址 ip 和我们需要覆盖的IP地址数量 n 。你的目标是使用 尽可能少的CIDR块 来 覆盖范围 [ip, ip + n - 1] 内的所有IP地址 。不应该覆盖范围之外的其他IP地址。
 *
 * 返回 包含IP地址范围的 CIDR块 的 最短 列表。如果有多个答案，返回其中 任何 一个 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：ip = "255.0.0.7", n = 10
 * 输出：["255.0.0.7/32","255.0.0.8/29","255.0.0.16/32"]
 * 解释：
 * 需要覆盖的IP地址有:
 * - 255.0.0.7 -> 11111111 00000000 00000000 00000111
 * - 255.0.0.8 -> 11111111 00000000 00000000 00001000
 * - 255.0.0.9 -> 11111111 00000000 00000000 00001001
 * - 255.0.0.10 -> 11111111 00000000 00000000 00001010
 * - 255.0.0.11 -> 11111111 00000000 00000000 00001011
 * - 255.0.0.12 -> 11111111 00000000 00000000 00001100
 * - 255.0.0.13 -> 11111111 00000000 00000000 00001101
 * - 255.0.0.14 -> 11111111 00000000 00000000 00001110
 * - 255.0.0.15 -> 11111111 00000000 00000000 00001111
 * - 255.0.0.16 -> 11111111 00000000 00000000 00010000
 * CIDR区块“255.0.0.7/32”包含第一个地址。
 * CIDR区块255.0.0.8/29包含中间的8个地址(二进制格式为11111111 00000000 00000000 00001xxx)。
 * CIDR区块“255.0.0.16/32”包含最后一个地址。
 * 请注意，虽然CIDR区块“255.0.0.0/28”覆盖了所有的地址，但它也包括范围之外的地址，所以我们不能使用它。
 * 示例 2:
 *
 * 输入：ip = "117.145.102.62", n = 8
 * 输出：["117.145.102.62/31","117.145.102.64/30","117.145.102.68/31"]
 *  
 *
 * 提示:
 *
 * 7 <= ip.length <= 15
 * ip 是一个有效的 IPv4 ，形式为 "a.b.c.d" ，其中 a, b, c,  d 是 [0, 255] 范围内的整数
 * 1 <= n <= 1000
 * 每个隐含地址 ip + x ( x < n) 都是有效的 IPv4 地址
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/ip-to-cidr
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode.cn/problems/ip-to-cidr/solution/ipdao-cidr-by-617076674/
     * @param ip
     * @param n
     * @return
     */
    public List<String> ipToCIDR(String ip, int n) {
        // 寻找 ip 对应 最右边的1 ，如果最右边的1 能表示n个数字 添加结果 返回
        long start = ipToNum(ip);
        int k = 0;
        while ((start & (1 << k)) == 0) {
            k++;
        }
        // 当前k位置能表示多少个数字
        long numCount = (1 <<k);
        // 如果恰好能包含就 直接找到答案并返回
        List<String> res = new ArrayList<>();
        if (n == numCount) {
            res.add(numToIp(start) + "/" + (32 - k));
            return res;
        } else if (n > numCount) {
            // 如果不够包含 就需要用ip + 到还没有包含的 和 对应 tmep n 递归找了
            res.add(numToIp(start) + "/" + (32 - k));
            res.addAll(ipToCIDR(numToIp(start + numCount), (int)(n - numCount)));
            return res;
        } else {
            // 如果 n 小于 count 说明啥
            while ((1 << k) > n) {
                k--;
            }
            res.add(numToIp(start) + "/" + (32 - k));
            // 还剩多少
            int remain = n - (1 << k);
            if (remain > 0) {
                res.addAll(ipToCIDR(numToIp(start + (1<<k)), remain));
            }
            return res;
        }
    }

    // ip -> 数字
    private long ipToNum(String ip) {
        String[] split = ip.split("\\.");
        long temp = 0;
        for (String num : split) {
            temp *= 256;
            temp += Long.parseLong(num);
        }
        return temp;
    }

    private String numToIp(long num) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            long l = num % 256;
            builder.insert(0, l);
            num /= 256;
            if (i != 3) {
                builder.insert(0, '.');
            }
        }
        return builder.toString();
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> strings = solution.ipToCIDR("255.0.0.7", 10);
        System.out.println(strings);


        strings = solution.ipToCIDR("0.0.0.0", 1);
        System.out.println(strings);
    }
}
