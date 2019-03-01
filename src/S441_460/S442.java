package S441_460;

import java.util.*;

/**
 * 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
 * <p>
 * 找到所有出现两次的元素。
 * <p>
 * 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
 * <p>
 * 示例：
 * <p>
 * 输入:
 * [4,3,2,7,8,2,3,1]
 * <p>
 * 输出:
 * [2,3]
 */
public class S442 {

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int t = Math.abs(nums[i]) - 1;  // 1 ≤ a[i] ≤ n
            if (nums[t] < 0)
                ans.add(Math.abs(nums[i]));
            else
                nums[t] = -1 * (nums[t]);
        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(new S442().findDuplicates(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }
}
