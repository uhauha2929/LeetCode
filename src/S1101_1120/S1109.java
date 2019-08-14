package S1101_1120;

import java.util.Arrays;

public class S1109 {

    // 使用差分的小技巧, 否则会超时
    // https://blog.csdn.net/K_R_forever/article/details/81775899
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] d = new int[n];
        for (int[] booking : bookings) {
            d[booking[0] - 1] += booking[2];
            if (booking[1] < n)
                d[booking[1]] -= booking[2];
        }
        for (int i = 1; i < d.length; i++) {
            d[i] += d[i - 1];
        }
        return d;
    }


    public static void main(String[] args) {
        int[][] bookings = new int[][]{{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
        System.out.println(Arrays.toString(new S1109()
                .corpFlightBookings(bookings, 5)));
    }
}
