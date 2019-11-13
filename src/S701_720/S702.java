package S701_720;

import java.util.Arrays;

/**
 * 给定一个升序整数数组，写一个函数搜索 nums 中数字 target。如果 target 存在，返回它的下标，否则返回 -1。注意，这个数组的大小是未知的。你只可以通过 ArrayReader 接口访问这个数组，ArrayReader.get(k) 返回数组中第 k 个元素（下标从 0 开始）。
 * 你可以认为数组中所有的整数都小于 10000。如果你访问数组越界，ArrayReader.get 会返回 2147483647。
 * 样例 1：
 * 输入: array = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 存在在 nums 中，下标为 4
 * 样例 2：
 * 输入: array = [-1,0,3,5,9,12], target = 2
 * 输出: -1
 * 解释: 2 不在数组中所以返回 -1
 * 注释 ：
 * 你可以认为数组中所有元素的值互不相同。
 * 数组元素的值域是 [-9999, 9999]。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-a-sorted-array-of-unknown-size
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S702 {

    interface ArrayReader {
        int get(int k);
    }

    public static class MyArrayReader implements ArrayReader {
        private int[] array;

        public MyArrayReader(int[] array) {
            Arrays.sort(array);
            this.array = array;
        }

        @Override
        public int get(int k) {
            if (k >= 0 && k < array.length)
                return array[k];
            return Integer.MAX_VALUE;
        }
    }

    public int search(ArrayReader reader, int target) {
        int l = 0, r = 1;
        while (reader.get(r) != Integer.MAX_VALUE)
            r *= 2;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (reader.get(m) < target) {
                l = m + 1;
            } else if (reader.get(m) > target) {
                r = m;
            } else {
                return m;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        ArrayReader reader = new MyArrayReader(new int[]{-1, 0, 3, 5, 9, 12});
        System.out.println(new S702().search(reader, 12));
    }
}
