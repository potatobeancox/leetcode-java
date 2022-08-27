package com.potato.study.leetcodecn.p01757.t001;

--  1083. 销售分析 II
--
-- Table: Product

-- +--------------+---------+
-- | Column Name  | Type    |
-- +--------------+---------+
-- | product_id   | int     |
-- | product_name | varchar |
-- | unit_price   | int     |
-- +--------------+---------+
-- product_id 是这张表的主键
-- Table: Sales
--
-- +-------------+---------+
-- | Column Name | Type    |
-- +-------------+---------+
-- | seller_id   | int     |
-- | product_id  | int     |
-- | buyer_id    | int     |
-- | sale_date   | date    |
-- | quantity    | int     |
-- | price       | int     |
-- +------ ------+---------+
-- 这个表没有主键，它可以有重复的行.
-- product_id 是 Product 表的外键.
-- 编写一个 SQL 查询，查询购买了 S8 手机却没有购买 iPhone 的买家。注意这里 S8 和 iPhone 是 Product 表中的产品。
--
-- 查询结果格式如下图表示：
--
-- Product table:
-- +------------+--------------+------------+
-- | product_id | product_name | unit_price |
-- +------------+--------------+------------+
-- | 1          | S8           | 1000       |
-- | 2          | G4           | 800        |
-- | 3          | iPhone       | 1400       |
-- +------------+--------------+------------+
--
-- Sales table:
-- +-----------+------------+----------+------------+----------+-------+
-- | seller_id | product_id | buyer_id | sale_date  | quantity | price |
-- +-----------+------------+----------+------------+----------+-------+
-- | 1         | 1          | 1        | 2019-01-21 | 2        | 2000  |
-- | 1         | 2          | 2        | 2019-02-17 | 1        | 800   |
-- | 2         | 1          | 3        | 2019-06-02 | 1        | 800   |
-- | 3         | 3          | 3        | 2019-05-13 | 2        | 2800  |
-- +-----------+------------+----------+------------+----------+-------+
--
-- Result table:
-- +-------------+
-- | buyer_id    |
-- +-------------+
-- | 1           |
-- +-------------+
-- id 为 1 的买家购买了一部 S8，但是却没有购买 iPhone，而 id 为 3 的买家却同时购买了这 2 部手机。
--
-- 来源：力扣（LeetCode）
-- 链接：https://leetcode.cn/problems/sales-analysis-ii
-- 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

-- having 对分组之后的结果 进行筛选 where 对所有结果进行筛选
-- https://baijiahao.baidu.com/s?id=1721307811100843867&wfr=spider&for=pc

SELECT
  Sales.buyer_id as buyer_id
FROM Sales
INNER JOIN Product USING(product_id)
GROUP BY Sales.buyer_id
HAVING COUNT(if(Product.product_name = 'S8', 1, NULL)) > 0
  AND COUNT(if(Product.product_name = 'iPhone', 1, NULL)) <= 0



