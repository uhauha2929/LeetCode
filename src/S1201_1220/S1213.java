package S1201_1220;

import java.util.*;

/**
 * 给出三个均为 严格递增排列 的整数数组 arr1，arr2 和 arr3。
 * 返回一个由 仅 在这三个数组中 同时出现 的整数所构成的有序数组。
 * 示例：
 * 输入: arr1 = [1,2,3,4,5], arr2 = [1,2,5,7,9], arr3 = [1,3,4,5,8]
 * 输出: [1,5]
 * 解释: 只有 1 和 5 同时在这三个数组中出现.
 * 提示：
 * 1 <= arr1.length, arr2.length, arr3.length <= 1000
 * 1 <= arr1[i], arr2[i], arr3[i] <= 2000
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-three-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S1213 {

    // 可以用哈希表或Map统计个数，考虑到三个数组已经有序，可以用三个指针取交集
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        List<Integer> res = new ArrayList<>();
        int i = 0, j = 0, k = 0;
        while (i < arr1.length && j < arr2.length && k < arr3.length) {
            // 小的数往后移动相应的指针
            if (arr1[i] < arr2[j] || arr1[i] < arr3[k]) {
                i++;
            } else if (arr2[j] < arr1[i] || arr2[j] < arr3[k]) {
                j++;
            } else if (arr3[k] < arr1[i] || arr3[k] < arr2[j]) {
                k++;
            } else {
                // 如果没有一个数比其它两个数要小，则三数相等
                res.add(arr1[i]);
                i++;
                j++;
                k++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new S1213().arraysIntersection(
                new int[]{1, 2, 3, 4, 5},
                new int[]{1, 2, 5, 7, 9},
                new int[]{1, 3, 4, 5, 8}
        ));
    }
}
