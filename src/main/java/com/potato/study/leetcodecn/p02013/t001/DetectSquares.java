package com.potato.study.leetcodecn.p02013.t001;

import java.util.HashMap;
import java.util.Map;

/**
 * 2013. 检测正方形
 *
 * 给你一个在 X-Y 平面上的点构成的数据流。设计一个满足下述要求的算法：

 添加 一个在数据流中的新点到某个数据结构中。可以添加 重复 的点，并会视作不同的点进行处理。
 给你一个查询点，请你从数据结构中选出三个点，使这三个点和查询点一同构成一个 面积为正 的 轴对齐正方形 ，统计 满足该要求的方案数目。
 轴对齐正方形 是一个正方形，除四条边长度相同外，还满足每条边都与 x-轴 或 y-轴 平行或垂直。

 实现 DetectSquares 类：

 DetectSquares() 使用空数据结构初始化对象
 void add(int[] point) 向数据结构添加一个新的点 point = [x, y]
 int count(int[] point) 统计按上述方式与点 point = [x, y] 共同构造 轴对齐正方形 的方案数。
  

 示例：


 输入：
 ["DetectSquares", "add", "add", "add", "count", "count", "add", "count"]
 [[], [[3, 10]], [[11, 2]], [[3, 2]], [[11, 10]], [[14, 8]], [[11, 2]], [[11, 10]]]
 输出：
 [null, null, null, null, 1, 0, null, 2]

 解释：
 DetectSquares detectSquares = new DetectSquares();
 detectSquares.add([3, 10]);
 detectSquares.add([11, 2]);
 detectSquares.add([3, 2]);
 detectSquares.count([11, 10]); // 返回 1 。你可以选择：
 //   - 第一个，第二个，和第三个点
 detectSquares.count([14, 8]);  // 返回 0 。查询点无法与数据结构中的这些点构成正方形。
 detectSquares.add([11, 2]);    // 允许添加重复的点。
 detectSquares.count([11, 10]); // 返回 2 。你可以选择：
 //   - 第一个，第二个，和第三个点
 //   - 第一个，第三个，和第四个点
  

 提示：

 point.length == 2
 0 <= x, y <= 1000
 调用 add 和 count 的 总次数 最多为 5000

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/detect-squares
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class DetectSquares {

    private Map<Integer, Map<Integer, Integer>> rowColCountMap;
    /**
     * https://leetcode-cn.com/problems/detect-squares/solution/gong-shui-san-xie-jian-dan-ha-xi-biao-yu-748e/
     */
    public DetectSquares() {
        // key 是 row value： key col  value 出现次数
        this.rowColCountMap = new HashMap<>();
    }

    public void add(int[] point) {
        int x = point[0];
        int y = point[1];

        Map<Integer, Integer> colCountMap = rowColCountMap.getOrDefault(x, new HashMap<>());
        Integer count = colCountMap.getOrDefault(y, 0);
        count++;
        colCountMap.put(y, count);
        rowColCountMap.put(x, colCountMap);
    }

    public int count(int[] point) {
        int x = point[0];
        int y = point[1];
        Map<Integer, Integer> colCountMap = rowColCountMap.getOrDefault(x, new HashMap<>());
        // 获取 与x 同行的数据 判定 与x之间差距多少 y1坐标之间 作为变成 d
        int xyCount = 1;
        int totalCount = 0;
        for (int dy : colCountMap.keySet()) {
            // 边长
            int dis = dy - y;
            if (dis == 0) {
                continue;
            }
            int[] dxs = new int[] {
                    x + dis, x - dis
            };
            int xdyCount = colCountMap.getOrDefault(dy, 0);
            for (int dx : dxs) {
                Map<Integer, Integer> yCount = rowColCountMap.getOrDefault(dx, new HashMap<>());
                Integer dxyCount = yCount.getOrDefault(y, 0);
                Integer dxdyCount = yCount.getOrDefault(dy, 0);

                totalCount += (xyCount * xdyCount * dxyCount * dxdyCount);
            }
        }

        // 判定 x - d / x+d y    x - d / x+d y1
        return totalCount;
    }

    public static void main(String[] args) {
        DetectSquares detectSquares = new DetectSquares();
        detectSquares.add(new int[]{3, 10});
        detectSquares.add(new int[]{11, 2});
        detectSquares.add(new int[]{3, 2});
        System.out.println(detectSquares.count(new int[] {11,10}));
        detectSquares.add(new int[]{14, 8});
        detectSquares.add(new int[]{11, 2});
        detectSquares.add(new int[]{11, 10});
//        ["DetectSquares","add","add","add","count","count","add","count"]
//[[],[[3,10]],[[11,2]],[[3,2]],[[11,10]],[[14,8]],[[11,2]],[[11,10]]]

    }
}

