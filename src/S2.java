import java.util.ArrayList;

/**
 * 给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class S2 {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            ArrayList<Integer> values = new ArrayList<>();
            ListNode cur = this;
            do {
                values.add(cur.val);
                cur = cur.next;
            } while (cur != null);
            return values.toString();
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        int temp = 0;
        ListNode head = new ListNode(0);
        ListNode cur3 = head;
        while (cur1 != null && cur2 != null) {
            temp = (cur1.val + cur2.val + temp);
            ListNode node = new ListNode(temp % 10);
            temp /= 10;
            cur3.next = node;
            cur3 = node;

            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        if (cur1 != null) {
            cur3.next = cur1;
            if (temp != 0) {
                ListNode pre = cur1;
                while (cur1 != null && temp != 0) {
                    temp += cur1.val;
                    cur1.val = temp % 10;
                    temp = temp / 10;
                    pre = cur1;
                    cur1 = cur1.next;
                }
                if (temp != 0) {
                    pre.next = new ListNode(temp);
                }
            }
        } else if (cur2 != null) {
            cur3.next = cur2;
            ListNode pre = null;
            while (cur2 != null && temp != 0) {
                temp += cur2.val;
                cur2.val = temp % 10;
                temp = temp / 10;
                pre = cur2;
                cur2 = cur2.next;
            }
            if (temp != 0) {
                pre.next = new ListNode(temp);
            }
        } else {
            if (temp != 0) {
                cur3.next = new ListNode(temp);
            }
        }
        return head.next;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode node = new ListNode(0);
        ListNode n1 = l1, n2 = l2, t = node;
        int sum = 0;
        while (n1 != null || n2 != null) {
            sum /= 10;
            if (n1 != null) {
                sum += n1.val;
                n1 = n1.next;
            }
            if (n2 != null) {
                sum += n2.val;
                n2 = n2.next;
            }
            t.next = new ListNode(sum % 10);
            t = t.next;
        }
        if (sum / 10 != 0) t.next = new ListNode(1);
        return node.next;
    }


    public static void main(String[] args) {
        ListNode l1 = new ListNode(6);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(8);
        l2.next.next.next = new ListNode(9);

        System.out.println(new S2().addTwoNumbers(l1, l2));
    }
}
