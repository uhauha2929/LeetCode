package S5281_5300;

import util.ListNode;

/**
 * 给你一个单链表的引用结点 head。链表中每个结点的值不是 0 就是 1。已知此链表是一个整数数字的二进制表示形式。
 * 请你返回该链表所表示数字的 十进制值 。
 * 示例 1：
 * 输入：head = [1,0,1]
 * 输出：5
 * 解释：二进制数 (101) 转化为十进制数 (5)
 * 示例 2：
 * 输入：head = [0]
 * 输出：0
 * 示例 3：
 * 输入：head = [1]
 * 输出：1
 * 示例 4：
 * 输入：head = [1,0,0,1,0,0,1,1,1,0,0,0,0,0,0]
 * 输出：18880
 * 示例 5：
 * 输入：head = [0,0]
 * 输出：0
 * 提示：
 * 链表不为空。
 * 链表的结点总数不超过 30。
 * 每个结点的值不是 0 就是 1。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-binary-number-in-a-linked-list-to-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S5283 {

    public int getDecimalValue(ListNode head) {
        int ans = 0;
        int e = 0;
        ListNode cur = reverse(head);
        while (cur != null) {
            ans += cur.val * Math.pow(2, e++);
            cur = cur.next;
        }
        return ans;
    }

    // See S206
    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    private int ans = 0;
    private int e = 0;

    public int getDecimalValue2(ListNode head) {
        recursion(head);
        return ans;
    }

    private void recursion(ListNode head) {
        if (head == null) return;
        recursion(head.next);
        ans += Math.pow(2, e++) * head.val;
    }

    // 十进制（101） = 十进制（10）* 2 + 1
    public int getDecimalValue3(ListNode head) {
        int ans = 0;
        ListNode cur = head;
        while (cur != null) {
            ans = ans * 2 + cur.val;
            cur = cur.next;
        }
        return ans;
    }


    public static void main(String[] args) {
        ListNode nodes = new ListNode(new int[]{1, 0, 1});
        System.out.println(new S5283().getDecimalValue3(nodes));
    }
}
