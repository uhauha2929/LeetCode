/*
 销售表 Sales：
+-------------+-------+
| Column Name | Type  |
+-------------+-------+
| sale_id     | int   |
| product_id  | int   |
| year        | int   |
| quantity    | int   |
| price       | int   |
+-------------+-------+
sale_id 是此表的主键。
产品 ID 是产品表的外键。
请注意，价格是按每单位计的。
产品表 Product：
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| product_id   | int     |
| product_name | varchar |
+--------------+---------+
产品 ID 是此表的主键。
编写一个 SQL 查询，选出每个销售产品的 第一年 的 产品 id、年份、数量 和 价格。
查询结果格式如下：
Sales table:
+---------+------------+------+----------+-------+
| sale_id | product_id | year | quantity | price |
+---------+------------+------+----------+-------+
| 1       | 100        | 2008 | 10       | 5000  |
| 2       | 100        | 2009 | 12       | 5000  |
| 7       | 200        | 2011 | 15       | 9000  |
+---------+------------+------+----------+-------+
Product table:
+------------+--------------+
| product_id | product_name |
+------------+--------------+
| 100        | Nokia        |
| 200        | Apple        |
| 300        | Samsung      |
+------------+--------------+
Result table:
+------------+------------+----------+-------+
| product_id | first_year | quantity | price |
+------------+------------+----------+-------+
| 100        | 2008       | 10       | 5000  |
| 200        | 2011       | 15       | 9000  |
+------------+------------+----------+-------+
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/product-sales-analysis-iii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
SELECT product_id, year AS first_year, quantity, price
FROM Sales
WHERE (product_id, year) in (SELECT product_id, MIN(year) AS first_year FROM Sales GROUP BY product_id);


SELECT s.product_id, t.first_year, s.quantity, s.price
FROM (SELECT product_id, MIN(year) AS first_year FROM Sales GROUP BY product_id) AS t,
     Sales AS s
WHERE t.product_id = s.product_id
  AND t.first_year = s.year;