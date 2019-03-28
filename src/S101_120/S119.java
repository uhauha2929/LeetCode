package S101_120;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 * <p>
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出: [1,3,3,1]
 * 进阶：
 * <p>
 * 你可以优化你的算法到 O(k) 空间复杂度吗？
 */
public class S119 {

    // 类似solution118
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        res.add(1);
        if (rowIndex == 0)
            return res;

        for (int i = 1; i <= rowIndex; i++) {
            List<Integer> temp = new ArrayList<>();
            temp.add(1);
            for (int j = 1; j < i; j++) {
                temp.add(res.get(j - 1) + res.get(j));
            }
            temp.add(1);
            res = temp;
        }
        return res;
    }


    public List<Integer> getRow2(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        res.add(1);
        long num = 1;
        for (int j = 1; j < rowIndex + 1; j++) {
            num = num * (rowIndex - j + 1) / j;
            res.add((int) num);
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new S119().getRow(3));
    }
}
