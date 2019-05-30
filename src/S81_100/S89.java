package S81_100;

import java.util.ArrayList;
import java.util.List;

/**
 * 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
 * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。格雷编码序列必须以 0 开头。
 * 示例 1:
 * 输入: 2
 * 输出: [0,1,3,2]
 * 解释:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * 对于给定的 n，其格雷编码序列并不唯一。
 * 例如，[0,2,3,1] 也是一个有效的格雷编码序列。
 * 00 - 0
 * 10 - 2
 * 11 - 3
 * 01 - 1
 * 示例 2:
 * 输入: 0
 * 输出: [0]
 * 解释: 我们定义格雷编码序列必须以 0 开头。
 * 给定编码总位数为 n 的格雷编码序列，其长度为 2^n。当 n = 0 时，长度为 20 = 1。
 * 因此，当 n = 0 时，其格雷编码序列为 [0]。
 */
public class S89 {

    /**
     * n=3
     * 0, (0+1)
     * 0, 1, (1 + 2), (0 + 2)
     * 0, 1, 3, 2, (2 + 4), (3 + 4), (1 + 4), (0 + 4)
     */
    public List<Integer> grayCode(int n) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        if (n == 0) return list;
        int increase = 1;
        for (int i = 0; i < n; i++) {
            for (int j = list.size() - 1; j >= 0; j--) {
                list.add(list.get(j) + increase);
            }
            increase *= 2;
        }
        return list;
    }

    public List<Integer> grayCode2(int n) {
        int len = (int) Math.pow(2, n);
        List<Integer> ans = new ArrayList<>(len);
        for (int i = 0; i < len; i++) {
            ans.add(i ^ (i >> 1));
        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(new S89().grayCode(3));
    }
}
