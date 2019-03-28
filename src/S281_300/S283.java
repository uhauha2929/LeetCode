package S281_300;

import java.util.Arrays;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 * <p>
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 */
public class S283 {

    public void moveZeroes(int[] nums) {
        int j = 0; // j始终比i慢
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i]; // 把不为0的数放到开头
            }
        }
        // 剩余填充0
        Arrays.fill(nums, j, nums.length, 0);
    }


    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0, 3, 12};
        new S283().moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
}
