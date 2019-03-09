package S881_900;

/**
 * 如果数组是单调递增或单调递减的，那么它是单调的。
 * <p>
 * 如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。
 * <p>
 * 当给定的数组 A 是单调数组时返回 true，否则返回 false。
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1,2,2,3]
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：[6,5,4,4]
 * 输出：true
 * 示例 3：
 * <p>
 * 输入：[1,3,2]
 * 输出：false
 * 示例 4：
 * <p>
 * 输入：[1,2,4,5]
 * 输出：true
 * 示例 5：
 * <p>
 * 输入：[1,1,1]
 * 输出：true
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 50000
 * -100000 <= A[i] <= 100000
 */
public class S896 {

    public boolean isMonotonic(int[] A) {
        if (A == null || A.length < 3) return true;
        int i = 0;
        while (i < A.length - 1) {
            if (A[i] < A[i + 1]) {
                while (++i < A.length - 1) {
                    if (A[i] > A[i + 1])
                        return false;
                }
            } else if (A[i] > A[i + 1]) {
                while (++i < A.length - 1) {
                    if (A[i] < A[i + 1])
                        return false;
                }
            }
            i++;
        }
        return true;
    }

    // 类似写法
    public boolean isMonotonic2(int[] A) {
        int store = 0;
        for (int i = 0; i < A.length - 1; ++i) {
            int c = Integer.compare(A[i], A[i + 1]);
            // c = 0, -1 或 1
            if (c != 0) {
                if (c != store && store != 0)
                    return false;
                store = c;
            }
        }
        return true;
    }

    // 类似写法
    public boolean isMonotonic3(int[] A) {
        if (A == null || A.length < 3) return true;
        // 和最后一个比较
        if (A[0] < A[A.length - 1]) { // 单调增
            for (int i = 0; i < A.length - 1; i++) {
                if (A[i] > A[i + 1])
                    return false;
            }
        } else { // 否则默认是单调减
            for (int i = 0; i < A.length - 1; i++) {
                if (A[i] < A[i + 1])
                    return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(new S896().isMonotonic3(new int[]{1, 2, 3, 2}));
    }
}
