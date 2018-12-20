import java.util.HashSet;

/**
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->1->2
 * 输出: 1->2
 * 示例 2:
 * <p>
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 */
public class S83 {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public void printNode(ListNode node) {
        if (node != null) {
            System.out.print(node.val + "\t");
            printNode(node.next);
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur1 = head;
        HashSet<Integer> set = new HashSet<>();

        ListNode start = new ListNode(0);
        ListNode cur2 = start;
        while (cur1 != null) {
            if (!set.contains(cur1.val)) {
                cur2.next = cur1;
                cur2 = cur2.next;

                set.add(cur1.val);
            }
            cur1 = cur1.next;
        }
        cur2.next = null;
        return start.next;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null) {
            // 因为已经排序，只需要比较相邻是否相同
            if (current.next.val == current.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(3);

        S83 s = new S83();
        s.printNode(head);
        System.out.println();
        head = s.deleteDuplicates2(head);
        s.printNode(head);
    }
}
