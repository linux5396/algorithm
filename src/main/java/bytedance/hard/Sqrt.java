package bytedance.hard;

/**
 * @author linxu
 * @date 2020/3/19
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 开平方
 */
public class Sqrt {
    public static int sqrt(int x) {
        if (x < 1) {
            throw new IllegalArgumentException("c less than 1");
        }
        int low = 0;
        //平方根最大不超过1/2+1
        int high = (x >>> 1) + 1;
        while (low <= high) {
            long mid = (low + high) >>> 1;
            long square = mid * mid;
            if (square == x)
                return (int) mid;
            else if (square < x)
                low = (int) (mid + 1);
            else
                high = (int) (mid - 1);
        }
        return high;
    }

    public static double newtonSqrt(double x) {
        if (x < 0) return Double.NaN;
        double t = x;
        double err = 1e-15;
        double pre;
        do {
            pre = t;
            t = (t + x / t) / 2;
        } while ((pre - t) > err);
        return t;
    }
}
