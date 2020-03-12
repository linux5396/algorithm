package meituan;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author linxu
 * @date 2020/3/12
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class BestMain {
    public int most(int[] seq, int X) {
        if (seq.length == 0) {
            return 0;
        }
        int[] dp = seq.clone();
        for (int i = 0; i < dp.length; i++) {
            dp[i] |= X;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < dp.length; i++) {
            map.put(dp[i], map.getOrDefault(dp[i], 0) + 1);
            max = Math.max(max, map.get(dp[i]));
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //int[] seq = {1, 2, 3, 3, 5};
        int length = 0;
        int X = 0;
        String line;
        int[] seq;
        int times = 0;
        while (!(line = scanner.nextLine()).equals("")) {
            if (times == 0) {
                length = Integer.valueOf(line.split(" ")[0]);
                X = Integer.valueOf(line.split(" ")[1]);
                times++;
            } else if (times == 1) {
                seq = new int[length];
                String[] number = line.split(" ");
                for (int i = 0; i < number.length; i++) {
                    seq[i] = Integer.valueOf(number[i]);
                }
                System.out.println(new BestMain().most(seq, X));
                times = 0;
            }
        }
    }
}
