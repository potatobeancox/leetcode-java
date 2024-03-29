package com.potato.study.leetcodecn.p00635.t001;


import java.util.*;

/**
 * 635. 设计日志存储系统
 *
 * 你将获得多条日志，每条日志都有唯一的 id 和 timestamp ，timestamp 是形如 Year:Month:Day:Hour:Minute:Second 的字符串，2017:01:01:23:59:59 ，所有值域都是零填充的十进制数。
 *
 * 实现 LogSystem 类：
 *
 * LogSystem() 初始化 LogSystem 对象
 * void put(int id, string timestamp) 给定日志的 id 和 timestamp ，将这个日志存入你的存储系统中。
 * int[] retrieve(string start, string end, string granularity) 返回在给定时间区间 [start, end] （包含两端）内的所有日志的 id 。start 、end 和 timestamp 的格式相同，granularity 表示考虑的时间粒度（例如，精确到 Day、Minute 等）。例如 start = "2017:01:01:23:59:59"、end = "2017:01:02:23:59:59" 且 granularity = "Day" 意味着需要查找从 Jan. 1st 2017 到 Jan. 2nd 2017 范围内的日志，可以忽略日志的 Hour、Minute 和 Second 。
 *  
 * 示例：
 *
 * 输入：
 * ["LogSystem", "put", "put", "put", "retrieve", "retrieve"]
 * [[], [1, "2017:01:01:23:59:59"], [2, "2017:01:01:22:59:59"], [3, "2016:01:01:00:00:00"], ["2016:01:01:01:01:01", "2017:01:01:23:00:00", "Year"], ["2016:01:01:01:01:01", "2017:01:01:23:00:00", "Hour"]]
 * 输出：
 * [null, null, null, null, [3, 2, 1], [2, 1]]
 *
 * 解释：
 * LogSystem logSystem = new LogSystem();
 * logSystem.put(1, "2017:01:01:23:59:59");
 * logSystem.put(2, "2017:01:01:22:59:59");
 * logSystem.put(3, "2016:01:01:00:00:00");
 *
 * // 返回 [3,2,1]，返回从 2016 年到 2017 年所有的日志。
 * logSystem.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Year");
 *
 * // 返回 [2,1]，返回从 Jan. 1, 2016 01:XX:XX 到 Jan. 1, 2017 23:XX:XX 之间的所有日志
 * // 不返回日志 3 因为记录时间 Jan. 1, 2016 00:00:00 超过范围的起始时间
 * logSystem.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Hour");
 *  
 *
 * 提示：
 *
 * 1 <= id <= 500
 * 2000 <= Year <= 2017
 * 1 <= Month <= 12
 * 1 <= Day <= 31
 * 0 <= Hour <= 23
 * 0 <= Minute, Second <= 59
 * granularity 是这些值 ["Year", "Month", "Day", "Hour", "Minute", "Second"] 之一
 * 最多调用 500 次 put 和 retrieve
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/design-log-storage-system
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class LogSystem {

    private Map<String, Integer> contentMap;
    private Map<String, Integer> typeMap;

    public LogSystem() {
        this.contentMap = new HashMap<>();
        this.typeMap = new HashMap<>();
        // init key 是 哪些 granularity 是这些值 ["Year", "Month", "Day", "Hour", "Minute", "Second"] value 是要截取的字符串个数
        typeMap.put("Year", 4);
        // 2017:01:01:23:59:59
        typeMap.put("Month", 7);
        typeMap.put("Day", 10);
        typeMap.put("Hour", 13);
        typeMap.put("Minute", 16);
        typeMap.put("Second", 19);
    }

    public void put(int id, String timestamp) {
        contentMap.put(timestamp, id);
    }

    /**
     * granularity 是这些值 ["Year", "Month", "Day", "Hour", "Minute", "Second"] 之一
     * @param start
     * @param end
     * @param granularity
     * @return
     */
    public List<Integer> retrieve(String start, String end, String granularity) {
        long startTime = getTimestampNum(start, granularity);
        long endTime = getTimestampNum(end, granularity);
        // 找到开始结束之间
        List<Integer> list = new ArrayList<>();
        for (String key : contentMap.keySet()) {
            long current = getTimestampNum(key, granularity);
            if (startTime <= current && current <= endTime) {
                list.add(contentMap.get(key));
            }
        }
        return list;
    }

    private long getTimestampNum(String timestamp, String granularity) {
        Integer validLen = typeMap.get(granularity);
        String substring = timestamp.substring(0, validLen);
        // 2017:01:01:23:59:59
        String[] split = substring.split(":");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            builder.append(split[i]);
        }
        String numStr = builder.toString();
        long l = Long.parseLong(numStr);
        return l;
    }

    public static void main(String[] args) {
        LogSystem logSystem = new LogSystem();
        logSystem.put(1,"2017:01:01:23:59:59");
        logSystem.put(2,"2017:01:01:22:59:59");
        logSystem.put(3,"2016:01:01:00:00:00");

        System.out.println(logSystem.retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year"));
        System.out.println(logSystem.retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour"));
    }


}
