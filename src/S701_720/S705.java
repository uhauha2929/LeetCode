package S701_720;

import java.util.ArrayList;

/**
 * 不使用任何内建的哈希表库设计一个哈希集合
 * 具体地说，你的设计应该包含以下的功能
 * add(value)：向哈希集合中插入一个值。
 * contains(value) ：返回哈希集合中是否存在这个值。
 * remove(value)：将给定值从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。
 * 示例:
 * MyHashSet hashSet = new MyHashSet();
 * hashSet.add(1);
 * hashSet.add(2);
 * hashSet.contains(1);    // 返回 true
 * hashSet.contains(3);    // 返回 false (未找到)
 * hashSet.add(2);
 * hashSet.contains(2);    // 返回 true
 * hashSet.remove(2);
 * hashSet.contains(2);    // 返回  false (已经被删除)
 * 注意：
 * 所有的值都在 [1, 1000000]的范围内。
 * 操作的总数目在[1, 10000]范围内。
 * 不要使用内建的哈希集合库。
 */
public class S705 {

    static class MyHashSet {
        boolean[] map = new boolean[1000005];

        public MyHashSet() {
        }

        public void add(int key) {
            map[key] = true;
        }

        public void remove(int key) {
            map[key] = false;
        }

        public boolean contains(int key) {
            return map[key];
        }
    }

    static class MyHashSet2 {

        private ArrayList[] lists = new ArrayList[30007];

        public MyHashSet2() {
        }

        public void add(int key) {
            int index = key % 30007;
            if (lists[index] == null)
                lists[index] = new ArrayList();
            if (!contains(key))
                lists[index].add(key);
        }

        public void remove(int key) {
            int index = key % 30007;
            if (lists[index] != null)
                lists[index].remove((Integer) key);
        }

        public boolean contains(int key) {
            int index = key % 30007;
            return lists[index] != null && lists[index].contains(key);
        }
    }


    public static void main(String[] args) {
        MyHashSet2 hashSet = new MyHashSet2();
        hashSet.add(1);
        hashSet.add(2);
        System.out.println(hashSet.contains(1));    // 返回 true
        System.out.println(hashSet.contains(3));    // 返回 false (未找到)
        hashSet.add(2);
        System.out.println(hashSet.contains(2));    // 返回 true
        hashSet.remove(2);
        System.out.println(hashSet.contains(2));    // 返回  false (已经被删除)
    }
}
