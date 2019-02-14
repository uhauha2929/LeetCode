/**
 * 我们把符合下列属性的数组 A 称作山脉：
 * <p>
 * A.length >= 3
 * 存在 0 < i < A.length - 1 使得A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]
 * 给定一个确定为山脉的数组，返回任何满足 A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1] 的 i 的值。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[0,1,0]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：[0,2,1,0]
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 3 <= A.length <= 10000
 * 0 <= A[i] <= 10^6
 * A 是如上定义的山脉
 */
public class S852 {

    public int peakIndexInMountainArray(int[] A) {
        for (int i = 1; i < A.length - 1; i++) {
            if (A[i] > A[i + 1])
                return i;
        }
        throw new RuntimeException();
    }

    // 二分查找
    public int peakIndexInMountainArray2(int[] A) {
        int l = 0, r = A.length - 1;
        while (l < r) {
            int m = (l + r) / 2;
            if (A[m - 1] < A[m] && A[m] > A[m + 1]) {
                return m;
            } else if (A[m - 1] < A[m]) {
                l = m;
            } else if (A[m] > A[m + 1]) {
                r = m;
            }
        }
        throw new RuntimeException();
    }


    public static void main(String[] args) {
        System.out.println(new S852().peakIndexInMountainArray2(
                new int[]{18, 29, 38, 59, 98, 100, 99, 98, 90}));
    }
}