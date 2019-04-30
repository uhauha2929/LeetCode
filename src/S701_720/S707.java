package S701_720;

/**
 * 设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next 是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。
 * <p>
 * 在链表类中实现这些功能：
 * <p>
 * get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
 * addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
 * addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
 * addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。
 * deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
 * <p>
 * <p>
 * 示例：
 * <p>
 * MyLinkedList linkedList = new MyLinkedList();
 * linkedList.addAtHead(1);
 * linkedList.addAtTail(3);
 * linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
 * linkedList.get(1);            //返回2
 * linkedList.deleteAtIndex(1);  //现在链表是1-> 3
 * linkedList.get(1);            //返回3
 * <p>
 * <p>
 * 提示：
 * <p>
 * 所有值都在 [1, 1000] 之内。
 * 操作次数将在  [1, 1000] 之内。
 * 请不要使用内置的 LinkedList 库。
 */
public class S707 {

    static class MyLinkedList {

        private Node first;
        private Node last;
        private int size;

        private class Node {

            private int val;
            private Node prev;
            private Node next;

            private Node(int val, Node prev, Node next) {
                this.val = val;
                this.prev = prev;
                this.next = next;
            }
        }

        public int get(int index) {
            if (index < 0 || index > size - 1) {
                return -1;
            }
            return nodeAt(index).val;
        }

        public void addAtHead(int val) {
            linkFirst(val);
        }

        public void addAtTail(int val) {
            linkLast(val);
        }

        public void addAtIndex(int index, int val) {
            if (index < 0 || index > size) {
                return;
            }
            if (index == size) {
                linkLast(val);
            } else {
                linkBefore(index, val);
            }
        }

        public void deleteAtIndex(int index) {
            if (index < 0 || index > size - 1) {
                return;
            }
            Node node = nodeAt(index);
            if (node.prev != null) {
                node.prev.next = node.next;
            } else {
                first = node.next;
            }
            if (node.next != null) {
                node.next.prev = node.prev;
            } else {
                last = node.prev;
            }
            size--;
        }

        private void linkBefore(int index, int val) {
            Node node = nodeAt(index);
            Node newNode = new Node(val, node.prev, node);
            if (node.prev != null) {
                node.prev.next = newNode;
            } else {
                first = newNode;
            }
            node.prev = newNode;
            size++;
        }

        private void linkFirst(int val) {
            Node newNode = new Node(val, null, first);
            if (first != null) {
                first.prev = newNode;
            } else {
                last = newNode;
            }
            first = newNode;
            size++;
        }

        private void linkLast(int val) {
            Node newNode = new Node(val, last, null);
            if (last != null) {
                last.next = newNode;
            } else {
                first = newNode;
            }
            last = newNode;
            size++;
        }

        private Node nodeAt(int index) {
            if (index < (size >> 1)) {
                Node node = first;
                for (int i = 0; i < index; i++) {
                    node = node.next;
                }
                return node;
            } else {
                Node node = last;
                for (int i = size - 1; i > index; i--) {
                    node = node.prev;
                }
                return node;
            }
        }
    }

    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtHead(1);
        linkedList.addAtIndex(1, 2);
        System.out.println(linkedList.get(1));
        System.out.println(linkedList.get(0));
        System.out.println(linkedList.get(2));
    }
}
