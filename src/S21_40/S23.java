package S21_40;

import util.ListNode;

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
        return S21.mergeTwoLists(l1, l2);
    }

    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists.length == 0) return null;
        // PriorityQueue是基于堆排序的，这里设定每次出队的都是最大的
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, Comparator.comparingInt(o -> o.val));
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
        ListNode l1 = new ListNode(new int[]{1, 4, 5});
        ListNode l2 = new ListNode(new int[]{1, 3, 4});
        ListNode l3 = new ListNode(new int[]{2, 6});
        ListNode[] lists = {l1, l2, l3};
        ListNode node = new S23().mergeKLists2(lists);
        System.out.println(node);
    }
}
