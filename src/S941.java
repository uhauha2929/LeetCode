/**
 * 给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。
 * <p>
 * 让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：
 * <p>
 * A.length >= 3
 * 在 0 < i < A.length - 1 条件下，存在 i 使得：
 * A[0] < A[1] < ... A[i-1] < A[i]
 * A[i] > A[i+1] > ... > A[B.length - 1]
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[2,1]
 * 输出：false
 * 示例 2：
 * <p>
 * 输入：[3,5,5]
 * 输出：false
 * 示例 3：
 * <p>
 * 输入：[0,3,2,1]
 * 输出：true
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= A.length <= 10000
 * 0 <= A[i] <= 10000
 */
public class S941 {

    public boolean validMountainArray(int[] A) {
        if (A.length < 3) return false;
        int i = 1;
        while (i < A.length) {
            if (A[i - 1] < A[i]) {
                ++i;
            } else if (A[i - 1] == A[i])
                return false;
            else break;
        }
        if (i == A.length || i == 1)
            return false;
        while (i < A.length) {
            if (A[i - 1] > A[i]) {
                ++i;
            } else if (A[i - 1] == A[i])
                return false;
            else break;
        }
        return i == A.length;
    }


    public static void main(String[] args) {
        System.out.println(new S941().validMountainArray(new int[]{3, 5, 5}));
    }
}
