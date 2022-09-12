package com.potato.study.leetcodecn.p01757.t001;

--  1677. 发票中的产品金额
--
--  SQL架构
-- Product 表：
--
-- +-------------+---------+
-- | Column Name | Type    |
-- +-------------+---------+
-- | product_id  | int     |
-- | name        | varchar |
-- +-------------+---------+
-- product_id 是这张表的主键
-- 表中含有产品 id 、产品名称。产品名称都是小写的英文字母，产品名称都是唯一的
-- Invoice 表：
--
-- +-------------+------+
-- | Column Name | Type |
-- +-------------+------+
-- | invoice_id  | int  |
-- | product_id  | int  |
-- | rest        | int  |
-- | paid        | int  |
-- | canceled    | int  |
-- | refunded    | int  |
-- +-------------+------+
-- invoice_id 发票 id ，是这张表的主键
-- product_id 产品 id
-- rest 应缴款项
-- paid 已支付金额
-- canceled 已取消金额
-- refunded 已退款金额
--
--
-- 要求写一个SQL查询，对于所有产品，返回每个产品的产品名称，以及全部发票累计的总应缴款项、总已支付金额、总已取消金额、总已退款金额。
--
-- 查询结果按 product_name 排序
--
-- 示例：
--
-- Product 表：
-- +------------+-------+
-- | product_id | name  |
-- +------------+-------+
-- | 0          | ham   |
-- | 1          | bacon |
-- +------------+-------+
-- Invoice table:
-- +------------+------------+------+------+----------+----------+
-- | invoice_id | product_id | rest | paid | canceled | refunded |
-- +------------+------------+------+------+----------+----------+
-- | 23         | 0          | 2    | 0    | 5        | 0        |
-- | 12         | 0          | 0    | 4    | 0        | 3        |
-- | 1          | 1          | 1    | 1    | 0        | 1        |
-- | 2          | 1          | 1    | 0    | 1        | 1        |
-- | 3          | 1          | 0    | 1    | 1        | 1        |
-- | 4          | 1          | 1    | 1    | 1        | 0        |
-- +------------+------------+------+------+----------+----------+
-- Result 表：
-- +-------+------+------+----------+----------+
-- | name  | rest | paid | canceled | refunded |
-- +-------+------+------+----------+----------+
-- | bacon | 3    | 3    | 3        | 3        |
-- | ham   | 2    | 4    | 5        | 3        |
-- +-------+------+------+----------+----------+
-- - bacon 的总应缴款项为 1 + 1 + 0 + 1 = 3
-- - bacon 的总已支付金额为 1 + 0 + 1 + 1 = 3
-- - bacon 的总已取消金额为 0 + 1 + 1 + 1 = 3
-- - bacon 的总已退款金额为 1 + 1 + 1 + 0 = 3
-- - ham 的总应缴款项为 2 + 0 = 2
-- - ham 的总已支付金额为 0 + 4 = 4
-- - ham 的总已取消金额为 5 + 0 = 5
-- - ham 的总已退款金额为 0 + 3 = 3

SELECT
  Product.name,
  ifnull(sum(Invoice.rest), 0) as rest,
  ifnull(sum(Invoice.paid), 0) as paid,
  ifnull(sum(Invoice.canceled), 0) as canceled,
  ifnull(sum(Invoice.refunded), 0) as refunded
FROM Invoice
right join Product using(product_id)
GROUP BY Product.product_id
ORDER BY Product.name asc
