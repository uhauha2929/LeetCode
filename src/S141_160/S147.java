package S141_160;

import utils.ListNode;

/**
 * 对链表进行插入排序。
 * 插入排序算法：
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 * 示例 1：
 * <p>
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2：
 * <p>
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 */
public class S147 {

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        ListNode p1 = head, p2, next;
        while (p1 != null) {
            // 先保存遍历的下一个节点
            next = p1.next;
            // 从前往后找到要插入位置的前一个节点
            p2 = dummy;
            while (p2.next != null && p2.next.val < p1.val)
                p2 = p2.next;
            // 进行插入
            p1.next = p2.next;
            p2.next = p1;
            // 从下一个节点继续循环
            p1 = next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(new int[]{4, 2, 1, 3});
        head = new S147().insertionSortList(head);
        System.out.println(head);
    }
}
