package S01_20;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * <p>
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * <p>
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * <p>
 * 示例 1:
 * <p>
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * 则中位数是 2.0
 * 示例 2:
 * <p>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * 则中位数是 (2 + 3)/2 = 2.5
 */
public class S4 {

    // O(m+n) 先合并成有序的, 再取中间值
    // 也可以直接取第k个数, 其中第k个数为我们要找的中位数
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] tmp = new int[nums1.length + nums2.length];
        int i = 0, j = 0, k = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                tmp[k++] = nums1[i++];
            } else {
                tmp[k++] = nums2[j++];
            }
        }
        while (i < nums1.length) {
            tmp[k++] = nums1[i++];
        }
        while (j < nums2.length) {
            tmp[k++] = nums2[j++];
        }
        if (tmp.length % 2 == 0) {
            return (tmp[tmp.length / 2 - 1] + tmp[tmp.length / 2]) / 2.0;
        } else {
            return tmp[tmp.length / 2];
        }
    }

    /**
     * *         left_part          |        right_part
     * *     A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
     * *     B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]
     * 因为两个数组是有序的, 需要找到i和j使得将两个数组的元素可以划分为两部分.
     * 只要满足:
     * 1. 两个部分的元素个数相同
     * 2. B[j-1] <= A[i] 且 A[i-1] <= B[j], 其中j = (m+n+1) / 2 - i
     * <p>
     * 当找到目标对象i时, 中位数为:
     * 当 m + n 为奇数: max(A[i-1], B[j-1])
     * 当 m + n 为偶数: [max(A[i-1], B[j-1]) + min(A[i], B[j])] / 2
     * <p>
     * 时间复杂度O(log(min(m,n)))
     */
    public double findMedianSortedArrays2(int[] A, int[] B) {
        if (A.length > B.length)  // A 始终是长度最小的
            return findMedianSortedArrays(B, A);
        int iMin = 0, iMax = A.length;  // i为左半部分的个数, j为有半部分的个数
        int i, j;
        int k = (A.length + B.length + 1) / 2;
        while (iMin <= iMax) {
            i = (iMin + iMax) / 2;  // 对A进行二分
            j = k - i;
            if (i < iMax && A[i] < B[j - 1]) // i不能超过临界值
                iMin = i + 1; // i太小了
            else if (i > iMin && A[i - 1] > B[j]) {  // i不能超过临界值
                iMax = i - 1; // i太大了
            } else {
                int maxLeft, minRight;
                if (i == 0)
                    maxLeft = B[j - 1];
                else if (j == 0)
                    maxLeft = A[i - 1];
                else
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                if ((A.length + B.length) % 2 == 1)
                    return maxLeft;

                if (i == A.length)
                    minRight = B[j];
                else if (j == B.length)
                    minRight = A[i];
                else
                    minRight = Math.min(A[i], B[j]);
                return (maxLeft + minRight) / 2.0;
            }

        }
        throw new RuntimeException("NOT FOUND!");
    }

    public static void main(String[] args) {
        System.out.println(new S4().findMedianSortedArrays2(
                new int[]{3, 4}, new int[]{1, 2}));
    }
}
