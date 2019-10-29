package S161_180;

import java.util.*;

/*
设计并实现一个 TwoSum 的类，使该类需要支持 add 和 find 的操作。
add 操作 -  对内部数据结构增加一个数。
find 操作 - 寻找内部数据结构中是否存在一对整数，使得两数之和与给定的数相等。
示例 1:
add(1); add(3); add(5);
find(4) -> true
find(7) -> false
示例 2:
add(3); add(1); add(2);
find(3) -> true
find(6) -> false
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/two-sum-iii-data-structure-design
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S170 {

    static class TwoSum {

        // 保存每个数字出现的次数
        private Map<Integer, Integer> numsCount = new HashMap<>();

        public TwoSum() {

        }

        public void add(int number) {
            numsCount.put(number, numsCount.getOrDefault(number,
                    0) + 1);
        }

        public boolean find(int value) {
            for (int num : numsCount.keySet()) {
                // 判断差值是否在HashMap里
                int temp = value - num;
                if (numsCount.containsKey(temp)) {
                    if (temp == num) {
                        // 如果差值与当前遍历的数相等
                        // 如果该数出现的次数大于等于2，则存在两数之和
                        if (numsCount.get(temp) >= 2)
                            return true;
                    } else {
                        // 如果差值与当前遍历的数不等，则肯定存在
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        TwoSum ts = new TwoSum();
        ts.add(0);
        ts.add(-1);
        ts.add(1);
        System.out.println(ts.find(0));
    }
}
