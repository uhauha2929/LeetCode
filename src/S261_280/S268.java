package S261_280;

import java.util.Arrays;

/**
 * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
 * 示例 1:
 * 输入: [3,0,1]
 * 输出: 2
 * 示例 2:
 * 输入: [9,6,4,2,3,5,7,0,1]
 * 输出: 8
 * 说明:
 * 你的算法应具有线性时间复杂度。你能否仅使用额外常数空间来实现?
 */
public class S268 {

    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        int i = 0;
        for (; i < nums.length; i++) {
            if (nums[i] != i) break;
        }
        return i;
    }

    public int missingNumber2(int[] nums) {
        int sum1 = 0, sum2 = 0;
        for (int i = 0; i <= nums.length; i++) {
            sum1 += i;
        }
        for (int num : nums) {
            sum2 += num;
        }
        return sum1 - sum2;
    }

    // 位运算, i ^ i = 0, i ^ 0 = i
    public int missingNumber3(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans = ans ^ nums[i];
            ans = ans ^ i;
        }
        return ans ^ nums.length;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1};
        System.out.println(new S268().missingNumber2(nums));
    }
}
