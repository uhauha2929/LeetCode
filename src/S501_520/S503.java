package S501_520;

import java.util.*;

/**
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,1]
 * 输出: [2,-1,2]
 * 解释: 第一个 1 的下一个更大的数是 2；
 * 数字 2 找不到下一个更大的数；
 * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 * 注意: 输入数组的长度不会超过 10000。
 */
public class S503 {

    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        Arrays.fill(res, -1);
        ArrayDeque<Integer> stack = new ArrayDeque<>(); // 使用ArrayDeque双端队列代替栈效率高

        for (int i = 0; i < len * 2; i++) {
            int num = nums[(i + len) % len];
            while (!stack.isEmpty() && num > nums[stack.peek()]) {
                res[stack.pop()] = num;  // 不同于Solution496，这里栈里存放的是下标，map里不会重复
            }
            if (i < len) stack.push(i);
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(new S503().nextGreaterElements(new int[]{1, 3, 1, 2, 1})));
    }
}
