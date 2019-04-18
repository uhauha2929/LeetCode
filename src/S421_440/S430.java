package S421_440;

import java.util.LinkedList;

/**
 * 您将获得一个双向链表，除了下一个和前一个指针之外，它还有一个子指针，可能指向单独的双向链表。这些子列表可能有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示
 * 扁平化列表，使所有结点出现在单级双链表中。您将获得列表第一级的头部。
 * 示例:
 * <p>
 * 输入:
 * *  1---2---3---4---5---6--NULL
 * *          |
 * *          7---8---9---10--NULL
 * *              |
 * *              11--12--NULL
 * *
 * 输出:
 * 1-2-3-7-8-11-12-9-10-4-5-6-NULL
 */
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _prev, Node _next, Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }
}

public class S430 {

    private Node tail;

    public Node flatten(Node head) {
        if (head == null) return null;
        // 找到第一个有孩子的节点
        Node cursor = head;
        while (cursor.next != null && cursor.child == null) {
            cursor = cursor.next;
        }
        // 如果节点都没有孩子, 则返回第一个节点
        if (cursor.child == null) {
            tail = cursor;
            return head;
        }
        Node next, temp, lastChild;
        // 此时当前节点有孩子, 保留当前节点的下一个节点
        next = cursor.next;
        // 将当前节点的孩子作为下一个节点
        temp = flatten(cursor.child);
        cursor.next = temp;
        temp.prev = cursor;
        cursor.child = null;
        // 此时tail指向孩子链表的最后一个节点
        lastChild = tail;
        // 将当前节点的下一个节点添加到孩子链表的最后一个节点后面
        if (next != null) {
            // 此时tail会改变, 所以之前用lastChild保存
            temp = flatten(next);
            lastChild.next = temp;
            temp.prev = lastChild;
        }
        return head;
    }

    // 类似的递归写法
    public Node flatten2(Node head) {
        if (head == null) return null;
        Node cur = head;
        // 向后遍历
        while (cur != null) {
            // 存在子链表，进行递归
            if (cur.child != null) {
                // 保留 next 节点
                Node next = cur.next;
                // 得到扁平化后的子链表，与之相连
                Node child = flatten(cur.child);
                cur.next = child;
                child.prev = cur;
                cur.child = null;
                // 连接原 next
                if (next != null) {
                    while (cur.next != null) {
                        cur = cur.next;
                    }
                    cur.next = next;
                    next.prev = cur;
                }
            }
            cur = cur.next;
        }
        return head;
    }

    // 类似的递归写法
    public Node flatten3(Node head) {
        dfs(head);
        return head;
    }

    // 这里返回的是尾节点
    private Node dfs(Node node) {
        if (node == null) return null;
        if (node.child == null && node.next == null)
            return node; // 返回尾节点
        Node next = node.next;
        Node child = node.child;
        Node tail = dfs(child);
        if (tail != null) {
            node.next = child;
            child.prev = node;
            node.child = null;
            tail.next = next;
            if (next != null) next.prev = tail;
        }
        return next == null ? tail : dfs(next);
    }

    // 非递归写法, 使用栈
    public Node flatten4(Node head) {
        Node cursor = head;
        LinkedList<Node> stack = new LinkedList<>();
        while (cursor != null) {
            if (cursor.child != null) {
                stack.push(cursor.next); // stack中可以为null
                cursor.next = cursor.child;
                cursor.child.prev = cursor;
                cursor.child = null;
            } else if (cursor.next == null && !stack.isEmpty()) {
                cursor.next = stack.pop();
                if (cursor.next != null)
                    cursor.next.prev = cursor;
            }
            cursor = cursor.next;
        }
        return head;
    }

    /**
     * *  1---2---3---4---5---6--NULL
     * *          |
     * *          7---8---9---10--NULL
     * *              |
     * *              11--12--NULL
     * *
     * *  1---2---3---7---8---10---4---5---6--NULL
     * *                  |
     * *                 11--12--NULL
     * *
     */
    public Node flatten5(Node head) {
        Node cur = head, next, last;
        while (cur != null) {
            if (cur.child != null) {
                next = cur.next;
                last = cur.child;
                while (last.next != null)
                    last = last.next;
                cur.next = cur.child;
                cur.next.prev = cur;
                cur.child = null;
                if (next != null) {
                    last.next = next;
                    next.prev = last;
                }
            }
            cur = cur.next;
        }
        return head;
    }
}
