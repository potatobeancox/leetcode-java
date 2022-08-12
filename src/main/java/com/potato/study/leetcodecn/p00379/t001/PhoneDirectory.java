package com.potato.study.leetcodecn.p00379.t001;

import java.util.TreeSet;

/**
 * 379. 电话目录管理系统
 *
 * 设计一个电话目录管理系统，让它支持以下功能：
 *
 * get: 分配给用户一个未被使用的电话号码，获取失败请返回 -1
 * check: 检查指定的电话号码是否被使用
 * release: 释放掉一个电话号码，使其能够重新被分配
 *  
 *
 * 示例：
 *
 * // 初始化电话目录，包括 3 个电话号码：0，1 和 2。
 * PhoneDirectory directory = new PhoneDirectory(3);
 *
 * // 可以返回任意未分配的号码，这里我们假设它返回 0。
 * directory.get();
 *
 * // 假设，函数返回 1。
 * directory.get();
 *
 * // 号码 2 未分配，所以返回为 true。
 * directory.check(2);
 *
 * // 返回 2，分配后，只剩一个号码未被分配。
 * directory.get();
 *
 * // 此时，号码 2 已经被分配，所以返回 false。
 * directory.check(2);
 *
 * // 释放号码 2，将该号码变回未分配状态。
 * directory.release(2);
 *
 * // 号码 2 现在是未分配状态，所以返回 true。
 * directory.check(2);
 *  
 *
 * 提示：
 *
 * 1 <= maxNumbers <= 10^4
 * 0 <= number < maxNumbers
 * 调用方法的总数处于区间 [0 - 20000] 之内
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/design-phone-directory
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class PhoneDirectory {


    private TreeSet<Integer> treeSet;
    private int maxNumbers;
    private int unuseNum;

    public PhoneDirectory(int maxNumbers) {
        this.treeSet = new TreeSet<>();
        this.maxNumbers = maxNumbers;
        this.unuseNum = 0;
    }

    public int get() {
        if (!treeSet.isEmpty()) {
            Integer integer = treeSet.pollFirst();
            return integer;
        }
        if (unuseNum == maxNumbers) {
            return -1;
        }
        int temp = unuseNum;
        unuseNum++;
        return temp;
    }

    public boolean check(int number) {
        if (treeSet.contains(number)) {
            return true;
        }
        return number >= unuseNum;
    }

    public void release(int number) {
        if (treeSet.contains(number)) {
            return;
        }
        if (number >= unuseNum) {
            return;
        }
        treeSet.add(number);
    }

    // ["PhoneDirectory","release","get","check","check","release","check","get","check","check","check"]
    //[[2],[1],[],[1],[1],[1],[1],[],[0],[1],[1]]

//    ["PhoneDirectory","get","get","check","get","check","release","check"]
//            [[3],[],[],            [2],[],        [2],[2],[2]]

    public static void main(String[] args) {
        // [null,0,1,true,2,false,null,false]
        PhoneDirectory phoneDirectory = new PhoneDirectory(3);
        int i = phoneDirectory.get();
        i = phoneDirectory.get();
        System.out.println(phoneDirectory.check(2));
        phoneDirectory.get();

        System.out.println(phoneDirectory.check(2));
        phoneDirectory.release(2);
        // false
        System.out.println(phoneDirectory.check(2));


    }

}
