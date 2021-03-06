package S121_140;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。
 * 找出那个只出现了一次的元素。
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * 示例 1:
 * 输入: [2,2,3,2]
 * 输出: 3
 * 示例 2:
 * 输入: [0,1,0,1,0,1,99]
 * 输出: 99
 */
public class S137 {

    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int key : map.keySet()) {
            if (map.get(key) != 3)
                return key;
        }
        return 0;
    }


    // 统计所有数字每一位出现的次数，如果不能被3整除，则保留那一位
    public int singleNumber2(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int cnt = 0, bit = 1 << i;
            for (int j = 0; j < nums.length; j++) {
                if ((nums[j] & bit) != 0)
                    cnt++;
            }
            if (cnt % 3 != 0)
                ans |= bit;
        }
        return ans;
    }

    public int singleNumber3(int[] nums) {
        int one = 0, two = 0, three;
        for (int num : nums) {
            // two的相应的位等于1，表示该位出现2次
            two |= (one & num);
            // one的相应的位等于1，表示该位出现1次
            one ^= num;
            // three的相应的位等于1，表示该位出现3次
            three = (one & two);
            // 如果相应的位出现3次，则该位重置为0
            two &= ~three;
            one &= ~three;
        }
        return one;
    }

    /**
     * 对于a,b 相对应的第i位( 0 <= i <= 31)
     *
     * 初始化: a[i]=0, b[i]=0
     * 每次执行一次如下代码:
     * b = (b ^ nums[i]) & ~a;
     * a = (a ^ nums[i]) & ~b;
     * a[i]和b[i]的进行如下更新:
     * 如果该位第一次遇到1: a[i]=0, b[i]=1
     * 如果该位第二次遇到1: a[i]=1, b[i]=0
     * 如果该位第三次遇到1: a[i]=0, b[i]=0
     * 所以b最后保留了多余的那个1的位.
     */
    public int singleNumber4(int[] nums) {
        int a = 0, b = 0;
        for (int num : nums) {
            b = (b ^ num) & ~a;
            a = (a ^ num) & ~b;
        }
        return b;
    }


    public static void main(String[] args) {
        System.out.println(new S137().singleNumber4(new int[]{0, 1, 0, 1, 0, 1, 99}));
    }
}
