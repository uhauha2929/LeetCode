package S101_120;

import utils.ListNode;
import utils.TreeNode;

/**
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * 示例:
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 * *       0
 * *      / \
 * *    -3   9
 * *    /   /
 * *  -10  5
 */
public class S109 {

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        if (head.next == null)
            return new TreeNode(head.val);
        ListNode preNode = preMiddleNode(head);
        ListNode midNode = preNode.next;
        preNode.next = null;
        TreeNode root = new TreeNode(midNode.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(midNode.next);
        return root;
    }

    public ListNode preMiddleNode(ListNode head) {
        // slow表示中间节点(偶数取后一个), pre表示中间节点的前一个节点
        ListNode slow = head, fast = head, pre = null;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        return pre;
    }

    // 类似写法
    public TreeNode sortedListToBST2(ListNode head) {
        return buildBST(head, null);
    }

    private TreeNode buildBST(ListNode start, ListNode end) {
        if (start == end) return null;
        ListNode midNode = start, fast = start;
        while (fast != end && fast.next != end) {
            midNode = midNode.next;
            fast = fast.next.next;
        }
        TreeNode root = new TreeNode(midNode.val);
        root.left = buildBST(start, midNode);
        root.right = buildBST(midNode.next, end);
        return root;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(new int[]{-10, -3, 0, 5, 9});
        TreeNode root = new S109().sortedListToBST2(head);
        TreeNode.inOrder(root);
    }
}
