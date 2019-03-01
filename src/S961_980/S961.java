package S961_980;

import java.util.HashSet;
import java.util.Set;

/**
 * 在大小为 2N 的数组 A 中有 N+1 个不同的元素，其中有一个元素重复了 N 次。
 * 返回重复了 N 次的那个元素。
 * <p>
 * 示例 1：
 * 输入：[1,2,3,3]
 * 输出：3
 * 示例 2：
 * <p>
 * 输入：[2,1,2,5,3,2]
 * 输出：2
 * 示例 3：
 * <p>
 * 输入：[5,1,5,2,5,3,5,4]
 * 输出：5
 * <p>
 * 提示：
 * <p>
 * 4 <= A.length <= 10000
 * 0 <= A[i] < 10000
 * A.length 为偶数
 */
public class S961 {

    /**
     * 只要从头遍历遇到重复的就是要找的数。
     * 因为题目要求有N+1个不同的元素，并且有一个元素出现了N次，所以2N个数里只有一个数是重复的，即我们要找的数。
     */
    public int repeatedNTimes(int[] A) {
        Set<Integer> set = new HashSet<>();
        for (int num : A) {
            if (!set.add(num))
                return num;
        }
        throw new RuntimeException();
    }


    public static void main(String[] args) {
        System.out.println(new S961().repeatedNTimes(new int[]{5, 1, 5, 2, 5, 3, 5, 4}));
    }
}
