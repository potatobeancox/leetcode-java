package com.potato.study.leetcodecn.p01757.t001;

--  1484. 按日期分组销售产品
--
-- 表 Activities：
--
-- +-------------+---------+
-- | 列名         | 类型    |
-- +-------------+---------+
-- | sell_date   | date    |
-- | product     | varchar |
-- +-------------+---------+
-- 此表没有主键，它可能包含重复项。
-- 此表的每一行都包含产品名称和在市场上销售的日期。
--  
--
-- 编写一个 SQL 查询来查找每个日期、销售的不同产品的数量及其名称。
-- 每个日期的销售产品名称应按词典序排列。
-- 返回按 sell_date 排序的结果表。
-- 查询结果格式如下例所示。
--
--  
--
-- 示例 1:
--
-- 输入：
-- Activities 表：
-- +------------+-------------+
-- | sell_date  | product     |
-- +------------+-------------+
-- | 2020-05-30 | Headphone   |
-- | 2020-06-01 | Pencil      |
-- | 2020-06-02 | Mask        |
-- | 2020-05-30 | Basketball  |
-- | 2020-06-01 | Bible       |
-- | 2020-06-02 | Mask        |
-- | 2020-05-30 | T-Shirt     |
-- +------------+-------------+
-- 输出：
-- +------------+----------+------------------------------+
-- | sell_date  | num_sold | products                     |
-- +------------+----------+------------------------------+
-- | 2020-05-30 | 3        | Basketball,Headphone,T-shirt |
-- | 2020-06-01 | 2        | Bible,Pencil                 |
-- | 2020-06-02 | 1        | Mask                         |
-- +------------+----------+------------------------------+
-- 解释：
-- 对于2020-05-30，出售的物品是 (Headphone, Basketball, T-shirt)，按词典序排列，并用逗号 ',' 分隔。
-- 对于2020-06-01，出售的物品是 (Pencil, Bible)，按词典序排列，并用逗号分隔。
-- 对于2020-06-02，出售的物品是 (Mask)，只需返回该物品名。
-- 通过次数15,657提交次数23,067
--
-- 来源：力扣（LeetCode）
-- 链接：https://leetcode.cn/problems/group-sold-products-by-the-date
-- 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
--


-- https://blog.csdn.net/qq_35531549/article/details/90383022
-- DISTINCT product 默认升序

-- https://leetcode.cn/problems/group-sold-products-by-the-date/solution/by-jam007-hhi4/

SELECT sell_date, count(DISTINCT product) as num_sold, GROUP_CONCAT(DISTINCT product) as products
FROM Activities GROUP BY sell_date ORDER BY sell_date