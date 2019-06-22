package S641_660;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 集合 S 包含从1到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个元素复制了成了集合里面的另外一个元素的值，导致集合丢失了一个整数并且有一个元素重复。
 * 给定一个数组 nums 代表了集合 S 发生错误后的结果。你的任务是首先寻找到重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
 * 示例 1:
 * 输入: nums = [1,2,2,4]
 * 输出: [2,3]
 * 注意:
 * 给定数组的长度范围是 [2, 10000]。
 * 给定的数组是无序的。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/set-mismatch
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S645 {

    public int[] findErrorNums(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int[] res = {0, 0};
        for (int num : nums) {
            if (set.contains(num)) {
                res[0] = num;
            }
            set.add(num);
        }
        for (int i = 1; i < nums.length + 1; i++) {
            if (!set.contains(i)) {
                res[1] = i;
                break;
            }
        }
        return res;
    }

    public int[] findErrorNums2(int[] nums) {
        int[] count = new int[10001];
        for (int num : nums) {
            count[num]++;
        }
        int[] ans = new int[2];
        for (int i = 1; i < count.length; i++) {
            if (ans[0] > 0 && ans[1] > 0) break;
            if (count[i] == 2) ans[0] = i;
            else if (count[i] == 0) ans[1] = i;
        }
        return ans;
    }
    // Map改用布尔数组
    public int[] findErrorNums3(int[] nums) {
        boolean[] a = new boolean[nums.length + 1];
        int dup = -1;
        for (int num : nums) {
            if (a[num]) dup = num;
            a[num] = true;
        }
        int mis = -1;
        for (int i = 1; i < a.length; i++) {
            if (!a[i]) mis = i;
        }
        return new int[]{dup, mis};
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(new S645().findErrorNums2(new int[]{1, 2, 2, 4})));
    }
}
