package S141_160;

import util.ListNode;

/**
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 * 示例 1:
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2:
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 */
public class S148 {

    // 归并排序, 时间复杂度O(nlogn), 空间复杂度O(logn)!, 而题目要求常数级空间复杂度!
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode fast = head;
        ListNode slow = head;
        ListNode pre = slow;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;

        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);

        return merge(l1, l2);
    }

    // 合并两个有序链表, 递归写法
    private ListNode merge(ListNode h1, ListNode h2) {
        if (h1 == null) return h2;
        if (h2 == null) return h1;
        if (h1.val < h2.val) {
            h1.next = merge(h1.next, h2);
            return h1;
        } else {
            h2.next = merge(h1, h2.next);
            return h2;
        }
    }

    // 快排, 比较慢
    public ListNode sortList2(ListNode head) {
        if (head == null || head.next == null) return head;
        quickSort(head, null);
        return head;
    }

    private void quickSort(ListNode head, ListNode end) {
        if (head == end) return;
        ListNode node = partition(head, end);
        quickSort(head, node);
        quickSort(node.next, end);
    }

    /**
     * * key=4
     * * 4, 2, 5, 3, 7, 9, 0, 1   p1++, swap(p1,p2), p2++
     * * p1 p2
     * * 4, 2, 5, 3, 7, 9, 0, 1   p2++
     * *    p1 p2
     * * 4, 2, 5, 3, 7, 9, 0, 1   p1++, swap(p1,p2), p2++
     * *    p1    p2
     * * 4, 2, 3, 5, 7, 9, 0, 1   p2++
     * *       p1    p2
     * * 4, 2, 3, 5, 7, 9, 0, 1   p2++
     * *       p1       p2
     * * 4, 2, 3, 5, 7, 9, 0, 1   p1++, swap(p1,p2), p2++
     * *       p1          p2
     * * 4, 2, 3, 0, 7, 9, 5, 1   p1++, swap(p1,p2), p2++
     * *          p1         p2
     * * 4, 2, 3, 0, 1, 9, 5, 7, null   swap(p1,head)
     * *             p1          p2
     * * 1, 2, 3, 0, 4, 9, 5, 7, null
     */
    private ListNode partition(ListNode head, ListNode end) {
        ListNode p1 = head, p2 = head.next;
        //走到末尾才停
        while (p2 != end) {
            //大于key值时，p1向前走一步，交换p1与p2的值
            if (p2.val < head.val) {
                p1 = p1.next;
                swap(p1, p2);
            }
            p2 = p2.next;
        }
        swap(p1, head);
        return p1;
    }

    private void swap(ListNode node1, ListNode node2) {
        if (node1 == node2) return;
        int tmp = node1.val;
        node1.val = node2.val;
        node2.val = tmp;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(new int[]{-1, 5, 3, 4, 0});
        head = new S148().sortList2(head);
        System.out.println(head);
    }
}
