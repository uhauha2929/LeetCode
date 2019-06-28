/*
有一个courses 表 ，有: student (学生) 和 class (课程)
请列出所有超过或等于5名学生的课。
例如,表:
+---------+------------+
| student | class      |
+---------+------------+
| A       | Math       |
| B       | English    |
| C       | Math       |
| D       | Biology    |
| E       | Math       |
| F       | Computer   |
| G       | Math       |
| H       | Math       |
| I       | Math       |
+---------+------------+
应该输出:
+---------+
| class   |
+---------+
| Math    |
+---------+
Note:
学生在每个课中不应被重复计算。
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/classes-more-than-5-students
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

SELECT B.CLASS
FROM (SELECT A.CLASS,
             COUNT(A.CLASS) C
      FROM (SELECT DISTINCT *
            FROM COURSES) A
      GROUP BY A.CLASS) B
WHERE B.C >= 5;
-- GROUP BY子句必须出现在WHERE子句之后, ORDER BY子句之前.
-- HAVING语句必须在ORDER BY子句之后(where先执行, 再groupby分组; groupby先分组,having在执行)
SELECT A.CLASS
FROM (SELECT DISTINCT *
      FROM COURSES) A
GROUP BY A.CLASS
HAVING COUNT(A.CLASS) >= 5;

SELECT CLASS
FROM COURSES
GROUP BY CLASS
HAVING COUNT(DISTINCT STUDENT) >= 5;