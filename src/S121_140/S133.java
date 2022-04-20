package S121_140;

import java.util.*;

/**
 * 给你无向连通图中一个节点的引用，请你返回该图的深拷贝（克隆）。
 *
 * 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
 *
 * class Node {
 *     public int val;
 *     public List<Node> neighbors;
 * }
 *
 * 测试用例格式：
 *
 * 简单起见，每个节点的值都和它的索引相同。例如，第一个节点值为 1（val = 1），第二个节点值为 2（val = 2），以此类推。该图在测试用例中使用邻接列表表示。
 *
 * 邻接列表 是用于表示有限图的无序列表的集合。每个列表都描述了图中节点的邻居集。
 *
 * 给定节点将始终是图中的第一个节点（值为 1）。你必须将给定节点的拷贝作为对克隆图的引用返回。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/clone-graph
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class S133 {

    private static class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    // 深度优先
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        // 创建过的新节点直接取
        Map<Integer, Node> nodeMap = new HashMap<>();
        return clone(node, nodeMap);
    }

    private Node clone(Node node, Map<Integer, Node> nodeMap) {
        Node newNode = new Node(node.val);
        nodeMap.put(node.val, newNode);
        if (node.neighbors == null) {
            return newNode;
        }
        for (Node n : node.neighbors) {
            if (nodeMap.containsKey(n.val)) {
                newNode.neighbors.add(nodeMap.get(n.val));
            } else {
                newNode.neighbors.add(clone(n, nodeMap));
            }
        }
        return newNode;
    }

    // 广度优先
    public Node cloneGraph2(Node node) {
        if (node == null) {
            return null;
        }
        // 创建过的新节点直接取
        Map<Integer, Node> lookup = new HashMap<>();
        Node clone = new Node(node.val);
        lookup.put(node.val, clone);
        Deque<Node> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            Node tmp = queue.poll();
            for (Node n : tmp.neighbors) {
                if (!lookup.containsKey(n.val)) {
                    // 存放新创建的节点
                    lookup.put(n.val, new Node(n.val));
                    // 将邻居加入队列
                    queue.offer(n);
                }
                // 在所有新创建的节点中设置关系
                lookup.get(tmp.val).neighbors.add(lookup.get(n.val));
            }
        }
        return clone;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        node1.neighbors.add(node2);
        node2.neighbors.add(node1);
        Node newNode = new S133().cloneGraph2(node1);
        System.out.println(newNode);
    }
}
