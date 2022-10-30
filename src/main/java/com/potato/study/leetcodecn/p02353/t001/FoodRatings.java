package com.potato.study.leetcodecn.p02353.t001;

import com.potato.study.leetcodecn.p01114.t001.Foo;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 2353. 设计食物评分系统
 *
 * 设计一个支持下述操作的食物评分系统：

 修改 系统中列出的某种食物的评分。
 返回系统中某一类烹饪方式下评分最高的食物。
 实现 FoodRatings 类：

 FoodRatings(String[] foods, String[] cuisines, int[] ratings) 初始化系统。食物由 foods、cuisines 和 ratings 描述，长度均为 n 。
 foods[i] 是第 i 种食物的名字。
 cuisines[i] 是第 i 种食物的烹饪方式。
 ratings[i] 是第 i 种食物的最初评分。
 void changeRating(String food, int newRating) 修改名字为 food 的食物的评分。
 String highestRated(String cuisine) 返回指定烹饪方式 cuisine 下评分最高的食物的名字。如果存在并列，返回 字典序较小 的名字。
 注意，字符串 x 的字典序比字符串 y 更小的前提是：x 在字典中出现的位置在 y 之前，也就是说，要么 x 是 y 的前缀，或者在满足 x[i] != y[i] 的第一个位置 i 处，x[i] 在字母表中出现的位置在 y[i] 之前。

  

 示例：

 输入
 ["FoodRatings", "highestRated", "highestRated", "changeRating", "highestRated", "changeRating", "highestRated"]
 [[["kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"], ["korean", "japanese", "japanese", "greek", "japanese", "korean"], [9, 12, 8, 15, 14, 7]], ["korean"], ["japanese"], ["sushi", 16], ["japanese"], ["ramen", 16], ["japanese"]]
 输出
 [null, "kimchi", "ramen", null, "sushi", null, "ramen"]

 解释
 FoodRatings foodRatings = new FoodRatings(["kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"], ["korean", "japanese", "japanese", "greek", "japanese", "korean"], [9, 12, 8, 15, 14, 7]);
 foodRatings.highestRated("korean"); // 返回 "kimchi"
 // "kimchi" 是分数最高的韩式料理，评分为 9 。
 foodRatings.highestRated("japanese"); // 返回 "ramen"
 // "ramen" 是分数最高的日式料理，评分为 14 。
 foodRatings.changeRating("sushi", 16); // "sushi" 现在评分变更为 16 。
 foodRatings.highestRated("japanese"); // 返回 "sushi"
 // "sushi" 是分数最高的日式料理，评分为 16 。
 foodRatings.changeRating("ramen", 16); // "ramen" 现在评分变更为 16 。
 foodRatings.highestRated("japanese"); // 返回 "ramen"
 // "sushi" 和 "ramen" 的评分都是 16 。
 // 但是，"ramen" 的字典序比 "sushi" 更小。
  

 提示：

 1 <= n <= 2 * 104
 n == foods.length == cuisines.length == ratings.length
 1 <= foods[i].length, cuisines[i].length <= 10
 foods[i]、cuisines[i] 由小写英文字母组成
 1 <= ratings[i] <= 108
 foods 中的所有字符串 互不相同
 在对 changeRating 的所有调用中，food 是系统中食物的名字。
 在对 highestRated 的所有调用中，cuisine 是系统中 至少一种 食物的烹饪方式。
 最多调用 changeRating 和 highestRated 总计 2 * 104 次

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/design-a-food-rating-system
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class FoodRatings {


    private Map<String, Food> nameFoodMap;
    private Map<String, TreeSet<Food>> cuisineFoodMap;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        this.nameFoodMap = new HashMap<>();
        this.cuisineFoodMap = new HashMap<>();

        int n = foods.length;

        for (int i = 0; i < n; i++) {
            Food food = new Food(foods[i], cuisines[i], ratings[i]);
            nameFoodMap.put(foods[i], food);
            TreeSet<Food> treeSet = cuisineFoodMap.getOrDefault(cuisines[i], new TreeSet<Food>((o1, o2) -> {
                int compare = Integer.compare(o2.rate, o1.rate);
                if (compare != 0) {
                    return compare;
                }
                return o1.foodNme.compareTo(o2.foodNme);
            }));
            treeSet.add(food);
            cuisineFoodMap.put(cuisines[i], treeSet);
        }

    }

    public void changeRating(String food, int newRating) {
        // 通过 food name 找到 food 改变
        Food target = nameFoodMap.get(food);
        if (target == null) {
            return;
        }

        // 对应 tresset 找到 并修改 tresert
        TreeSet<Food> treeSet = cuisineFoodMap.get(target.cuisine);
        treeSet.remove(target);

        target.rate = newRating;
        treeSet.add(target);
    }

    public String highestRated(String cuisine) {
        TreeSet<Food> orDefault = cuisineFoodMap.getOrDefault(cuisine, new TreeSet<>());
        if (orDefault.isEmpty()) {
            return "";
        }
        Food first = orDefault.first();
        return first.foodNme;
    }

    class Food {
        public String foodNme;
        // 种类
        public String cuisine;
        // 评分
        public int rate;

        public Food(String foodNme, String cuisine, int rate) {
            this.foodNme = foodNme;
            this.cuisine = cuisine;
            this.rate = rate;
        }

    }

    public static void main(String[] args) {
        String[] foods = new String[] {
                "czopaaeyl","lxoozsbh","kbaxapl"
        };
        String[] cuisines = new String[] {
                "dmnuqeatj","dmnuqeatj","dmnuqeatj"
        };
        int[] ratings = new int[] {
                11,2,15
        };
        FoodRatings foodRatings = new FoodRatings(foods, cuisines, ratings);

        foodRatings.changeRating("czopaaeyl",12);
        foodRatings.highestRated("dmnuqeatj");

        foodRatings.changeRating("kbaxapl",8);
        foodRatings.changeRating("lxoozsbh",5);
        foodRatings.highestRated("dmnuqeatj");
    }
}
