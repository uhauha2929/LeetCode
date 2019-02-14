import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 */
public class S23 {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            if (this.next == null) return "" + this.val;
            return this.val + "->" + this.next.toString();
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode cur1 = l1, cur2 = l2, cur3 = head;
        while (cur1 != null && cur2 != null) {
            if (cur1.val < cur2.val) {
                cur3.next = cur1;
                cur3 = cur1;
                cur1 = cur1.next;
            } else {
                cur3.next = cur2;
                cur3 = cur2;
                cur2 = cur2.next;
            }
        }
        // 如果两个链表其中之一没有遍历完，把剩余的接上
        cur3.next = cur1 == null ? cur2 : cur1;
        return head.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        return merge(lists, 0, lists.length - 1);
    }

    // 采用分治法
    public ListNode merge(ListNode[] lists, int left, int right) {
        if (left >= right)
            return lists[left];
        int mid = (left + right) / 2;
        ListNode l1 = merge(lists, left, mid);
        ListNode l2 = merge(lists, mid + 1, right);
        return mergeTwoLists(l1, l2);
    }

    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists.length == 0) return null;
        // PriorityQueue是基于堆排序的，这里设定每次出队的都是最大的
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        ListNode head = new ListNode(0), tmp = head;
        // 把所有头节点全部入队
        for (ListNode l : lists) {
            if (l != null) queue.add(l);
        }
        while (!queue.isEmpty()) {
            // 当前最大的出队
            tmp.next = queue.poll();
            tmp = tmp.next; // 当前最大的依次尾部相连
            if (tmp.next != null) queue.add(tmp.next);  // 继续按大小入队
        }
        return head.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);

        ListNode[] lists = {l1, l2, l3};
        ListNode node = new S23().mergeKLists(lists);
        System.out.println(node);
    }
}
