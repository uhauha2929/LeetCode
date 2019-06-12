package S381_400;

import utils.ListNode;

/**
 * 给定一个单链表，随机选择链表的一个节点，并返回相应的节点值。保证每个节点被选的概率一样。
 * 进阶:
 * 如果链表十分大且长度未知，如何解决这个问题？你能否使用常数级空间复杂度实现？
 * 示例:
 * // 初始化一个单链表 [1,2,3].
 * ListNode head = new ListNode(1);
 * head.next = new ListNode(2);
 * head.next.next = new ListNode(3);
 * Solution solution = new Solution(head);
 * // getRandom()方法应随机返回1,2,3中的一个，保证每个元素被返回的概率相等。
 * solution.getRandom();
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/linked-list-random-node
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S382 {

    static class Solution {

        private ListNode head;

        /**
         * The linked list's head.
         * Note that the head is guaranteed to be not null,
         * so it contains at least one node.
         */
        public Solution(ListNode head) {
            this.head = head;
        }

        /**
         * Returns a random node's value.
         * 蓄水池抽样
         * 对于n(n>=1), 每次以1/i的概率决定是否替换选中的元素直到n,
         * 那么每个元素被选中的概率相等即1/n.
         */
        public int getRandom() {
            int randVal = head.val;
            ListNode cur = head.next;
            int index = 2;
            while (cur != null) {
                if (Math.random() < 1.0 / index++)
                    randVal = cur.val;
                cur = cur.next;
            }
            return randVal;
        }
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(new int[]{1, 2, 3, 4, 5});
        System.out.println(new Solution(head).getRandom());
    }
}
