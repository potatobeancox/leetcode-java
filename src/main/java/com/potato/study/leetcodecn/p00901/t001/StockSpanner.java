package com.potato.study.leetcodecn.p00901.t001;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 901. 股票价格跨度
 *
 * 编写一个 StockSpanner 类，它收集某些股票的每日报价，并返回该股票当日价格的跨度。
 *
 * 今天股票价格的跨度被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始往回数，包括今天）。
 *
 * 例如，如果未来7天股票的价格是 [100, 80, 60, 70, 60, 75, 85]，那么股票跨度将是 [1, 1, 1, 2, 1, 4, 6]。
 *
 *  
 *
 * 示例：
 *
 * 输入：["StockSpanner","next","next","next","next","next","next","next"], [[],[100],[80],[60],[70],[60],[75],[85]]
 * 输出：[null,1,1,1,2,1,4,6]
 * 解释：
 * 首先，初始化 S = StockSpanner()，然后：
 * S.next(100) 被调用并返回 1，
 * S.next(80) 被调用并返回 1，
 * S.next(60) 被调用并返回 1，
 * S.next(70) 被调用并返回 2，
 * S.next(60) 被调用并返回 1，
 * S.next(75) 被调用并返回 4，
 * S.next(85) 被调用并返回 6。
 *
 * 注意 (例如) S.next(75) 返回 4，因为截至今天的最后 4 个价格
 * (包括今天的价格 75) 小于或等于今天的价格。
 *  
 *
 * 提示：
 *
 * 调用 StockSpanner.next(int price) 时，将有 1 <= price <= 10^5。
 * 每个测试用例最多可以调用  10000 次 StockSpanner.next。
 * 在所有测试用例中，最多调用 150000 次 StockSpanner.next。
 * 此问题的总时间限制减少了 50%。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/online-stock-span
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class StockSpanner {

    private Stack<Integer> indexStack;
    private List<Integer> spanner;
    private List<Integer> priceList;

    // 单调递增 记录好每个日期比之前多少天大
    public StockSpanner() {
        this.indexStack = new Stack<>();
        this.spanner = new ArrayList<>();
        this.priceList = new ArrayList<>();
    }

    public int next(int price) {
        // price 与当前栈顶比较 如果 price 大于等于栈顶，那么price之后的点，最多也就走到price
        int index = spanner.size();
        if (indexStack.isEmpty()) {
            indexStack.push(index);
            spanner.add(1);
            priceList.add(price);
            return 1;
        }
        // 循环出栈，获取每个出栈元素的 权重，求sum
        int total = 1;
        while (!indexStack.isEmpty() && priceList.get(indexStack.peek()) <= price) {
            Integer popIndex = indexStack.pop();
            total += spanner.get(popIndex);
        }
        // 更新并入栈
        indexStack.add(index);
        spanner.add(total);
        priceList.add(price);
        return total;
    }

//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int[] arr = new int[] {3,1,2,4};
//        int[] res = solution.sortArrayByParity(arr);
//        System.out.println(Arrays.toString(res));
////        Assert.assertArrayEquals(, res);
//    }

}
