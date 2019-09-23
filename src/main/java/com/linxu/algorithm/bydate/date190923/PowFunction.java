package com.linxu.algorithm.bydate.date190923;

import com.linxu.algorithm.CostTime;

/**
 * @author linxu
 * @date 2019/9/23
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 在不调用任何库函数下，实现基数的N次方，不考虑大数问题。
 */
public class PowFunction {
    /**
     * 用于判0
     */
    private static final double ZERO_THRESHOLD = 0.00000001;

    /**
     * 高效求幂
     *
     * @param baseNumber 基数
     * @param exponent   指数
     * @return 结果
     */
    //TODO 该方法还可以加多一个参数，threshold，即把判断是否为0的依据作为用户可调控的标准
    @CostTime("logN")
    public static double pow(double baseNumber, int exponent) {
        //判0
        if (Math.abs(baseNumber) < ZERO_THRESHOLD) {
            return 0;
        }
        if (exponent == 0) {
            return 1;
        }
        if (exponent == 1) {
            return baseNumber;
        }
        //高效pow
        //a^n=a^(n/2)*a^(n/2)  //偶数
        //a^(n+1)=a^(n/2)*a^(n/2)*a//n+1=exponent，这个时候N为偶数
        double result = pow(baseNumber, exponent >> 1);
        result *= result;
        //如果是奇数
        if ((exponent & 1) == 1) {
            result *= baseNumber;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(pow(2, 4));
    }
}
