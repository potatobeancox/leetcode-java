package com.potato.study.other;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.ListUtils;
import org.junit.Assert;

import lombok.Data;

/**
 * @author liuzhao03 <liuzhao03@kuaishou.com>
 * Created on 2022-08-24
 */
public class TestForReach {

    /**
     *
     * @param nums
     * @return
     */
    public boolean canVisit(int nums[]) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        boolean[] status = new boolean[nums.length];
        status[0] = true;
        for (int i = 0; i < nums.length; i++) {
            // 如果当前位置 i 已经无法到达 直接返回 false
            if (!status[i]) {
                return false;
            }
            // 更新 达到状态
            for (int j = 1; j <= nums[i]; j++) {
                if (i + j >= nums.length) {
                    break;
                }
                status[i+j] = true;
            }
        }
        return status[nums.length - 1];
    }

    public static void main(String[] args) {
        TestForReach testForReach = new TestForReach();

//        输入：
//        输出：true
        int[] nums = new int[] {
                2,3,1,1,4
        };
        boolean b = testForReach.canVisit(nums);
        System.out.println(b);
        Assert.assertEquals(true, b);

//        输入：nums = [3,2,1,0,4]
//        输出：false

        nums = new int[] {
                3,2,1,0,4
        };
        b = testForReach.canVisit(nums);
        System.out.println(b);
        Assert.assertEquals(false, b);


        List<User> userList = new ArrayList<>();
        userList.add(new User());

        Map<String, List<String>> map = ListUtils.emptyIfNull(userList).stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(user -> String.valueOf(user.getIndex()),
                        User::getStrList, (a, c) -> a));

        System.out.println("succees");

    }

    @Data
    static class User {
        private List<String> strList = new ArrayList<>();
        private int index;
    }
}
