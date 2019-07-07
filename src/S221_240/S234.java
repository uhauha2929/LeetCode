package S221_240;

import util.ListNode;

/**
 * 请判断一个链表是否为回文链表。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 * <p>
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */
public class S234 {

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;
        ListNode cur = head, fast = head, next = cur.next, left = null;
        while (fast.next != null && fast.next.next != null) {
            // 快指针先走2步
            fast = fast.next.next;
            // 进行反转
            cur.next = left;
            left = cur;
            cur = next;
            next = cur.next;
        }
        ListNode right = cur.next;
        // 如果为节点个数为偶数, 添加left的最后一个节点
        if (fast.next != null) {
            cur.next = left;
            left = cur;
        }
        // 此时left为反转后的左半部分, right为右半部分
        while (left != null && right != null) {
            if (left.val != right.val)
                return false;
            left = left.next;
            right = right.next;
        }
        return true;
    }

    private ListNode p;

    public boolean isPalindrome2(ListNode head) {
        p = head;
        return helper(p);
    }

    private boolean helper(ListNode cur) {
        if (cur == null)
            return true;
        boolean ans = helper(cur.next) && cur.val == p.val;
        p = p.next;
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new S234().isPalindrome2(new ListNode(new int[]{1, 2, 1})));
    }
}
