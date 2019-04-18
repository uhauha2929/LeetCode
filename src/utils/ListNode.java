package utils;

// 单链表
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public ListNode(int[] nums) {
        this.val = nums[0];
        ListNode cur = this;
        for (int i = 1; i < nums.length; i++) {
            cur.next = new ListNode(nums[i]);
            cur = cur.next;
        }
    }

    @Override
    public String toString() {
        if (this.next == null) return "" + this.val;
        return this.val + "->" + this.next.toString();
    }
}
