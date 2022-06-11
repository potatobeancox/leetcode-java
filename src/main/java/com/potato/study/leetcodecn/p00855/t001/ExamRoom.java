package com.potato.study.leetcodecn.p00855.t001;

import com.potato.study.leetcodecn.p01674.t001.Solution;
import org.junit.Assert;

import java.util.*;

/**
 * 855. 考场就座
 *
 * 在考场里，一排有 N 个座位，分别编号为 0, 1, 2, ..., N-1 。

 当学生进入考场后，他必须坐在能够使他与离他最近的人之间的距离达到最大化的座位上。如果有多个这样的座位，他会坐在编号最小的座位上。(另外，如果考场里没有人，那么学生就坐在 0 号座位上。)

 返回 ExamRoom(int N) 类，它有两个公开的函数：其中，函数 ExamRoom.seat() 会返回一个 int （整型数据），代表学生坐的位置；函数 ExamRoom.leave(int p) 代表坐在座位 p 上的学生现在离开了考场。每次调用 ExamRoom.leave(p) 时都保证有学生坐在座位 p 上。

  

 示例：

 输入：["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
 输出：[null,0,9,4,2,null,5]
 解释：
 ExamRoom(10) -> null
 seat() -> 0，没有人在考场里，那么学生坐在 0 号座位上。
 seat() -> 9，学生最后坐在 9 号座位上。
 seat() -> 4，学生最后坐在 4 号座位上。
 seat() -> 2，学生最后坐在 2 号座位上。
 leave(4) -> null
 seat() -> 5，学生最后坐在 5 号座位上。
  

 提示：

 1 <= N <= 10^9
 在所有的测试样例中 ExamRoom.seat() 和 ExamRoom.leave() 最多被调用 10^4 次。
 保证在调用 ExamRoom.leave(p) 时有学生正坐在座位 p 上。


 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/exam-room
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class ExamRoom {


    private TreeSet<Integer> seatSet;
    private int n;

    /**
     * 使用一个 TreeSet 记录已经使用的座位位置
     * @param n
     */
    public ExamRoom(int n) {
        this.seatSet = new TreeSet<>();
        this.n = n;
    }


    /**
     * 遍历 treeSet 找到两个位置差中间的那个位置就坐
     * @return
     */
    public int seat() {
        // 没有人开始做 第一个开始
        if (seatSet.size() == 0) {
            seatSet.add(0);
            return 0;
        }
        int maxDistance = 0;
        int seatIndex = -1;
        // 从set每个位置开始 找到与之前位置之间空了多少个位置 计算要落座的位置
        Integer first = seatSet.first();
        // 计算第一个位置和 0 之前的距离
        if (first != 0) {
            maxDistance = first - 0;
            seatIndex = 0;
        }
        int lastIndex = -1;
        Iterator<Integer> iterator = seatSet.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (lastIndex == -1) {
                lastIndex = next;
                continue;
            }
            // 有了last 中间有多少个空位
            int dis = next - lastIndex - 1;
            if (dis == 0) {
                // 没有空间
                lastIndex = next;
                continue;
            }
            // 有空间 距离两边最小距离
            int minDistanceToCurrent = (dis + 1) / 2;
            if (minDistanceToCurrent > maxDistance) {
                maxDistance = minDistanceToCurrent;
                seatIndex = lastIndex + minDistanceToCurrent;
            }
            lastIndex = next;
        }

        Integer last = seatSet.last();
        // 最后一个位置
        if (last != n-1) {
            int thisDistance = n-1-last;
            if (thisDistance > maxDistance) {
//                maxDistance = thisDistance;
                seatIndex = n-1;
            }
        }

        seatSet.add(seatIndex);
        return seatIndex;
    }

    /**
     * 直接删除 p位置
     * @param p
     */
    public void leave(int p) {
        seatSet.remove(p);
    }

    public static void main(String[] args) {
        ExamRoom examRoom = new ExamRoom(10);
        System.out.println(examRoom.seat()); // 0
        System.out.println(examRoom.seat()); // 9
        System.out.println(examRoom.seat()); // 4
        System.out.println(examRoom.seat()); // 2
        examRoom.leave(4);
        System.out.println(examRoom.seat()); // 5



        examRoom = new ExamRoom(4);
        System.out.println(examRoom.seat()); // 0
        System.out.println(examRoom.seat()); // 3
        System.out.println(examRoom.seat()); // 1
        System.out.println(examRoom.seat()); // 2
        examRoom.leave(1);
        examRoom.leave(3);
        System.out.println(examRoom.seat()); // 1



    }
}
