package S1041_1060;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 有一堆石头，每块石头的重量都是正整数。
 * 每一回合，从中选出两块最重的石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
 * 提示：
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 1000
 */
public class S1046 {

    public int lastStoneWeight(int[] stones) {
        Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int stone : stones)
            queue.add(stone);
        Integer s1, s2;
        while (!queue.isEmpty()) {
            s1 = queue.poll();
            s2 = queue.poll();
            if (s1 != null && s2 != null) {
                if (!s1.equals(s2)) {
                    queue.add(s1 - s2);
                }
            } else {
                if (s1 != null) return s1;
                if (s2 != null) return s2;
            }
        }
        return 0;
    }

    public int lastStoneWeight2(int[] stones) {
        Arrays.sort(stones);
        int len = stones.length;
        int x, y, z, i;
        while (len > 1) {
            x = stones[len - 1];
            y = stones[len - 2];
            if (x == y) {
                len -= 2;
            } else {
                len--;
                z = x - y;
                i = len - 2;
                while (i >= 0) {
                    if (stones[i] > z) {
                        stones[i + 1] = stones[i];
                        --i;
                    } else break;
                }
                stones[i + 1] = z;
            }
        }
        return len == 0 ? 0 : stones[0];
    }


    public static void main(String[] args) {
        System.out.println(new S1046().lastStoneWeight2(new int[]{2, 7, 4, 1, 8, 1}));
    }
}
