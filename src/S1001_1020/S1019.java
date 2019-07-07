package S1001_1020;

import util.ListNode;

import java.util.*;

/**
 * 给出一个以头节点 head 作为第一个节点的链表。链表中的节点分别编号为：node_1, node_2, node_3, ... 。
 * <p>
 * 每个节点都可能有下一个更大值（next larger value）：对于 node_i，如果其 next_larger(node_i) 是 node_j.val，那么就有 j > i 且  node_j.val > node_i.val，而 j 是可能的选项中最小的那个。如果不存在这样的 j，那么下一个更大值为 0 。
 * <p>
 * 返回整数答案数组 answer，其中 answer[i] = next_larger(node_{i+1}) 。
 * <p>
 * 注意：在下面的示例中，诸如 [2,1,5] 这样的输入（不是输出）是链表的序列化表示，其头节点的值为 2，第二个节点值为 1，第三个节点值为 5 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[2,1,5]
 * 输出：[5,5,0]
 * 示例 2：
 * <p>
 * 输入：[2,7,4,3,5]
 * 输出：[7,0,5,5,0]
 * 示例 3：
 * <p>
 * 输入：[1,7,5,1,9,2,5,1]
 * 输出：[7,9,9,9,0,5,0,0]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 对于链表中的每个节点，1 <= node.val <= 10^9
 * 给定列表的长度在 [0, 10000] 范围内
 */
public class S1019 {

    // 类似solution496, 739
    public int[] nextLargerNodes(ListNode head) {
        int[] stack = new int[10000];
        int[] res = new int[10000];
        int[] temp = new int[10000];
        int top = -1, i = 0;
        ListNode node = head;
        while (node != null) {
            while (top != -1 && temp[stack[top]] < node.val)
                res[stack[top--]] = node.val;
            stack[++top] = i;
            temp[i++] = node.val;
            node = node.next;
        }
        return Arrays.copyOf(res, i);
    }

    public int[] nextLargerNodes2(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int[] res = new int[list.size()];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < list.size(); i++) {
            while (!stack.isEmpty() && list.get(stack.peek()) < list.get(i)) {
                res[stack.pop()] = list.get(i);
            }
            stack.push(i);
        }
        return res;
    }


    public static void main(String[] args) {
        int[] res = new S1019().nextLargerNodes(
                new ListNode(new int[]{4, 6, 3, 2, 6, 3, 9, 9, 3}));
        System.out.println(Arrays.toString(res));
    }
}
