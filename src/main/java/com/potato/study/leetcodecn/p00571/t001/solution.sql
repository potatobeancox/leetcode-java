--
-- 571. 给定数字的频率查询中位数
--
--
-- SQL架构
-- Numbers 表：
--
-- +-------------+------+
-- | Column Name | Type |
-- +-------------+------+
-- | num         | int  |
-- | frequency   | int  |
-- +-------------+------+
-- num 是这张表的主键。这张表的每一行表示某个数字在该数据库中的出现频率。
--
--
-- 中位数 是将数据样本中半数较高值和半数较低值分隔开的值。
-- 编写一个 SQL 查询，解压 Numbers 表，报告数据库中所有数字的 中位数 。结果四舍五入至 一位小数 。
--
-- 查询结果如下例所示。
--
--
--
-- 示例：
--
-- 输入：
-- Numbers 表：
-- +-----+-----------+
-- | num | frequency |
-- +-----+-----------+
-- | 0   | 7         |
-- | 1   | 1         |
-- | 2   | 3         |
-- | 3   | 1         |
-- +-----+-----------+
-- 输出：
-- +--------+
-- | median |
-- +--------+
-- | 0.0    |
-- +--------+
-- 解释：
-- 如果解压这个 Numbers 表，可以得到 [0, 0, 0, 0, 0, 0, 0, 1, 2, 2, 2, 3] ，所以中位数是 (0 + 0) / 2 = 0 。


select
    round(avg(num), 1) as median
from (
    select
        Numbers.*,
        sum(frequency) over(order by num asc) as rank1,
        sum(frequency) over(order by num desc) as rank2,
        sum(frequency) over() as rr
    from Numbers
) as tab
where tab.rank1 >= tab.rr/2 and tab.rank2 >= tab.rr/2
