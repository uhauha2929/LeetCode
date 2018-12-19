import java.util.Arrays;
import java.util.Stack;

/**
 * 根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高的天数。如果之后都不会升高，请输入 0 来代替。
 * <p>
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 * <p>
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的都是 [30, 100] 范围内的整数。
 */
public class Solution739 {
    // 两个指针
    public int[] dailyTemperatures(int[] T) {
        if (T == null || T.length == 0)
            return T;
        for (int i = 0; i < T.length; i++) {
            int j = i + 1;
            for (; j < T.length; j++) {
                if (T[j] > T[i]) {
                    T[i] = j - i;
                    break;
                }
            }
            if (j == T.length) {
                T[i] = 0;
            }
        }
        return T;
    }

    // 递减栈
    public int[] dailyTemperatures2(int[] T) {
        if (T == null || T.length == 0)
            return T;
        int[] result = new int[T.length];
        // 按值递减保存原数组的下标
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < T.length; i++) {
            // 如果栈不为空，依次弹出小于当前元素的栈顶元素，并同时记录下标间隔
            while (!stack.empty() && T[i] > T[stack.peek()]) {
                int index = stack.pop();
                result[index] = i - index;
            }
            // 如果栈为空或栈顶元素大于当前元素，则压入栈
            stack.push(i);
        }
        return result;
    }

    public int[] dailyTemperatures3(int[] T) {
        if (T == null || T.length == 0)
            return T;
        int[] result = new int[T.length];
        int[] stack = new int[T.length]; // 用int数组代替stack
        int top = -1;
        for (int i = 0; i < T.length; i++) {
            while (top > -1 && T[i] > T[stack[top]]) {
                int index = stack[top--];
                result[index] = i - index;
            }
            stack[++top] = i;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(Arrays.toString(new Solution739()
                .dailyTemperatures3(nums)));
    }
}
