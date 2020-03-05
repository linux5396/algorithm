package bytedance;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author linxu
 * @date 2020/3/5
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class RedPackAlgorithm {
    /**
     * @param restMoney 钱
     * @param restNum   人数
     * @return 红包序列
     */
    private static List<BigDecimal> compute(BigDecimal restMoney, int restNum) {
        List<BigDecimal> res = new ArrayList<>(restNum);
        Random rander = new Random();
        while (restNum > 1) {
            //最大红包为两倍平均红包
            BigDecimal max = restMoney.divide(new BigDecimal(restNum), MathContext.DECIMAL64).multiply(new BigDecimal(2));
            //产生0到1的随机数
            double r = rander.nextDouble();
            //抢红包的区间[0,moneyRange)
            BigDecimal moneyRange = max.multiply(new BigDecimal(r));
            if (moneyRange.doubleValue() < 0.01) {
                moneyRange = new BigDecimal(0.01);
            } else {
                moneyRange = new BigDecimal(Math.floor(moneyRange.doubleValue() * 100) / 100);
            }
            res.add(moneyRange);
            restNum--;
            restMoney = restMoney.subtract(moneyRange);
        }
        res.add(new BigDecimal(Math.floor(restMoney.doubleValue() * 100) / 100));
        return res;
    }

    public static List<Double> redPack(Double money, int numbers) {
        List<BigDecimal> redpacks = compute(new BigDecimal(money), numbers);
        List<Double> res = new ArrayList<>(redpacks.size());
        for (BigDecimal m : redpacks) {
            res.add(m.doubleValue());
        }
        return res;
    }

    public static List<Double> redPack(double money, int numbers) {
        List<Double> res = new ArrayList<>(numbers);
        Random random = new Random();
        while (numbers > 1) {
            //最高是平均的两倍；当然，可以多倍
            double max = (money / numbers) * 2;
            //根据随机算法，计算一个0到最高之内的区间金额。
            double r = random.nextDouble();
            //算区间
            double top = max * r;
            if (top < 0.01) {
                top = 0.01;
            } else {
                top = Math.floor(top * 100) / 100;
            }
            res.add(top);
            numbers--;
            money -= top;
        }
        res.add(Math.floor(money * 100) / 100);
        return res;
    }

    public static void main(String[] args) {
        Double all = 0D;
        for (Double d : redPack(1.0D, 10)
                ) {
            System.out.print(d + ",");
            all += d * 100;
        }
        System.err.println("all:" + all);
    }
}
