/**
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 * <p>
 * 你应当保留两个分区中每个节点的初始相对位置。
 * <p>
 * 示例:
 * <p>
 * 输入: head = 1->4->3->2->5->2, x = 3
 * 输出: 1->2->2->4->3->5
 */
public class S86 {

    private static class ListNode {

        int val;

        ListNode next;


        ListNode(int x) {
            val = x;
        }

    }

    private void print(ListNode head) {
        if (head != null) {
            System.out.println(head.val);
            print(head.next);
        }
    }

    // 将链表分成两部分，最后将两部分连起来
    public ListNode partition(ListNode head, int x) {
        ListNode left = new ListNode(0);
        ListNode right = new ListNode(0);
        ListNode cur1 = left;
        ListNode cur2 = right;
        while (head != null) {
            if (head.val < x) {
                cur1.next = head;
                head = head.next;
                cur1 = cur1.next;
                cur1.next = null;
            } else {
                cur2.next = head;
                head = head.next;
                cur2 = cur2.next;
                cur2.next = null;
            }
        }
        cur1.next = right.next;
        return left.next;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);
        S86 s = new S86();
        head = s.partition(head, 3);
        s.print(head);
    }
}
