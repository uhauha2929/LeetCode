package S141_160;

/**
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 * 示例:
 * <p>
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 */

public class S155 {

    static class MinStack {

        private int top = -1;
        private int[] minIndex = new int[9999];  // 保存当前栈顶位置最小值的索引
        private int[] stack = new int[9999];

        public MinStack() {
        }

        public void push(int x) {
            stack[++top] = x;
            if (top > 0)
                minIndex[top] = x < stack[minIndex[top - 1]] ? top : minIndex[top - 1];
        }

        public void pop() {
            if (top > -1)
                top--;
        }

        public int top() {
            return stack[top];
        }

        public int getMin() {
            return stack[minIndex[top]];
        }
    }


    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(2147483646);
        minStack.push(2147483646);
        minStack.push(2147483647);
        System.out.println(minStack.top());
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        minStack.push(2147483647);
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
        minStack.push(-2147483648);
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
    }
}
