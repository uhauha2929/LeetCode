package S101_120;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * <p>
 * 输入: 5
 * 输出:
 * * [
 * *      [1],
 * *     [1,1],
 * *    [1,2,1],
 * *   [1,3,3,1],
 * *  [1,4,6,4,1]
 * * ]
 */
public class S118 {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows <= 0)
            return res;
        res.add(Collections.singletonList(1));
        if (numRows == 1)
            return res;
        res.add(Arrays.asList(1, 1));
        for (int i = 3; i <= numRows; i++) {
            List<Integer> row = new ArrayList<>();
            row.add(1);

            List<Integer> lastRow = res.get(res.size() - 1);
            for (int j = 1; j < i - 1; j++) {
                row.add(lastRow.get(j - 1) + lastRow.get(j));
            }
            row.add(1);

            res.add(row);
        }
        return res;
    }

    /**
     * 二项式定理
     * c^k_n = c^{k-1}_n *(n-k+1)/k
     */
    public List<List<Integer>> generate2(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 1; i <= numRows; i++) {
            List<Integer> cur = new ArrayList<>();
            cur.add(1);
            long num = 1;
            for (int j = 1; j < i; j++) {
                num = num * (i - j) / j;
                cur.add((int) num);
            }
            res.add(cur);
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new S118().generate2(6));
    }
}
