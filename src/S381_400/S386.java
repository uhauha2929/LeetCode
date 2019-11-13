package S381_400;

import java.util.*;

/**
 * 给定一个整数 n, 返回从 1 到 n 的字典顺序。
 * 例如，
 * 给定 n =1 3，返回 [1,10,11,12,13,2,3,4,5,6,7,8,9] 。
 * 请尽可能的优化算法的时间复杂度和空间复杂度。 输入的数据 n 小于等于 5,000,000。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lexicographical-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S386 {

    public List<Integer> lexicalOrder(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        list.sort(Comparator.comparing(Objects::toString));
        return list;
    }

    private List<Integer> res = new ArrayList<>();

    public List<Integer> lexicalOrder2(int n) {
        // 第一次从1~9
        for (int i = 1; i < 10; i++) {
            if (i > n) break;
            lexicalOrder2(i, n);
        }
        return res;
    }

    private void lexicalOrder2(int i, int n) {
        res.add(i);
        // 之后每次扩大10倍后从0加到9
        i *= 10;
        for (int j = 0; j < 10; j++) {
            int k = i + j;
            if (k > n) break;
            lexicalOrder2(k, n);
        }
    }


    public static void main(String[] args) {
        System.out.println(new S386().lexicalOrder2(100));
    }
}
