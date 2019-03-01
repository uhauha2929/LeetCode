package S441_460;

import utils.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给定两个非空链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储单个数字。将这两数相加会返回一个新的链表。
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * <p>
 * 进阶:
 * <p>
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 * <p>
 * 示例:
 * <p>
 * 输入: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出: 7 -> 8 -> 0 -> 7
 */
public class S445 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> s1 = new ArrayDeque<>();
        Deque<Integer> s2 = new ArrayDeque<>();
        ListNode cur = l1;
        while (cur != null) {
            s1.push(cur.val);
            cur = cur.next;
        }
        cur = l2;
        while (cur != null) {
            s2.push(cur.val);
            cur = cur.next;
        }
        ListNode ans = null;
        ListNode temp;
        int sum = 0;
        while (!s1.isEmpty() || !s2.isEmpty()) {
            if (!s1.isEmpty())
                sum += s1.pop();
            if (!s2.isEmpty())
                sum += s2.pop();
            temp = new ListNode(sum % 10);
            temp.next = ans;
            ans = temp;
            sum /= 10;
        }
        if (sum != 0) {
            temp = new ListNode(sum);
            temp.next = ans;
            ans = temp;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new S445().addTwoNumbers(
                new ListNode(new int[]{9, 4, 4, 3}),
                new ListNode(new int[]{7, 6, 4})));
    }
}
