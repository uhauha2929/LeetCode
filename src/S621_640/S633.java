package S621_640;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c。
 * 示例1:
 * 输入: 5
 * 输出: True
 * 解释: 1 * 1 + 2 * 2 = 5
 * 示例2:
 * 输入: 3
 * 输出: False
 */
public class S633 {

    public boolean judgeSquareSum(int c) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i <= (int) Math.sqrt(c); i++) {
            set.add(i * i);
            if (set.contains(c - i * i))
                return true;
        }
        return false;
    }

    public boolean judgeSquareSum2(int c) {
        for (int i = (int) Math.sqrt(c); i >= 0; i--) {
            if (i * i == c) return true;
            int j = c - i * i;
            int k = (int) Math.sqrt(j);
            if (k * k == j) return true;
        }
        return false;
    }

    public boolean judgeSquareSum3(int c) {
        int i = 0, j = (int) Math.sqrt(c);
        while (i <= j) {
            int sum = i * i + j * j;
            if (sum < c) i++;
            else if (sum > c) j--;
            else return true;
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(new S633().judgeSquareSum3(16));
    }
}
