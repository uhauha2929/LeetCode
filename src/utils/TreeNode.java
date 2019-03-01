package utils;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    public TreeNode(Integer[] nums) {
        Queue<TreeNode> queue = new LinkedList<>();
        this.val = nums[0];
        int i = 1;
        queue.add(this);
        while (i < nums.length) {
            int count = queue.size();
            while (count > 0 && i < nums.length) {
                TreeNode node = queue.remove();
                if (node != null) {
                    if (nums[i] != null)
                        node.left = new TreeNode(nums[i]);
                    i++;
                    if (i == nums.length)
                        return;
                    if (nums[i] != null)
                        node.right = new TreeNode(nums[i]);
                    i++;
                    queue.add(node.left);
                    queue.add(node.right);
                }
                count--;
            }
        }
    }

    public static void preOrder(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + "\t");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public static void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.val + "\t");
            inOrder(root.right);
        }
    }

    public static void postOrder(TreeNode root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.val + "\t");
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Integer[]{1, null, 2, 3, null, 4});
        inOrder(root);
    }
}