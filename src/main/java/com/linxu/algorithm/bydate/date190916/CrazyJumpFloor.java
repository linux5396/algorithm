package com.linxu.algorithm.bydate.date190916;

import com.linxu.algorithm.Recommend;

/**
 * @author linxu
 * @date 2019/9/16
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 同样是青蛙跳台阶，但是这次，青蛙它跳每一次跳的可以是1，2，3...N
 * 求出有多少总跳法。
 */
public class CrazyJumpFloor {
    /**
     * 疯狂跳台阶算法
     * 使用数学归纳法，将f(n)=2*f(n-1);即可
     */
    @Recommend
    public static int crazilyJumpFloor(int target) {
        if (target <= 0) {
            return 0;
        }
        if (target == 1 || target == 2) {
            return 1;
        }
        return 2 * crazilyJumpFloor(target - 1);
    }

    /**
     * 使用n个2*1的矩形去覆盖2*n的大矩形有多少种方法
     * <p>
     * 斐波那契的拓展
     * </p>
     */
    public static int coverRectangle(int n) {
        if (n == 0) {
            return 0;
        }
        if (n <= 2) {
            return n;
        }
        //如果横着放，则与斐波那契相同，剩下N-1
        //如果横着放，会影响到下面一行的也必须横着放，所以为N-2
        //所以N=N-1+N-2
        return coverRectangle(n - 1) + coverRectangle(n - 2);
    }
}
