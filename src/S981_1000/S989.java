package S981_1000;

import java.util.LinkedList;
import java.util.List;

/**
 * 对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。
 * 给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式。
 * 示例 1：
 * 输入：A = [1,2,0,0], K = 34
 * 输出：[1,2,3,4]
 * 解释：1200 + 34 = 1234
 * 解释 2：
 * 输入：A = [2,7,4], K = 181
 * 输出：[4,5,5]
 * 解释：274 + 181 = 455
 * 示例 3：
 * 输入：A = [2,1,5], K = 806
 * 输出：[1,0,2,1]
 * 解释：215 + 806 = 1021
 * 示例 4：
 * 输入：A = [9,9,9,9,9,9,9,9,9,9], K = 1
 * 输出：[1,0,0,0,0,0,0,0,0,0,0]
 * 解释：9999999999 + 1 = 10000000000
 * 提示：
 * 1 <= A.length <= 10000
 * 0 <= A[i] <= 9
 * 0 <= K <= 10000
 * 如果 A.length > 1，那么 A[0] != 0
 */
public class S989 {

    public List<Integer> addToArrayForm(int[] A, int K) {
        LinkedList<Integer> res = new LinkedList<>();
        int carry = 0, sum;
        for (int i = A.length - 1; i >= 0 || K > 0; i--) {
            sum = (i >= 0 ? A[i] : 0) + K % 10 + carry;
            K /= 10;
            carry = sum / 10;
            res.addFirst(sum % 10);
        }
        if (carry != 0) res.addFirst(1);
        return res;
    }

    public List<Integer> addToArrayForm2(int[] A, int K) {
        int sum = K;
        LinkedList<Integer> ans = new LinkedList<>();
        for (int i = A.length - 1; i >= 0 || sum > 0; i--) {
            if (i >= 0) sum += A[i];
            ans.addFirst(sum % 10);
            sum /= 10;
        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(new S989().addToArrayForm(new int[]{9, 9}, 121));
    }
}
