package S961_980;

import java.util.Arrays;

/**
 * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 * <p>
 * 示例 1：
 * <p>
 * 输入：[-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 示例 2：
 * <p>
 * 输入：[-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 10000
 * -10000 <= A[i] <= 10000
 * A 已按非递减顺序排序。
 */
public class S977 {

    // 时间复杂度O(nlogn)，空间复杂度O(n)
    public int[] sortedSquares(int[] A) {
        for (int i = 0; i < A.length; i++) {
            A[i] = A[i] * A[i];
        }
        Arrays.sort(A);
        return A;
    }

    /**
     * 先找到中点，然后分别对负数和正数比较排序。
     * 时间复杂度O(n)，空间复杂度O(n)
     */
    public int[] sortedSquares2(int[] A) {
        int i = 0;
        while (i < A.length && A[i] < 0) {
            i++;
        }
        int j = i - 1;
        int k = 0;
        int[] res = new int[A.length];
        while (j >= 0 && i < A.length) {
            if (Math.abs(A[i]) < Math.abs(A[j])) {
                res[k++] = (int) Math.pow(A[i++], 2);
            } else {
                res[k++] = (int) Math.pow(A[j--], 2);
            }
        }
        while (i < A.length) {
            res[k++] = (int) Math.pow(A[i++], 2);
        }
        while (j >= 0) {
            res[k++] = (int) Math.pow(A[j--], 2);
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(new S977().sortedSquares2(new int[]{-7, -3, 2, 3, 11})));
    }
}
