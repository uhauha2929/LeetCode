package S1001_1020;

/**
 * 给定一个整数数组 A，只有我们可以将其划分为三个和相等的非空部分时才返回 true，否则返回 false。
 * 形式上，如果我们可以找出索引 i+1 < j 且满足 (A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... + A[j-1] == A[j] + A[j-1] + ... + A[A.length - 1]) 就可以将数组三等分。
 * 示例 1：
 * 输出：[0,2,1,-6,6,-7,9,1,2,0,1]
 * 输出：true
 * 解释：0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
 * 示例 2：
 * 输入：[0,2,1,-6,6,7,9,-1,2,0,1]
 * 输出：false
 * 示例 3：
 * 输入：[3,3,6,5,-2,2,5,1,-9,4]
 * 输出：true
 * 解释：3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4
 * 提示：
 * 3 <= A.length <= 50000
 * -10000 <= A[i] <= 10000
 */
public class S1013 {

    public boolean canThreePartsEqualSum(int[] A) {
        int sum = 0;
        for (int num : A)
            sum += num;
        if (sum % 3 != 0) return false;
        int average = sum / 3;
        int i = 0, j = A.length - 1;
        int lSum = 0, RSum = 0;
        while (i < j) {
            if (lSum != average)
                lSum += A[i++];
            if (RSum != average)
                RSum += A[j--];
            if (lSum == average && RSum == average)
                return true;
        }
        return false;
    }

    public boolean canThreePartsEqualSum2(int[] A) {
        int sum = 0;
        for (int num : A)
            sum += num;
        if (sum % 3 != 0) return false;
        int average = sum / 3;
        int count = 0, cur = 0;
        for (int num : A) {
            cur += num;
            if (cur == average) {
                cur = 0;
                if (++count == 2)
                    return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(new S1013().canThreePartsEqualSum(new int[]{
                0, 2, 1, -6, 6, -7, 9, 1, 2, 0, 1
        }));
    }
}
